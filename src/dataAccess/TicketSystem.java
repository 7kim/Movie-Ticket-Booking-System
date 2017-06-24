package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.synth.Region;

import dataStructure.ConnectionFactory;
import dataStructure.DBBuilder;
import dataStructure.MovieInfo;
import dataStructure.MovieTicket;
import dataStructure.MovieTicketList;
import dataStructure.SeatNum;
import dataStructure.Users;

public class TicketSystem {
	// SQL Global 變數初始化
			Connection connection = null;
			// query , insert, update statement
			PreparedStatement qStmt  = null;
			PreparedStatement insertStmt = null;
			PreparedStatement updateStmt = null;
			// result set
			ResultSet result = null;
			String movie_name = null;
			// big_romm List, region List
			private List<String> big_rooms = Arrays.asList("武當","華山","少林");
			
			/**
			 * 這個function 用來建立連線
			 * @return Connection : 回傳連線
			 * @throws SQLException : 連線有誤時
			 */
			private Connection getConnection() throws SQLException {
			  try {
			    return ConnectionFactory.getInstance().getConnection();
			  } catch (SQLException e) {
			    System.out.println(e.getMessage());
			  }
			  return null;
			}
			
			/**
			 * 用來關閉DB連線
			 *  @param conn : 欲關閉的連線
			 */
			private void closeConnection(Connection conn) {
				ConnectionFactory.closeConnection(conn);
			}
			
			
			public String bookTicket(int userID,int movieID, String time, String selectedRow, 
					String selectedRegion, int numTickets, boolean consecutive) throws ticketSystemException{
				  if(selectedRegion == null)
					  selectedRegion = "gray";
				  // 獲取電影資訊
				  System.out.println(userID + " " + movieID + " " + time
						  + " " + selectedRow + " " + selectedRegion
						  + " " + numTickets + " " + consecutive);
				  MovieInfo movieInfo = new Movie_DAO().getMovieInfo(movieID);
				  this.movie_name = movieInfo.getMovie();
				  // 獲得使用者資訊
				  Users user = new Movie_DAO().getUser(userID);
				  time = DBBuilder.convertChar(time);
				  if(!validBook(user.get_age(), movieInfo.getClassification())){
					  throw new ticketSystemException("失敗，該電影分級為 " + movieInfo.getClassification() 
					  	+ "，" + user.get_age() + " 歲無法購買");
				  }
				  
				  // 拿剩餘座位陣列 --> [gray,blue,yellow,red]
				 SeatNum remainSeats = new Movie_DAO().getRemainSeats(movieID, time);
				 
				 int rowRemainSeats = new Movie_DAO().getRowRemainSeats(movieID, time, selectedRow, selectedRegion);
				 
				 if(remainSeats.getGray() - numTickets < 0 || rowRemainSeats - numTickets < 0 ){
					 System.out.println("lala");
					 throw new ticketSystemException("失敗， " + movieID  + " 於 " + time + " 座位數量不夠");
				 }
				  try{
				    connection = getConnection();
				    insertStmt = connection.prepareStatement("INSERT INTO Movie_ticket (id,movie_name,movie_time,hall,row,seatNum,region) "
				        + "VALUES (?,?,?,?,?,?,?)");
				    
				    // 這邊要指定座位
				    MovieTicketList tickets = new MovieTicketList();
				    
				    // 隨機選n個位子
				    tickets = selectRandomSeat(time, movieInfo.getHall(), selectedRow, selectedRegion,numTickets);
				    if(tickets != null)
					      updateTicket(movieID,time,remainSeats,selectedRegion,"minus",numTickets);
				    
				    String ticketIDs = "";
				    // insert to DB
				    for(MovieTicket t : tickets){
				    	insertStmt.setString(1, t.getId());
				    	insertStmt.setString(2, movieInfo.getMovie());
					    insertStmt.setString(3, time);
					    insertStmt.setString(4, movieInfo.getHall());
					    insertStmt.setString(5, t.getRow());
					    insertStmt.setString(6, t.getSeatNum());
					    insertStmt.setString(7, selectedRegion);
					    insertStmt.addBatch();
					    ticketIDs += t.getId() + System.lineSeparator();
				    }
				    insertStmt.executeBatch(); 
//				    System.out.println(ticketIDs);
				    
				    // 回傳訂票成功後之字串--> <電影ID>於<播映時間>目前仍有<剩餘空位數>
				    remainSeats = new Movie_DAO().getRemainSeats(movieID, time);
				    String seatString = null;
				    if(big_rooms.contains(movieInfo.getHall())){
				      seatString = "gray:" + remainSeats.getGray()
				        + " blue:" + remainSeats.getBlue()
				        + " yellow:" + remainSeats.getYellow()
				        + " red:" + remainSeats.getRed();
				    } else{
				      seatString = "" + remainSeats.getGray();
				    }
				    return ticketIDs + movieID + " " + movieInfo.getMovie() + " 於 " + time + " 目前仍有 " + seatString;
				  } catch (Exception e){
					  throw new ticketSystemException(e.getMessage() + " -> 無法完成訂票");
				  } finally {
					  closeConnection(connection);
				  }
			}	
			
			private MovieTicketList selectRandomSeat(String time, String hallName, 
					String selectedRow, String selectedRegion, int numTickets) throws ticketSystemException{
				
				String[] seatNum = new String[numTickets];
				int count = 0;
				
				// print selectRow + selectRegion
				System.out.println("指定排數：" + selectedRow + " 指定區域：" + selectedRegion);
				
				try {
					connection = getConnection();
					if(big_rooms.contains(hallName)){ 
						String selectStmt = "SELECT row, seatNum FROM Big_room as a "
			    		 			+ "WHERE NOT EXISTS( "
		    		 					+ "SELECT row, seatNum "
		    		 					+ "FROM Movie_ticket as b "
		    		 					+ "WHERE a.row = b.row "
		    		 					+ "AND a.seatNum = b.seatNum "
		    		 					+ "AND b.movie_time = ? ) "
		    		 				+ "AND a.region = ? ";
					     // 大廳選位子
				    	 if(selectedRow == null){
				    		 qStmt = connection.prepareStatement(selectStmt
				    		            + "ORDER BY RAND() "
				    		            + "LIMIT " + numTickets);
				    		 qStmt.setString(1, time);
				    		 qStmt.setString(2, selectedRegion);
				    	 } else{
				    		 qStmt = connection.prepareStatement(selectStmt
			    		 				+ "AND a.row = ? "
			    		            + "ORDER BY RAND() "
			    		            + "LIMIT " + numTickets);
				    		 qStmt.setString(1, time);
				    		 qStmt.setString(2, selectedRegion);
				    		 qStmt.setString(3, selectedRow);
				    	 }
				        result = qStmt.executeQuery();
				        while(result.next()){
				          String row = result.getString("row");
				          String num = result.getString("seatNum");
				          seatNum[count] = ( row + "_" + num);
				          count++;
				        }
					} else {
						if(selectedRegion != "gray")
							throw new ticketSystemException("該電影上影廳位為小廳，無法選擇區域！");
					      	String selectStmt = "SELECT row, seatNum FROM Small_room as a "
					      			+ "WHERE NOT EXISTS( "
					 					+ "SELECT row, seatNum "
					 					+ "FROM Movie_ticket as b "
					 					+ "WHERE a.row = b.row "
					 					+ "AND a.seatNum = b.seatNum "
					 					+ "AND b.movie_time = ? ) ";
				        //小廳選位子
				    	if(selectedRow == null){
				    		qStmt = connection.prepareStatement(selectStmt
				    	            + "ORDER BY RAND() "
				    	            + "LIMIT " + numTickets);
				    		qStmt.setString(1, time);
				    	    result = qStmt.executeQuery();
				    	} else{
				    		qStmt = connection.prepareStatement(selectStmt
				    					+ "AND a.row = ? "
				    	            + "ORDER BY RAND() "
				    	            + "LIMIT " + numTickets);
				    		qStmt.setString(1, time);
				    		qStmt.setString(2, selectedRow);
				    	    result = qStmt.executeQuery();
				    	}
				        while(result.next()){
				          String row = result.getString("row");
				          String num = result.getString("seatNum");
				          seatNum[count] = ( row + "_"  + num);
				          count++;
				        }
				   	}
					
					// print
					System.out.println("訂票座位如下: ");
					for(String seat : seatNum)
						System.out.println(seat);
					
					// 回傳系統選擇之座位陣列
					MovieTicketList tickets = new MovieTicketList();
					for(String s : seatNum){
						String ticketID = Long.toHexString(Double.doubleToLongBits(Math.random()));
						try {
							tickets.add(ticketID, movie_name, time, hallName, s.split("_")[0], s.split("_")[1],selectedRegion);
						} catch (Exception e) {
							throw new ticketSystemException(e.getMessage());
						}
					}
					return tickets;
				} catch (SQLException e) {
					System.out.println(e.getMessage() + " -> SQL 錯誤");
				} finally {
					closeConnection(connection);
				}
				return null;
			}
			
			
			private void updateTicket(int movieID, String time,
			  	SeatNum remainSeats, String selectedRegion, String operation, int numTickets) {
				
				//System.out.println("更新欄位：" + selectedRegion);
				try {
					connection = getConnection();
				    updateStmt = connection.prepareStatement("UPDATE Seat_num SET "
				        + selectedRegion
				        + " = ? "
				        + "WHERE movie_ID = ? AND time = ?");
				    int newSeatNum = 0;
				    // 更新剩餘座位數
				    if(operation.equals("minus")){
				    	newSeatNum = remainSeats.getSeatNum(selectedRegion) - numTickets;
				    	remainSeats.UpdateSeatNum(selectedRegion, newSeatNum);
				    }
				    else{
				    	newSeatNum = remainSeats.getSeatNum(selectedRegion) + numTickets;
				    	remainSeats.UpdateSeatNum(selectedRegion, newSeatNum);
				    }
				    
//				    System.out.println("更新完座位數 : " 
//				    		+ "gray:" + remainSeats.getGray()
//				    		+ " blue:" + remainSeats.getBlue()
//				    		+ " yellow: " + remainSeats.getYellow()
//				    		+ " red" + remainSeats.getRed());
				
				    updateStmt.setInt(1, newSeatNum);
				    updateStmt.setInt(2, movieID);
				    updateStmt.setString(3, time);
				    updateStmt.executeUpdate();
				  } catch (Exception e) {
				    System.out.println(e.getMessage() + " -> 無法更新座位數table");
				  }
			}
			
			/**
			 * 這個function用來判斷使用者可不可以訂票
			 * @param userAge : 使用者年齡
			 * @param classification : 欲看電影之分級
			 * @return boolean : 表示可不可以定
			 */
			private boolean validBook(int userAge, String classification){
				// 可不可以買用boolean來表示
				boolean valid = false;
				switch(classification){
					case "普遍":
						valid = true;
						break;
					case "保護":
						valid = (userAge < 6) ? false : true;
						break;
					case "輔導":
						valid = (userAge < 12) ? false : true;
						break;
					case "限制":
						valid = (userAge < 18) ? false : true;
						break;
				}
				return valid;
			}
			
			public String cancelTicket(String ticketID) throws ticketSystemException{
				// 現在系統時間
				LocalTime currentTime = LocalTime.of(14,0);
//				LocalTime currentTime = LocalTime.now();
				
				// 取得訂票資訊 -> [id,名稱,上映時間,廳,排,座位號碼,區域]
				MovieTicket ticketInfo = new Movie_DAO().getMovieTicket(ticketID);
				if(ticketInfo == null){
					throw new ticketSystemException("退票失敗，此電影票ID不存在");
				}
				else {
					// 取得欲退票之預訂電影資訊
					
					MovieInfo movie_info = new Movie_DAO().getMovieInfo(convertMovieName2ID(ticketInfo.getMovie_name()));
					
					// 上映時間
					LocalTime showTime = ticketInfo.getTime().getTime();
					
					System.out.println(currentTime);
					System.out.println(showTime);
					
					// 計算距離播映時間還有多久
					long minutesBetween = ChronoUnit.MINUTES.between(currentTime,showTime);
					System.out.println("離播映還有 : " + minutesBetween + " 分");
					if(minutesBetween < 20)
						throw new ticketSystemException("退票失敗，退票需於開場時間前20分鐘前");
					else{
						return removeTicket(ticketID,ticketInfo,movie_info);
					}
				}
			}
			
			/**
			 * 這個function用來轉換電影名稱-> 電影ID
			 * @param movie_name : 輸入之電影名稱
			 * @return int : 電影ID
			 */
			public int convertMovieName2ID(String movie_name){
				try {
					connection = getConnection();
				    qStmt = connection.prepareStatement("SELECT * FROM Movie_info "
				        + "WHERE movie = ?");
				    qStmt.setString(1, movie_name);
				    result = qStmt.executeQuery();
				    if(result.next())
				    	return result.getInt("id");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return 0;
			}
			
			public String removeTicket(String ticketID, MovieTicket ticketInfo, MovieInfo cancelMovieInfo){
			  try {
			    connection = getConnection();
			    PreparedStatement dStmt = connection.prepareStatement("DELETE FROM Movie_ticket "
			        + "WHERE id = ?");
			    dStmt.setString(1, ticketID);
			    int effectRows = dStmt.executeUpdate();
			    if(effectRows != 0){
			    	String region = ticketInfo.getRegion();
			    	System.out.println("region" + region);
			    	// 更新座位區數量
			    	SeatNum remainSeats = new Movie_DAO().getRemainSeats(cancelMovieInfo.getId(), 
			    			ticketInfo.getTime().getTime().toString());
			    	System.out.println("退票前座位數 : " + remainSeats.getSeatNum(region));
			    	updateTicket(cancelMovieInfo.getId(), ticketInfo.getTime().getTime().toString(), 
			    			remainSeats , region, "add", 1);
			    	// 退票成功
			    	return  "退票成功，全額退款";
			    }
			  } catch (Exception e) {
			    System.out.println(e.getMessage() + "-> Cannot remove ticket from DB");
			  } finally {
			    closeConnection(connection);
			  }
			  return null;
			}
}
