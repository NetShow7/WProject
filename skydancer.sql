-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2017 a las 13:31:50
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `skydancer`
--
CREATE DATABASE IF NOT EXISTS `skydancer` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `skydancer`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flights`
--

CREATE TABLE IF NOT EXISTS `flights` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `duration` int(3) DEFAULT NULL,
  `origin` varchar(15) DEFAULT NULL,
  `destination` varchar(15) DEFAULT NULL,
  `pilot1` varchar(12) DEFAULT NULL,
  `pilot2` varchar(12) DEFAULT NULL,
  `tickets` int(3) DEFAULT NULL,
  `tickets_sold` int(3) DEFAULT NULL,
  `fdate` date DEFAULT NULL,
  `price` float DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `flights`
--

INSERT INTO `flights` (`id`, `duration`, `origin`, `destination`, `pilot1`, `pilot2`, `tickets`, `tickets_sold`, `fdate`, `price`, `photo`) VALUES
(1, 540, 'Bilbao', 'Helsinki', 'Vlad', 'Paco', 500, 362, '2017-06-14', 160, NULL),
(2, 270, 'Lisboa', 'New York', 'Natalia', 'Jon', 200, 38, '2018-06-02', 69, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `f_id` int(4) NOT NULL,
  `u_id` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userFK` (`u_id`),
  KEY `flightFK` (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reservations`
--

INSERT INTO `reservations` (`id`, `f_id`, `u_id`) VALUES
(1, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) DEFAULT NULL,
  `name` varchar(12) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `phone` int(9) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `passwd` varchar(512) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `dni`, `name`, `surname`, `birth`, `address`, `phone`, `username`, `passwd`, `email`, `isAdmin`) VALUES
(3, '74628523G', 'Jokin', 'Iñurrieta', '1995-06-03', 'Yes street', 943726384, 'Jokin_335', 'bestpasswordever1', 'jokininu@gmail.com', NULL),
(4, '12345678r', 'Test', 'Test', '1999-09-09', 'Test', 123456789, 'test', 'test', 'test@test.test', NULL);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `flightFK` FOREIGN KEY (`f_id`) REFERENCES `flights` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userFK` FOREIGN KEY (`u_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
