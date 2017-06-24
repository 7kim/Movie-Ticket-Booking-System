package dataStructure;

public class ScoreSearch
{

	private int _id;
	private String _movie;

	

	public ScoreSearch(int id, String movie) {
		this._id = id;
		this._movie = movie;
	}
	
	public int getID() {
		return this._id;
	}
	
	public String getMovie()
	{
		return _movie;
	}
}
