package entities;

import constants.KidsFriendlyStatus;
import runner.*;

public abstract class Bookmark {

	private long id;
	private String title;
	private String profileUrl;
	private String kidsFriendlyStatus = KidsFriendlyStatus.UNKNOWN;
	private User kidsFriendlyMarkedBy;
	private User sharedBy;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
	public abstract boolean isKidsFriendlyEligible();
	
	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", title=" + title + ", profileUrl=" + profileUrl + "]";
	}

	public String getKidsFriendlyStatus() {
		return kidsFriendlyStatus;
	}

	public void setKidsFriendlyStatus(String kidsFriendlyStatus) {
		this.kidsFriendlyStatus = kidsFriendlyStatus;
	}

	public User getKidsFriendlyMarkedBy() {
		return kidsFriendlyMarkedBy;
	}

	public void setKidsFriendlyMarkedBy(User kidsFriendlyMarkedBy) {
		this.kidsFriendlyMarkedBy = kidsFriendlyMarkedBy;
	}

	public User getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(User sharedBy) {
		this.sharedBy = sharedBy;
	}

}
