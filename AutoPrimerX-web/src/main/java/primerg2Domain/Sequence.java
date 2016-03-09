package primerg2Domain;

public class Sequence {
	
	private String name;
	
	private String sequence;
	
	private String comp;
	
	private String fwd;
	
	private String rev;
	
	private double tm_fwd;
	
	private double tm_rev;
		
	public Sequence(String name, String sequence, String comp, String fwd, String rev, double tm_fwd, double tm_rev) {

		this.name = name;
		this.sequence = sequence;
		this.comp = comp;
		this.fwd = fwd;
		this.rev = rev;
		this.tm_fwd = tm_fwd;
		this.tm_rev = tm_rev;
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

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getFwd() {
		return fwd;
	}

	public void setFwd(String fwd) {
		this.fwd = fwd;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public double getTm_fwd() {
		return tm_fwd;
	}

	public void setTm_fwd(double tm_fwd) {
		this.tm_fwd = tm_fwd;
	}

	public double getTm_rev() {
		return tm_rev;
	}

	public void setTm_rev(double tm_rev) {
		this.tm_rev = tm_rev;
	}

	@Override
	public String toString() {
		return "Sequence [name=" + name + ", sequence=" + sequence + ", comp=" + comp + ", fwd=" + fwd + ", rev=" + rev
				+ ", tm_fwd=" + tm_fwd + ", tm_rev=" + tm_rev + "]";
	}

	
}
