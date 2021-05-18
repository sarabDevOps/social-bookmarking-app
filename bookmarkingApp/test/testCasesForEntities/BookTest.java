package testCasesForEntities;


import static org.junit.Assert.assertFalse;

import org.junit.Test;

import constants.BookGenre;
import entities.Book;
import managers.BookmarkManager;


public class BookTest {
	
	@Test
	public void testIsKidsFriendlyEligible() {
	
		//BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"},BookGenre.PHILOSOPHY ,4.3);
		//1 : if book genre contains PHILOSOPHY
		Book book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"},BookGenre.PHILOSOPHY ,4.3);
		boolean isKidsFriendly = book.isKidsFriendlyEligible();
		assertFalse("retuens false if it contains PHILOSOPHY in genre  ",isKidsFriendly);
		
		
		
		//2 : if book genre contains self-help
		book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"},BookGenre.SELF_HELP ,4.3);
	    isKidsFriendly = book.isKidsFriendlyEligible();
		assertFalse("retuens false if it contains SELF_HELP in genre  ",isKidsFriendly);
		
		
	}
	
	
	
	
	

	/*@Test
	public void testIsKidsFriendlyEligible() {
	
		//BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"},BookGenre.PHILOSOPHY ,4.3);
		//1 : if book genre contains PHILOSOPHY
		Book book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"},BookGenre.PHILOSOPHY ,4.3);
		boolean isKidsFriendly = book.isKidsFriendlyEligible();
		assertFalse("retuens false if it contains PHILOSOPHY in genre  ",isKidsFriendly);
		
		
		
		//2 : if book genre contains self-help
		book = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Henry David Thoreau"},BookGenre.SELF_HELP ,4.3);
	    isKidsFriendly = book.isKidsFriendlyEligible();
		assertFalse("retuens false if it contains SELF_HELP in genre  ",isKidsFriendly);
		
		
	}*/
	
	
	
	

}
