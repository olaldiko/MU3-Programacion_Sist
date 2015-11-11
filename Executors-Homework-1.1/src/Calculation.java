import java.util.Vector;

public class Calculation {

	int id = 0;
	Oper operation;
	Vector<Integer> values = new Vector<>();
	int result = 0;
	
	public Calculation(int id) {
		this.id = id;
	}
	
	public void setOperation(Oper operation) {
		this.operation = operation;
	}
	public void addValue(int value) {
		values.addElement(value);
	}
	public Vector<Integer> getValues() {
		return this.values;
	}
	public Oper getOperation() {
		return this.operation;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getResult() {
		return result;
	}
	
	public String toString() {
		return "Calculation "+id+": Operand--> "+operation+" Values--> "+values.toString();
	}
}
