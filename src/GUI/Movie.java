package GUI;

public class Movie {

    String movieName;
    String movieTime;
    String movieHall;
    
    public Movie() {
        
    }
    
    public Movie(String movieName, String movieTime, String movieHall) {
        this.movieName = movieName;
        this.movieTime = movieTime;
        this.movieHall = movieHall;
    }
    
    private static Movie[] allMovies = {
            new Movie("異形：聖約 Alien: Covenant", "09：40、12：00、14：20、16：40、19：00、21：20、23：40", "武當"), 
            new Movie("亞瑟：王者之劍 King Arthur: Legend of the Sword", "09：05、11：30、14：45、17：10、19：35、22：05", "少林"),
            new Movie("逃出絕命鎮 Get Out", "11：20、15：20、19：10、23：00", "華山"),
            new Movie("電影版影子籃球員LAST GAME", "09：10、11：15、13：00", "峨嵋"),
            new Movie("攻殼機動隊1995 GHOST IN THE SHELL", "17:50、21:30", "峨嵋"),
            new Movie("我和我的冠軍女兒 Dangal", "10：50、13：50、16：50、19：50、21：50", "崆峒"),
    };
    
    public static String getMovieName(int movieID) {
        return allMovies[movieID].movieName;
    }
    
    public static String getMovieTime(int movieID) {
        return allMovies[movieID].movieTime;
    }
    
    public static String getMovieHall(int movieID) {
        return allMovies[movieID].movieHall;
    }
    
    public static String[] getAllMovieNames() {
        String [] allMovieNames = new String[allMovies.length];
        for (int i = 0; i < allMovies.length; i++) {
            allMovieNames[i] = allMovies[i].movieName;
        } 
        return allMovieNames;
    }

}