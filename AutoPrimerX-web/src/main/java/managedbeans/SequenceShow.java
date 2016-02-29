/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@SessionScoped
public class SequenceShow implements Serializable {
    
    private String seq;
    private boolean sitioConservado;
    
    public SequenceShow(String str){
        this.seq = str;
        this.sitioConservado = false;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    public boolean isSitioConservado() {
        return sitioConservado;
    }

    public void setSitioConservado(boolean sitioConservado) {
        this.sitioConservado = sitioConservado;
    }
    
}
