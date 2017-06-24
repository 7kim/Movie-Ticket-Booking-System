package dataStructure;

import java.time.LocalTime;

public class MovieTime
{
	// 時間變數
	private LocalTime movie_time;
	
	/**
	 * 這個是constructor , 建構電影時刻物件
	 * @param time : 傳入的時間
	 */
	public MovieTime(String time) {
		time = DBBuilder.convertChar(time);
		this.movie_time = LocalTime.parse(time);
	}
	
	/**
	 * 這個function會回傳MovieTime物件的時間
	 * @return LocalTime : MovieTime物件的時間
	 */
	public LocalTime getTime() {
		return this.movie_time;
	}
	
	/**
	 * 這個function 會回傳是否在這個時刻之前
	 * @param time : 傳入的時間
	 * @return boolean
	 */
	public boolean isBefore(MovieTime time) {
		return (this.movie_time.isBefore(time.getTime()));
	}
	
	/**
	 * 這個function 會回傳是否在這個時刻之後
	 * @param time : 傳入的時間
	 * @return boolean
	 */
	public boolean isAfter(MovieTime time) {
		return (this.movie_time.isAfter(time.getTime()));
	}

	/**
	 * 這個function 用來判斷是否在兩個MovieTime 中間
	 * @param timeStart : 最早時間
	 * @param timeEnd : 最晚時間
	 * @return boolean 
	 */
	public boolean isBetween(MovieTime timeStart, MovieTime timeEnd) {
		return (this.isBefore(timeEnd) && this.isAfter(timeStart)) ? true : false;
	}
	
	
	public String toString()
	{
		return this.movie_time.toString();
	}
}
