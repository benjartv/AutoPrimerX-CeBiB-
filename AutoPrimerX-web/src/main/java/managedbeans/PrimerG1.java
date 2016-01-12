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
    private String rev_sequence;
    
    private String text;
    
    public PrimerG1() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

        
    
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getRev_sequence() {
        return rev_sequence;
    }

    public void setRev_sequence(String rev_sequence) {
        this.rev_sequence = rev_sequence;
    }
    public void submit(){
        char[] charArray = new char[sequence.length()];
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == 'A') {
                charArray[i] = 'T';
            }
            else{
                if (sequence.charAt(i) == 'T') {
                    charArray[i] = 'A';
                }
                else{
                    if (sequence.charAt(i) == 'G') {
                        charArray[i] = 'C';
                    }
                    else{
                        if (sequence.charAt(i) == 'C') {
                            charArray[i] = 'G';
                        }
                    }
                }
            }
        }
        rev_sequence = new String(charArray);
    }
}
