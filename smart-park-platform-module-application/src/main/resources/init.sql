-- ============================================
-- smart_park 数据库创建
-- ============================================
CREATE DATABASE IF NOT EXISTS smart_park
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE smart_park;

-- ============================================
-- applet_tourist 游客表
-- ============================================
DROP TABLE IF EXISTS applet_tourist;
CREATE TABLE applet_tourist
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    park_id      BIGINT       NOT NULL DEFAULT 0  COMMENT '园区ID',
    tourist_id   VARCHAR(128) NOT NULL DEFAULT '' COMMENT '访客ID(微信openid等)',
    tourist_type VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '访客类型',
    tourist_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '访客姓名',
    phone        VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '手机号',
    id_card      VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '身份证号',
    visit_time   DATETIME     NULL COMMENT '访问时间',
    leave_time   DATETIME     NULL COMMENT '离开时间',
    reason       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '访问原因',
    status       TINYINT      NOT NULL DEFAULT 0 COMMENT '状态（0=待审核，1=已通过，2=已拒绝）',

    -- 公共审计字段（继承 BaseDeletableEntity）
    create_by   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建人',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新人',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted     TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除（0=正常，1=删除）',

    INDEX idx_tourist_id (tourist_id),
    INDEX idx_park_id (park_id),
    INDEX idx_status (status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='小程序首页游客访问数据';

-- ============================================
-- 测试数据
-- ============================================
INSERT INTO applet_tourist (park_id, tourist_id, tourist_type, tourist_name, phone, id_card,
                            visit_time, leave_time, reason, status, create_by)
VALUES ('1', 'wx_openid_001', '员工', '张三', '13800138001', '110101199001011234',
        '2026-06-09 09:00:00', '2026-06-09 18:00:00', '办公', 1, 'system'),
       ('1', 'wx_openid_002', '访客', '李四', '13800138002', '110101199002021235',
        '2026-06-09 10:00:00', '2026-06-09 16:00:00', '商务会谈', 1, 'system'),
       ('2', 'wx_openid_003', '访客', '王五', '13800138003', '110101199003031236',
        '2026-06-09 14:00:00', NULL, '参观', 0, 'system');



-- ============================================
-- base_park 园区表
-- ============================================
DROP TABLE IF EXISTS base_park;
CREATE TABLE base_park
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID（雪花算法）',
    park_name  VARCHAR(100)   NOT NULL DEFAULT '' COMMENT '园区名称',
    address    VARCHAR(255)   NOT NULL DEFAULT '' COMMENT '园区详细地址',
    longitude  DECIMAL(10, 7) NOT NULL DEFAULT 0 COMMENT '经度',
    latitude   DECIMAL(10, 7) NOT NULL DEFAULT 0 COMMENT '纬度',
    city       VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '城市',

    -- 公共审计字段
    create_by   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建人',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新人',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted     TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除（0=正常，1=删除）',

    INDEX idx_city (city),
    INDEX idx_park_name (park_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='园区管理';

-- ============================================
-- base_building 楼栋表
-- ============================================
DROP TABLE IF EXISTS base_building;
CREATE TABLE base_building
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID（雪花算法）',
    park_id       BIGINT       NOT NULL DEFAULT 0 COMMENT '所属园区ID，关联 base_park.id',
    building_name VARCHAR(100) NOT NULL DEFAULT '' COMMENT '楼栋名称',
    floor_number  INT          NOT NULL DEFAULT 0 COMMENT '楼层层数',

    -- 公共审计字段
    create_by   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建人',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新人',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted     TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除（0=正常，1=删除）',

    INDEX idx_park_id (park_id),
    INDEX idx_building_name (building_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='楼栋管理';

-- ============================================
-- base 模块测试数据
-- ============================================
INSERT INTO base_park (park_name, address, longitude, latitude, city, create_by)
VALUES ('未来科技园', '北京市海淀区中关村大街1号', 116.3267489, 39.9798765, '北京', 'system'),
       ('张江高科技园', '上海市浦东新区张江路100号', 121.6146821, 31.2115768, '上海', 'system'),
       ('深圳湾科技园', '深圳市南山区科技南路18号', 113.9512345, 22.5367890, '深圳', 'system');

INSERT INTO base_building (park_id, building_name, floor_number, create_by)
VALUES (1, 'A栋', 12, 'system'),
       (1, 'B栋', 8, 'system'),
       (1, 'C栋', 6, 'system'),
       (2, '研发楼', 15, 'system'),
       (2, '创新楼', 10, 'system'),
       (3, '总部大楼', 20, 'system');


-- ============================================
-- -- t_user_openid 用户微信OpenID绑定表
-- ============================================
CREATE TABLE `t_user_openid` (
                                 `id`          BIGINT       NOT NULL COMMENT '主键ID（雪花算法）',
                                 `account_id`  VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户账号ID',
                                 `openid`      VARCHAR(128) NOT NULL DEFAULT '' COMMENT '微信小程序openid',
                                 `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建人',
                                 `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新人',
                                 PRIMARY KEY (`id`),
                                 INDEX `idx_openid` (`openid`),
                                 INDEX `idx_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户微信OpenID绑定表';


-- ============================================
-- t_user_park 用户园区关联表
-- ============================================
CREATE TABLE `t_user_park` (
                               `id`          BIGINT       NOT NULL COMMENT '主键ID（雪花算法）',
                               `user_id`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户主键ID',
                               `park_id`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '园区ID',
                               `tenant_id`   VARCHAR(64)  DEFAULT '' COMMENT '租户ID',
                               `deleted`     TINYINT(1)   DEFAULT 0  COMMENT '逻辑删除标记（0-未删，1-已删）',
                               `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `create_by`   VARCHAR(64)  DEFAULT '' COMMENT '创建人',
                               `update_by`   VARCHAR(64)  DEFAULT '' COMMENT '更新人',
                               PRIMARY KEY (`id`),
                               INDEX `idx_user_id` (`user_id`),
                               INDEX `idx_park_id` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户园区关联表';



-- ============================================
-- t_config 各模块配置表
-- ============================================
CREATE TABLE t_config (
                          id              BIGINT AUTO_INCREMENT NOT NULL   COMMENT '主键ID',
                          module_name     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '配置所属模块',
                          config_type     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '配置类型',
                          config_key      VARCHAR(128) NOT NULL DEFAULT '' COMMENT '配置key',
                          config_value    VARCHAR(500) NOT NULL DEFAULT '' COMMENT '配置value',
                          config_extend   TEXT         COMMENT '扩展字段(JSON格式)',
                          tenant_id       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '租户ID',
                          create_by       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建者',
                          create_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          update_by       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新者',
                          update_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          deleted         TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标识：0未删除 1已删除',
                          PRIMARY KEY (id),
                          UNIQUE INDEX uk_config (module_name, config_type, config_key),
                          INDEX idx_type (config_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配置表';



INSERT INTO t_config (module_name, config_type, config_key, config_value, tenant_id, create_by, update_by)
VALUES
    ('applet', 'tourist', 'max_days', '7', '', 'admin', 'admin'),
    ('applet', 'tourist', 'need_audit', 'true', '', 'admin', 'admin'),
    ('base', 'system', 'site_name', '智慧园区平台', '', 'admin', 'admin');



-- ============================================
-- t_base_file 附件表
-- ============================================
CREATE TABLE t_base_file (
                             id              BIGINT AUTO_INCREMENT NOT NULL   COMMENT '主键ID',
                             module_id       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '模块类型',
                             module_data_id  VARCHAR(128) NOT NULL DEFAULT '' COMMENT '模块数据ID',
                             file_id         VARCHAR(128) NOT NULL DEFAULT '' COMMENT '文件系统主键ID',
                             name            VARCHAR(255) NOT NULL DEFAULT '' COMMENT '附件名称',
                             path            VARCHAR(500) NOT NULL DEFAULT '' COMMENT '附件地址',
                             extension_name  VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '文件扩展名',
                             tenant_id       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '租户ID',
                             create_by       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建者',
                             create_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             update_by       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新者',
                             update_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             deleted         TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标识',
                             PRIMARY KEY (id),
                             INDEX idx_module (module_id, module_data_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表';



-- ============================================
-- t_base_process 主表：处理进度表
-- ============================================
CREATE TABLE t_base_process (
                                id              BIGINT AUTO_INCREMENT NOT NULL COMMENT '主键ID',
                                module_data_id  VARCHAR(128) NOT NULL DEFAULT '' COMMENT '模块数据ID（关联的业务数据）',
                                action          INT          NOT NULL DEFAULT 0 COMMENT '操作：0提交 1通过 2驳回 3流转',
                                action_name     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '操作名称（如"部门经理审批"）',
                                tenant_id       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '租户ID',
                                deleted         TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标识',
                                create_by       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建者',
                                create_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                update_by       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新者',
                                update_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (id),
                                INDEX idx_module_data (module_data_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='处理进度表';

-- ============================================
-- t_base_process_user 子表：处理人员表
-- ============================================
CREATE TABLE t_base_process_user (
                                     id                BIGINT AUTO_INCREMENT NOT NULL COMMENT '主键ID',
                                     process_id        BIGINT       NOT NULL COMMENT '处理进度ID（关联 t_base_process.id）',
                                     process_user_id   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '处理人ID',
                                     process_user_name VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '处理人姓名',
                                     comment           VARCHAR(500) NOT NULL DEFAULT '' COMMENT '处理意见',
                                     created_time      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '处理时间',
                                     extend            TEXT         COMMENT '扩展字段(JSON)',
                                     create_by         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建者',
                                     create_time       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     update_by         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新者',
                                     update_time       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     deleted           TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标识',
                                     PRIMARY KEY (id),
                                     INDEX idx_process (process_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='处理人员表';

-- ============================================
-- t_park_change 园区变更申请表
-- ============================================
CREATE TABLE t_park_change (
                               id                      BIGINT AUTO_INCREMENT NOT NULL   COMMENT '主键ID',
                               applicant_user_id       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '申请人ID',
                               origin_park_id          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '原所属园区ID',
                               applying_park_id        VARCHAR(500) NOT NULL DEFAULT '' COMMENT '申请园区ID(逗号分隔，可申请多个)',
                               role_id                 VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '角色ID',
                               origin_department_id    VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '原部门ID',
                               applying_department_id  VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '申请部门ID',
                               reason                  VARCHAR(500) NOT NULL DEFAULT '' COMMENT '申请原因',
                               approval_status         INT          NOT NULL DEFAULT 0 COMMENT '审批状态：0待审批 1流转中 2已审批 3已驳回',
                               approved_park_id        VARCHAR(500) DEFAULT NULL COMMENT '已审批通过的园区ID',
                               tenant_id               VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '租户ID',
                               create_by               VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '创建者',
                               create_time             DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               update_by               VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '更新者',
                               update_time             DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               deleted                 TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标识',
                               PRIMARY KEY (id),
                               INDEX idx_applicant (applicant_user_id),
                               INDEX idx_status (approval_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='园区变更申请表';


-- ============================================
-- 测试数据
-- ============================================

-- --------------------------------------------
-- t_user_park 测试数据：用户园区关联
-- --------------------------------------------
INSERT INTO t_user_park (user_id, park_id, tenant_id, create_by, update_by)
VALUES
    ('user_001', '1', 'tenant_default', 'admin', 'admin'),
    ('user_001', '2', 'tenant_default', 'admin', 'admin'),
    ('user_002', '1', 'tenant_default', 'admin', 'admin'),
    ('user_003', '3', 'tenant_default', 'admin', 'admin'),
    ('user_004', '1', 'tenant_default', 'admin', 'admin'),
    ('user_004', '2', 'tenant_default', 'admin', 'admin'),
    ('user_004', '3', 'tenant_default', 'admin', 'admin');


-- --------------------------------------------
-- t_config 测试数据：各模块配置
-- --------------------------------------------
INSERT INTO t_config (module_name, config_type, config_key, config_value, config_extend, tenant_id, create_by, update_by)
VALUES
    -- base 模块配置（site_name 在前面已有，跳过避免重复）
    ('base', 'system', 'site_logo', '/static/logo.png', NULL, 'tenant_default', 'admin', 'admin'),
    ('base', 'system', 'copyright', '© 2026 Mumu Tech', NULL, 'tenant_default', 'admin', 'admin'),
    -- park 模块配置
    ('park', 'rule', 'max_visitor_days', '7', '{"unit":"day","desc":"访客最长访问天数"}', 'tenant_default', 'admin', 'admin'),
    ('park', 'rule', 'need_audit', 'true', '{"desc":"是否需要审批"}', 'tenant_default', 'admin', 'admin'),
    -- file 模块配置
    ('file', 'upload', 'max_size_mb', '50', '{"unit":"MB","desc":"单文件最大大小"}', 'tenant_default', 'admin', 'admin'),
    ('file', 'upload', 'allow_types', 'jpg,png,pdf,doc,xlsx', '{"desc":"允许的文件类型"}', 'tenant_default', 'admin', 'admin'),
    -- process 模块配置
    ('process', 'rule', 'timeout_hours', '24', '{"unit":"hour","desc":"审批超时时间"}', 'tenant_default', 'admin', 'admin');


-- --------------------------------------------
-- t_base_file 测试数据：附件信息
-- --------------------------------------------
INSERT INTO t_base_file (module_id, module_data_id, file_id, name, path, extension_name, tenant_id, create_by, update_by)
VALUES
    -- 园区变更申请附件（关联ParkChange的id=1,2）
    ('parkChange', '1', 'file_20260623_001', '在职证明.pdf', '/upload/2026/06/在职证明_001.pdf', 'pdf', 'tenant_default', 'user_001', 'user_001'),
    ('parkChange', '1', 'file_20260623_002', '身份证正面.jpg', '/upload/2026/06/身份证_001.jpg', 'jpg', 'tenant_default', 'user_001', 'user_001'),
    ('parkChange', '1', 'file_20260623_003', '身份证反面.jpg', '/upload/2026/06/身份证_002.jpg', 'jpg', 'tenant_default', 'user_001', 'user_001'),
    ('parkChange', '2', 'file_20260623_004', '调动申请表.docx', '/upload/2026/06/调动表_002.docx', 'docx', 'tenant_default', 'user_002', 'user_002'),
    -- 游客访问附件（关联applet_tourist的id=1,2）
    ('appletTourist', '1', 'file_20260623_005', '访客照片.png', '/upload/2026/06/访客_001.png', 'png', 'tenant_default', 'user_001', 'user_001'),
    ('appletTourist', '2', 'file_20260623_006', '健康码截图.jpg', '/upload/2026/06/健康码_002.jpg', 'jpg', 'tenant_default', 'user_002', 'user_002');


-- --------------------------------------------
-- t_park_change 测试数据：园区变更申请
-- --------------------------------------------
INSERT INTO t_park_change (applicant_user_id, origin_park_id, applying_park_id, role_id,
                            origin_department_id, applying_department_id, reason, approval_status,
                            approved_park_id, tenant_id, create_by, update_by)
VALUES
    -- 待审批申请
    ('user_001', '1', '2', 'role_employee', 'dept_tech', 'dept_product', '项目组调整，需要常驻张江园区', 0, NULL, 'tenant_default', 'user_001', 'user_001'),
    -- 已审批通过
    ('user_002', '1', '3', 'role_manager', 'dept_tech', 'dept_tech', '部门搬迁至深圳湾科技园', 2, '3', 'tenant_default', 'user_002', 'admin'),
    -- 已驳回
    ('user_003', '3', '1', 'role_employee', 'dept_operations', 'dept_tech', '希望转岗到技术部', 3, NULL, 'tenant_default', 'user_003', 'admin'),
    -- 流转中（多园区申请）
    ('user_004', '2', '1,3', 'role_employee', 'dept_product', 'dept_design', '设计部门多园区协同', 1, NULL, 'tenant_default', 'user_004', 'admin'),
    -- 待审批
    ('user_005', '1', '2', 'role_employee', 'dept_tech', 'dept_tech', '内部调岗到张江研发部', 0, NULL, 'tenant_default', 'user_005', 'user_005');


-- --------------------------------------------
-- t_base_process 测试数据：处理进度
-- --------------------------------------------
INSERT INTO t_base_process (module_data_id, action, action_name, tenant_id, create_by, update_by)
VALUES
    -- ParkChange id=1 的处理流程（3步审批）
    ('1', 0, '提交申请', 'tenant_default', 'user_001', 'user_001'),
    ('1', 3, '部门经理审批', 'tenant_default', 'mgr_001', 'mgr_001'),
    ('1', 1, '园区管理员审批通过', 'tenant_default', 'admin', 'admin'),
    -- ParkChange id=2 的处理流程
    ('2', 0, '提交申请', 'tenant_default', 'user_002', 'user_002'),
    ('2', 1, '已审批通过', 'tenant_default', 'admin', 'admin'),
    -- ParkChange id=3 的处理流程
    ('3', 0, '提交申请', 'tenant_default', 'user_003', 'user_003'),
    ('3', 2, '已驳回', 'tenant_default', 'admin', 'admin');


-- --------------------------------------------
-- t_base_process_user 测试数据：处理人员
-- --------------------------------------------
INSERT INTO t_base_process_user (process_id, process_user_id, process_user_name, comment, created_time, create_by, update_by)
VALUES
    -- 申请1的处理人
    (1, 'user_001', '张三', '申请从北京未来科技园调到张江高科技园', '2026-06-20 09:00:00', 'user_001', 'user_001'),
    (2, 'mgr_001', '李经理', '同意部门内部调动', '2026-06-21 10:30:00', 'mgr_001', 'mgr_001'),
    (2, 'user_001', '张三', '已收到部门经理审批意见', '2026-06-21 11:00:00', 'user_001', 'user_001'),
    (3, 'admin', '系统管理员', '园区分配通过', '2026-06-22 14:00:00', 'admin', 'admin'),
    -- 申请2的处理人
    (4, 'user_002', '李四', '申请调到深圳湾科技园', '2026-06-18 09:00:00', 'user_002', 'user_002'),
    (5, 'admin', '系统管理员', '审批通过，深圳湾园区已开通', '2026-06-19 16:00:00', 'admin', 'admin'),
    -- 申请3的处理人
    (6, 'user_003', '王五', '申请技术部转岗', '2026-06-15 09:00:00', 'user_003', 'user_003'),
    (7, 'admin', '系统管理员', '技术部当前无空缺岗位，驳回', '2026-06-16 17:00:00', 'admin', 'admin');