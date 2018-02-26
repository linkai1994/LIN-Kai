package texaspoker;

public class Cpu extends Role{
	private String name,character;
	String[] hand = new String[2];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	
}
