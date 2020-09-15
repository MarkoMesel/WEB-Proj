package dbm;

public class ContentDbModel {
	public String id;
	public String name;
	
	public ContentDbModel(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ContentDbModel(String[] array) {
		this.id = array[0];
		this.name = array[1];
	}
}
