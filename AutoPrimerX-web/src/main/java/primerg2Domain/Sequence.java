package primerg2Domain;

public class Sequence {
	
	private String name;
	
	private String sequence;
	
	private String comp;
	
	private String fwd;
	
	private String rev;
	
	private String fwd_ext;
	
	private String rev_ext;
	
	private double tmParam;

	public Sequence(String name, String sequence, double tmParam) {
		this.name = name;
		this.sequence = sequence;
		this.comp = G2Utils.complemento(this.sequence);
		this.fwd = G2Utils.forward(this.sequence, tmParam);
		this.rev = G2Utils.reverse(this.sequence, tmParam);
		this.fwd_ext = G2Utils.forward_EXT(this.sequence, tmParam);
		this.rev_ext = G2Utils.reverse_EXT(this.sequence, tmParam);
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

	public String getFwd_ext() {
		return fwd_ext;
	}

	public void setFwd_ext(String fwd_ext) {
		this.fwd_ext = fwd_ext;
	}

	public String getRev_ext() {
		return rev_ext;
	}

	public void setRev_ext(String rev_ext) {
		this.rev_ext = rev_ext;
	}

	public double getTmParam() {
		return tmParam;
	}

	public void setTmParam(double tmParam) {
		this.tmParam = tmParam;
	}

	@Override
	public String toString() {
		return "Sequence [name=" + name + ", sequence=" + sequence + ", comp=" + comp + ", fwd=" + fwd + ", rev=" + rev
				+ ", fwd_ext=" + fwd_ext + ", rev_ext=" + rev_ext + "]";
	}
	

}
