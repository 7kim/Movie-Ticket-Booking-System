package dataStructure;

/**
 * 這個class存放記錄在MovieInfo table裡的資料，並含有兩個constructors和拿取資料的get functions
 *
 */
public class MovieInfo
{
	private int _id;
	private String _movie;
	private String _classification;
	private String _descri;
	private int _infor;
	private double _score;
	private MovieTimeList _time;
	private String _hall;
	
	/**
	 * constructor, 創MovieInfo物件
	 * 
	 * @param id 是int， 為電影ID
	 * @param movie 是String， 為電影名稱
	 * @param classification 是String，為電影分級
	 * @param descri 是String，為電影簡介
	 * @param infor 是int，為電影長度
	 * @param score 是double，為電影網友評分
	 * @param time 是MovieTimeList物件，存放該電影所有的場次時間
	 * @param hall 是String，為該電影播放的廳位
	 * @throws Exception 無法建構MovieInfo物件時
	 */
	public MovieInfo(int id, String movie, String classification, String descri, 
			int infor, double score, MovieTimeList time, String hall) throws Exception
	{
		this._id = id;
		this._movie = movie;
		this._classification = classification;
		this._descri = descri;
		this._infor = infor;
		this._score = score;
		this._time = time;
		this._hall = hall;
	}
	
	/**
	 * constructor, 創MovieInfo物件
	 * 
	 * @param movie 是String， 為電影名稱
	 * @param classification 是String，為電影分級
	 * @param time 是MovieTimeList物件，存放該電影所有的場次時間
	 * @param hall 是String，為該電影播放的廳位
	 */
	public MovieInfo(String movie, String classification, MovieTimeList time, String hall)
	{
		this._movie = movie;
		this._classification = classification;
		this._time = time;
		this._hall = hall;
		
	}
	
	/**
	 * 這個function 用來取得物件裡的電影ID
	 * @return 物件裡的電影ID
	 */
	public int getId()
	{
		return this._id;
	}

	/**
	 * 這個function 用來取得物件裡的電影名稱
	 * @return 物件裡的電影名稱
	 */
	public String getMovie()
	{
		return this._movie;
	}

	/**
	 * 這個function 用來取得物件裡的電影分級
	 * @return 物件裡的電影分級
	 */
	public String getClassification()
	{
		return this._classification;
	}

	/**
	 * 這個function 用來取得物件裡的電影簡介
	 * @return 物件裡的電影簡介
	 */
	public String getDescri()
	{
		return this._descri;
	}

	/**
	 * 這個function 用來取得物件裡的電影長度
	 * @return 物件裡的電影長度
	 */
	public int getInfor()
	{
		return this._infor;
	}

	/**
	 * 這個function 用來取得物件裡的電影網友評分
	 * @return 物件裡的電影網友評分
	 */
	public double getScore()
	{
		return this._score;
	}

	/**
	 * 這個function 用來取得物件裡的電影所有場次
	 * @return 物件裡的電影所有場次 (為MovieTimeList物件)
	 */
	public MovieTimeList getTime()
	{
		return this._time;
	}

	/**
	 * 這個function 用來取得物件裡的電影廳位
	 * @return 物件裡的電影廳位
	 */
	public String getHall()
	{
		return this._hall;
	}

}
