package primerg2Domain;

public class SequenceExt {

	private String name;

	private String sequence;

	private String comp;

	private String fwd;

	private String rev;

	private String fwd_ext;

	private String rev_ext;

	private double tm_fwd;

	private double tm_rev;

	private double tm_fwd_primer;

	private double tm_rev_primer;

	public SequenceExt(String name, String sequence, String comp, String fwd, String rev, String fwd_ext,
			String rev_ext, double tm_fwd, double tm_rev, double tm_fwd_primer, double tm_rev_primer) {
		this.name = name;
		this.sequence = sequence;
		this.comp = comp;
		this.fwd = fwd;
		this.rev = rev;
		this.fwd_ext = fwd_ext;
		this.rev_ext = rev_ext;
		this.tm_fwd = tm_fwd;
		this.tm_rev = tm_rev;
		this.tm_fwd_primer = tm_fwd_primer;
		this.tm_rev_primer = tm_rev_primer;
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

	public double getTm_fwd_primer() {
		return tm_fwd_primer;
	}

	public void setTm_fwd_primer(double tm_fwd_primer) {
		this.tm_fwd_primer = tm_fwd_primer;
	}

	public double getTm_rev_primer() {
		return tm_rev_primer;
	}

	public void setTm_rev_primer(double tm_rev_primer) {
		this.tm_rev_primer = tm_rev_primer;
	}

	@Override
	public String toString() {
		return "SequenceExt [name=" + name + ", sequence=" + sequence + ", comp=" + comp + ", fwd=" + fwd + ", rev="
				+ rev + ", fwd_ext=" + fwd_ext + ", rev_ext=" + rev_ext + ", tm_fwd=" + tm_fwd + ", tm_rev=" + tm_rev
				+ ", tm_fwd_primer=" + tm_fwd_primer + ", tm_rev_primer=" + tm_rev_primer + "]";
	}

}
