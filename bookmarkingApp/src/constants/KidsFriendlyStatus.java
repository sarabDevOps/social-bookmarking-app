package constants;

public class KidsFriendlyStatus {
	
	//so this class need to be instanciated so private constructor
	
	private KidsFriendlyStatus(){}
	
	public static final String APPROVED = "approved";
	public static final String REJECTED = "rejected";
	public static final String UNKNOWN = "unknown";
	
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
