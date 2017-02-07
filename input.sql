USE sapphire;

DROP TABLE if EXISTS USER;

CREATE TABLE USER
(
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  ROLE_UID BIGINT NOT NULL,
  USERNAME VARCHAR(50) NOT NULL,
  PASSWORD VARCHAR(50) NOT NULL,
  EMAIL VARCHAR(50) NOT NULL
) ENGINE=innodb DEFAULT CHARSET=UTF8;

DROP TABLE if EXISTS ROLE;
CREATE TABLE ROLE
(
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  ROLE_NAME VARCHAR(50) NOT NULL,
  PRIORITY TINYINT
) ENGINE=innodb DEFAULT CHARSET=UTF8;

INSERT INTO ROLE (UIDPK, ROLE_NAME, PRIORITY) VALUES (1, 'ROLE_ADMIN', 10);
INSERT INTO ROLE (UIDPK, ROLE_NAME, PRIORITY) VALUES (2, 'ROLE_USER', 5);
INSERT INTO ROLE (UIDPK, ROLE_NAME, PRIORITY) VALUES (3, 'ROLE_GUEST', 0);

DROP TABLE IF EXISTS BLOG;
CREATE TABLE BLOG (
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  USER_UID BIGINT NOT NULL,
  BLOG_TITLE VARCHAR(50) NOT NULL,
  BLOG_CONTENT MEDIUMTEXT,
  CREATE_TIME TIMESTAMP NOT NULL,
  LAST_MODIFY_TIME DATETIME NOT NULL,
  BLOG_STATUS TINYINT NOT NULL DEFAULT 0,
  HIT BIGINT DEFAULT 0
) ENGINE=innodb DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS COMMENT;
CREATE TABLE COMMENT (
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  USER_UID BIGINT NOT NULL,
  BLOG_UID BIGINT NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL ,
  LAST_MODIFY_TIME DATETIME NOT NULL,
  CONTENT VARCHAR(200) DEFAULT ''
) ENGINE=innodb DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  CREATE_USER_UID BIGINT NOT NULL,
  ASSIGN_USER_UID BIGINT NOT NULL,
  TITLE VARCHAR(50) NOT NULL,
  DESCRIPTION VARCHAR(500) NOT NULL,
  PROJECT_UID BIGINT NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL,
  LAST_MODIFY_TIME DATETIME NOT NULL,
  START_TIME DATETIME NOT NULL,
  END_TIME DATETIME NOT NULL,
  STATUS TINYINT NOT NULL,
  TICKET_TYPE TINYINT NOT NULL,
  PRIORITY TINYINT NOT NULL
) ENGINE=innodb DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS PROJECT;
CREATE TABLE PROJECT (
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  TITLE VARCHAR(50) NOT NULL,
  DESCRIPTION VARCHAR(500) NOT NULL,
  REPO_URL VARCHAR(255) NOT NULL,
  CREATE_TIME DATETIME NOT NULL,
  LAST_MODIFY_TIME DATETIME NOT NULL
) ENGINE=innodb DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS USER_INFO;
CREATE TABLE USER_INFO (
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(50) NOT NULL,
  USER_UID BIGINT UNIQUE NOT NULL,
  IMG_SRC VARCHAR(255) NOT NULL
) ENGINE=innodb DEFAULT CHARSET=UTF8;

DROP TABLE if EXISTS STOCK_ITEM;

CREATE TABLE STOCK_ITEM(
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  CODE VARCHAR(10) NOT NULL,
  NAME VARCHAR(20) NOT NULL,
  INCREASE_RATE DOUBLE(7, 2),
  INDUSTRY VARCHAR(20) NOT NULL,
  START_PRICE DOUBLE(7, 2),
  END_PRICE DOUBLE(7, 2),
  HIGHEST_PRICE DOUBLE(7, 2),
  LOWEST_PRICE DOUBLE(7, 2),
  CIRCULATION_MARKET_VALUE DOUBLE(40,2),
  LOG_DATE DATE,
  TRADING DOUBLE(40, 2),
  TRADING_VALUE DOUBLE(40,3),
  MACD_DEA DOUBLE(11, 6),
  MACD_DIFF DOUBLE(11, 6),
  MACD DOUBLE(11, 6),
  EMA_12 DOUBLE(11, 6),
  EMA_26 DOUBLE(11, 6),
  IS_LAST BOOL DEFAULT FALSE,
  IS_STOP BOOL DEFAULT FALSE,
  AVERAGE_5 DOUBLE(7, 2),
  AVERAGE_10 DOUBLE(7, 2),
  AVERAGE_20 DOUBLE(7, 2),
  AVERAGE_30 DOUBLE(7, 2),
  IS_NEW BOOL DEFAULT TRUE
) ENGINE=innodb DEFAULT CHARSET=UTF8;

ALTER TABLE STOCK_ITEM ADD INDEX (CODE);

ALTER TABLE STOCK_ITEM ADD INDEX (INDUSTRY);

DROP TABLE IF EXISTS BLOG_TAG;
CREATE TABLE BLOG_TAG (
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  BLOG_UID BIGINT,
  TAG_NAME VARCHAR(20)
) ENGINE=innodb DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS STOCK_STATISTICS;

CREATE TABLE STOCK_STATISTICS(
  UIDPK BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  CODE VARCHAR(10) NOT NULL,
  AVERAGE_GOLD_DAYS INT,
  INCREASE_TOTAL DOUBLE(7, 2),
  HIGHEST_PRICE DOUBLE(7, 2),
  LOWEST_MACD DOUBLE(11, 6),
  LAST_MODIFY_DATE TIMESTAMP,
  EARLY_DATE DATE
) ENGINE=innodb DEFAULT CHARSET=UTF8;