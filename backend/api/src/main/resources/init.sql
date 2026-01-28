CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户资料';

CREATE TABLE IF NOT EXISTS `attraction` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128) NOT NULL COMMENT '景点名称',
  `level` varchar(8) DEFAULT NULL COMMENT '等级',
  `category` varchar(32) NOT NULL COMMENT '分类',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `open_time` varchar(128) DEFAULT NULL COMMENT '开放时间',
  `ticket_price` decimal(10,2) DEFAULT NULL COMMENT '门票价格',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `intro` text COMMENT '简介',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图',
  `audio_url` varchar(255) DEFAULT NULL COMMENT '语音导览',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='景点';

CREATE TABLE IF NOT EXISTS `food_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128) NOT NULL COMMENT '美食名称',
  `category` varchar(32) NOT NULL COMMENT '分类',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `intro` text COMMENT '简介',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图',
  `video_url` varchar(255) DEFAULT NULL COMMENT '视频',
  `rating` decimal(3,2) DEFAULT 4.5 COMMENT '评分',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='美食';

CREATE TABLE IF NOT EXISTS `topic` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '话题名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_topic_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话题';

CREATE TABLE IF NOT EXISTS `travel_note` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `topic_id` bigint DEFAULT NULL COMMENT '话题ID',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图',
  `like_count` int DEFAULT 0 COMMENT '点赞数',
  `favorite_count` int DEFAULT 0 COMMENT '收藏数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游记';

CREATE TABLE IF NOT EXISTS `itinerary` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `title` varchar(128) NOT NULL COMMENT '行程标题',
  `start_date` date DEFAULT NULL COMMENT '开始日期',
  `days` int DEFAULT 1 COMMENT '天数',
  `status` varchar(32) DEFAULT 'DRAFT' COMMENT '状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='行程';

CREATE TABLE IF NOT EXISTS `itinerary_day` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `itinerary_id` bigint NOT NULL COMMENT '行程ID',
  `day_index` int NOT NULL COMMENT '第几天',
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_itinerary_id` (`itinerary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='行程日';

CREATE TABLE IF NOT EXISTS `itinerary_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `itinerary_day_id` bigint NOT NULL COMMENT '行程日ID',
  `attraction_id` bigint NOT NULL COMMENT '景点ID',
  `start_time` time DEFAULT NULL COMMENT '开始时间',
  `duration_minutes` int DEFAULT NULL COMMENT '停留时长',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort_order` int DEFAULT 0 COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_day_id` (`itinerary_day_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='行程项';

CREATE TABLE IF NOT EXISTS `user_favorite` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `target_type` varchar(32) NOT NULL COMMENT '收藏类型',
  `target_id` bigint NOT NULL COMMENT '目标ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_fav` (`user_id`, `target_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏';

CREATE TABLE IF NOT EXISTS `user_like` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `target_type` varchar(32) NOT NULL COMMENT '点赞类型',
  `target_id` bigint NOT NULL COMMENT '目标ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_like` (`user_id`, `target_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点赞';

INSERT INTO `user_profile` (`id`, `nickname`, `avatar`, `city`, `phone`) VALUES
  (1, '小晋', '', '太原', '13800000000'),
  (2, '悟空打卡', '', '大同', '13900000000');

INSERT INTO `attraction` (`id`, `name`, `level`, `category`, `city`, `address`, `open_time`, `ticket_price`, `latitude`, `longitude`, `intro`, `cover_image`, `audio_url`) VALUES
  (1, '云冈石窟', '5A', 'GROTTO', '大同', '大同市云冈区', '08:30-17:30', 120.00, 40.109600, 113.122000, '世界文化遗产，北魏时期石窟艺术。', '', ''),
  (2, '平遥古城', '5A', 'ANCIENT_BUILDING', '晋中', '平遥县', '全天', 125.00, 37.189000, 112.175000, '保存完整的明清古城。', '', ''),
  (3, '悬空寺', '5A', 'ANCIENT_BUILDING', '大同', '浑源县恒山', '08:00-18:00', 130.00, 39.699000, 113.720000, '三教合一的山寺奇观。', '', '');

INSERT INTO `food_item` (`id`, `name`, `category`, `city`, `intro`, `cover_image`, `video_url`, `rating`) VALUES
  (1, '刀削面', 'NOODLE', '太原', '山西面食代表之一，口感筋道。', '', '', 4.8),
  (2, '栲栳栳', 'NOODLE', '吕梁', '荞麦面制作的传统美食。', '', '', 4.6),
  (3, '陈醋', 'SPECIALTY', '清徐', '山西老陈醋，香醇浓郁。', '', '', 4.7);

INSERT INTO `topic` (`id`, `name`, `description`, `cover_image`) VALUES
  (1, '寻找最美古建', '探访山西古建筑之美', ''),
  (2, '山西下雪了', '冬季雪景与古城风情', '');

INSERT INTO `travel_note` (`id`, `user_id`, `topic_id`, `title`, `content`, `cover_image`, `like_count`, `favorite_count`) VALUES
  (1, 1, 1, '一日游平遥古城', '从城墙到票号，满满历史感。', '', 12, 5),
  (2, 2, 2, '雪后的云冈石窟', '石窟在雪中更显庄严。', '', 8, 3);

INSERT INTO `itinerary` (`id`, `user_id`, `title`, `start_date`, `days`, `status`) VALUES
  (1, 1, '大同两日游', '2026-02-01', 2, 'DRAFT');

INSERT INTO `itinerary_day` (`id`, `itinerary_id`, `day_index`, `summary`) VALUES
  (1, 1, 1, '云冈石窟 + 旧城'),
  (2, 1, 2, '悬空寺 + 恒山');

INSERT INTO `itinerary_item` (`id`, `itinerary_day_id`, `attraction_id`, `start_time`, `duration_minutes`, `note`, `sort_order`) VALUES
  (1, 1, 1, '09:00:00', 180, '上午游览云冈石窟', 1),
  (2, 2, 3, '10:00:00', 120, '打卡悬空寺', 1);
