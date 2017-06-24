package dataStructure;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  這個class是為了存放多個ScoreSearche物件
 *
 */
public class ScoreSearchList extends ArrayList<ScoreSearch>
{

	/**
	 * 這個function會加進符合評分條件ScoreSearch物件
	 * 
	 * @param id 是int, 為符合評分條件的電影ID
	 * @param name 是String, 為符合評分條件的電影名稱
	 */
	public void add(int id, String name)
	{
		super.add(new ScoreSearch(id, name));
	}
}
