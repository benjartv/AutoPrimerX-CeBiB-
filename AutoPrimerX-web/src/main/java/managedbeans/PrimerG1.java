/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Enzyme;
import managedbeans.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import primerg1Domain.Primer;
import primerg1Domain.PrimerEnzRes;
import primerg1Domain.PrimerRF;
import sessionBeans.EnzymeFacadeLocal;

/**
 *
 * @author MacBookPro
 */
@Named(value = "primerG1")
@SessionScoped
public class PrimerG1 implements Serializable{

    /**
     * Creates a new instance of primerG1
     */
    private String sequence;
    private String com_sequence;
    private int size_seq;
    private int largo1 = 20;
    private int largo2 = 30;
    
    private List<Primer> primers_rev;
    private List<Primer> primers_fwd;
    private List<PrimerRF> primersMatch;
    private List<PrimerEnzRes> primersExt;
    
    private Enzyme enzima_sel;
    private Enzyme enzima_sel2;
    
    private Primer service = new Primer();
    private PrimerRF serviceMatch = new PrimerRF();
    private PrimerEnzRes serviceExt = new PrimerEnzRes();
    
    private PrimerEnzRes selected;
    
    private Boolean var = false;
    
    @EJB
    private EnzymeFacadeLocal enzymeFacade;

    public EnzymeFacadeLocal getEnzymeFacade() {
        return enzymeFacade;
    }
    
    public PrimerG1() {
    }

    public Enzyme getEnzima_sel2() {
        return enzima_sel2;
    }

    public void setEnzima_sel2(Enzyme enzima_sel2) {
        this.enzima_sel2 = enzima_sel2;
    }

    public Boolean getVar() {
        return var;
    }

    public void setVar(Boolean var) {
        this.var = var;
    }

    public PrimerEnzRes getSelected() {
        return selected;
    }

    public void setSelected(PrimerEnzRes selected) {
        this.selected = selected;
    }

    public Enzyme getEnzima_sel() {
        return enzima_sel;
    }

    public void setEnzima_sel(Enzyme enzima_sel) {
        this.enzima_sel = enzima_sel;
    }
    
    public int getSize_seq() {
        return size_seq;
    }

    public void setSize_seq(int size_seq) {
        this.size_seq = size_seq;
    }

    public int getLargo1() {
        return largo1;
    }

    public void setLargo1(int largo1) {
        this.largo1 = largo1;
    }

    public int getLargo2() {
        return largo2;
    }

    public void setLargo2(int largo2) {
        this.largo2 = largo2;
    }
    
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getCom_sequence() {
        return com_sequence;
    }

    public void setCom_sequence(String com_sequence) {
        this.com_sequence = com_sequence;
    }

    public List<Primer> getPrimers_rev() {
        return primers_rev;
    }

    public void setPrimers_rev(List<Primer> primers_rev) {
        this.primers_rev = primers_rev;
    }

    public List<Primer> getPrimers_fwd() {
        return primers_fwd;
    }

    public void setPrimers_fwd(List<Primer> primers_fwd) {
        this.primers_fwd = primers_fwd;
    }

    public List<PrimerRF> getPrimersMatch() {
        return primersMatch;
    }

    public void setPrimersMatch(List<PrimerRF> primersMatch) {
        this.primersMatch = primersMatch;
    }

    public List<PrimerEnzRes> getPrimersExt() {
        return primersExt;
    }

    public void setPrimersExt(List<PrimerEnzRes> primersExt) {
        this.primersExt = primersExt;
    }
    
    public void chageEnz(){
        var = true;
    }


    public void submit(){
        if (enzima_sel == null) {
            System.out.println("No hay enzima seleccionada");
            System.out.println("Iniciando...");
            sequence = sequence.replaceAll("\\s+","");
            size_seq = sequence.length();
            com_sequence = service.complemento(sequence);
            String sequence_back = new StringBuilder(sequence).reverse().toString();
            primers_rev = service.createPrimer(sequence_back, com_sequence, largo1, largo2);
            primers_fwd = service.createPrimer(com_sequence, sequence_back, largo1, largo2);
            System.out.println("Primers Calculados");
            System.out.println("Realizando match de primers");
            System.out.println("size rev: " + primers_rev.size());
            System.out.println("size fwd: " + primers_fwd.size());
            primersMatch = serviceMatch.findbestPrimers(primers_fwd, primers_rev, sequence_back, com_sequence);
            System.out.println("Proceso terminado");
        }
        else{
            System.out.println("Iniciando...");
            sequence = sequence.replaceAll("\\s+","");
            size_seq = sequence.length();
            com_sequence = service.complemento(sequence);
            String sequence_back = new StringBuilder(sequence).reverse().toString();
            //String sequence_back_comp = new StringBuilder(com_sequence).reverse().toString();
            primers_rev = service.createPrimer(sequence_back, com_sequence, largo1, largo2);
            primers_fwd = service.createPrimer(com_sequence, sequence_back, largo1, largo2);
            System.out.println("Primers Calculados");
            System.out.println("Realizando match de primers");
            System.out.println("size rev: " + primers_rev.size());
            System.out.println("size fwd: " + primers_fwd.size());
            primersMatch = serviceMatch.findbestPrimers(primers_fwd, primers_rev, sequence_back, com_sequence);
            if (enzima_sel2 == null) {
                System.out.println("Calculando primer extensión");
                System.out.println("Enzymas: " + enzima_sel.getDnaTarget() + ", " + enzima_sel.getDnaTarget() );
                primersExt = serviceExt.addEnzyme(primersMatch, enzima_sel, enzima_sel, sequence_back, com_sequence);
                System.out.println("Proceso terminado");
            }
            else{
                System.out.println("Calculando primer extensión");
                System.out.println("Enzymas: " + enzima_sel.getDnaTarget() + ", " + enzima_sel2.getDnaTarget() );
                primersExt = serviceExt.addEnzyme(primersMatch, enzima_sel, enzima_sel2, sequence_back, com_sequence);
                System.out.println("Proceso terminado");
            }
        }
        
        
    }
    
    
    
    
}
