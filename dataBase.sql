
CREATE TABLE BET (
    ID_BET INTEGER NOT NULL,
    AMOUNT FLOAT NOT NULL,
    STATUS INTEGER NOT NULL,
    K FLOAT NOT NULL,
    USER INTEGER NOT NULL,
    ID_OUTCOME INTEGER NOT NULL);


CREATE TABLE COMPANYUSER (
    ID_COMPANY INTEGER NOT NULL,
    LOGNAME VARCHAR(30) NOT NULL,
    PASSWORD VARCHAR(30) NOT NULL,
    BALANCE FLOAT NOT NULL,
    FULLNAME VARCHAR(60) NOT NULL);


CREATE TABLE EVENT (
    ID_EVENT INTEGER NOT NULL,
    DESCRIPTION VARCHAR(200) NOT NULL,
    EXPIRATIONTIME TIMESTAMP NOT NULL,
    STATUS INTEGER NOT NULL,
    ID_COMPANY INTEGER NOT NULL);


CREATE TABLE OUTCOME (
    ID_OUTCOME INTEGER NOT NULL,
    K FLOAT NOT NULL,
    ID_EVENT INTEGER NOT NULL);


CREATE TABLE OWNERUSER (
    ID_OWNER INTEGER NOT NULL,
    LOGNAME VARCHAR(30) NOT NULL,
    PASSWORD VARCHAR(30) NOT NULL,
    BALANCE FLOAT NOT NULL);


CREATE TABLE PAYMENT (
    ID_PAYMENT INTEGER NOT NULL,
    STATUS INTEGER NOT NULL,
    ID_WINNEROUTCOME INTEGER NOT NULL,
    ID_EVENT INTEGER NOT NULL);


CREATE TABLE SELFUSER (
    ID_USER INTEGER NOT NULL,
    LOGNAME VARCHAR(30) NOT NULL,
    PASSWORD VARCHAR(30) NOT NULL,
    BALANCE FLOAT NOT NULL,
    FULLNAME VARCHAR(60) NOT NULL);




/******************************************************************************/
/***                                 Views                                  ***/
/******************************************************************************/



/******************************************************************************/
/***                              Primary keys                              ***/
/******************************************************************************/


ALTER TABLE BET ADD CONSTRAINT BET_PK PRIMARY KEY (ID_BET);
ALTER TABLE COMPANYUSER ADD CONSTRAINT COMPANYUSER_PK PRIMARY KEY (ID_COMPANY);
ALTER TABLE EVENT ADD CONSTRAINT EVENT_PK PRIMARY KEY (ID_EVENT);
ALTER TABLE OUTCOME ADD CONSTRAINT OUTCOME_PK PRIMARY KEY (ID_OUTCOME);
ALTER TABLE OWNERUSER ADD CONSTRAINT OWNERUSER_PK PRIMARY KEY (ID_OWNER);
ALTER TABLE PAYMENT ADD CONSTRAINT PAYMENT_PK PRIMARY KEY (ID_PAYMENT);
ALTER TABLE SELFUSER ADD CONSTRAINT SELFUSER_PK PRIMARY KEY (ID_USER);

/******************************************************************************/
/***                           Unique constraints                           ***/
/******************************************************************************/



/******************************************************************************/
/***                              Foreign keys                              ***/
/******************************************************************************/


ALTER TABLE BET ADD CONSTRAINT BET_USER_FK FOREIGN KEY (USER) REFERENCES SELFUSER (ID_USER);
ALTER TABLE BET ADD CONSTRAINT BET_OUTCOME_FK FOREIGN KEY (ID_OUTCOME) REFERENCES OUTCOME (ID_OUTCOME);
ALTER TABLE EVENT ADD CONSTRAINT EVENT_COMPANY_FK FOREIGN KEY (ID_COMPANY) REFERENCES COMPANYUSER (ID_COMPANY);
ALTER TABLE OUTCOME ADD CONSTRAINT OUTCOME_EVENT_FK FOREIGN KEY (ID_EVENT) REFERENCES EVENT (ID_EVENT);
ALTER TABLE PAYMENT ADD CONSTRAINT PAYMENT_EVENT_FK FOREIGN KEY (ID_EVENT) REFERENCES EVENT (ID_EVENT);

/******************************************************************************/
/***                           Check constraints                            ***/
/******************************************************************************/



/******************************************************************************/
/***                                Indices                                 ***/
/******************************************************************************/



/******************************************************************************/
/***                                Triggers                                ***/
/******************************************************************************/


