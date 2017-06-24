package dataStructure;

import java.util.ArrayList;

/**
 * 這個class是為了存放多個MoviePlay物件
 *
 */

public class MoviePlayList extends ArrayList<MoviePlay>
{
	/**
	 * 這個function會加進符合限定播映時間/限定片長長度查詢的電影(MoviePlay物件)
	 * 
	 * @param id 是int，為符合條件的電影ID
	 * @param name 是String，為符合條件的電影名稱
	 * @param time 是MovieTimeList，為符合條件的電影場次
	 */
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
