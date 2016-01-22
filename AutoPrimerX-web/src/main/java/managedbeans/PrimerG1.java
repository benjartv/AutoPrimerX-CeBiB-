/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import managedbeans.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import primerg1Domain.Primer;

/**
 *
 * @author MacBookPro
 */
@Named(value = "primerG1")
@ManagedBean
public class PrimerG1{

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
    
    private Primer service = new Primer();
    
    public PrimerG1() {
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


    public void submit(){
        sequence = sequence.replaceAll("\\s+","");
        size_seq = sequence.length();
        com_sequence = service.complemento(sequence);
        String sequence_back = new StringBuilder(sequence).reverse().toString();
        primers_rev = service.createPrimer(sequence_back, com_sequence, largo1, largo2);
        primers_fwd = service.createPrimer(com_sequence, sequence_back, largo1, largo2);
    }
    
    
    
    
}
