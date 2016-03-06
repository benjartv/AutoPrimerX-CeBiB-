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


@Named
@SessionScoped
public class PrimerG2 implements Serializable{
	
	private String name;
	
	private String sequence;
		
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

		sequences = new ArrayList<>();
		
		primers_fwd = new ArrayList<>();
		
		primers_rev = new ArrayList<>();
		
		homologys = new ArrayList<>();

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
	
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public void lineal(){
		
		primers_fwd = G2Utils.forwards_lineal(sequences);
		primers_rev = G2Utils.revs_lineal(sequences);
		homologys = G2Utils.homologys_lineal(sequences);
		
	}
	
	public void circular(){
		
		primers_fwd = G2Utils.forwards_circular(sequences);
		primers_rev = G2Utils.revs_circular(sequences);
		homologys = G2Utils.homologys_circular(sequences);
		
	}
	
	public void download(){
		
		//Hacer código para generar PDF
		
	}
	
	public void add(){
		
		Sequence sequence = new Sequence(this.name, this.sequence, TM);
		sequences.add(sequence);
		this.name = "";
		this.sequence = "";
		
	}
	
	public void remove(Sequence sequence) {
        sequences.remove(sequence);
       
    }
	
}
