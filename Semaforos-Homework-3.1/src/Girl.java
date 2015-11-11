
public class Girl extends Person {

	public Girl(int id, Toilet t) {
		super(id, t);
	}
	@Override
	public String toString() {
		return "Girl "+id;
	}
}
