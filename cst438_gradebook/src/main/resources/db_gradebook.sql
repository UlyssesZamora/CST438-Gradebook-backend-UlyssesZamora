-- script to create gradebook database with sample data
create schema gradebook;

use gradebook;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `instructor` varchar(255) DEFAULT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`course_id`)
);

insert into course values(123456, 'dwisneski@csumb.edu', 'fall', 'cst438-software engineering', 2021);
insert into course values(999001, 'dwisneski@csumb.edu', 'fall', 'cst363-database', 2021);



DROP TABLE IF EXISTS `enrollment`;
CREATE TABLE `enrollment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_email` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbhhcqkw1px6yljqg92m0sh2gt` (`course_id`),
  CONSTRAINT `FKbhhcqkw1px6yljqg92m0sh2gt` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
);

insert into enrollment values(null, 'student1@csumb.edu', 'Student One', 123456);
insert into enrollment values(null, 'student2@csumb.edu', 'Student Two', 123456);
insert into enrollment values(null, 'student1@csumb.edu', 'Student One', 999001);
insert into enrollment values(null, 'student3@csumb.edu', 'Student Three', 999001);

DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `due_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `needs_grading` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrop26uwnbkstbtfha3ormxp85` (`course_id`),
  CONSTRAINT `FKrop26uwnbkstbtfha3ormxp85` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ;

insert into assignment values(null, '2021-09-01', 'a1 - db design', 1, 999001);
insert into assignment values(null, '2021-09-02', 'hw1 - requirements', 1, 123456);

DROP TABLE IF EXISTS `assignment_grade`;
CREATE TABLE `assignment_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` varchar(255) DEFAULT NULL,
  `assignment_id` int(11) DEFAULT NULL,
  `enrollment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKph33axj4dnucgpe69qif06dlj` (`assignment_id`),
  KEY `FKaxh9254n4nfmxxxovn8g591y7` (`enrollment_id`),
  CONSTRAINT `FKaxh9254n4nfmxxxovn8g591y7` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollment` (`id`),
  CONSTRAINT `FKph33axj4dnucgpe69qif06dlj` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`)
);
