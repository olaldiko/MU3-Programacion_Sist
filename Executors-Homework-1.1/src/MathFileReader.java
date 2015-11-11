import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class MathFileReader {

	BufferedReader reader;
	Vector<String> readedLines = new Vector<>();
	
	
	public void readFile() {
		String str;
		try {
			reader = new BufferedReader(new FileReader("input.txt"));
			while((str = reader.readLine()) != null) {
				readedLines.addElement(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Calculation> parseFile() {
		Vector<Calculation> retVal = new Vector<>();
		String[] strParts;
		Calculation c;
		for(int i = 0; i < readedLines.size(); i++) {
			c = new Calculation(i);
			strParts = readedLines.get(i).split(" ");
			switch(strParts[0]) {
				case "Sumar":
					c.setOperation(Oper.ADD);
					break;
				case "Restar":
					c.setOperation(Oper.REMOVE);
					break;
				case "Multiplicar":
					c.setOperation(Oper.MULTIPLY);
					break;
				case "Dividir":
					c.setOperation(Oper.DIVIDE);
					break;
				default:
					c.setOperation(Oper.ADD);
			}
			for(int j = 1; j < strParts.length; j++) {
				c.addValue(Integer.parseInt(strParts[j]));
			}
			System.out.println(c+" added");
			retVal.addElement(c);
		}
	return retVal;
	}
}
