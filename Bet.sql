-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Июн 08 2015 г., 08:28
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Дамп данных таблицы `BET`
--

INSERT INTO `BET` (`ID_BET`, `AMOUNT`, `STATUS`, `K`, `USER`, `ID_OUTCOME`) VALUES
(20, 100, 1, 2, 22, 2),
(21, 150, 1, 0.35, 22, 3),
(22, 50, 1, 2, 22, 2),
(23, 100, 1, 0.35, 76, 3),
(24, 50, 0, 2, 22, 76),
(27, 50, 1, 2, 22, 2),
(28, 100, 0, 2, 22, 76),
(29, 100, 1, 3, 120, 86),
(30, 50, 1, 3, 120, 86),
(31, 200, 1, 3, 120, 86),
(32, 50, 1, 3, 120, 86),
(33, 50, 1, 0.3, 129, 87),
(34, 100, 1, 0.3, 129, 87),
(35, 50, 1, 0.3, 129, 87),
(36, 100, 1, 0.5, 120, 89),
(37, 100, 1, 2, 120, 88);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=57 ;

--
-- Дамп данных таблицы `COMPANYUSER`
--

INSERT INTO `COMPANYUSER` (`ID_COMPANY`, `LOGNAME`, `PASSWORD`, `BALANCE`, `FULLNAME`) VALUES
(1, 'llama', '11235815', 550, 'Llamas corporation'),
(24, 'newCompany', '12345', 400, 'newCompany'),
(32, 'qwerty', '123', 600, 'qwerty'),
(35, 'test1', '11235813', 750, 'test1');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=82 ;

--
-- Дамп данных таблицы `EVENT`
--

INSERT INTO `EVENT` (`ID_EVENT`, `DESCRIPTION`, `EXPIRATIONTIME`, `STATUS`, `ID_COMPANY`) VALUES
(21, 'Can I close this session?', '2015-08-07 05:11:37', 2, 1),
(75, 'Default', '2015-06-28 14:05:25', 2, 1),
(76, 'Default', '2015-06-28 17:01:57', 2, 1),
(77, 'Default', '2015-06-28 17:06:15', 2, 1),
(78, 'Default', '2015-06-28 17:08:26', 2, 1),
(79, 'Default', '2015-06-29 02:35:20', 0, 1),
(80, 'testEvent1', '2015-06-29 03:11:29', 2, 35),
(81, 'Defaulttest', '2015-06-29 03:22:02', 2, 35);

-- --------------------------------------------------------

--
-- Структура таблицы `OUTCOME`
--

CREATE TABLE IF NOT EXISTS `OUTCOME` (
  `ID_OUTCOME` int(11) NOT NULL AUTO_INCREMENT,
  `K` float NOT NULL,
  `ID_EVENT` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_OUTCOME`),
  KEY `OUTCOME_EVENT_FK` (`ID_EVENT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=90 ;

--
-- Дамп данных таблицы `OUTCOME`
--

INSERT INTO `OUTCOME` (`ID_OUTCOME`, `K`, `ID_EVENT`, `NAME`) VALUES
(2, 2, 21, 'Yes!'),
(3, 0.35, 21, 'Nope!'),
(76, 2, 75, 'first'),
(77, 0.5, 75, 'second'),
(78, 2, 76, 'first'),
(79, 0.5, 76, 'second'),
(80, 2, 77, 'first'),
(81, 0.5, 77, 'second'),
(82, 2, 78, 'first'),
(83, 0.5, 78, 'second'),
(84, 2, 79, 'first'),
(85, 0.5, 79, 'second'),
(86, 3, 80, 'test1'),
(87, 0.3, 80, 'test2'),
(88, 2, 81, 'first'),
(89, 0.5, 81, 'second');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `OWNERUSER`
--

INSERT INTO `OWNERUSER` (`ID_OWNER`, `LOGNAME`, `PASSWORD`, `BALANCE`) VALUES
(1, 'admin', '11235813', 0);

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
  UNIQUE KEY `ID_EVENT` (`ID_EVENT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Дамп данных таблицы `PAYMENT`
--

INSERT INTO `PAYMENT` (`ID_PAYMENT`, `STATUS`, `ID_WINNEROUTCOME`, `ID_EVENT`) VALUES
(23, 1, 2, 21),
(24, 0, 79, 76),
(25, 0, 76, 75),
(26, 0, 80, 77),
(27, 0, 83, 78),
(30, 1, 86, 80),
(31, 1, 89, 81);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=173 ;

--
-- Дамп данных таблицы `SELFUSER`
--

INSERT INTO `SELFUSER` (`ID_USER`, `LOGNAME`, `PASSWORD`, `BALANCE`, `FULLNAME`) VALUES
(22, 'llama', '11235813', 750, 'crazy llama'),
(76, 'random', '123', 0, 'random'),
(95, 'llamaafdas', '11235813', 100, 'llamaafdas'),
(120, 'winner', '11235813', 350, 'winner'),
(129, 'looser', '11235813', 100, 'looser');

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
