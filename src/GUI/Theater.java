package GUI;

public class Theater {

    int[] userID = new int[21]; 
    
    public Theater() {
        for (int i = 0; i < 21; i++) {
            userID[i] = i + 1;
        }
    }
    
    public enum HallSize {
        LARGE, SMALL
    }
    
    public HallSize getHallSize(String movieHall) {
        HallSize hallSize;
        switch (movieHall) {
        case "武當":
            hallSize = HallSize.LARGE;
            break;
        case "少林":
            hallSize = HallSize.LARGE;
            break;
        case "華山":
            hallSize = HallSize.LARGE;
            break;
        case "峨嵋 ":
            hallSize = HallSize.SMALL;
            break;
        case "崆峒 ":
            hallSize = HallSize.SMALL;
            break;
        default: 
            hallSize = HallSize.LARGE;
            break;
        }
        return hallSize;
    }
    
    public String[] getHallAllRows(HallSize hallSize) {
        String[] seatRows = null;
        switch (hallSize) {
        case LARGE:
            seatRows = "ABCDEFGHIJKLM".split("");
            break;
        case SMALL:
            seatRows = "ABCDEFGHI".split("");
            break;
        }
        return seatRows;
    }
    
    public String[] getAllAreas() {
        String[] seatArea = { "精華", "最佳", "次佳" };
        return seatArea;
    }
    
    public String[] getAreaRows(String area) {
        String[] seatRows = null;
        switch (area) {
        case "精華":
            seatRows = "IJ".split("");
            break;
        case "最佳":
            seatRows = "HIJK".split("");
            break;
        case "次佳":
            seatRows = "GHIJKL".split("");
            break;
        default: 
            seatRows = "ABCDEFGHIJKLM".split("");
            break;
        }
        return seatRows;
    }
    
}
