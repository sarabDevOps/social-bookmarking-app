package runner;


import java.util.List;

import bgjobs.WebpageDownloaderTask;
import entities.Bookmark;
import entities.User;
import managers.BookmarkManager;
import managers.UserManager;

public class Launch {

	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	
	private static void loadData() {
		
		System.out.println("1 . Loading Data");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
		
		System.out.println("Printing data ...");
		printUserData();
		printBookmarkData();
		
		
	}


	private static void printUserData() {
		for(User user : users){
			System.out.println(user);
		}
		
	}
	
	private static void printBookmarkData() {
		
		for(List<Bookmark> bookmarkList : bookmarks){
			
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
				
			}
		}
		
	}

	
	private static void start() {
		//System.out.println( " \n 2 . Bookmarking .....");
		
		for (User user: users ) {
			View.browse(user, bookmarks);
		}
		
	}
	
	public static void main(String[] args) {

		loadData();
		start();
		
		//Background jobs
		//runDownloaderJobs();
	}
	

	public static void runDownloaderJobs(){
		
		//instanciate the class responsible for background download process
		WebpageDownloaderTask task = new WebpageDownloaderTask(true);
		(new Thread(task)).start();
	}


	

	
}
