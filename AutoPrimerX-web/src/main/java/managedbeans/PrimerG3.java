/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import managedbeans.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import primerg3Domain.NomenclaturaIUPAC;

@Named(value = "primerG3")
@ManagedBean
public class PrimerG3 {
    private String sequences;
    private String log;
    public ArrayList<NomenclaturaIUPAC> iupac;
    
    
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    
    public String getSequences() {
        return sequences;
    }

    public void setSequences(String sequences) {
        this.sequences = sequences;
    }
    
    public void submit(){
        createIUPAC();
        for (int i = 0; i< iupac.size(); i++) {
            System.out.println(iupac.get(i).getNombreletra());
        }
        String [] sequencesSplit = sequences.split("\n");
        for (int i = 0; i < sequencesSplit.length; i++) {
            sequencesSplit[i] = sequencesSplit[i].trim();
        }
        Integer largo = sequencesSplit[0].length();
        for (int i = 1; i < sequencesSplit.length; i++) {
               if(largo != sequencesSplit[i].length()) {
                   log = "Las sequiencias deben tener el mismo largo";
                   break;
            } else log = "OK";
        }
    
    }
    
    public void createIUPAC(){
            iupac = new ArrayList();
                /*
    A Adenina
    C Citocina
    G Guanina
    T o U Timina o Uracilo
    R A o G
    Y C o T
    S G o C
    W A o T
    K G o T
    M A o C
    B C o G o T
    D A o G o T
    H A o C o T
    V A o C o G
    N Cualquiera
    */
            iupac.add(new NomenclaturaIUPAC("A",1,0,0,0));
            iupac.add(new NomenclaturaIUPAC("C",0,1,0,0));
            iupac.add(new NomenclaturaIUPAC("G",0,0,1,0));
            iupac.add(new NomenclaturaIUPAC("ToU",0,0,0,1));
            iupac.add(new NomenclaturaIUPAC("R",1,0,1,0));
            iupac.add(new NomenclaturaIUPAC("Y",0,1,0,1));
            iupac.add(new NomenclaturaIUPAC("S",0,1,1,0));
            iupac.add(new NomenclaturaIUPAC("W",1,0,0,1));
            iupac.add(new NomenclaturaIUPAC("K",0,0,1,1));
            iupac.add(new NomenclaturaIUPAC("M",1,1,0,0));
            iupac.add(new NomenclaturaIUPAC("B",0,1,1,1));
            iupac.add(new NomenclaturaIUPAC("D",1,0,1,1));
            iupac.add(new NomenclaturaIUPAC("H",1,1,0,1));
            iupac.add(new NomenclaturaIUPAC("V",1,1,1,0));
            iupac.add(new NomenclaturaIUPAC("N",1,1,1,1));
            
    }
    
}
