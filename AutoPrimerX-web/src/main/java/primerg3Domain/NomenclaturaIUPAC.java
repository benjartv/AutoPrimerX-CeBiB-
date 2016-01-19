/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg3Domain;

/**
 *
 * @author Dany
 */
public class NomenclaturaIUPAC {
    private String nombreletra;
    private Integer A;
    private Integer C;
    private Integer G;
    private Integer ToU; //Timina o Uracilo

    public NomenclaturaIUPAC(String nombreletra, Integer A, Integer C, Integer G, Integer ToU) {
        this.nombreletra = nombreletra;
        this.A = A;
        this.C = C;
        this.G = G;
        this.ToU = ToU;
    }

    public String getNombreletra() {
        return nombreletra;
    }

    public void setNombreletra(String nombreletra) {
        this.nombreletra = nombreletra;
    }

    public Integer getA() {
        return A;
    }

    public void setA(Integer A) {
        this.A = A;
    }

    public Integer getC() {
        return C;
    }

    public void setC(Integer C) {
        this.C = C;
    }

    public Integer getG() {
        return G;
    }

    public void setG(Integer G) {
        this.G = G;
    }

    public Integer getToU() {
        return ToU;
    }

    public void setToU(Integer ToU) {
        this.ToU = ToU;
    }
    
    
}
