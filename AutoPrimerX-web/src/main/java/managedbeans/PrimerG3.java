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

    public String getSequences() {
        return sequences;
    }

    public void setSequences(String sequences) {
        this.sequences = sequences;
    }
    
    
}
