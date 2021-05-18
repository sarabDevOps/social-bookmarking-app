package runner;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entities.Bookmark;
import entities.User;
import entities.UserBookmark;
import managers.BookmarkManager;
import managers.UserManager;


public class DataStore {

	public static final int USER_BOOKMARK_LIMIT = 5;
	public static final int BOOKMARK_COUNT_PER_TYPE = 5;
	public static final int BOOKMARK_TYPES_COUNT = 3;
	public static final int TOTAL_USER_COUNT = 5;

	public static List<User>users = new ArrayList<>();

	public static List<User> getUsers() { // will return all users
		return users;
	}

	public static List<List<Bookmark>> bookmarks = new ArrayList<>();// Bookmark[BOOKMARK_TYPES_COUNT][BOOKMARK_COUNT_PER_TYPE];

	public static List<List<Bookmark>> getBookmarks() { // this will return all the
												// bookmarks
		return bookmarks;
	}

	public static List <UserBookmark> userBookmarks = new ArrayList<>();
	

	public static void loadData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//new com.mysql.jdbc.Driver(); 
			            // OR
			//System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		
		                // OR java.sql.DriverManager
		    //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// try-with-resources ==> conn & stmt will be closed
		// Connection string: <protocol>:<sub-protocol>:<data-source details>
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "sarab");
				/**
				 * Connection is for openning the connection 
				 * Statement is to execute the queries
				 */
				Statement stmt = conn.createStatement();) {
			loadUsers(stmt);    // Student is expected to implement this method
			loadWebLinks(stmt); // Student is expected to implement this method
			loadMovies(stmt);
			loadBooks(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	


	private static void loadUsers(Statement smt) throws SQLException {
		
		String query = "Select * from User";
		ResultSet rs = smt.executeQuery(query);
		while(rs.next()){
			long id = rs.getLong("id");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			int gender_id = rs.getInt("gender_id");
			int user_type_id = rs.getInt("user_type_id");
			String UserId = String.valueOf(user_type_id);
			User user = UserManager.getInstance().createUser(id, email, password,
					first_name , last_name,  gender_id ,UserId);
			users.add(user);
			
		}//while ends here 
		
		/*
		 * users[0] =
		 * UserManager.getInstance().createUser(1000,"user0@semanticsquare.com",
		 * "test", "John", "M" ,Gender.MALE,UserType.USER); users[1] =
		 * UserManager.getInstance().createUser(1001,"user1@semanticsquare.com",
		 * "test", "Sam","M",Gender.MALE,UserType.USER); users[2] =
		 * UserManager.getInstance().createUser(1002,"user2@semanticsquare.com",
		 * "test", "Anita" ,"M",Gender.FEMALE,UserType.EDITOR); users[3] =
		 * UserManager.getInstance().createUser(1003,"user3@semanticsquare.com",
		 * "test", "Sara", "M" ,Gender.FEMALE,UserType.EDITOR); users[4] =
		 * UserManager.getInstance().createUser(1004,"user4@semanticsquare.com",
		 * "test", "Dheeru","M",Gender.MALE,UserType.CHEIF_EDITOR);
		 *//*
		//String[] data = new String[TOTAL_USER_COUNT];
		List<String> data  = new ArrayList<>();
		IOUtil.read(data, "User");
		for (String row : data) {
			String[] values = row.split("\t");
			int gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;

			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			} // elseIfEndsHere

			User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2],
					values[3], values[4], gender, values[6]);
			users.add(user);

		} // for in ends here*/
		
		
		
		
		
	}// load users ends here

	private static void loadWebLinks(Statement smt) throws SQLException {

		String query = "Select * from weblink";
		ResultSet rs = smt.executeQuery(query);
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		while(rs.next()){
			long id = rs.getLong("id");
			String title = rs.getString("title");
			String url = rs.getString("url");
			String host = rs.getString("host");
			Bookmark bookmark = BookmarkManager.getInstance().createWebLink(id,title,url,host);//	values[2], values[3]/* , values[4] */);
			bookmarkList.add(bookmark);
		}
		
		bookmarks.add(bookmarkList);
		/*
		 * bookmarks[0][0] = BookmarkManager.getInstance().createWebLink(
		 * 2000,"Taming Tiger, Part 2"
		 * ,"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html"
		 * ,"http://www.javaworld.com"); bookmarks[0][1] =
		 * BookmarkManager.getInstance().createWebLink(
		 * 2001,"How do I import a pre-existing Java project into Eclipse and get up and running?"
		 * ,"http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running"
		 * ,"http://www.stackoverflow.com"); bookmarks[0][2] =
		 * BookmarkManager.getInstance().createWebLink(
		 * 2002,"Interface vs Abstract Class"
		 * ,"http://mindprod.com/jgloss/interfacevsabstract.html",
		 * "http://mindprod.com"); bookmarks[0][3] =
		 * BookmarkManager.getInstance().createWebLink(
		 * 2003,"NIO tutorial by Greg Travis"
		 * ,"http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf",
		 * "http://cs.brown.edu"); bookmarks[0][4] =
		 * BookmarkManager.getInstance().createWebLink(
		 * 2004,"Virtual Hosting and Tomcat"
		 * ,"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html"
		 * ,"http://tomcat.apache.org");
		 */
		//String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
	
		//List<String> data  = new ArrayList<>();
 		//IOUtil.read(data, "WebLink");
		//List<Bookmark> bookmarkList  = new ArrayList<>();
		//for (String row : data) {
			//String[] values = row.split("\t");

			//Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
				//	values[2], values[3]/* , values[4] */);
			
			//bookmarkList.add(bookmark);
		//} 
		
		//bookmarks.add(bookmarkList);
	}// load weblinks ends here

	// long id, String title, String profileUrl, int releaseYear, String[]
	// cast,String[] directors, String genre, double imbdRating
	private static void loadMovies(Statement stmt) throws SQLException {
		String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
				+ " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
				+ "where m.id = ma.movie_id and ma.actor_id = a.id and "
				      + "m.id = md.movie_id and md.director_id = d.id group by m.id";
		ResultSet rs = stmt.executeQuery(query);
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int releaseYear = rs.getInt("release_year");
			String[] cast = rs.getString("cast").split(",");
			String[] directors = rs.getString("directors").split(",");			
			int genre_id = rs.getInt("movie_genre_id");
			String genreID = String.valueOf(genre_id); 
			//MovieGenre genre = MovieGenre.values()[genre_id];
			double imdbRating = rs.getDouble("imdb_rating");
			
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, "", releaseYear, cast, directors, genreID, imdbRating/*, values[7]*/);
    		bookmarkList.add(bookmark); 
		}
		bookmarks.add(bookmarkList);
		
		
		/*
		 * bookmarks[1][0] =
		 * BookmarkManager.getInstance().createMovie(3000,"Citizen Kane"
		 * ,"www.jatt.com",1941,new String[]{"Orson Welles,Joseph Cotten"},new
		 * String[]{"Orson Welles"},MovieGenre.CLASSICS,8.5); bookmarks[1][1] =
		 * BookmarkManager.getInstance().createMovie(3001,"The Grapes of Wrath"
		 * ,"www.jatt.com",1940 ,new String[]{"	Henry Fonda,Jane Darwell"},new
		 * String[]{"John Ford"},MovieGenre.CLASSICS,8.2); bookmarks[1][2] =
		 * BookmarkManager.getInstance().createMovie(3002,"A Touch of Greatness"
		 * ,"www.jatt.com",2004,new String[]{"Albert Cullum	"}, new
		 * String[]{"Leslie Sullivan"},MovieGenre.DOCUMENTARIES ,7.3);
		 * bookmarks[1][3] =
		 * BookmarkManager.getInstance().createMovie(3003,"The Big Bang Theory"
		 * ,"www.jatt.com",2007,new String[]{"	Kaley Cuoco,Jim Parsons	"},new
		 * String[]{"Chuck Lorre,Bill Prady"},MovieGenre.TV_SHOWS,8.7 );
		 * bookmarks[1][4] =
		 * BookmarkManager.getInstance().createMovie(3004,"Ikiru","",1952,new
		 * String[]{"	Takashi Shimura,Minoru Chiaki"},new
		 * String[]{"Akira Kurosawa"},MovieGenre.FOREIGN_MOVIES, 8.4);
		 */
		//String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "Movie");
//		
//		List<Bookmark> bookmarkList  = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//			String[] cast = values[3].split(",");
//			String[] directors = values[4].split(",");
//			Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",
//					Integer.parseInt(values[2]), cast, directors, values[5],
//					Double.parseDouble(values[6])/* , values[7] */);
//			bookmarkList.add(bookmark);
//		} // for ends here
//		bookmarks.add(bookmarkList);
	}// load movies ends here

	private static void loadBooks(Statement stmt) throws SQLException {
		
		String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
				+ " from Book b, Publisher p, Author a, Book_Author ba "
				+ "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
    	ResultSet rs = stmt.executeQuery(query);
		
    	List<Bookmark> bookmarkList = new ArrayList<>();
    	while (rs.next()) {
    		long id = rs.getLong("id");
			String title = rs.getString("title");
			int publicationYear = rs.getInt("publication_year");
			String publisher = rs.getString("name");		
			String[] authors = rs.getString("authors").split(",");			
			int genre_id = rs.getInt("book_genre_id");
			//BookGenre genre = BookGenre.values()[genre_id];
			String genreId  = String.valueOf(genre_id);
			double amazonRating = rs.getDouble("amazon_rating");
			
			Date createdDate = rs.getDate("created_date");
			System.out.println("createdDate: " + createdDate);
			Timestamp timeStamp = rs.getTimestamp(8);
			System.out.println("timeStamp: " + timeStamp);
			System.out.println("localDateTime: " + timeStamp.toLocalDateTime());
			
			System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genreId + ", amazonRating: " + amazonRating);
    	
    		Bookmark bookmark = BookmarkManager.getInstance().createBook(id, title, publicationYear, publisher, authors, genreId, amazonRating/*, values[7]*/);
    		bookmarkList.add(bookmark); 
    	}
    	bookmarks.add(bookmarkList);
		

		/*
		 * bookmarks[2][0] =
		 * BookmarkManager.getInstance().createBook(4000,"Walden",
		 * 1854,"Wilder Publications",new
		 * String[]{"Henry David Thoreau"},BookGenre.PHILOSOPHY ,4.3);
		 * bookmarks[2][1] = BookmarkManager.getInstance().createBook(
		 * 4001,"Self-Reliance and Other Essays",1993,"Dover Publications",new
		 * String[]{"Ralph Waldo Emerson"},BookGenre.PHILOSOPHY,4.5);
		 * bookmarks[2][2] =
		 * BookmarkManager.getInstance().createBook(4002,"Light From Many Lamps"
		 * ,1988,"Touchstone",new
		 * String[]{"Lillian Eichler Watson"},BookGenre.PHILOSOPHY,5.0);
		 * bookmarks[2][3] = BookmarkManager.getInstance().createBook(
		 * 4003,"Head First Design Patterns",2004,"O'Reilly Media",new
		 * String[]{"Eric Freeman,Bert Bates,Kathy Sierra,Elisabeth Robson"},
		 * BookGenre.TECHNICAL,4.5); bookmarks[2][4] =
		 * BookmarkManager.getInstance().createBook(
		 * 4004,"Effective Java Programming Language Guide",2007,"Prentice Hall"
		 * , new String[]{"Joshua Bloch"}, BookGenre.TECHNICAL,4.9);
		 */
		//String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "Book");
//		
//		List<Bookmark> bookmarkList  = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//			String[] authors = values[4].split(",");
//			Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
//					Integer.parseInt(values[2]), values[3], authors, values[5],
//					Double.parseDouble(values[6])/* , values[7] */);
//			bookmarkList.add(bookmark);
//		} // for ends here
//		bookmarks.add(bookmarkList);
	}// load books ends here

	public static void add(UserBookmark userBookmark) {

		userBookmarks.add(userBookmark);
		

	}

}
