
public class Girl extends Person {

	public Girl(int id, Disco disco) {
		super(id, disco);
	}
	
	@Override
	public String toString() {
		return "Girl "+id;
	}
	
}
