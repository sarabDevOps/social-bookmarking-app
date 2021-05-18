package controllers;

import entities.Bookmark;
import entities.User;
import managers.BookmarkManager;

public class BookmarkController {
	
	//create controller 
	//1 create private controller 
	//2 creare a static instance of the class 
	//3 create a method for singleton acces of the class 
	
	
	private BookmarkController(){}
	
	
	private static BookmarkController instance = new BookmarkController();
	
	public static BookmarkController getInstance(){
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user , bookmark);
		
	}

	public void setKidsFriendlyStatus(User user, String kidsFriendlyStatus, Bookmark bookmark) {
		BookmarkManager.getInstance().setKidsFriendlyStatus(user,kidsFriendlyStatus,bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().share(user , bookmark);
		
	}
}
