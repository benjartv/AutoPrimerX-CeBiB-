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
public class PrimerRF{
    int id;
    String pri_seq_fw;
    String pri_seq_rv;
    int length_fw;
    int length_rv;
    double Tmr;
    double Tmf;
    double TmD;
    double TmP;
    double GCr;
    double GCf;
    String fwdseqLCS;
    String fwdcomLCS;
    String revseqLCS;
    String revcomLCS;
    String fwdLCS;
    String revLCS;
    String frLCS;
    
    
    
    PrimerRF(int id, String pri_seq_fw, String pri_seq_rv, int length_fw, 
            int length_rv, double Tmf, double Tmr, double TmD, double TmP, double GCf, double GCr, 
            String fwdseqLCS, String fwdcomLCS, String revseqLCS, String revcomLCS, String fwdLCS, String revLCS, String frLCS){
        this.id = id;
        this.pri_seq_fw = pri_seq_fw;
        this.pri_seq_rv = pri_seq_rv;
        this.length_fw = length_fw;
        this.length_rv = length_rv;
        this.Tmf = Tmf;
        this.Tmr = Tmr;
        this.TmD = TmD;
        this.TmP = TmP;
        this.GCf = GCf;
        this.GCr = GCr;
        this.fwdseqLCS = fwdseqLCS;
        this.fwdcomLCS = fwdcomLCS;
        this.revseqLCS = revseqLCS;
        this.revcomLCS = revcomLCS;
        this.fwdLCS = fwdLCS;
        this.revLCS = revLCS;
        this.frLCS = frLCS;
    }
    
    public PrimerRF(){
    }
    
    public List<PrimerRF> findbestPrimers(List<Primer> p_fwd, List<Primer> p_rv, String seq, String comp_seq){
        List<PrimerRF> primers_list = new ArrayList<PrimerRF>();
        for (Primer p_fwd1 : p_fwd) {
            for (Primer p_rv1 : p_rv) {
                //System.out.println("Tm fwd: "+ p_fwd.get(i).Tm);
                //System.out.println("Tm rev: "+ p_rv.get(j).Tm);
                //System.out.println("Tm delta: " + Math.abs(p_fwd.get(i).Tm - p_rv.get(j).Tm));
                if (Math.abs(p_fwd1.Tm - p_rv1.Tm) <= 1.00) {
                    //System.out.println("MATCH");
                    double TmP1 = p_fwd1.Tm + p_rv1.Tm;
                    TmP1 = TmP1 / 2;
                    TmP1 = (double)Math.round(TmP1 * 100d) / 100d;
                    double TmD1 = Math.abs(p_fwd1.Tm - p_rv1.Tm);
                    TmD1 = (double)Math.round(TmD1 * 100d) / 100d;
                    String pfwd_rev = new StringBuilder(p_fwd1.seq).reverse().toString();
                    String prev_rev = new StringBuilder(p_rv1.seq).reverse().toString();
                    PrimerRF pmatch = new PrimerRF(0, p_fwd1.seq, p_rv1.seq, p_fwd1.largo, p_rv1.largo, p_fwd1.Tm,
                            p_rv1.Tm, TmD1, TmP1, p_fwd1.GC, p_rv1.GC, 
                            alignPrimer(comp_seq, p_fwd1.seq), alignPrimer2(seq, p_fwd1.seq),
                            alignPrimer(seq, p_rv1.seq), alignPrimer2(comp_seq, p_rv1.seq),
                            alignPrimer2(pfwd_rev, p_fwd1.seq), alignPrimer2(prev_rev, p_rv1.seq),
                            alignPrimer2(p_fwd1.seq, p_rv1.seq));
                    primers_list.add(pmatch);
                }
            }
        }
        return primers_list;
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
    
    public double calculateTm(String seq){
        int A = 0;
        int C = 0;
        int T = 0;
        int G = 0;
        double Tm;
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
            Tm = (A+T) * 2 + (G+C) * 4;
        }
        else{
            Tm = 64.9 +41*(G+C-16.4)/(A+T+G+C);
        }
        Tm = (double)Math.round(Tm * 100d) / 100d;
        return Tm;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTmP() {
        return TmP;
    }

    public String getFwdLCS() {
        return fwdLCS;
    }

    public void setFwdLCS(String fwdLCS) {
        this.fwdLCS = fwdLCS;
    }

    public String getRevLCS() {
        return revLCS;
    }

    public void setRevLCS(String revLCS) {
        this.revLCS = revLCS;
    }

    public String getFwdseqLCS() {
        return fwdseqLCS;
    }

    public void setFwdseqLCS(String fwdseqLCS) {
        this.fwdseqLCS = fwdseqLCS;
    }

    public String getFwdcomLCS() {
        return fwdcomLCS;
    }

    public void setFwdcomLCS(String fwdcomLCS) {
        this.fwdcomLCS = fwdcomLCS;
    }

    public String getRevseqLCS() {
        return revseqLCS;
    }

    public void setRevseqLCS(String revseqLCS) {
        this.revseqLCS = revseqLCS;
    }

    public String getRevcomLCS() {
        return revcomLCS;
    }

    public void setRevcomLCS(String revcomLCS) {
        this.revcomLCS = revcomLCS;
    }

    public String getFrLCS() {
        return frLCS;
    }

    public void setFrLCS(String frLCS) {
        this.frLCS = frLCS;
    }

    public void setTmP(double TmP) {
        this.TmP = TmP;
    }

    public String getPri_seq_fw() {
        return pri_seq_fw;
    }

    public void setPri_seq_fw(String pri_seq_fw) {
        this.pri_seq_fw = pri_seq_fw;
    }

    public String getPri_seq_rv() {
        return pri_seq_rv;
    }

    public void setPri_seq_rv(String pri_seq_rv) {
        this.pri_seq_rv = pri_seq_rv;
    }

    public double getTmr() {
        return Tmr;
    }

    public void setTmr(double Tmr) {
        this.Tmr = Tmr;
    }

    public double getTmf() {
        return Tmf;
    }

    public void setTmf(double Tmf) {
        this.Tmf = Tmf;
    }

    public double getTmD() {
        return TmD;
    }

    public void setTmD(double TmD) {
        this.TmD = TmD;
    }

    public double getGCr() {
        return GCr;
    }

    public void setGCr(double GCr) {
        this.GCr = GCr;
    }

    public double getGCf() {
        return GCf;
    }

    public void setGCf(double GCf) {
        this.GCf = GCf;
    }

    public int getLength_fw() {
        return length_fw;
    }

    public void setLength_fw(int length_fw) {
        this.length_fw = length_fw;
    }

    public int getLength_rv() {
        return length_rv;
    }

    public void setLength_rv(int length_rv) {
        this.length_rv = length_rv;
    }

    
    
    
    
}
