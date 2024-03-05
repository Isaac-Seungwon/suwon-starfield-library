-- ddl
SELECT * FROM tabs ORDER BY table_name;

-- system
-- create user starfield_library identified by suwon1234;
-- grant connect, resource, dba to starfield_library;

-- Drop Book table
DROP TABLE Book;

-- Drop Author table
DROP TABLE Author;

-- Drop Sequences
DROP SEQUENCE author_seq;
DROP SEQUENCE book_seq;

-- Create Author table
CREATE TABLE Author (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100)
);

-- Create Book table with foreign key constraint
CREATE TABLE Book (
    id NUMBER PRIMARY KEY,
    title VARCHAR2(100),
    price VARCHAR2(100),
    author_id NUMBER,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES Author(id)
);

-- Sequence for auto-incrementing IDs
CREATE SEQUENCE author_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE book_seq START WITH 1 INCREMENT BY 1;
