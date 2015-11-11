
public class Boy extends Person {

	public Boy(int id, Toilet t) {
		super(id, t);
	}

	
	@Override
	public String toString() {
		return "Boy "+id;
	}
}
