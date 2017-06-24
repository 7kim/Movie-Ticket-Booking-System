package dataStructure;

public class MoviePlay
{
	private int id;
	private String movie_name;
	private MovieTimeList time;
	



	public MoviePlay(int id, String movie_name, MovieTimeList time)
	{
		this.id = id;
		this.movie_name = movie_name;
		this.time = time;
	}
	
	
	public int getId()
	{
		return id;
	}

	public String getMovie_name()
	{
		return movie_name;
	}

	public MovieTimeList getTime()
	{
		return time;
	}
	
	public void setTime(MovieTimeList time)
	{
		this.time = time;
	}


	@Override
	public String toString()
	{
		return  id  + movie_name  + time  ;
	}

	
}
