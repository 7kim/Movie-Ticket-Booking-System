package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;
import dataStructure.*;

/**
 * 除了訂票退票外，這個class存放所有和MySQL連結的function，包含取得資料庫裡的table，以及查詢的function
 *
 */
public class Movie_DAO
{
	// SQL Global 變數初始化
	Connection connection = null;
	// query , insert, update statement
	PreparedStatement qStmt = null;
	PreparedStatement insertStmt = null;
	PreparedStatement updateStmt = null;
	// result set
	ResultSet result = null;

	// big_romm List, region List
	private List<String> big_rooms = Arrays.asList("武當", "華山", "少林");
	private List<String> region = Arrays.asList("gray", "blue", "yellow", "red");

	/**
	 * 這個function 用來建立連線
	 * 
	 * @return Connection : 回傳連線
	 * @throws SQLException
	 *             : 連線有誤時
	 */
	private Connection getConnection() throws SQLException
	{
		try
		{
			return ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 用來關閉DB連線
	 * 
	 * @param conn
	 *            : 欲關閉的連線
	 */
	private void closeConnection(Connection conn)
	{
		ConnectionFactory.closeConnection(conn);
	}

	/**
	 * 這個function用來拿User的Info
	 * 
	 * @param userID
	 *            : 輸入User ID
	 * @return Users : 回傳一個Users的object,裡面包含user 的資訊
	 * @throws ticketSystemException
	 *             : 如果撈不到使用者資料時
	 */
	public Users getUser(int userID) throws ticketSystemException
	{
		// 初始化一個Users object
		Users user = null;
		try
		{
			connection = getConnection();
			qStmt = connection.prepareStatement("SELECT * FROM Users " + "WHERE id = ?");
			qStmt.setInt(1, userID);
			result = qStmt.executeQuery();
			if (result.next())
				// 從SQL中獲得user 資料建構Users Object
				user = new Users(result.getInt("id"), result.getString("name"), result.getInt("age"));
		} catch (SQLException e)
		{
			throw new ticketSystemException(e.getMessage() + "-> 無法獲得該使用者資訊");
		} finally
		{
			closeConnection(connection);
		}
		return user;
	}

	/**
	 * 這個function用來回傳MovieInfo Object
	 * 
	 * @param movieID
	 *            : 輸入之movieID
	 * @return MovieInfo : 電影資料的object
	 * @throws ticketSystemException
	 *             : 如果沒有這比電影資料
	 */
	public MovieInfo getMovieInfo(int movieID) throws ticketSystemException
	{
		MovieInfo movie_info = null;
		MovieTimeList timeList = new MovieTimeList();
		try
		{
			connection = getConnection();
			qStmt = connection.prepareStatement("SELECT * FROM Movie_info " + "WHERE id = ?");
			qStmt.setInt(1, movieID);
			result = qStmt.executeQuery();
			if (result.next())
			{
				String movie_name = result.getString("movie");
				String classification = result.getString("classification");
				String description = result.getString("descri");
				int infor = result.getInt("infor");
				Double score = result.getDouble("score");

				// 把七個欄位都先拿出來，不是null的再加進去MovieTimeList
				for (int i = 1; i < 7; i++)
				{
					String tmp_time = result.getString("time_" + i);
					if (tmp_time != null)
						timeList.add(tmp_time);
				}
				String hall = result.getString("hall");

				// new 一個MovieInfo 物件
				movie_info = new MovieInfo(movieID, movie_name, classification, description, infor, score, timeList,
						hall);
			}
		} catch (Exception e)
		{
			throw new ticketSystemException(e.getMessage() + "cannot get Movie Info");
		} finally
		{
			closeConnection(connection);
		}
		return movie_info;
	}

	/**
	 * 這個function 用來回傳對應場次的剩餘座位
	 * 
	 * @param movieID
	 *            : 輸入的Movie ID
	 * @param time
	 *            : 輸入的場次時間
	 * @return SeatNum : 座位數物件
	 * @throws ticketSystemException
	 *             : 如果查詢不到座位數
	 */
	public SeatNum getRemainSeats(int movieID, String time) throws ticketSystemException
	{
		SeatNum tmp_SeatNum = null;
		try
		{
			connection = getConnection();
			time = DBBuilder.convertChar(time);
			System.out.println("movieID:" + movieID + " " + "time: " + time);
			qStmt = connection.prepareStatement("SELECT * FROM Seat_Num "
			+ "WHERE movie_ID = ? AND time = ? ");
			qStmt.setInt(1, movieID);
			qStmt.setString(2, time);
			result = qStmt.executeQuery();
			if (result.next())
			{
				tmp_SeatNum = new SeatNum(result.getInt("gray"), result.getInt("blue"), result.getInt("yellow"),
						result.getInt("red"));
			}
		} catch (SQLException e)
		{
			throw new ticketSystemException(e.getMessage() + " -> 無法獲該電影剩餘座位資訊");
		}
		return tmp_SeatNum;
	}

	/**
	 * 這個function 是用來回傳特殊指定條件的座位數
	 * 
	 * @param movieID
	 *            : 輸入的Movie ID
	 * @param time
	 *            : 輸入的場次時間
	 * @param selectedRow
	 *            : 指定排數
	 * @param selectedRegion
	 *            : 指定區域
	 * @return int : 指定條件下的座位數
	 * @throws ticketSystemException
	 *             : 如果查詢不到座位數
	 */
	public int getRowRemainSeats(int movieID, String time, String selectedRow, String selectedRegion)
			throws ticketSystemException
	{
		int originalRowSeatNum = 0;
		int bookedRowSeatNum = 0;
		String room = (movieID >= 1 && movieID <= 3) ? "Big_room" : "Small_room";
		System.out.println("上映廳位:" + room);
		String whereStmt1 = (room.equals("Big_room")) ? " WHERE region = ?" : "";
		String whereStmt2 = (room.equals("Big_room")) ? " WHERE row = ?  AND region = ?" : " WHERE row = ?";
		
		try {

			connection = getConnection();
			// 取得原始該排座位數
			if (selectedRow == null)
			{
				qStmt = connection.prepareStatement("SELECT COUNT(*) FROM " + room + whereStmt1);
				if (room.equals("Big_room"))
					qStmt.setString(1, selectedRegion);
			} else
			{
				qStmt = connection.prepareStatement("SELECT COUNT(*) FROM " + room + whereStmt2);
				qStmt.setString(1, selectedRow);
				if (room.equals("Big_room"))
					qStmt.setString(2, selectedRegion);
			}

			result = qStmt.executeQuery();
			if (result.next())
				originalRowSeatNum = result.getInt("COUNT(*)");

			// 取得訂票紀錄該排、該區域座位數
			if (selectedRow == null)
			{
				qStmt = connection.prepareStatement("SELECT COUNT(*) FROM Movie_ticket " 
						+ "WHERE region = ?");
				qStmt.setString(1, selectedRegion);
//				qStmt.setString(2, time);
			} else
			{
				qStmt = connection
						.prepareStatement("SELECT COUNT(*) FROM Movie_ticket " 
						+ "WHERE row = ? AND region = ? ");
				qStmt.setString(1, selectedRow);
				qStmt.setString(2, selectedRegion);
//				qStmt.setString(3, time);
			}

			result = qStmt.executeQuery();
			if (result.next())
				bookedRowSeatNum = result.getInt("COUNT(*)");

			// print 指定條件剩餘座位數
			System.out.println("指定條件剩餘座位數 ：" + (originalRowSeatNum - bookedRowSeatNum));
			return originalRowSeatNum - bookedRowSeatNum;
		} catch (Exception e)
		{
			throw new ticketSystemException(e.getMessage() + " -> 無法獲得取得單排剩餘座位數");
		} finally
		{
			closeConnection(connection);
		}
	}	

	/**
	 *  這個function 是用來查詢電影評價，輸入網友評分，列出高於此分數的所有電影
	 *  
	 * @param score
	 *            : 為一double，輸入的網友評價分數
	 * @return ScoreSearchList
	 *            : 回傳ScoreSearchList的物件，存有包含所有大於該分數的電影(電影為ScoreSearch的物件)
	 */
	public ScoreSearchList getScoreMovie(double score)
	{
		ScoreSearchList list = new ScoreSearchList();
		try
		{
			connection = getConnection();
			qStmt = connection.prepareStatement("SELECT id, movie FROM movie_info WHERE score > ?");
			qStmt.setDouble(1, score);
			result = qStmt.executeQuery();

			while (result.next())
			{
				list.add(result.getInt("id"), result.getString("movie"));
			}

		} catch (SQLException e)
		{
			System.out.println(e.getMessage() + "-> cannot get any movie");
		}
		return list;
	}

	/**
	 * 這個function 是用來查詢電影票，輸入電影票ID，回傳該張電影票訊息
	 * 
	 * @param movieTicketID
	 *            : 輸入電影票ID，ID為一字串，於訂票成功時產生
	 * @return MovieTicket
	 *            : 回傳MovieTicket物件，此物件存有該電影票ID裡的資訊(名稱、時間、廳位、座位)
	 */
	public MovieTicket getMovieTicket(String movieTicketID)
	{
		MovieTicket ticket = null;
		try
		{
			connection = getConnection();
			qStmt = connection.prepareStatement("SELECT * FROM Movie_ticket " + "WHERE id = ?");
			qStmt.setString(1, movieTicketID);
			result = qStmt.executeQuery();
			
			if (result.next())
			{
			ticket = new MovieTicket(result.getString("id"), result.getString("movie_name"),
					result.getString("movie_Time"), result.getString("hall"), result.getString("row"),
					result.getString("seatNum"), result.getString("region"));
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage() );
		}
		return ticket;
	}

	
	/**
	 * 這個function 是用來查詢電影資訊，輸入電影ID，回傳指定電影資訊
	 * 
	 * @param movieID
	 *            : 輸入電影票ID，ID為一整數
	 * @return MovieInfo
	 *            : 回傳MovieInfo物件，存放指定的電影資訊(名稱、分級、場次、廳位)
	 */
	public MovieInfo getMovieInfo_output(int movieID)
	{
		MovieInfo info = null;
		MovieTimeList timeList = new MovieTimeList();
		try
		{
			connection = getConnection();
			qStmt = connection.prepareStatement("SELECT * FROM Movie_info " + "WHERE id = ?");
			qStmt.setInt(1, movieID);
			result = qStmt.executeQuery();

			if (result.next())
			{
				for (int i = 1; i < 7; i++)
				{
					String tmp_time = result.getString("time_" + i);
					if (tmp_time != null)
						timeList.add(tmp_time);
				}
				info = new MovieInfo(result.getString("movie"), result.getString("classification"), timeList,
						result.getString("hall"));
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage() + "cannot get Movie Info");
		}
		return info;
	}

	/**
	 *  這個function 是用來查詢某段期間、某區間片長，還有指定票數的所有電影及其場次
	 *  
	 * @param seatNum
	 *　　　　　　　　　 :　輸入指定座位數，為一int
	 * @param early
     *　　　　　　　　　 :　輸入最早能看電影的時間，為一String
	 * @param late
     *　　　　　　　　　 :　輸入最晚能看電影的時間，為一String
	 * @param longest
     *　　　　　　　　　 :　輸入想看的最長片長，為一int
	 * @param shortest
     *　　　　　　　　　 :　輸入想看的最短片長，為一int
	 * @return MoviePlayList
	 *            : 回傳MoviePlayList的物件，存有所有符合上述條件的電影及場次(為MoviePlay的物件)
	 * @throws Exception
	 *            : 如果輸入不正確的時間
	 */
	public MoviePlayList getTimeInfor(int seatNum, String early, String late, int longest, int shortest) throws Exception
	{
		MoviePlayList list = new MoviePlayList();
		MovieTime earlyTime = new MovieTime(early);
		MovieTime lateTime = new MovieTime(late);
		MoviePlayList listNew = new MoviePlayList(); 
		try
		{
			connection = getConnection();
			qStmt = connection.prepareStatement("select id, movie, time_1,time_2, time_3, time_4, time_5, time_6, time_7 "
					                           + " from Movie_Info "
					                           + " where infor > ? && infor < ?");
			qStmt.setInt(1, shortest);
			qStmt.setInt(2, longest);
			result = qStmt.executeQuery();

			while (result.next())
			{	
				MovieTimeList timeList = new MovieTimeList();
				for (int i = 1; i < 7; i++)
				{
					String tmp_time = result.getString("time_" + i);
					if (tmp_time != null)
						timeList.add(tmp_time);
				}
				list.add(result.getInt("id"), result.getString("movie"), timeList);
			}

		} catch (SQLException e)
		{
			System.out.println(e.getMessage() + "-> cannot get any movie");
		}
		for (MoviePlay e : list)
		{

			MovieTimeList list2 = new MovieTimeList();
			int movieID = e.getId();
			for (MovieTime element : e.getTime())
			{
				SeatNum remainSeat = getRemainSeats(movieID,element.toString());
				if (element.isBetween(earlyTime, lateTime) && remainSeat.getTotal() >= seatNum){
					list2.add(element);
				}		
			}
			e.setTime(list2);
			if(list2.size() != 0)
				listNew.add(e);
		}
		 
		return listNew;
	}
}
