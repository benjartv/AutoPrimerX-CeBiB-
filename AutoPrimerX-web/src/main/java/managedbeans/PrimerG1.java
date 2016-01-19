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
    private int largo1 = 1;
    private int largo2 = 1;
    
    private List<Primer> primers_seq;
    private List<Primer> primers_com;
    
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

    public List<Primer> getPrimers_seq() {
        return primers_seq;
    }

    public void setPrimers_seq(List<Primer> primers_seq) {
        this.primers_seq = primers_seq;
    }

    public List<Primer> getPrimers_com() {
        return primers_com;
    }

    public void setPrimers_com(List<Primer> primers_com) {
        this.primers_com = primers_com;
    }


    public void submit(){
        sequence = sequence.replaceAll("\\s+","");
        size_seq = sequence.length();
        com_sequence = service.complemento(sequence);
        String rev_sequence = new StringBuilder(sequence).reverse().toString();
        primers_com = service.createPrimer(com_sequence, largo1, largo2);
        primers_seq = service.createPrimer(rev_sequence, largo1, largo2);
    }
    
    
    
    
}
