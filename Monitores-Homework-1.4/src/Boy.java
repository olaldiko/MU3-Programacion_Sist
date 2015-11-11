
public class Boy extends Person {

	public Boy(int id, Disco disco) {
		super(id, disco);
	}
	
	@Override
	public String toString() {
		return "Boy "+id;
	}
}
