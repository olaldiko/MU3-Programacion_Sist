public class Buffer {
	volatile int valor;
	
	public void put(int value) {
		System.out.println("Buffer recibe valor "+value);
		valor = value;
	}
	public int get() {
		System.out.println("Buffer envia valor "+valor);
		return valor;
	}
}
