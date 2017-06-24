package dataStructure;

public class BigRoom
{
	private String _id;
	private String _row;
	private int _seatNum;
	private String _region;
	
	public BigRoom(String id, String row, int seatNum, String region)
	{
		this._id = id;
		this._row = row;
		this._seatNum = seatNum;
		this._region = region;
	}
	
	public String get_id()
	{
		return this._id;
	}

	public String get_row()
	{
		return this._row;
	}

	public int get_seatNum()
	{
		return this._seatNum;
	}
	
	public String get_region()
	{
		return this._region;
	}

}
