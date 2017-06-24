package GUI;

import java.util.Arrays;
import dataAccess.*;
import dataStructure.*;

public class TheaterGUIController {
	
	Theater t = new Theater();
	TicketSystem systemA = new TicketSystem();
	Movie_DAO systemB = new Movie_DAO();
	
	public TheaterGUIController() {
		
	}

	public String[] getAllUserID() {
		String[] userID = new String[t.userID.length];
		for (int i = 0; i < t.userID.length; i++) {
			userID[i] = Integer.toString(t.userID[i]);	
		}
		return userID;
	}
	
	public String[] getAllMovieNames() {
		return Movie.getAllMovieNames();
	}
	
	public String[] getMovieTime(int movieID) {
		String movieTimeInfo = Movie.getMovieTime(movieID);
		String[] movieTime = movieTimeInfo.split("、");
		return movieTime;
	}
	
	public String getMovieHall(int movieID) {
		String movieHall = Movie.getMovieHall(movieID);
		return movieHall;
	}
	
	public String getMovieHallSize(int movieID) {
		String movieHallSize = t.getHallSize(getMovieHall(movieID)).toString();
		return movieHallSize;
	}
	
	public String[] getMovieHallAllRows(int movieID) {
		String[] movieHallAllRows = t.getHallAllRows(t.getHallSize(getMovieHall(movieID)));
		return movieHallAllRows;
	}
	
	public String[] getAllAreas() {
		String[] allHallAreas = t.getAllAreas();
		return allHallAreas;
	}
	
	public String[] getAreaRows(String area) {
		String[] areaRows = t.getAreaRows(area);
		return areaRows;
	}
	
	public String book(int userID, int movieID, String movieTime, boolean seatCont,
			boolean seatRow, String row, boolean seatArea, String area, String ticketNumber) throws ticketSystemException {
		userID = userID + 1;
		movieID = movieID + 1;
		switch (area) {
		case "精華":
			area = "red";
			break;
		case "最佳":
			area = "yellow";
			break;
		case "次佳":
			area = "blue";
			break;
		default:
			area = "gray";
			break;
		}
		int ticketN = Integer.parseInt(ticketNumber);
		if (seatRow == true && seatArea == true) {
			return systemA.bookTicket(userID, movieID, movieTime, row, area, ticketN, seatCont);
		} else if (seatRow == true && seatArea == false) {
			return systemA.bookTicket(userID, movieID, movieTime, row, null, ticketN, seatCont);
		} else if (seatRow == false && seatArea == true) {
			return systemA.bookTicket(userID, movieID, movieTime, null, area, ticketN, seatCont);
		} else {
			return systemA.bookTicket(userID, movieID, movieTime, null, null, ticketN, seatCont);
		}
	}
	
	public String cancel(String ticketID) throws ticketSystemException{
		return systemA.cancelTicket(ticketID);
	}
	
//	private String[] getTicketInfo(String ticketIDString) {
//		int ticketID = Integer.parseInt(ticketIDString);
//		String movieName = systemB.getMovieTicket(ticketID).getMovie_name();
//		String movieTime = systemB.getMovieTicket(ticketID).getTime().toString();
//		String movieHall = systemB.getMovieTicket(ticketID).getHall();
//		String seatRow = systemB.getMovieTicket(ticketID).getRow();
//		String seatNum = systemB.getMovieTicket(ticketID).getSeatNum();
//		String seat = seatRow + "_" + seatNum;
//		String[] ticketInfo = new String[4];
//		ticketInfo[0] = movieName;
//		ticketInfo[1] = movieTime;
//		ticketInfo[2] = movieHall;
//		ticketInfo[3] = seat;
//		return ticketInfo; 
//	}
	
	public String getTicketMovieName(String ticketIDString) {
		String movieName = systemB.getMovieTicket(ticketIDString).getMovie_name();
		return movieName; 
	}
	
	public String getTicketMovieTime(String ticketIDString) {
		String movieTime = systemB.getMovieTicket(ticketIDString).getTime().toString();
		return movieTime;
	}
	
	public String getTicketMovieHall(String ticketIDString) {
		String movieHall = systemB.getMovieTicket(ticketIDString).getHall();
		return movieHall; 
	}
	
	public String getTicketSeat(String ticketIDString) {
		String seatRow = systemB.getMovieTicket(ticketIDString).getRow();
		String seatNum = systemB.getMovieTicket(ticketIDString).getSeatNum();
		String seat = seatRow + "_" + seatNum;
		return seat; 
	}
	
	public String getScoredMovies(String movieScore) {
		double score = Double.parseDouble(movieScore);
		ScoreSearchList list = systemB.getScoreMovie(score);
		String scoredMovies = "";
		for (ScoreSearch e: list)
		{
			scoredMovies += e.getMovie() + "\n";
		}
		return scoredMovies;
	}
	
//	public String[] getMovieInfo(int movieID) {
//		movieID = movieID + 1;
//		String movieRate = systemB.getMovieInfo_output(movieID).getClassification();
//		String movieTime = systemB.getMovieInfo_output(movieID).getTime().toString();
//		String movieHall = systemB.getMovieInfo_output(movieID).getHall();
//		String[] movieInfo = new String[3];
//		movieInfo[0] = movieRate;
//		movieInfo[1] = movieTime;
//		movieInfo[2] = movieHall;
//		return movieInfo;
//	}
	
	public String getSearchMovieRate(int movieID) {
		movieID = movieID + 1;
		String movieRate = systemB.getMovieInfo_output(movieID).getClassification();
		return movieRate;
	}
	
	public String getSearchMovieTime(int movieID) {
		movieID = movieID + 1;
		String movieTime = systemB.getMovieInfo_output(movieID).getTime().toString();
		return movieTime;
	}
	
	public String getSearchMovieHall(int movieID) {
		movieID = movieID + 1;
		String movieHall = systemB.getMovieInfo_output(movieID).getHall();
		return movieHall;
	}
	
	public String getSeatableMovies(String ticketNumber, String earlyTime, String lateTime, String shortestTime, String longestTime) throws Exception {
		int ticketN = Integer.parseInt(ticketNumber);
		int shortest = Integer.parseInt(shortestTime); 
		int longest =  Integer.parseInt(longestTime);
		MoviePlayList list = systemB.getTimeInfor(ticketN, earlyTime, lateTime, longest, shortest);
		String seatableMovies = "";
		for (MoviePlay e: list)
		{
			seatableMovies += e.toString() + System.lineSeparator();
		}
		return seatableMovies;
	}
	
}
