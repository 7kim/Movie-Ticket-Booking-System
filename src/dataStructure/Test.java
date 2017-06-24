package dataStructure;

import java.time.LocalTime;

import dataAccess.*;



public class Test {
	public static void main(String[] args) {
		DBBuilder builder = new DBBuilder();
		try {
			builder.createDB();
		} catch (Exception e) {
			System.out.println(e);
		}
		TicketSystem bookSystem = new TicketSystem();
//		Movie_DAO system = new Movie_DAO();
		try {
//			ScoreSearchList list = system.getScoreMovie(9);
//			for(ScoreSearch s : list)
////				System.out.println(s.getID() + " " + s.getMovie());
//			System.out.println(list);
//			System.out.println(bookSystem.bookTicket(13, 6, "13:10", null, "red", 2, false));
//			bookSystem.cancelTicket("123");
//			MoviePlayList list = system.getTimeInfor(2, "20:00", "22:00", 180, 0);
//			System.out.println(list);
//			MovieTicket ticket = system.getMovieTicket("3f9986566f6052e0");
//			System.out.println(ticket.getMovie_name() + System.lineSeparator()
//			 	+ ticket.getTime() + System.lineSeparator()
//			 	+ ticket.getHall() + System.lineSeparator()
//			 	+ ticket.getRow() + "_" + ticket.getSeatNum() + System.lineSeparator());
//			MovieInfo info = system.getMovieInfo_output(3);
//			System.out.println(info.getMovie());
//			System.out.println(info.getClassification());
//			System.out.println(info.getTime());
//			System.out.println(info.getHall());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
