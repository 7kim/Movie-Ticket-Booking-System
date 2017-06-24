package dataStructure;

/**
 * 這個class負責管理限定播映時間/限定片長長度的電影列表之查詢，含有一個constructors和拿取資料的get/set functions
 *
 */
public class MoviePlay
{
	private int id;
	private String movie_name;
	private MovieTimeList time;
	



	/**
	 * constructor, 創MoviePlay物件
	 * 
	 * @param id 是int， 為電影ID
	 * @param movie_name 是String， 為電影名稱
	 * @param time 是MovieTimeList物件，存放該電影符合查詢條件的所有場次時間
	 */
	public MoviePlay(int id, String movie_name, MovieTimeList time)
	{
		this.id = id;
		this.movie_name = movie_name;
		this.time = time;
	}
	
	
	/**
	 * 這個function 用來取得符合查詢條件的電影ID
	 * @return 電影ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * 這個function 用來取得符合查詢條件的電影名稱
	 * @return 電影名稱
	 */
	public String getMovie_name()
	{
		return movie_name;
	}

	/**
	 * 這個function 用來取得符合查詢條件的電影場次時間
	 * @return 電影場次時間
	 */
	public MovieTimeList getTime()
	{
		return time;
	}
	
	/**
	 * 這個function 用來設定符合查詢條件的電影場次時間
	 */
	public void setTime(MovieTimeList time)
	{
		this.time = time;
	}


	public String toString()
	{
		return  id  + movie_name  + time  ;
	}

	
}
