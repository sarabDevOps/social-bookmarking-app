package runner;



import java.util.List;

import constants.KidsFriendlyStatus;
import constants.UserType;
import controllers.BookmarkController;
import entities.Bookmark;
import entities.User;
import partner.Shareable;

public class View {
	
	public static void browse(User user, List<List<Bookmark>> bookmarks){
	System.out.println("\n " + user.getEmail()+ "  is browsing items.....");
	
	//System.out.println(View.class.getName());
	
	int bookmarkCount = 0;
	
	for (List<Bookmark>bookmarkList : bookmarks) {
		for(Bookmark bookmark : bookmarkList){
			//Bookmarking 
			//if(bookmarkCount < DataStore.USER_BOOKMARK_LIMIT ){
				//get bookmark decision and pass the bookmark
				 boolean isBookmarked = getBookmarkDecision(bookmark);
				 //if its true means user wants to bookmark
				 if(isBookmarked){
					 bookmarkCount++;
					 

						BookmarkController.getInstance().saveUserBookmark(user,bookmark);
						
						System.out.println(" New Item Bookmarked --" + bookmark);
					 
					 
				// }//if ends here 
				
			}//if ends here 
	
			
			//Logic for marking kidsFriendly 
			if(user.getUserType().equals(UserType.EDITOR) ||user.getUserType().equals(UserType.CHIEF_EDITOR)){
				if(bookmark.isKidsFriendlyEligible() && bookmark.getKidsFriendlyStatus().equals(KidsFriendlyStatus.UNKNOWN)){
					String kidsFriendlyStatus =  getKidsFriendlyStatusDecision(bookmark);
					//now if the status is other than Unknown only then we need to set status
					if(!kidsFriendlyStatus.equals(KidsFriendlyStatus.UNKNOWN) ){
						//should pass the implementation to controller and Manager classes 
						BookmarkController.getInstance().setKidsFriendlyStatus(user,kidsFriendlyStatus,bookmark);
						
					}//inner inner if ends here 
				}//inner if ends here 
				
				if(bookmark.getKidsFriendlyStatus().equals(KidsFriendlyStatus.APPROVED)
						&& bookmark instanceof Shareable){
					boolean isShared = getShareDecision();
					//after he decides and click the share link we send info to controller 
					if(isShared){
						BookmarkController .getInstance().share(user,bookmark);
					}
				}//if ends here 
			}//if ends here 
			
			
			
			
		}//inner for 
	}//outer for
	
	
	
	
}
	// TODO: will be done after IO from console user input
	private static boolean getShareDecision() {
		//so if Math.random() return less the 0.5 user wants to bookmark other wise no 
		return Math.random() < 0.5 ? true : false;
	}

	private static String getKidsFriendlyStatusDecision(Bookmark bookmark) {
	
		//ternery operator are very good 
		/*return Math.random() <0.4 ? KidsFriendlyStatus.APPROVED : 
			(Math.random() >= 0.4 && Math.random()<0.8) ? KidsFriendlyStatus.REJECTED:
				KidsFriendlyStatus.UNKNOWN;*/
		
		
		double decision = Math.random();
		
		return  decision < 0.4 ? KidsFriendlyStatus.APPROVED :
			(decision >= 0.4 && decision < 0.8) ? KidsFriendlyStatus.REJECTED : 
				KidsFriendlyStatus.UNKNOWN;	
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		//so if Math.random() return less the 0.5 user wants to bookmark other wise no 
		return Math.random() < 0.5 ? true : false;
	}
	
	
	
	
	
	/*public static void bookmark(User user, Bookmark [][] bookmarks){
		System.out.println("\n " + user.getEmail()+ "  is bookmarking..");
		
		for(int i = 0 ; i< DataStore.USER_BOOKMARK_LIMIT;i++){
			
			int typeOffset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT); 
			int bookmarkOffset = (int) (Math.random() *DataStore.BOOKMARK_COUNT_PER_TYPE);
			
			Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
			
			BookmarkController.getInstance().saveUserBookmark(user,bookmark);
			
			System.out.println(bookmark);
			
			
		}//for ends here 
		
	}*/

}
