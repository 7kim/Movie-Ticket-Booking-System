package dataStructure;

public class MovieTicket
{
	private String id;
	private String movie_name;
	private MovieTime time;
	private String hall;
	private String row;
	private String seatNum;
	private String region;
	private String seat;
	
	public MovieTicket(String id, String movie_name, String time, 
			String hall, String row, String seat, String region) 
	{
		this.id = id;
		this.movie_name = movie_name;
		this.time = new MovieTime(time);
		this.hall = hall;
		this.row = row;
		this.seatNum = seat;
		this.region = region;
	
	}
	
	public String getId()
	{	
		System.out.println("~");
		return this.id;
	}

	public String getMovie_name()
	{
		return this.movie_name;
	}

	public MovieTime getTime()
	{
		return this.time;
	}

	public String getHall()
	{
		return this.hall;
	}
	
	public String getRow()
	{
		return row;
	}

	public String getSeatNum()
	{
		return seatNum;
	}
	
	public String getRegion()
	{
		return region;
	}
}
