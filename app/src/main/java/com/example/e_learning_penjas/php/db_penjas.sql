/*
SQLyog Community v12.09 (64 bit)
MySQL - 10.4.8-MariaDB : Database - db_penjas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_penjas` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_penjas`;

/*Table structure for table `guru` */

DROP TABLE IF EXISTS `guru`;

CREATE TABLE `guru` (
  `id_guru` int(10) NOT NULL AUTO_INCREMENT,
  `nip` int(10) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `jk` enum('LK','PR') DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `tempat_lahir` varchar(20) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `nama_mapel` varchar(10) DEFAULT NULL,
  `foto` text NOT NULL,
  PRIMARY KEY (`id_guru`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `guru` */

insert  into `guru`(`id_guru`,`nip`,`nama`,`alamat`,`jk`,`tgl_lahir`,`tempat_lahir`,`no_hp`,`nama_mapel`,`foto`) values (1,1234,'budi','jambi','LK','2020-01-07','jambi','123','Penjas','fxp0nwu59kr7ube6t77k.png'),(2,321,'xxx','jambi','LK','2020-07-06','Jambi','313','xxx','dup698mramervbpuanqr.png');

/*Table structure for table `kelas` */

DROP TABLE IF EXISTS `kelas`;

CREATE TABLE `kelas` (
  `id_kelas` int(10) NOT NULL AUTO_INCREMENT,
  `nama_kelas` varchar(255) DEFAULT NULL,
  `wali_kelas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_kelas`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kelas` */

insert  into `kelas`(`id_kelas`,`nama_kelas`,`wali_kelas`) values (1,'12 ipa 1','Budi'),(2,'Bologi','AAAA');

/*Table structure for table `materi` */

DROP TABLE IF EXISTS `materi`;

CREATE TABLE `materi` (
  `id_materi` int(10) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `smester` char(20) DEFAULT NULL,
  `bab` char(20) DEFAULT NULL,
  `url` text DEFAULT NULL,
  `id_guru` int(10) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_materi`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `materi` */

insert  into `materi`(`id_materi`,`nama`,`smester`,`bab`,`url`,`id_guru`,`status`) values (11,'Bola Kaki','1','BAB 1','ijh7iqd3j59j3t2ca2rz1504026207920002_kartuDaftar.pdf',1,1),(18,'2','1','2','9kpgughg9t2z2b3drr1zpengumuman_82.pdf',2,1);

/*Table structure for table `nilai` */

DROP TABLE IF EXISTS `nilai`;

CREATE TABLE `nilai` (
  `id_nilai` int(10) NOT NULL AUTO_INCREMENT,
  `nis` int(10) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `status` char(2) DEFAULT NULL,
  `waktu` datetime DEFAULT NULL,
  `id_guru` int(10) DEFAULT NULL,
  `id_kelas` int(10) DEFAULT NULL,
  `quiz` varchar(50) DEFAULT NULL,
  `nilai` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_nilai`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nilai` */

insert  into `nilai`(`id_nilai`,`nis`,`nama`,`status`,`waktu`,`id_guru`,`id_kelas`,`quiz`,`nilai`) values (14,123,'ade','1','2020-06-07 12:26:20',1,1,'1','100'),(16,123,'ade','1','2020-07-06 18:35:33',1,1,'2','80'),(17,12345,'tes','1','2020-07-06 19:18:26',2,2,'1','20');

/*Table structure for table `siswa` */

DROP TABLE IF EXISTS `siswa`;

CREATE TABLE `siswa` (
  `id_siswa` int(10) NOT NULL AUTO_INCREMENT,
  `nis` int(10) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `tempat_lahir` varchar(20) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jk` enum('LK','PR') DEFAULT NULL,
  `no_hp` char(20) DEFAULT NULL,
  `id_guru` int(10) DEFAULT NULL,
  `foto` text DEFAULT NULL,
  `id_kelas` int(10) DEFAULT NULL,
  PRIMARY KEY (`id_siswa`),
  KEY `id_guru` (`id_guru`),
  CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `siswa` */

insert  into `siswa`(`id_siswa`,`nis`,`nama`,`alamat`,`tempat_lahir`,`tgl_lahir`,`jk`,`no_hp`,`id_guru`,`foto`,`id_kelas`) values (5,123,'ade','jambi','jambi','2020-04-15','LK','1',1,'kki50ihm3u9nsrgjvaim.png',1),(6,12345,'tes','jambi','Jambi','2020-07-06','LK','313',2,'2tu50db6n0086k6yyisa.png',2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nis` int(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` enum('guru','siswa') DEFAULT NULL,
  `token` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`nis`,`password`,`status`,`token`) values (18,123,'fcea920f7412b5da7be0cf42b8c93759','siswa','1'),(19,1234,'e10adc3949ba59abbe56e057f20f883e','guru','1'),(21,12345,'e10adc3949ba59abbe56e057f20f883e','siswa','1'),(22,321,'e10adc3949ba59abbe56e057f20f883e','guru','1');

/*Table structure for table `vidio` */

DROP TABLE IF EXISTS `vidio`;

CREATE TABLE `vidio` (
  `id_vidio` int(10) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `foto` text DEFAULT NULL,
  `link` text DEFAULT NULL,
  PRIMARY KEY (`id_vidio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `vidio` */

insert  into `vidio`(`id_vidio`,`nama`,`foto`,`link`) values (1,'Bola Voli','Permainan-Bola-Voli.jpg','https://www.youtube.com/watch?v=QarKskCq4oM');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
