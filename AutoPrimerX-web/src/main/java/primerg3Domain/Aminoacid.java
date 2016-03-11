/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg3Domain;

/**
 *
 * @author JAno
 */
public class Aminoacid {
    private String aminoacid;
    private String probabilidad;

    public Aminoacid(String aminoacid, String probabilidad){
        this.aminoacid = aminoacid;
        this.probabilidad = probabilidad;
    }
    
    public String getAminoacid() {
        return aminoacid;
    }

    public void setAminoacid(String aminoacid) {
        this.aminoacid = aminoacid;
    }

    public String getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
    }
    
    
}
