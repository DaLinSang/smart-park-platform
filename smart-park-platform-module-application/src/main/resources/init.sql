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
    park_id      VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '园区ID',
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
VALUES ('P001', 'wx_openid_001', '员工', '张三', '13800138001', '110101199001011234',
        '2026-06-09 09:00:00', '2026-06-09 18:00:00', '办公', 1, 'system'),
       ('P001', 'wx_openid_002', '访客', '李四', '13800138002', '110101199002021235',
        '2026-06-09 10:00:00', '2026-06-09 16:00:00', '商务会谈', 1, 'system'),
       ('P002', 'wx_openid_003', '访客', '王五', '13800138003', '110101199003031236',
        '2026-06-09 14:00:00', NULL, '参观', 0, 'system');