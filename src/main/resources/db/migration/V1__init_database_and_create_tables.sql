CREATE TABLE IF NOT EXIST USER_INFO
(
    id                  VARCHAR(64) NOT NULL PRIMARY KEY,
    username            VARCHAR(64) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    password            VARCHAR(64) NOT NULL,
    bio                 VARCHAR(64),
    images              VARCHAR(64)
)

CREATE TABLE IF NOT EXIST ARTICLES
(
    id                  BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 100000000000),
    author_id           VARCHAR(64) NOT NULL,
    title               VARCHAR(255) NOT NULL,
    description         VARCHAR(128) NOT NULL,
    content             VARCHAR(255) NOT NULL,
    favorites_count     INTEGER,
    created_at          TIMESTAMP,
    updated_at          TIMESTAMP
)

CREATE TABLE IF NOT EXIST COMMENTS
(
    id                  BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 100000000000),
    author_id           VARCHAR(64) NOT NULL,
    article_id          VARCHAR(64) NOT NULL,
    content             VARCHAR(255),
    created_at          TIMESTAMP,
    updated_at          TIMESTAMP
)

CREATE TABLE IF NOT EXIST TAGS
(
    id                  BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 100000000000),
    article_id          VARCHAR(64) NOT NULL,
    tag                 VARCHAR(255) NOT NULL,
    created_at          TIMESTAMP,
)




