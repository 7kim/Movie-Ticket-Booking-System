package dataStructure;

public class Users
{
	private int _id;
	private String _name;
	private int _age;
	
	public Users(int id, String name, int age)
	{
		this._id = id;
		this._name = name;
		this._age = age;
	}

	public int get_id()
	{
		return this._id;
	}

	public String get_name()
	{
		return this._name;
	}

	public int get_age()
	{
		return this._age;
	}

}
