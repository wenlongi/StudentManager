/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : school4

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-12-25 08:40:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `banji`
-- ----------------------------
DROP TABLE IF EXISTS `banji`;
CREATE TABLE `banji` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `stuNums` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banji
-- ----------------------------
INSERT INTO `banji` VALUES ('1', 'java一班', '2');
INSERT INTO `banji` VALUES ('2', 'UI一班', '2');
INSERT INTO `banji` VALUES ('3', 'UI二班', '0');
INSERT INTO `banji` VALUES ('4', 'Java二班', '4');
INSERT INTO `banji` VALUES ('5', '一年级一班', '0');

-- ----------------------------
-- Table structure for `m_bj_sub`
-- ----------------------------
DROP TABLE IF EXISTS `m_bj_sub`;
CREATE TABLE `m_bj_sub` (
  `bj_id` int(11) NOT NULL DEFAULT '0',
  `sub_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`bj_id`,`sub_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_bj_sub
-- ----------------------------
INSERT INTO `m_bj_sub` VALUES ('1', '1');
INSERT INTO `m_bj_sub` VALUES ('1', '2');
INSERT INTO `m_bj_sub` VALUES ('1', '3');
INSERT INTO `m_bj_sub` VALUES ('2', '1');
INSERT INTO `m_bj_sub` VALUES ('2', '2');
INSERT INTO `m_bj_sub` VALUES ('2', '3');
INSERT INTO `m_bj_sub` VALUES ('3', '1');
INSERT INTO `m_bj_sub` VALUES ('3', '2');
INSERT INTO `m_bj_sub` VALUES ('4', '1');
INSERT INTO `m_bj_sub` VALUES ('4', '2');
INSERT INTO `m_bj_sub` VALUES ('5', '2');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) DEFAULT NULL,
  `sub_id` int(11) DEFAULT NULL,
  `result` int(120) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('110', '2', '1', '73', '一般');
INSERT INTO `score` VALUES ('111', '3', '1', '86', '良好');
INSERT INTO `score` VALUES ('118', '3', '2', '66', '及格');
INSERT INTO `score` VALUES ('119', '4', '1', '98', '优秀');
INSERT INTO `score` VALUES ('120', '1', '1', '56', '不及格');
INSERT INTO `score` VALUES ('121', '8', '1', '96', '优秀');
INSERT INTO `score` VALUES ('122', '9', '1', '89', '良好');
INSERT INTO `score` VALUES ('123', '5', '1', '56', '不及格');
INSERT INTO `score` VALUES ('124', '10', '1', '65', '及格');
INSERT INTO `score` VALUES ('125', '1', '2', '56', '不及格');
INSERT INTO `score` VALUES ('126', '2', '3', '75', '一般');
INSERT INTO `score` VALUES ('127', '4', '3', '98', '优秀');
INSERT INTO `score` VALUES ('128', '9', '2', '87', '良好');
INSERT INTO `score` VALUES ('129', '8', '2', '98', '优秀');
INSERT INTO `score` VALUES ('130', '1', '2', '56', '不及格');
INSERT INTO `score` VALUES ('131', '10', '2', '63', '及格');
INSERT INTO `score` VALUES ('132', '5', '2', '99', '优秀');
INSERT INTO `score` VALUES ('133', '3', '3', '26', '不及格');
INSERT INTO `score` VALUES ('134', '2', '3', '75', '一般');
INSERT INTO `score` VALUES ('135', '4', '3', '98', '优秀');
INSERT INTO `score` VALUES ('136', '9', '2', '87', '良好');
INSERT INTO `score` VALUES ('137', '8', '2', '98', '优秀');
INSERT INTO `score` VALUES ('138', '1', '2', '56', '不及格');
INSERT INTO `score` VALUES ('139', '10', '2', '63', '及格');
INSERT INTO `score` VALUES ('140', '10', '3', '96', '优秀');
INSERT INTO `score` VALUES ('141', '5', '2', '99', '优秀');
INSERT INTO `score` VALUES ('142', '3', '3', '26', '不及格');
INSERT INTO `score` VALUES ('143', '2', '2', '79', '一般');
INSERT INTO `score` VALUES ('144', '2', '2', '65', '及格');
INSERT INTO `score` VALUES ('145', '2', '2', '65', '及格');
INSERT INTO `score` VALUES ('146', '4', '2', '89', '良好');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `age` int(50) DEFAULT NULL,
  `bj_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '小明', '男', '21', '4');
INSERT INTO `student` VALUES ('2', '小红', '女', '20', '2');
INSERT INTO `student` VALUES ('3', '小青', '女', '21', '1');
INSERT INTO `student` VALUES ('4', '小强', '男', '20', '2');
INSERT INTO `student` VALUES ('5', '小王', '男', '163', '4');
INSERT INTO `student` VALUES ('8', '的方法', '男', '12', '4');
INSERT INTO `student` VALUES ('9', '啦啦啦啦', '女', '21', '4');
INSERT INTO `student` VALUES ('10', '小明', '男', '22', '1');

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', 'java');
INSERT INTO `subject` VALUES ('2', 'UI');
INSERT INTO `subject` VALUES ('3', 'H50322');

-- ----------------------------
-- View structure for `v_stu_sub_sc`
-- ----------------------------
DROP VIEW IF EXISTS `v_stu_sub_sc`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_stu_sub_sc` AS (select `stu`.`id` AS `stuId`,`stu`.`name` AS `stuName`,`bj`.`id` AS `bjId`,`bj`.`name` AS `bjName`,`sub`.`id` AS `subId`,`sub`.`name` AS `subName`,`sc`.`id` AS `scId`,`sc`.`result` AS `result`,`sc`.`grade` AS `grade` from ((((`student` `stu` join `banji` `bj` on((`stu`.`bj_id` = `bj`.`id`))) join `m_bj_sub` `m` on((`bj`.`id` = `m`.`bj_id`))) join `subject` `sub` on((`m`.`sub_id` = `sub`.`id`))) left join `score` `sc` on(((`sc`.`stu_id` = `stu`.`id`) and (`sc`.`sub_id` = `sub`.`id`))))) ;

-- ----------------------------
-- Procedure structure for `clearGradeSchool4`
-- ----------------------------
DROP PROCEDURE IF EXISTS `clearGradeSchool4`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `clearGradeSchool4`()
BEGIN
	#Routine body goes here...
update score set grade=null;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `setGradeSchool4`
-- ----------------------------
DROP PROCEDURE IF EXISTS `setGradeSchool4`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `setGradeSchool4`()
BEGIN
	#Routine body goes here...
declare  tgrade VARCHAR(50);
declare  tid int;
declare  tresult int;
DECLARE   size  int;
DECLARE   isLoop int DEFAULT 1;

--   游标
declare   cur CURSOR for SELECT id,RESult from score;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET ISlOOP=0;
-- SELECT COUNT(id) into size from score;
--  使用必须open
open cur;

while(isLoop>0)
DO
FETCH cur into tid,tresult;
if(tresult>=90)
THEN
set tgrade='优秀';
ELSEIF(tresult>=80)
THEN
set tgrade='良好';
ELSEIF(tresult>=70)
THEN
set tgrade='一般';
ELSEIF(tresult>=60)
THEN
set tgrade='及格';
ELSE
set tgrade='不及格';
end if;
update score SET grade=tgrade where id=tid;
--  set isLoop=isLoop+1;
end WHILE;

END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `aaaa`;
DELIMITER ;;
CREATE TRIGGER `aaaa` BEFORE INSERT ON `score` FOR EACH ROW begin

if(new.result>=90)
then
set new.grade='优秀';
ELSEIF(new.result>=80)
then
set new.grade='良好';
ELSEIF(new.result>=70)
then
set new.grade='一般';
ELSEIF(new.result>=60)
then
set new.grade='及格';
ELSE
set new.grade='不及格';
end if;

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `bbbb`;
DELIMITER ;;
CREATE TRIGGER `bbbb` BEFORE UPDATE ON `score` FOR EACH ROW begin

if(new.result>=90)
then
set new.grade='优秀';
ELSEIF(new.result>=80)
then
set new.grade='良好';
ELSEIF(new.result>=70)
then
set new.grade='一般';
ELSEIF(new.result>=60)
then
set new.grade='及格';
ELSE
set new.grade='不及格';
end if;

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `aa`;
DELIMITER ;;
CREATE TRIGGER `aa` AFTER INSERT ON `student` FOR EACH ROW begin

update banji set stunums=ifNull(stunums,0)+1 where id=new.bj_id;

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `cc`;
DELIMITER ;;
CREATE TRIGGER `cc` BEFORE UPDATE ON `student` FOR EACH ROW begin

update banji set stunums=ifNull(stunums,0)-1 where id=old.bj_id;

update banji set stunums=ifNull(stunums,0)+1 where id=new.bj_id;

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `bb`;
DELIMITER ;;
CREATE TRIGGER `bb` BEFORE DELETE ON `student` FOR EACH ROW begin

update banji set stunums=ifNull(stunums,0)-1 where id=old.bj_id;

end
;;
DELIMITER ;
