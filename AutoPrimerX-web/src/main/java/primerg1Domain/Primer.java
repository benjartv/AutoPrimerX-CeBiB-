/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg1Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MacBookPro
 */
public class Primer {
    String seq;
    double Tm;
    double GC;
    int largo;
    String maxAF;
    String maxAR;

    
    Primer(String seq, double Tm, double GC, int largo, String maxAF, String maxAR){
        this.seq = seq;
        this.Tm = Tm;
        this.GC = GC;
        this.largo = largo;
        this.maxAF = maxAF;
        this.maxAR = maxAR;
    }

    public Primer() {
    }
    
    public List<Primer> createPrimer(String seq, String comp_seq, int largo1, int largo2){
        List<Primer> primer_list = new ArrayList<Primer>();
        for (int i = largo1; i <= largo2; i++) {
            char[] seq_array = new char[i];
            for (int j = 0; j < i; j++) {
                seq_array[j] = seq.charAt(j);
            }
            String seq_string = new String(seq_array);
            String primer_string = complemento(seq_string);
            Primer primer = new Primer(primer_string, calculateTm(primer_string), calculateGC(primer_string), primer_string.length(), 
                    "", "");
            primer_list.add(primer);
        }
        return primer_list;
    }
    
    
    public String alignPrimer(String seq, String primer){
        char[] blanks = new char[primer.length()];
        for (int i = 0; i < primer.length(); i++) {
            blanks[i] = '-';
        }
        String seq_blanks = new String(blanks);
        String seq_target = seq_blanks + seq.substring(primer.length()) + seq_blanks;
        
        String match = "";
        String match2 = "";
        
        for (int i = 0; i < seq.length(); i++) {
            for (int j = 0; j < primer.length(); j++) {
                if (seq_target.charAt(i+j) == complemNUCL(primer.charAt(j))) {
                    match2 = match2 + seq_target.charAt(i+j);
                }
                else{
                    if (match.length() < match2.length()) {
                        match = match2;
                    }
                    match2 = "";
                }
            }
        }
        double pTM = calculateTm(match);
        match = match + " - " + pTM;
        return match;
    }
    
    public String alignPrimer2(String seq, String primer){
        char[] blanks = new char[primer.length()];
        for (int i = 0; i < primer.length(); i++) {
            blanks[i] = '-';
        }
        String seq_blanks = new String(blanks);
        String seq_target = seq_blanks + seq + seq_blanks;
        
        String match = "";
        String match2 = "";
        
        for (int i = 0; i < seq.length(); i++) {
            for (int j = 0; j < primer.length(); j++) {
                if (seq_target.charAt(i+j) == complemNUCL(primer.charAt(j))) {
                    match2 = match2 + seq_target.charAt(i+j);
                }
                else{
                    if (match.length() < match2.length()) {
                        match = match2;
                    }
                    match2 = "";
                }
            }
        }
        double pTM = calculateTm(match);
        match = match + " - " + pTM;
        return match;
    }
    
    public char complemNUCL(char nucleotido){
        char resp;
        resp = 'X';
        if (nucleotido == 'A') {
            resp = 'T';
        }else{
            if (nucleotido == 'T') {
                resp = 'A';
            }else{
                if (nucleotido == 'G') {
                    resp = 'C';
                }else{
                    if (nucleotido == 'C') {
                        resp = 'G';
                    }
                }
            }
        }
        return resp;
    }
    
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public double getTm() {
        return Tm;
    }

    public void setTm(double Tm) {
        this.Tm = Tm;
    }

    public double getGC() {
        return GC;
    }

    public void setGC(double GC) {
        this.GC = GC;
    }

    public String getMaxAF() {
        return maxAF;
    }

    public void setMaxAF(String maxAF) {
        this.maxAF = maxAF;
    }

    public String getMaxAR() {
        return maxAR;
    }

    public void setMaxAR(String maxAR) {
        this.maxAR = maxAR;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    
    
     
    
    public double calculateTm(String seq){
        int A = 0;
        int C = 0;
        int T = 0;
        int G = 0;
        double Tm1;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == 'A') {
                A = A + 1;
            }
            else{
                if (seq.charAt(i) == 'T') {
                    T = T + 1;
                }
                else{
                    if (seq.charAt(i) == 'G') {
                        G = G + 1;
                    }
                    else{
                        if (seq.charAt(i) == 'C') {
                            C = C + 1;
                        }
                    }
                }
            }
        }
        if(seq.length() < 14){
            Tm1 = (A+T) * 2 + (G+C) * 4;
        }
        else{
            Tm1 = 64.9 +41*(G+C-16.4)/(A+T+G+C);
        }
        Tm1 = (double)Math.round(Tm1 * 100d) / 100d;
        return Tm1;
    }
    
    public double calculateGC(String seq){
        double GC1;
        double cantidadGC = 0;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == 'C' || seq.charAt(i) == 'G') {
                cantidadGC = cantidadGC + 1;
            }
        }
        GC1 = (cantidadGC / seq.length())*100.00;
        GC1 = (double)Math.round(GC1 * 100d) / 100d;
        return GC1;
    }
    
    public String complemento(String seq){
        char[] charArray = new char[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == 'A') {
                charArray[i] = 'T';
            }
            else{
                if (seq.charAt(i) == 'T') {
                    charArray[i] = 'A';
                }
                else{
                    if (seq.charAt(i) == 'G') {
                        charArray[i] = 'C';
                    }
                    else{
                        if (seq.charAt(i) == 'C') {
                            charArray[i] = 'G';
                        }
                    }
                }
            }
        }
        String comp_seq;
        comp_seq = new String(charArray);
        return comp_seq;
    }
    
}