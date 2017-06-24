package dataStructure;

import java.util.ArrayList;

public class MoviePlayList extends ArrayList<MoviePlay>
{
	public void add(int id, String name, MovieTimeList time) {
		super.add(new MoviePlay(id, name, time));
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
				result += "\n" + this.get(i);
			}
		}
		return result;
	}
}
