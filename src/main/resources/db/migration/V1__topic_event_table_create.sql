-- V1__1_init_topic_table.sql

CREATE TABLE topic (
  id                VARCHAR(50)     NOT NULL PRIMARY KEY,
  title             VARCHAR(255)    NOT NULL,
  description       TEXT,
  number_of_views   BIGINT          DEFAULT 0   NOT NULL,
  number_of_likes   BIGINT          DEFAULT 0   NOT NULL,
  slug              VARCHAR(255)    NOT NULL UNIQUE,
  status            VARCHAR(50)     NOT NULL,
  created_at        TIMESTAMP       DEFAULT NOW() NOT NULL,
  updated_at        TIMESTAMP       DEFAULT NOW() NOT NULL,
  deleted_at        TIMESTAMP,
  created_by        VARCHAR(50)     NOT NULL,
  updated_by        VARCHAR(50)     NOT NULL,
  deleted_by        VARCHAR(50)
);



-- V1__2__create_event_table.sql

CREATE TABLE event (
  id                VARCHAR(50)     NOT NULL PRIMARY KEY,
  name              VARCHAR(255)    NOT NULL,
  details           TEXT,
  number_of_likes   BIGINT          DEFAULT 0   NOT NULL,
  number_of_views   BIGINT          DEFAULT 0   NOT NULL,
  topic_id          VARCHAR(50)     NOT NULL,
  slug              VARCHAR(255)    NOT NULL UNIQUE,
  created_at        TIMESTAMP       DEFAULT NOW() NOT NULL,
  updated_at        TIMESTAMP       DEFAULT NOW() NOT NULL,
  deleted_at        TIMESTAMP,
  created_by        VARCHAR(50)     NOT NULL,
  updated_by        VARCHAR(50)     NOT NULL,
  deleted_by        VARCHAR(50)
);

-- Foreign‐key constraint (enforces referential integrity)
ALTER TABLE event
  ADD CONSTRAINT fk_event_topic
    FOREIGN KEY (topic_id)
    REFERENCES topic(id);

-- Index to speed up queries by topic_id
CREATE INDEX idx_event_topic_id
  ON event(topic_id);
