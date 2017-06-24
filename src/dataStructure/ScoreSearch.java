package dataStructure;

/**
 * 這個class負責管理符合評分查詢的電影，含有一個constructors和拿取資料的get functions
 *
 */
public class ScoreSearch
{

	private int _id;
	private String _movie;

	

	/**
	 * constructor, 創ScoreSearch物件
	 * @param id 是int, 為符合評分條件的電影ID
	 * @param movie 是String, 為符合評分條件的電影名稱
	 */
	public ScoreSearch(int id, String movie) {
		this._id = id;
		this._movie = movie;
	}
	
	/**
	 * 這個function 用來取得符合評分條件的電影ID
	 * @return 電影ID 
	 */
	public int getID() {
		return this._id;
	}
	
	/**
	 * 這個function 用來取得符合評分條件的電影名稱
	 * @return 電影名稱
	 */
	public String getMovie()
	{
		return _movie;
	}
}
