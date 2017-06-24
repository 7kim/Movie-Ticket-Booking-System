package dataStructure;

/**
 * 這個class存放記錄在BigRoom table裡的資料，並含有一個constructor和拿取資料的get functions
 *
 */
public class BigRoom
{
	private String _id;
	private String _row;
	private int _seatNum;
	private String _region;
	
	/**
	 * constructor, 創BigRoom物件
	 * 
	 * @param id 是一個String，每一個座位都有一個id
	 * @param row 是一個String，存放廳位的排
	 * @param seatNum 是一個int，存放位置的號碼
	 * @param region 是一個String，存放大廳的座位區域
	 */
	public BigRoom(String id, String row, int seatNum, String region)
	{
		this._id = id;
		this._row = row;
		this._seatNum = seatNum;
		this._region = region;
	}
	
	/**
	 * 這個function 用來取得大廳的座位ID
	 * @return 大廳的座位ID
	 */
	public String get_id()
	{
		return this._id;
	}

	/**
	 *  這個function 用來取得大廳的排
	 * @return 大廳的排
	 */
	public String get_row()
	{
		return this._row;
	}

	/**
	 * 這個function 用來取得大廳的位置號碼
	 * @return 大廳的位置號碼
	 */
	public int get_seatNum()
	{
		return this._seatNum;
	}
	
	/**
	 * 這個function 用來取得大廳的座位區域
	 * @return 大廳的座位區域
	 */
	public String get_region()
	{
		return this._region;
	}

}
