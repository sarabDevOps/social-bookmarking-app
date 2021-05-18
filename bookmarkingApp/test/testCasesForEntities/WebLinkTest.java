package testCasesForEntities;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.WebLink;
import managers.BookmarkManager;

public class WebLinkTest {

	@Test
	public void testIsKidsFriendlyEligible() {
		//Test 1 : porn in url -- false
	    WebLink weblink =	BookmarkManager.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.porn.com");
		
	   // System.out.println(weblink.getUrl());
	    boolean isKidsFriendlyEligible = weblink.isKidsFriendlyEligible();
		
	    assertFalse("For porn in url - isKidsFriendlyEligible() must return false",isKidsFriendlyEligible);
	    
	    
		//Test 2: porn in title --false
	   weblink =	BookmarkManager.getInstance().createWebLink(2000,"Taming porn, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
		
	   isKidsFriendlyEligible = weblink.isKidsFriendlyEligible();
		
	    assertFalse("For porn in title - isKidsFriendlyEligible() must return false",isKidsFriendlyEligible);
		
		
		//Test 3: adult in host --false
	    weblink =	BookmarkManager.getInstance().createWebLink(2000,"Taming tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html","http://www.javaworld.com");
		
	    	//System.out.println(weblink.getHost());
		 isKidsFriendlyEligible = weblink.isKidsFriendlyEligible();
			
		  assertFalse("For adult in host - isKidsFriendlyEligible() must return false",isKidsFriendlyEligible);
			
		
		
		//Test 4 : adult in url, but not in host part --true
		
		  weblink =	BookmarkManager.getInstance().createWebLink(2000,"Taming tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.adult.com");
			
		   isKidsFriendlyEligible = weblink.isKidsFriendlyEligible();
		   
		//	System.out.println(weblink.getUrl());
		    assertTrue("For adult in url,but not host  - isKidsFriendlyEligible() must return false",isKidsFriendlyEligible);
			
	
		//Test 5: adult in title only 
		    
		    weblink =	BookmarkManager.getInstance().createWebLink(2000,"Taming adult, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
			
			 isKidsFriendlyEligible = weblink.isKidsFriendlyEligible();
				
			 assertTrue("For adult in title must retrun true - isKidsFriendlyEligible() must return false",isKidsFriendlyEligible);
				
		    
		    
	}
	

}
//com.semanticsquare.thrillio.entities