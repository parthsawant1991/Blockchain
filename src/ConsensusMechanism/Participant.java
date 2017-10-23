
public class Participant {

	private int index;

	public Participant() {
		
	}
	public Participant(int _index) {
		this.index = _index;
	}

	public int getIndex() {
		return this.index;
	}

	public int vote() {
		return Math.abs((int)System.nanoTime()%50);
	}

}
