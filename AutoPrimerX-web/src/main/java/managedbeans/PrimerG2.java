package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import primerg2Domain.Sequence;
import primerg2Domain.SequenceExt;
import primerg2Domain.G2Utils;
import primerg2Domain.Input;
import primerg2Domain.Ligamiento;

@Named
@SessionScoped
public class PrimerG2 implements Serializable {

	private String name;

	private String sequence;

	private String option;

	private double tolerance;

	private double sodio;

	private double TM;

	private double TMh;

	private double TMprimer;

	private List<Input> inputs;

	private List<SequenceExt> sequences_ext;

	private List<List<Ligamiento>> results;

	private List<Sequence> pre_sequences;

	private List<Ligamiento> primers_fwd;

	private List<Ligamiento> primers_rev;

	private List<Ligamiento> homologys;

	@PostConstruct
	public void init() {
		TM = 45;
		TMh = 52;
		TMprimer = 72;
		sodio = 50;
		tolerance = 3;
		option = "lineal";

		primers_fwd = new ArrayList<>();

		primers_rev = new ArrayList<>();

		homologys = new ArrayList<>();

		inputs = new ArrayList<>();

		pre_sequences = new ArrayList<>();

		sequences_ext = new ArrayList<>();

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

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

	public double getTMh() {
		return TMh;
	}

	public void setTMh(double tMh) {
		TMh = tMh;
	}

	public double getTMprimer() {
		return TMprimer;
	}

	public void setTMprimer(double tMprimer) {
		TMprimer = tMprimer;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public List<Sequence> getPre_sequences() {
		return pre_sequences;
	}

	public void setPre_sequences(List<Sequence> pre_sequences) {
		this.pre_sequences = pre_sequences;
	}

	public List<List<Ligamiento>> getResults() {
		return results;
	}

	public void setResults(List<List<Ligamiento>> results) {
		this.results = results;
	}

	public List<SequenceExt> getSequences_ext() {
		return sequences_ext;
	}

	public void setSequences_ext(List<SequenceExt> sequences_ext) {
		this.sequences_ext = sequences_ext;
	}

	public void assembly() {
		System.out.println("opcion" + this.option);
		if (option.equals("lineal")) {
			// List<Sequence> pre_sequences = new ArrayList<>();
			pre_sequences = G2Utils.create_sequence(inputs, "lineal", TM, tolerance);
			sequences_ext = G2Utils.sequences_ext(pre_sequences, "lineal", TMprimer);
			results = G2Utils.results(sequences_ext, "lineal", TM, TMh, TMprimer, tolerance);
			if (results != null) {
				homologys = results.get(0);
				primers_fwd = results.get(1);
				primers_rev = results.get(2);
				//fwd de extension de la secuencia 1 en el primer
				//homologys.get(homologys.size() -1).getSeq2();
				
			} else {
				FacesContext context;
				context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Not coincidences"));
			}

			/*
			 * primers_fwd = G2Utils.forwards_lineal(sequences); primers_rev =
			 * G2Utils.revs_lineal(sequences); homologys =
			 * G2Utils.homologys_lineal(sequences);
			 */

		} else {

			pre_sequences = G2Utils.create_sequence(inputs, "circular", TM, tolerance);
			sequences_ext = G2Utils.sequences_ext(pre_sequences, "circular", TMprimer);
			results = G2Utils.results(sequences_ext, "circular", TM, TMh, TMprimer, tolerance);
			if (results != null) {
				homologys = results.get(0);
				primers_fwd = results.get(1);
				primers_rev = results.get(2);
				primers_fwd.get(0).setSeq1(homologys.get(homologys.size() -1).getSeq2());
			} else {
				FacesContext context;
				context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Not coincidences"));
			}

		}

	}

	public void add() {

		Input input = new Input(this.name, this.sequence);
		inputs.add(input);

		this.name = "";
		this.sequence = "";

	}

	public void remove(Input input) {
		inputs.remove(input);

	}

}
