package dataStructure;

public class SmallRoom
{
	private String _id;
	private String _row;
	private int _seatNum;
	private boolean _occupied;
	
	public SmallRoom(String id, String row, int seatNum, boolean occupied)
	{
		this._id = id;
		this._row = row;
		this._seatNum = seatNum;
		this._occupied = occupied;
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

	public boolean is_occupied()
	{
		return this._occupied;
	}
	

}
