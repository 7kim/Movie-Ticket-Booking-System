package dataStructure;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 這個class是為了存放多個MovieTime物件
 *
 */
public class MovieTimeList extends ArrayList<MovieTime>
{


	/**
	 * 這個function會加進符合條件MovieTime物件
	 * 
	 * @param time 是String，為某部電影的某個場次時間
	 * @throws Exception 當時間不符合格式時
	 */
	public void add(String time) throws Exception
	{
		super.add(new MovieTime(time));
	}

	/**
	 * 這個function會加進在區間內的MovieTime物件，並回傳符合在這區間內的MovieTimes
	 * 
	 * @param early 是MovieTime，標示最早的時間
	 * @param late 是MovieTime，標示最晚的時間
	 * @return 所有在early和late之間的MovieTimes
	 */
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
