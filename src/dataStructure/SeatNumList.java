package dataStructure;

import java.util.ArrayList;

public class SeatNumList extends ArrayList<SeatNum>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1183142943667244285L;

	public void add(int gray, int blue, int yellow, int red) throws Exception {
		super.add(new SeatNum(gray,blue,yellow,red));
	}

}
