package dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Book;
import entities.Bookmark;
import entities.Movie;
import entities.UserBookmark;
import entities.WebLink;
import runner.DataStore;

public class BookmarkDao {

	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		//DataStore.add(userBookmark);
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "sarab");
				/**
				 * Connection is for openning the connection 
				 * Statement is to execute the queries
				 */
				Statement stmt = conn.createStatement();) {
			if(userBookmark.getBookmark() instanceof Book){
				saveUserBook(userBookmark , stmt);
			} else if(userBookmark.getBookmark() instanceof Movie){
				saveUserMovie(userBookmark , stmt);
			}else{
				saveUserWeblink(userBookmark , stmt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	private void saveUserWeblink(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_WebLink (user_id ,weblink_id) values  (" +
				userBookmark.getUser().getId() + " ," + userBookmark.getBookmark().getId()	 + " )";
		stmt.executeUpdate(query);
	}

	private void saveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Movie (user_id ,movie_id) values  (" +
				userBookmark.getUser().getId() + " ," + userBookmark.getBookmark().getId()	 + " )";
		stmt.executeUpdate(query);
		
	}

	private void saveUserBook(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Book (user_id ,book_id) values  (" +
				userBookmark.getUser().getId() + " ," + userBookmark.getBookmark().getId()	 + " )";
		//Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement or anSQL
		//statement that returns nothing, such as an SQL DDL statement
		
		//either (1) the row count for SQL Data Manipulation Language (DML) statementsor 
		//(2) 0 for SQL statements that return nothing
		stmt.executeUpdate(query);
	}

	// In real world we have here SQL query or hibernate query
	public List<WebLink> getAllWeblinks() {
		List<WebLink> results = new ArrayList<>();
		List<List<Bookmark>> getBookMarks = DataStore.getBookmarks();
		List<Bookmark> allWeblinks = getBookMarks.get(0);
		for (Bookmark bookmark : allWeblinks) {
			results.add((WebLink) bookmark);
		}
		return results;
	}

	public List<WebLink> getWeblink(WebLink.DownloadStatus downlodedStatus) {
		List<WebLink> results = new ArrayList<>();

		List<WebLink> allWebLinks = getAllWeblinks();

		for (WebLink weblink : allWebLinks) {
			if (weblink.getDownloadStatus().equals(downlodedStatus)) {
				results.add(weblink);
			}
		}

		return results;
	}

}
