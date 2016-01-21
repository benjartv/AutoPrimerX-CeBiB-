/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg3Domain;


public class Codon {
    private String codon;
    private Double probabilidad;

    public Codon(String codon, Double probabilidad) {
        this.codon = codon;
        this.probabilidad = probabilidad;
    }
    
    public Codon(){
        this.codon = "";
        this.probabilidad = 0.0;
    }
    
    public String getcodon() {
        return codon;
    }

    public void setcodon(String codon) {
        this.codon = codon;
    }

    public Double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Double probabilidad) {
        this.probabilidad = probabilidad;
    }
    
    
    
}
