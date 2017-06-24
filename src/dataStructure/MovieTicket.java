package dataStructure;

/**
 * 這個class負責電影票之查詢，含有一個constructors和拿取資料的get functions
 *
 */
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
	
	/**
	 * constructor, 創MovieTicket物件
	 * 
	 * @param id 是String, 為電影票ID
	 * @param movie_name是String, 為該張電影票上的電影名稱
	 * @param time是String, 為該張電影票上的上映時間
	 * @param hall是String, 為該張電影票上的廳
	 * @param row是String, 為該張電影票上的排
	 * @param seat是String, 為該張電影票上的座位號碼
	 * @param region是String, 為該張電影票上的廳位區域
	 */
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
	
	/**
	 * 這個function 用來取得該張電影票的電影票ID
	 * @return 電影票ID
	 */
	public String getId()
	{	
		System.out.println("~");
		return this.id;
	}

	/**
	 * 這個function 用來取得該張電影票的電影名稱
	 * @return 電影名稱
	 */
	public String getMovie_name()
	{
		return this.movie_name;
	}

	/**
	 * 這個function 用來取得該張電影票的電影上映時間
	 * @return 是一MovieTime物件，為上映時間 
	 */
	public MovieTime getTime()
	{
		return this.time;
	}

	/**
	 * 這個function 用來取得該張電影票的廳
	 * @return 電影票的廳
	 */
	public String getHall()
	{
		return this.hall;
	}
	
	/**
	 * 這個function 用來取得該張電影票的排
	 * @return 電影票的排
	 */
	public String getRow()
	{
		return row;
	}

	/**
	 * 這個function 用來取得該張電影票的位置號碼
	 * @return 位置號碼
	 */
	public String getSeatNum()
	{
		return seatNum;
	}
	
	/**
	 * 這個function 用來取得該張電影票的區域
	 * @return 區域
	 */
	public String getRegion()
	{
		return region;
	}
}
