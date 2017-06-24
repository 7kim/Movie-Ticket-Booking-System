package dataStructure;

/**
 * 這個class存放記錄在SmallRoom table裡的資料，並含有一個constructor和拿取資料的get functions
 *
 */
public class SmallRoom
{
	private String _id;
	private String _row;
	private int _seatNum;
	
	
	/**
	 * constructor, 創SmallRoom物件
	 * 
	 * @param id 是一個String，每一個座位都有一個id
	 * @param row 是一個String，存放廳位的排
	 * @param seatNum 是一個int，存放位置的號碼
	 */
	public SmallRoom(String id, String row, int seatNum)
	{
		this._id = id;
		this._row = row;
		this._seatNum = seatNum;
	}
	
	/**
	 * 這個function 用來取得小廳的座位ID
	 * @return 小廳的座位ID
	 */
	public String get_id()
	{
		return this._id;
	}
	
	/**
	 * 這個function 用來取得小廳的排
	 * @return 小廳的排
	 */
	public String get_row()
	{
		return this._row;
	}

	/**
	 * 這個function 用來取得小廳的位置號碼
	 * @return 小廳的位置號碼
	 */
	public int get_seatNum()
	{
		return this._seatNum;
	}
	

}
