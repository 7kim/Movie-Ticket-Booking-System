package dataStructure;


import java.awt.print.Printable;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DBBuilder {
	// 設定DB變數初始值
	Connection connection = null;
	PreparedStatement createTable = null;
	PreparedStatement insertStmt = null;
	
	private String user_json = "/Users/Brian/Desktop/Java/jsonTest/json/user.json";
	private String movie_json = "/Users/Brian/Desktop/Java/jsonTest/json/movie_info.json";
	private String big_room = "/Users/Brian/Desktop/Java/jsonTest/json/big_room.json";
	private String small_room = "/Users/Brian/Desktop/Java/jsonTest/json/small_room.json";
	
	// big_romm List
	private List<String> big_rooms = Arrays.asList("武當","華山","少林");

	/**
	 * 這個function會建立與DB的連線
	 * return Connection : 與DB的連線
	 * throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		return ConnectionFactory.getInstance().getConnection();
	}
	
	/**
	 * 用來關閉DB連線
	 * @param conn : 欲關閉的連線
	 */
	private void closeConnection(Connection conn) {
		ConnectionFactory.closeConnection(conn);
 	}
	
	/**
	 * 這個function是用來parse JSON
	 * @param filePath : input 檔案路徑
	 * @return JSONArray : parse 完的檔案
	 */
	private JSONArray parseJSON(String filePath) {
		try {
			// read JSON file
			FileReader reader = new FileReader(filePath);
			
			// parse file into JSONArray
			JSONParser parser = new JSONParser();
			return (JSONArray) parser.parse(reader);
			
		} catch (Exception e) {
			System.out.println(e + " -> parse JSON fail!");
			return null;
		}
	}
	
	/**
	 * 這個function 會建立 Users 的 table
	 */
	private void createUserTable() {
		try {
			// SQL code to create Users table
			connection = getConnection();
			createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Users("
					+ "id int NOT NULL AUTO_INCREMENT,"
					+ "name varchar(45),"
					+ "age int NOT NULL,"
					+ "PRIMARY KEY(id))");
			createTable.executeUpdate();
			
			// insert users info into table
			String name;
			long age;
			insertStmt = connection.prepareStatement("INSERT INTO Users(name,age)"
					+ "VALUES (?,?)");
			JSONArray userList = parseJSON(user_json);
			for(Object user : userList){
				JSONObject currentUser = (JSONObject) user;
				name = (String) currentUser.get("name");
				age = (long) currentUser.get("age");
				
				// set placeholder in SQL code
				insertStmt.setString(1, name);
				insertStmt.setLong(2, age);
				insertStmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " -> Cannot Create User Table");
		} 
	}
	
	/**
	 * 這個 function 會建立 Movie_info 的 table
	 */	
	private void createMovieTable() {
		try {
			// SQL code to create Movie_info table
			connection = getConnection();
			createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Movie_Info("
					+ "id int NOT NULL AUTO_INCREMENT,"
					+ "movie varchar(100),"
					+ "classification varchar(2),"
					+ "descri varchar(255),"
					+ "infor int,"
					+ "score Double,"
					+ "time_1 TIME NULL,"
					+ "time_2 TIME NULL,"
					+ "time_3 TIME NULL,"
					+ "time_4 TIME NULL,"
					+ "time_5 TIME NULL,"
					+ "time_6 TIME NULL,"
					+ "time_7 TIME NULL,"
					+ "hall varchar(2),"
					+ "PRIMARY KEY(id))");
			createTable.executeUpdate();
			
			// insert movie_info into table
			String movieName;
			String classification;
			String description;
			int info;
			double score;
			String[] timeList = new String[7];
			String hall;
			insertStmt = connection.prepareStatement("INSERT INTO Movie_Info(movie,classification,descri,infor,score"
					+ ",time_1,time_2,time_3,time_4,time_5,time_6,time_7,hall)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			JSONArray movieList = parseJSON(movie_json);
			System.out.println(movieList.size());
			for(Object movie : movieList){
				JSONObject currentMovie = (JSONObject) movie;
				// method trim() is used to remove whitespace at the beginning and end of string
				movieName = ((String) currentMovie.get("movie")).trim();
				classification = (String) currentMovie.get("classification");
				description = (String) currentMovie.get("descri");
				String tmp_info = convertChar((String) currentMovie.get("infor"));
				int indexStart = tmp_info.indexOf(":") + 1;
				int indexEnd = tmp_info.indexOf("分");
				info = Integer.valueOf(tmp_info.substring(indexStart, indexEnd));
				
				// remove the part of score after "/" character
				String tmpScore = ((String) currentMovie.get("score")).trim();
				int indexOfEnd = tmpScore.indexOf("/");
				tmpScore = tmpScore.substring(0, indexOfEnd);
				score = Double.parseDouble(tmpScore);
				String[] tmp_timeList = convertChar(((String) currentMovie.get("time")).trim()).split("、");
				for (int i = 0 ; i < tmp_timeList.length; i++){
					timeList[i] = tmp_timeList[i];
				}
				
				for(int i = tmp_timeList.length; i < timeList.length; i++){
					timeList[i] = null;
				}
				
				hall = ((String) currentMovie.get("hall")).trim();
				insertStmt.setString(1, movieName);
				insertStmt.setString(2, classification);
				insertStmt.setString(3, description);
				insertStmt.setInt(4, info);
				insertStmt.setDouble(5, score);
				insertStmt.setString(6, timeList[0]);
				insertStmt.setString(7, timeList[1]);
				insertStmt.setString(8, timeList[2]);
				insertStmt.setString(9, timeList[3]);
				insertStmt.setString(10, timeList[4]);
				insertStmt.setString(11, timeList[5]);
				insertStmt.setString(12, timeList[6]);
				insertStmt.setString(13, hall);
				insertStmt.addBatch();
			}
			// insert into DB
			insertStmt.executeBatch();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " -> Cannot create Movie_info table");
		}
	}
	
	/**
	 * 這個function會建立 Big_room 的 table
	 */
	private void createBigRoomTable() {
		try {
			// SQL code to create big_room table
			connection = getConnection();
			createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Big_room("
					+ "id varchar(24),"
					+ "row varchar(1),"
					+ "seatNum varchar(2),"
					+ "region varchar(10),"
					+ "PRIMARY KEY(id))");
			createTable.executeUpdate();
			
			// insert big_room seat info into table
			String id;
			String row;
			String seatNum;
			String occupied_hall1;
			String occupied_hall2;
			String occupied_hall3;
			String region;
			int movie_num_hall1 = 7;
			int movie_num_hall2 = 6;
			int movie_num_hall3 = 4;
			JSONArray seatList = parseJSON(big_room);
			insertStmt = connection.prepareStatement("INSERT INTO Big_room(id,row,seatNum,region)"
					+ "VALUES (?,?,?,?)");
			for (Object seat : seatList){
				JSONObject currentSeat = (JSONObject) seat;
				id = (String) currentSeat.get("id");
				row = (String) currentSeat.get("row");
				long num = (long) currentSeat.get("seatNum");
				seatNum = (String) (num < 10 ? "0" : "") + num;
				// initialize occupied strings
				if(currentSeat.get("occupied") != null){
					region = (String) currentSeat.get("region");
				} else{
					region = "blank";
				}
				// insert into DB
				insertStmt.setString(1, id);
				insertStmt.setString(2, row);
				insertStmt.setString(3, seatNum);
				insertStmt.setString(4, region);
				insertStmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println(e + " -> cannot create Big_room table");
		}
	}

	/**
	 * 這個 function 會建立 Small_room 的 table
	 * param seatList : 傳入 small_room.json 的座位表
	 */
	private void createSmallRoomTable() {
		try {
			// SQL code to create small_room table
			connection = getConnection();
			createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Small_room("
					+ "id varchar(24),"
					+ "row varchar(1),"
					+ "seatNum varchar(2),"
					+ "PRIMARY KEY(id))");
			
			createTable.executeUpdate();
			
			// insert small_room info to table
			String id;
			String row;
			String seatNum;
			String occupied_hall1;
			String occupied_hall2;
			int movie_num = 5;
			JSONArray seatList = parseJSON(small_room);
			insertStmt = connection.prepareStatement("INSERT INTO Small_room(id,row,seatNum)"
					+ "VALUES (?,?,?)");
			for (Object seat : seatList){
				JSONObject currentSeat = (JSONObject) seat;
				id = (String) currentSeat.get("id");
				row = (String) currentSeat.get("row");
				long num = (long) currentSeat.get("seatNum");
				seatNum = (String) (num < 10 ? "0" : "") + num;
				
				// insert into DB
				insertStmt.setString(1, id);
				insertStmt.setString(2, row);
				insertStmt.setString(3, seatNum);
				insertStmt.executeUpdate();
			}			
		} catch (Exception e) {
			System.out.println(e +" -> cannot create Small_room table");
		}
	}
	
	/**
	 *  這個 function 會建立訂票成功之電影票的table
	 */
	private void createTicketTable() {
		try {
			// SQL code to create Movie_tickets table
			connection = getConnection();
			createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Movie_ticket("
					+ "id varchar(255),"
					+ "movie_name varchar(255),"
					+ "movie_time TIME,"
					+ "hall varchar(2),"
					+ "row varchar(1), "
					+ "seatNum varchar(2),"
					+ "region varchar(10),"
					+ "PRIMARY KEY(id))");
			createTable.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " -> Cannot create Movie_tickets table");
		} 
	}
	/**
	 * 這個funciton 是用來建立一個table來存每個廳
	 * 在放映時刻有多少座位
	 */
	private void createSeatNumTable() {
		try {
			// SQL code to create seat Num table
			connection = getConnection();
			createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Seat_num("
					+ "seat_ID int NOT NULL AUTO_INCREMENT,"
					+ "movie_ID int,"
					+ "time TIME,"
					+ "gray int,"
					+ "blue int,"
					+ "yellow int,"
					+ "red int,"
					+ "PRIMARY KEY (seat_ID))");
			createTable.executeUpdate();
			
			
			// initialize seat_num table
			insertStmt = connection.prepareStatement("INSERT INTO Seat_num(movie_ID,time,gray,blue,yellow,red)"
					+ "VALUES (?,?,?,?,?,?)");
			String[] timeList = null;
			String hall = null;
			JSONArray movieList = parseJSON(movie_json);
			int movieCount = 1;
			for(Object movie : movieList){
				JSONObject currentMovie = (JSONObject) movie;
				timeList = convertChar(((String) currentMovie.get("time")).trim()).split("、");
				hall = ((String) currentMovie.get("hall")).trim();
				
				// 座位數要看聽
				int gray;
				int blue = 0;
				int yellow = 0;
				int red = 0;
				if(big_rooms.contains(hall)){
					gray = 277;
					blue = 44;
					yellow = 62;
					red = 24;
				} else {
					gray = 144;
				}
				for (int i = 0; i < timeList.length; i++){
					insertStmt.setInt(1, movieCount);
					insertStmt.setString(2, timeList[i]);
					insertStmt.setInt(3, gray);
					insertStmt.setInt(4,blue);
					insertStmt.setInt(5, yellow);
					insertStmt.setInt(6, red);
					insertStmt.addBatch();
				}
				movieCount++;
			}
			insertStmt.executeBatch();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " - > cannot create seat_num table");
		} 
	}
	
	
	/**
	 * 這個function 是用來把全形字轉成半形
	 * @param str : 輸入之字串
	 * @return String : convert 完成的字串
	 */
	public static String convertChar(String str){
		for(char c : str.toCharArray()){
			str = str.replaceAll(" ", " ");
			if((int) c >= 65281 && c <= 65374)
				str = str.replace(c, (char) (((int) c) - 65248));
		}
		return str;
	}
	
	/** 
	 *  這邊設一個public 的 method 來建立DB
	 */
	public void createDB(){
		try {
//			createUserTable();
//			createMovieTable();
//			createBigRoomTable();
//			createSmallRoomTable();
			createTicketTable();
			createSeatNumTable(); 
		} finally {
			closeConnection(connection);
		}
	}
}
