package dataStructure;

import java.util.ArrayList;
import java.util.Collection;

public class MovieTimeList extends ArrayList<MovieTime>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8991888755859362344L;

	public void add(String time) throws Exception
	{
		super.add(new MovieTime(time));
	}

	public MovieTimeList findBetween(MovieTime early, MovieTime late)
	{
		MovieTimeList result = new MovieTimeList();

		for(MovieTime t : this){
			if(t.isBetween(early, late))
				result.add(t);
		}
		return result;
	}

	public String toString()
	{
		String result = "";
		int n = this.size();

		if (n > 0)
		{
			result = this.get(0).toString();
			for (int i = 1; i < n; i++)
			{
				result += "、" + this.get(i);
			}
		}
		return result;
	}
}
