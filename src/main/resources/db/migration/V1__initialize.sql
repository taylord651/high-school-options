-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 25, 2020 at 09:07 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `high-school`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(5),
(5);

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `gpa` int(11) DEFAULT NULL,
  `map` int(11) DEFAULT NULL,
  `name` varchar(70) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `specialty` int(11) DEFAULT NULL,
  `sports` tinyblob NOT NULL,
  `type` int(11) DEFAULT NULL,
  `website` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `school`
--

INSERT INTO `school` (`id`, `address`, `gender`, `gpa`, `map`, `name`, `phone`, `specialty`, `sports`, `type`, `website`) VALUES
(2, '2156 Russell Blvd. | St. Louis, MO 63104', 0, 0, 0, 'McKinley Classical Leadership Academy High School', '3147730027', 0, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000006770400000006740008466f6f7462616c6c74000a566f6c6c657962616c6c7400084261736562616c6c74000c43686565726c656164696e6774000544616e6365740005547261636b78, 0, 'https://www.slps.org/Domain/2997'),
(3, '101 N Warson Rd, St. Louis, MO 63124', 0, 1, 1, 'MICDS (Mary Institute and Country Day School)', '3149935100', 3, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000006770400000006740008466f6f7462616c6c74000a566f6c6c657962616c6c7400084261736562616c6c740006536f6363657274000a4261736b657462616c6c740005547261636b78, 2, 'https://www.micds.org/'),
(4, '4015 McPherson, St. Louis, MO 63108', 0, 1, 1, 'Metro Academic and Classical High School', '3145343894', 3, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000006770400000006740008466f6f7462616c6c74000a566f6c6c657962616c6c7400084261736562616c6c740006536f6363657274000c43686565726c656164696e67740005547261636b78, 0, 'https://www.slps.org/Domain/2483');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `role`) VALUES
(1, 'Domonique', '1234', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `user_schools`
--

CREATE TABLE `user_schools` (
  `users_id` int(11) NOT NULL,
  `schools_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_schools`
--

INSERT INTO `user_schools` (`users_id`, `schools_id`) VALUES
(1, 4),
(1, 2),
(1, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_schools`
--
ALTER TABLE `user_schools`
  ADD KEY `FKeenuk9skvifloaje5bov0o4vt` (`schools_id`),
  ADD KEY `FK899oe2c4nd561dv1w6ux6e05c` (`users_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
