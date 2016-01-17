/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import managedbeans.util.JsfUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;

@Named(value = "primerG3")
@ManagedBean
public class PrimerG3 {
    private String sequences;
    private String log;

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
        String [] sequencesSplit = sequences.split("\n");
        Integer largo = sequencesSplit[0].length();
        System.out.println(largo);
        // La ultima secuencia marca largo -1 no entiendo porque
        for (int i = 1; i < sequencesSplit.length; i++) {
               System.out.println("aa " + sequencesSplit[i].length() +" " +sequencesSplit[i] );
               if(largo != sequencesSplit[i].length()) {
                   log = "Las sequiencias deben tener el mismo largo";
                   break;
            } else log = "OK";
        }
    
    }
    
}
