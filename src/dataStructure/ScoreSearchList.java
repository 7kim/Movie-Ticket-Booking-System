package dataStructure;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreSearchList extends ArrayList<ScoreSearch>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void add(int id, String name) {
		super.add(new ScoreSearch(id, name));
	}
}
