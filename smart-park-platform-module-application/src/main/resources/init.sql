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

