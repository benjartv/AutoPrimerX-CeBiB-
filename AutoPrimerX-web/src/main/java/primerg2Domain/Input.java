package primerg2Domain;

public class Input {
	
	private String name;
	
	private String sequence;

	public Input(String name, String sequence) {
		super();
		this.name = name;
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "Input [name=" + name + ", sequence=" + sequence + "]";
	}
	

}
