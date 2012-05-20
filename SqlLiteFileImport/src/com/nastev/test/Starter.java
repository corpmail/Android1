package com.nastev.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Imports für das Lesen von CVS
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class Starter {
	public static void main(String[] args) throws Exception {

		/*
		 * stat.executeUpdate("drop table if exists people;");
		 * stat.executeUpdate("create table people (name, occupation);");
		 * PreparedStatement prep =
		 * conn.prepareStatement("insert into people values (?, ?);");
		 * 
		 * prep.setString(1, "Gandhi"); prep.setString(2, "politics");
		 * prep.addBatch();
		 * 
		 * prep.setString(1, "Turing"); prep.setString(2, "computers");
		 * prep.addBatch();
		 * 
		 * prep.setString(1, "Wittgenstein"); prep.setString(2, "smartypants");
		 * prep.addBatch();
		 * 
		 * conn.setAutoCommit(false); prep.executeBatch();
		 * conn.setAutoCommit(true);
		 */
		// loadLandFk();
		// loadGenreFk();

		// createTables();
		// importCVS(filenam,table,anzFelder); /filename, tabelle, anzanlfelder
		// setFK ???
		// deleteCollumns ???

		createDB();
		
		splitCVS("tbl_bestand","tbl_bestand.csv");
		splitCVS("tbl_land","tbl_land.csv");
		splitCVS("tbl_genre","tbl_genre.csv");
		splitCVS("tbl_autor","tbl_autor.csv");
		
		
	}

	protected static void loadLandFk() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:sqlite_db\\tt.sqlite");
		Statement stat = conn.createStatement();
		ResultSet rs = stat
				.executeQuery("select l.landid, b.bestandid,b.fk_land  from tbl_bestand b, tbl_land l where l.land=b.land;");
		while (rs.next()) {
			System.out.println("landid " + rs.getInt("landid")
					+ " bestandid = " + rs.getInt("bestandid"));
			Statement stat2 = conn.createStatement();
			stat2.executeUpdate("update tbl_bestand set fk_land = "
					+ rs.getInt("landid") + " where bestandid="
					+ rs.getInt("bestandid") + " ;");

		}
		rs.close();
		conn.close();
	}

	protected static void loadGenreFk() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:sqlite_db\\tt.sqlite");
		Statement stat = conn.createStatement();
		ResultSet rs = stat
				.executeQuery("select g.genreid, b.bestandid ,b.fk_genre  from tbl_bestand b, tbl_genre g where g.genre=b.genre;");
		while (rs.next()) {
			System.out.println("genreid " + rs.getInt("genreid")
					+ " bestandid = " + rs.getInt("bestandid"));
			Statement stat2 = conn.createStatement();
			stat2.executeUpdate("update tbl_bestand set fk_genre = "
					+ rs.getInt("genreid") + " where bestandid="
					+ rs.getInt("bestandid") + " ;");

		}
		rs.close();
		conn.close();
	}

	protected static void splitCVS(String name_tbl, String name_file) throws ClassNotFoundException,
			SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:sqlite_db\\tt.sqlite");
		Statement stat = conn.createStatement();

		String trennzeichen = ";";
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(
					"cvs_files\\"+name_file+"")));
			String readString;
			String[] columnNamesArray;
			int columnNamesArrayLength;
			String columnNamesString = "(";
			int lineCounter1 = 0;
			while ((readString = in.readLine()) != null) {
				lineCounter1++;
				String[] splittArray = readString.split(trennzeichen);
				//System.out.println("READSTRING "+readString);
				if (lineCounter1==1){
					columnNamesArray = readString.split(trennzeichen);
					columnNamesArrayLength = columnNamesArray.length - 1;
					for (int i = 0; i <= columnNamesArrayLength; i++) {
						if (i < columnNamesArrayLength) {
							columnNamesString += "'" + columnNamesArray[i] + "'" + ",";
							//System.out.println("1---------------: "+columnNamesString);

						} else {
							columnNamesString += "'" + columnNamesArray[i] + "'" + ")";
							//System.out.println("2---------------: "+columnNamesString);
						}
					}
				}
				
				//System.out.println("3---------------: "+columnNamesString);
				
				if (lineCounter1 > 1) {
					String req = "INSERT INTO "+name_tbl+" "+columnNamesString+" VALUES (";
					int splittArrayLength = splittArray.length - 1;

					for (int i = 0; i <= splittArrayLength; i++) {
						if (i < splittArrayLength) {
							req += "'" + splittArray[i] + "'" + ",";

						} else {
							req += "'" + splittArray[i] + "'" + ");";
						}
					}

					System.out.println("req: " + req);

					try {
						stat.executeUpdate(req);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
	}

	protected static void createDB() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:sqlite_db\\tt.sqlite");
		Statement stat = conn.createStatement();

		stat.executeUpdate("DROP TABLE IF EXISTS tbl_bestand;");
		stat.executeUpdate("CREATE TABLE tbl_bestand (BestandId INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Autor TEXT, Titel TEXT, Land TEXT, Genre TEXT, fk_land INTEGER, fk_genre INTEGER)");

		stat.executeUpdate("DROP TABLE IF EXISTS tbl_land;");
		stat.executeUpdate("CREATE TABLE tbl_land (LandId INTEGER PRIMARY KEY  NOT NULL  UNIQUE , Land TEXT NOT NULL  UNIQUE )");

		stat.executeUpdate("DROP TABLE IF EXISTS tbl_genre;");
		stat.executeUpdate("CREATE TABLE tbl_genre (GenreID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Genre TEXT NOT NULL  UNIQUE )");

		stat.executeUpdate("DROP TABLE IF EXISTS tbl_autor;");
		stat.executeUpdate("CREATE TABLE tbl_autor (AutorId INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Autor TEXT UNIQUE )");

		conn.close();
	}

}