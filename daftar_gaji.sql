-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2021 at 08:31 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gaji_karyawan`
--

-- --------------------------------------------------------

--
-- Table structure for table `daftar_gaji`
--

CREATE TABLE `daftar_gaji` (
  `no_pegawai` varchar(99) NOT NULL,
  `nama_pegawai` varchar(99) NOT NULL,
  `jabatan` varchar(99) NOT NULL,
  `kehadiran` int(11) NOT NULL,
  `potongan` int(40) NOT NULL,
  `total_gaji` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `daftar_gaji`
--

INSERT INTO `daftar_gaji` (`no_pegawai`, `nama_pegawai`, `jabatan`, `kehadiran`, `potongan`, `total_gaji`) VALUES
('432', 'srs', 'Sekretaris', 27, 150000, 19850000),
('567', 'stn', 'Manager', 26, 200000, 9800000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
