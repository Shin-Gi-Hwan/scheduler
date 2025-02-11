CREATE TABLE SCHEDULE (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    USER_ID BIGINT NOT NULL,
    USERNAME NVARCHAR(128) NOT NULL,
    TITLE NVARCHAR(256) NOT NULL,
    CONTENT NVARCHAR(1000) NOT NULL,
    CREATED_AT DATETIME NOT NULL,
    MODIFIED_AT DATETIME NOT NULL,
    CONSTRAINT SCHEDULE_TABLE_PK PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES USER(ID)
);

CREATE TABLE USER (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    USERNAME NVARCHAR(128) NOT NULL,
    EMAIL NVARCHAR(256) NOT NULL,
    CREATED_AT DATETIME NOT NULL,
    MODIFIED_AT DATETIME NOT NULL,
    CONSTRAINT USER_TABLE_PK PRIMARY KEY (ID)
);
