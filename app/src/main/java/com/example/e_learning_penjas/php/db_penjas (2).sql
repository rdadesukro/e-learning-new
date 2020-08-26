-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2020 at 02:47 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_penjas`
--

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `id_guru` int(10) NOT NULL,
  `nip` int(10) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `jk` enum('LK','PR') DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `tempat_lahir` varchar(20) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `nama_mapel` varchar(10) DEFAULT NULL,
  `foto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`id_guru`, `nip`, `nama`, `alamat`, `jk`, `tgl_lahir`, `tempat_lahir`, `no_hp`, `nama_mapel`, `foto`) VALUES
(1, 1234, 'budi', 'jambi', 'LK', '2020-01-07', 'jambi', '123', 'Penjas', 'dup698mramervbpuanqr.png'),
(2, 321, 'xxx', 'jambi', 'LK', '2020-07-06', 'Jambi', '313', 'xxx', 'dup698mramervbpuanqr.png');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id_kelas` int(10) NOT NULL,
  `nama_kelas` varchar(255) DEFAULT NULL,
  `wali_kelas` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id_kelas`, `nama_kelas`, `wali_kelas`) VALUES
(1, '12 ipa 1', 'Budi'),
(2, 'Bologi', 'AAAA');

-- --------------------------------------------------------

--
-- Table structure for table `materi`
--

CREATE TABLE `materi` (
  `id_materi` int(10) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `smester` char(20) DEFAULT NULL,
  `bab` char(20) DEFAULT NULL,
  `url` text DEFAULT NULL,
  `id_guru` int(10) DEFAULT NULL,
  `status` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `materi`
--

INSERT INTO `materi` (`id_materi`, `nama`, `smester`, `bab`, `url`, `id_guru`, `status`) VALUES
(11, 'Bola Kaki', '1', 'BAB 1', '3ctua4rhfmzszv8ebu62PENGUKURAN_KINERJA_UTAMA_2017 (1).pdf', 1, 1),
(18, '2', '1', '2', '9kpgughg9t2z2b3drr1zpengumuman_82.pdf', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `id_nilai` int(10) NOT NULL,
  `nis` int(10) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `status` char(2) DEFAULT NULL,
  `waktu` datetime DEFAULT NULL,
  `id_guru` int(10) DEFAULT NULL,
  `id_kelas` int(10) DEFAULT NULL,
  `quiz` varchar(50) DEFAULT NULL,
  `nilai` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`id_nilai`, `nis`, `nama`, `status`, `waktu`, `id_guru`, `id_kelas`, `quiz`, `nilai`) VALUES
(14, 123, 'ade', '1', '2020-06-07 12:26:20', 1, 1, '1', '100'),
(16, 123, 'ade', '1', '2020-07-06 18:35:33', 1, 1, '2', '80'),
(17, 12345, 'tes', '1', '2020-07-06 19:18:26', 2, 2, '1', '20');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id_siswa` int(10) NOT NULL,
  `nis` int(10) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `tempat_lahir` varchar(20) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jk` enum('LK','PR') DEFAULT NULL,
  `no_hp` char(20) DEFAULT NULL,
  `id_guru` int(10) DEFAULT NULL,
  `foto` text DEFAULT NULL,
  `id_kelas` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id_siswa`, `nis`, `nama`, `alamat`, `tempat_lahir`, `tgl_lahir`, `jk`, `no_hp`, `id_guru`, `foto`, `id_kelas`) VALUES
(5, 123, 'ade', 'jambi', 'jambi', '2020-04-15', 'LK', '1', 1, 'g8hx17g4cr1sc6jvsm62.png', 1),
(6, 12345, 'tes', 'jambi', 'Jambi', '2020-07-06', 'LK', '313', 2, '2tu50db6n0086k6yyisa.png', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `nis` int(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` enum('guru','siswa') DEFAULT NULL,
  `token` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nis`, `password`, `status`, `token`) VALUES
(18, 123, 'fcea920f7412b5da7be0cf42b8c93759', 'siswa', '1'),
(19, 1234, 'fcea920f7412b5da7be0cf42b8c93759', 'guru', '1'),
(21, 12345, 'e10adc3949ba59abbe56e057f20f883e', 'siswa', '1'),
(22, 321, 'e10adc3949ba59abbe56e057f20f883e', 'guru', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`id_guru`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `materi`
--
ALTER TABLE `materi`
  ADD PRIMARY KEY (`id_materi`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`id_nilai`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id_siswa`),
  ADD KEY `id_guru` (`id_guru`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `guru`
--
ALTER TABLE `guru`
  MODIFY `id_guru` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id_kelas` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `materi`
--
ALTER TABLE `materi`
  MODIFY `id_materi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `nilai`
--
ALTER TABLE `nilai`
  MODIFY `id_nilai` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id_siswa` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
