CREATE TABLE occurrence (
  id BIGSERIAL NOT NULL PRIMARY KEY,
  word VARCHAR,
  count INTEGER,
  occurrences_id INTEGER
);
