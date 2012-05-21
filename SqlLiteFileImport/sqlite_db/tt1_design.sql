DROP TABLE IF EXISTS tbl_buch_has_tbl_genre;

DROP TABLE IF EXISTS tbl_buch_has_tbl_autor;

DROP TABLE IF EXISTS tbl_buch;

DROP TABLE IF EXISTS tbl_autor;

DROP TABLE IF EXISTS tbl_verlag;

DROP TABLE IF EXISTS tbl_genre;

DROP TABLE IF EXISTS tbl_land;

CREATE TABLE tbl_land (
  tbl_land_id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  name TEXT  NOT NULL);



CREATE TABLE tbl_genre (
  tbl_genre_id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  name TEXT  NOT NULL);



CREATE TABLE tbl_verlag (
  tbl_verlag_id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  name TEXT  NOT NULL);



CREATE TABLE tbl_autor (
  tbl_autor_id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  FK_tbl_land_id INTEGER  NOT NULL  ,
  vname TEXT  NOT NULL  ,
  nname TEXT    ,
  zusatz TEXT,
  FOREIGN KEY(FK_tbl_land_id)
    REFERENCES tbl_land(tbl_land_id));



CREATE TABLE tbl_buch (
  tbl_buch_id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  FK_tbl_verlag_id INTEGER  NOT NULL  ,
  FK_tbl_land_id INTEGER  NOT NULL  ,
  titel TEXT  NOT NULL  ,
  jahr INTEGER    ,
  seitenzahl INTEGER,
  FOREIGN KEY(FK_tbl_land_id)
    REFERENCES tbl_land(tbl_land_id),
  FOREIGN KEY(FK_tbl_verlag_id)
    REFERENCES tbl_verlag(tbl_verlag_id));



CREATE TABLE tbl_buch_has_tbl_autor (
  FK_tbl_buch_id INTEGER  NOT NULL  ,
  FK_tbl_autor_id INTEGER  NOT NULL    ,
PRIMARY KEY(FK_tbl_buch_id, FK_tbl_autor_id),
  FOREIGN KEY(FK_tbl_buch_id)
    REFERENCES tbl_buch(tbl_buch_id),
  FOREIGN KEY(FK_tbl_autor_id)
    REFERENCES tbl_autor(tbl_autor_id));



CREATE TABLE tbl_buch_has_tbl_genre (
  FK_tbl_buch_id INTEGER  NOT NULL  ,
  FK_tbl_genre_id INTEGER  NOT NULL    ,
PRIMARY KEY(FK_tbl_buch_id, FK_tbl_genre_id),
  FOREIGN KEY(FK_tbl_buch_id)
    REFERENCES tbl_buch(tbl_buch_id),
  FOREIGN KEY(FK_tbl_genre_id)
    REFERENCES tbl_genre(tbl_genre_id));



