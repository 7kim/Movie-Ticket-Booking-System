package dataStructure;

import java.util.ArrayList;
import java.util.Collection;

public class UsersList extends ArrayList<Users>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void add(int id, String name, int age) {
		super.add(new Users(id, name, age));
	}

	
}
