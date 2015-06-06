-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Май 31 2015 г., 09:57
-- Версия сервера: 5.5.32-0ubuntu7
-- Версия PHP: 5.5.3-1ubuntu2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `Bet`
--

-- --------------------------------------------------------

--
-- Структура таблицы `BET`
--

CREATE TABLE IF NOT EXISTS `BET` (
  `ID_BET` int(11) NOT NULL AUTO_INCREMENT,
  `AMOUNT` float NOT NULL,
  `STATUS` int(11) NOT NULL,
  `K` float NOT NULL,
  `USER` int(11) NOT NULL,
  `ID_OUTCOME` int(11) NOT NULL,
  PRIMARY KEY (`ID_BET`),
  KEY `BET_USER_FK` (`USER`),
  KEY `BET_OUTCOME_FK` (`ID_OUTCOME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `COMPANYUSER`
--

CREATE TABLE IF NOT EXISTS `COMPANYUSER` (
  `ID_COMPANY` int(11) NOT NULL AUTO_INCREMENT,
  `LOGNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `BALANCE` float NOT NULL,
  `FULLNAME` varchar(60) NOT NULL,
  PRIMARY KEY (`ID_COMPANY`),
  UNIQUE KEY `LOGNAME` (`LOGNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `EVENT`
--

CREATE TABLE IF NOT EXISTS `EVENT` (
  `ID_EVENT` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(200) NOT NULL,
  `EXPIRATIONTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `STATUS` int(11) NOT NULL,
  `ID_COMPANY` int(11) NOT NULL,
  PRIMARY KEY (`ID_EVENT`),
  KEY `EVENT_COMPANY_FK` (`ID_COMPANY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `OUTCOME`
--

CREATE TABLE IF NOT EXISTS `OUTCOME` (
  `ID_OUTCOME` int(11) NOT NULL AUTO_INCREMENT,
  `K` float NOT NULL,
  `ID_EVENT` int(11) NOT NULL,
  PRIMARY KEY (`ID_OUTCOME`),
  KEY `OUTCOME_EVENT_FK` (`ID_EVENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `OWNERUSER`
--

CREATE TABLE IF NOT EXISTS `OWNERUSER` (
  `ID_OWNER` int(11) NOT NULL AUTO_INCREMENT,
  `LOGNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `BALANCE` float NOT NULL,
  PRIMARY KEY (`ID_OWNER`),
  UNIQUE KEY `LOGNAME` (`LOGNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `PAYMENT`
--

CREATE TABLE IF NOT EXISTS `PAYMENT` (
  `ID_PAYMENT` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` int(11) NOT NULL,
  `ID_WINNEROUTCOME` int(11) NOT NULL,
  `ID_EVENT` int(11) NOT NULL,
  PRIMARY KEY (`ID_PAYMENT`),
  KEY `PAYMENT_EVENT_FK` (`ID_EVENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `SELFUSER`
--

CREATE TABLE IF NOT EXISTS `SELFUSER` (
  `ID_USER` int(11) NOT NULL AUTO_INCREMENT,
  `LOGNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `BALANCE` float NOT NULL,
  `FULLNAME` varchar(60) NOT NULL,
  PRIMARY KEY (`ID_USER`),
  UNIQUE KEY `LOGNAME` (`LOGNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `BET`
--
ALTER TABLE `BET`
  ADD CONSTRAINT `BET_OUTCOME_FK` FOREIGN KEY (`ID_OUTCOME`) REFERENCES `OUTCOME` (`ID_OUTCOME`),
  ADD CONSTRAINT `BET_USER_FK` FOREIGN KEY (`USER`) REFERENCES `SELFUSER` (`ID_USER`);

--
-- Ограничения внешнего ключа таблицы `EVENT`
--
ALTER TABLE `EVENT`
  ADD CONSTRAINT `EVENT_COMPANY_FK` FOREIGN KEY (`ID_COMPANY`) REFERENCES `COMPANYUSER` (`ID_COMPANY`);

--
-- Ограничения внешнего ключа таблицы `OUTCOME`
--
ALTER TABLE `OUTCOME`
  ADD CONSTRAINT `OUTCOME_EVENT_FK` FOREIGN KEY (`ID_EVENT`) REFERENCES `EVENT` (`ID_EVENT`);

--
-- Ограничения внешнего ключа таблицы `PAYMENT`
--
ALTER TABLE `PAYMENT`
  ADD CONSTRAINT `PAYMENT_EVENT_FK` FOREIGN KEY (`ID_EVENT`) REFERENCES `EVENT` (`ID_EVENT`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
