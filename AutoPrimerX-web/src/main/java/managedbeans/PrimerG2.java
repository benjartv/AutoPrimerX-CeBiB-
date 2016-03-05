package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import primerg2Domain.Sequence;
import primerg2Domain.G2Utils;
import primerg2Domain.Ligamiento;
import primerg2Domain.Seq;


@Named
@SessionScoped
public class PrimerG2 implements Serializable{
	
	private String name;
		
	//private List<Seq> inputs; 
	
	private Sequence sequence1;
	
	private Sequence sequence2;
	
	private Sequence sequence3;
	
	private Sequence sequence4;
	
	private Sequence sequence5;
	
	private List<Sequence> sequences; 
	
	private List<Ligamiento> primers_fwd;
	
	private List<Ligamiento> primers_rev;
	
	private List<Ligamiento> homologys;
	
	private double sodio;
	
	private double TM;
	
	@PostConstruct
	public void init(){
		TM = 45;
		sodio = 50;
		
		sequence1 = new Sequence("secuencia1", "GTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTA", TM);
		sequence2 = new Sequence("secuencia2", "ATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCC", TM);
		sequence3 = new Sequence("secuencia3", "CTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTGCTTG", TM);
		sequence4 = new Sequence("secuencia4", "AGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGT", TM);
		sequence5 = new Sequence("secuencia5", "CCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTTCCGGTT", TM);
		
	/*	sequence3.setName(null);
		sequence4.setName(null);
		sequence5.setName(null);
		
		sequence3.setSequence(null);
		sequence4.setSequence(null);
		sequence5.setSequence(null); */
	
	/*	sequence1.setName("secuencia1");
		sequence2.setName("secuencia2");
		sequence1.setSequence("GTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTAGTA");
		sequence2.setSequence("ATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCCATCC");
		*/	
		
		sequences = new ArrayList<>();
		
		primers_fwd = new ArrayList<>();
		
		primers_rev = new ArrayList<>();
		
		homologys = new ArrayList<>();
		
		sequences.add(sequence1);	
		sequences.add(sequence2);
		sequences.add(sequence3);
		sequences.add(sequence4);
		sequences.add(sequence5);
		
//		inputs = new ArrayList<>();
	}
	
	public Sequence getSequence1() {
		return sequence1;
	}

	public void setSequence1(Sequence sequence1) {
		this.sequence1 = sequence1;
	}

	public Sequence getSequence2() {
		return sequence2;
	}

	public void setSequence2(Sequence sequence2) {
		this.sequence2 = sequence2;
	}

	public Sequence getSequence3() {
		return sequence3;
	}

	public void setSequence3(Sequence sequence3) {
		this.sequence3 = sequence3;
	}

	public Sequence getSequence4() {
		return sequence4;
	}

	public void setSequence4(Sequence sequence4) {
		this.sequence4 = sequence4;
	}
	
	public Sequence getSequence5() {
		return sequence5;
	}

	public void setSequence5(Sequence sequence5) {
		this.sequence5 = sequence5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ligamiento> getPrimers_fwd() {
		return primers_fwd;
	}

	public void setPrimers_fwd(List<Ligamiento> primers_fwd) {
		this.primers_fwd = primers_fwd;
	}

	public List<Ligamiento> getPrimers_rev() {
		return primers_rev;
	}

	public void setPrimers_rev(List<Ligamiento> primers_rev) {
		this.primers_rev = primers_rev;
	}

	public List<Ligamiento> getHomologys() {
		return homologys;
	}

	public void setHomologys(List<Ligamiento> homologys) {
		this.homologys = homologys;
	}

	public double getSodio() {
		return sodio;
	}

	public void setSodio(double sodio) {
		this.sodio = sodio;
	}

	public List<Sequence> getSequences() {
		return sequences;
	}

	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}

	public double getTM() {
		return TM;
	}

	public void setTM(double tM) {
		TM = tM;
	}
	
	
	public void lineal(){
		/*
		if(sequence3.getName() != null && sequence3.getSequence() != null){
			sequence3 = new Sequence(sequence3.getName(), sequence3.getSequence(), TM);
			sequences.add(sequence3);
		}
		if(sequence4.getName() != null && sequence4.getSequence() != null){
			sequence4 = new Sequence(sequence4.getName(), sequence4.getSequence(), TM);
			sequences.add(sequence4);
		}
		if(sequence5.getName() != null && sequence5.getSequence() != null){
			sequence5 = new Sequence(sequence5.getName(), sequence5.getSequence(), TM);
			sequences.add(sequence5);
		}
		
		*/
		
		primers_fwd = G2Utils.forwards_lineal(sequences);
		primers_rev = G2Utils.revs_lineal(sequences);
		homologys = G2Utils.homologys_lineal(sequences);
		
	}
	
	public void circular(){
		
		primers_fwd = G2Utils.forwards_circular(sequences);
		primers_rev = G2Utils.revs_circular(sequences);
		homologys = G2Utils.homologys_circular(sequences);
		
		
	}
	
	
	
	/*	
	public List<Seq> getInputs() {
		return inputs;
	}

	public void setInputs(List<Seq> inputs) {
		this.inputs = inputs;
	}

	public void add(){
		inputs.add(new Seq());
		
		System.out.println();
		sequences.add(new Sequence(inputs.get(inputs.size()-1).getName(), inputs.get(inputs.size()-1).getSequence(), TM));
		
	}
	
	public void remove(Seq seq) {
        inputs.remove(seq);
       
    }
    */
	
	
	
	

	
}
