package dataStructure;

import java.util.ArrayList;
import java.util.Collection;

public class MovieTicketList extends ArrayList<MovieTicket>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6092961869599973569L;


	public void add(String id, String movie_name, String time, String hall, String row, String seat, String region) throws Exception {
		super.add(new MovieTicket(id, movie_name, time, hall, row, seat, region));
	}

}
