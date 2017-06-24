package dataStructure;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 這個class是為了存放多個MovieTicket物件
 *
 */
public class MovieTicketList extends ArrayList<MovieTicket>
{


	/**
	 * 這個function會加進查詢的電影票(MovieTicket物件)
	 * 
	 * @param id 是String, 為電影票ID
	 * @param movie_name是String, 為該張電影票上的電影名稱
	 * @param time是String, 為該張電影票上的上映時間
	 * @param hall是String, 為該張電影票上的廳
	 * @param row是String, 為該張電影票上的排
	 * @param seat是String, 為該張電影票上的座位號碼
	 * @param region是String, 為該張電影票上的廳位區域
	 * @throws Exception 當時間有誤時
	 */
	public void add(String id, String movie_name, String time, String hall, String row, String seat, String region) throws Exception {
		super.add(new MovieTicket(id, movie_name, time, hall, row, seat, region));
	}

}
