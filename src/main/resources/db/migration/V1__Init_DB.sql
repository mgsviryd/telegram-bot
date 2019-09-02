CREATE TABLE hibernate_sequence (
  next_val BIGINT
);
INSERT INTO hibernate_sequence VALUES (1);
INSERT INTO hibernate_sequence VALUES (1);
CREATE TABLE city (
  id          BIGINT        NOT NULL,
  name        VARCHAR(255)  NOT NULL,
  information VARCHAR(2048) NOT NULL,
  PRIMARY KEY (id)
);