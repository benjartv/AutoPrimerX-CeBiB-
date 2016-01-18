/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg3Domain;


public class Codon {
    private String cddon;
    private Double probabilidad;

    public Codon(String cddon, Double probabilidad) {
        this.cddon = cddon;
        this.probabilidad = probabilidad;
    }

    public String getCddon() {
        return cddon;
    }

    public void setCddon(String cddon) {
        this.cddon = cddon;
    }

    public Double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Double probabilidad) {
        this.probabilidad = probabilidad;
    }
    
    
    
}
