package testCasesForEntities;

import static org.junit.Assert.*;

import org.junit.Test;

import constants.MovieGenre;
import entities.Movie;
import managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidsFriendlyEligible() {

		// Test 1: if there is thriller

		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "www.jatt.com", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		
		boolean isKidsFriendly = movie.isKidsFriendlyEligible();
		
		assertFalse("Should retrun false as it is horror",isKidsFriendly);

		// Test 2: if there is adult
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "www.jatt.com", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);
		
		 isKidsFriendly = movie.isKidsFriendlyEligible();
		
		assertFalse("Should retrun false as it is thriller",isKidsFriendly);
		

	}

}
