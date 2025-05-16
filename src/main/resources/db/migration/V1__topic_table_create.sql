-- src/main/resources/db/migration/V1__init_topic_table.sql

CREATE TABLE topic (
  id                BIGINT       NOT NULL PRIMARY KEY,
  title             VARCHAR(255) NOT NULL,
  description       TEXT,
  number_of_views   BIGINT       DEFAULT 0   NOT NULL,
  number_of_likes   BIGINT       DEFAULT 0   NOT NULL,
  slug              VARCHAR(255) NOT NULL UNIQUE,
  status            VARCHAR(50)  NOT NULL,
  created_at        TIMESTAMP    NOT NULL,
  updated_at        TIMESTAMP    NOT NULL
);
