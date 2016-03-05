package primerg2Domain;


public class Ligamiento{
	
	private String nameSequence;
	
	private String sequence;
	
	private String seq1;
	
	private String seq2;
	
	private String primer;
	
	private double largo;
	
	private double tm;
	
	private double gc;
	
	private double tmAdjust;
	
	private double molWeight;
	
	public Ligamiento(String nameSequence, String sequence,  String seq1, String seq2) {
		this.nameSequence = nameSequence;
		this.sequence = sequence;
		this.seq1 = seq1;
		this.seq2 = seq2;
		this.primer = seq1 + seq2;
		this.largo = primer.length();
		this.tm = G2Utils.funcionTM(primer);
		this.gc = G2Utils.porcentajeGC(primer);
		this.tmAdjust = G2Utils.funcionTMajustada(primer);
		this.molWeight = G2Utils.molecularWeight(primer);
	}
	
	public String getSeq1() {
		return seq1;
	}
	public void setSeq1(String seq1) {
		this.seq1 = seq1;
	}
	public String getSeq2() {
		return seq2;
	}
	public void setSeq2(String seq2) {
		this.seq2 = seq2;
	}
	public String getPrimer() {
		return primer;
	}
	public void setPrimer(String primer) {
		this.primer = primer;
	}
	public double getLargo() {
		return largo;
	}
	public void setLargo(double largo) {
		this.largo = largo;
	}
	public double getTm() {
		return tm;
	}
	public void setTm(double tm) {
		this.tm = tm;
	}

	public String getNameSequence() {
		return nameSequence;
	}

	public void setNameSequence(String nameSequence) {
		this.nameSequence = nameSequence;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public double getGc() {
		return gc;
	}

	public void setGc(double gc) {
		this.gc = gc;
	}

	public double getTmAdjust() {
		return tmAdjust;
	}

	public void setTmAdjust(double tmAdjust) {
		this.tmAdjust = tmAdjust;
	}

	public double getMolWeight() {
		return molWeight;
	}

	public void setMolWeight(double molWeight) {
		this.molWeight = molWeight;
	}

	@Override
	public String toString() {
		return "Ligamiento [nameSequence=" + nameSequence + ", sequence=" + sequence + ", seq1=" + seq1 + ", seq2="
				+ seq2 + ", primer=" + primer + ", largo=" + largo + ", tm=" + tm + ", gc=" + gc + ", tmAdjust="
				+ tmAdjust + ", molWeight=" + molWeight + "]";
	}

	
}
