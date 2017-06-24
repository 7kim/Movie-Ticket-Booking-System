package dataStructure;

public class SeatNum
{
	int _numGray=0;
	int _numBlue=0;
	int _numYellow=0;
	int _numRed=0;

	/**
	 * 這個constructor是用來建立SeatNum 物件
	 * @param gray : 灰色區域數量
	 * @param blue : 藍色區域數量
	 * @param yellow : 黃色區域數量
	 * @param red : 紅色區域數量
	 */
	public SeatNum(int gray, int blue, int yellow, int red)  
	{
		_numGray = gray;
		_numBlue = blue;
		_numYellow = yellow;
		_numRed = red;
	}
	
	/**
	 * 這個function 用來回傳總座位數
	 * @return int : 總座位數
	 */
	public int getTotal()
	{
		return _numGray + _numBlue + _numYellow + _numRed ;
	}
	
	/**
	 * 這個function用來回傳灰色區域座位數
	 * @return int : 灰色區域座位數
	 */
	public int getGray(){
		return _numGray;
	}
	

	public void setGray(int gray){
		_numGray = gray;
	}
	
	/**
	 * 這個function用來回傳藍色區域座位數
	 * @return int : 藍色區域座位數
	 */
	public int getBlue(){
		return _numBlue;
	}
	
	public void setBlue(int blue){
		_numBlue = blue;
	}
	
	/**
	 * 這個function用來回傳黃色區域座位數
	 * @return int : 黃色區域座位數
	 */
	public int getYellow(){
		return _numYellow;
	}
	
	public void setYellow(int yellow){
		_numYellow = yellow;
	}
	
	/**
	 * 這個function用來回傳紅色區域座位數
	 * @return int : 紅色區域座位數
	 */
	public int getRed(){
		return _numRed;
	}
	
	public void setRed(int red){
		_numRed = red;
	}
	
	public void UpdateSeatNum(String region, int num){
		switch (region) {
		case "gray":
			setGray(num);
			break;
		case "yellow":
			setYellow(num);
			break;
		case "blue":
			setBlue(num);
			break;
		case "red":
			setRed(num);
			break;
		}
	}
	
	public int getSeatNum(String region){
		int seatnum = 0;
		switch (region) {
		case "gray":
			seatnum = getGray();
			break;
		case "yellow":
			seatnum = getYellow();
			break;
		case "blue":
			seatnum = getBlue();
			break;
		case "red":
			seatnum = getRed();
			break;
		}
		return seatnum;
	}
}
