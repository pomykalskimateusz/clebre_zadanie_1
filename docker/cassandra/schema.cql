create keyspace rekrutacja with replication={'class':'SimpleStrategy', 'replication_factor':1};

use rekrutacja;

CREATE TABLE note(
   noteId timeuuid PRIMARY KEY,
   title text,
   content text,
   author text,
   timestamp bigint
);