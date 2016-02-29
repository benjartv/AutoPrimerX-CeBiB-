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
public class PrimerRF {
    String pri_seq_fw;
    String pri_seq_rv;
    int length_fw;
    int length_rv;
    double Tmr;
    double Tmf;
    double TmD;
    double GCr;
    double GCf;
    
    private PrimerRF(String pri_seq_fw, String pri_seq_rv, int length_fw, 
            int length_rv, double Tmf, double Tmr, double TmD, double GCf, double GCr){
        this.pri_seq_fw = pri_seq_fw;
        this.pri_seq_rv = pri_seq_rv;
        this.length_fw = length_fw;
        this.length_rv = length_rv;
        this.Tmf = Tmf;
        this.Tmr = Tmr;
        this.TmD = TmD;
        this.GCf = GCf;
        this.GCr = GCr;
    }
    
    public PrimerRF(){
    }
    
    public List<PrimerRF> findbestPrimers(List<Primer> p_fwd, List<Primer> p_rv){
        List<PrimerRF> primers_list = new ArrayList<PrimerRF>();
        for (int i = 0; i < p_fwd.size(); i++) {
            for (int j = 0; j < p_rv.size(); j++) {
                //System.out.println("Tm fwd: "+ p_fwd.get(i).Tm);
                //System.out.println("Tm rev: "+ p_rv.get(j).Tm);
                //System.out.println("Tm delta: " + Math.abs(p_fwd.get(i).Tm - p_rv.get(j).Tm));
                if (Math.abs(p_fwd.get(i).Tm - p_rv.get(j).Tm) <= 1.00) {
                    //System.out.println("MATCH");
                    PrimerRF pmatch = new PrimerRF(p_fwd.get(i).seq, p_rv.get(j).seq, p_fwd.get(i).largo, p_rv.get(j).largo,
                            p_fwd.get(i).Tm, p_rv.get(j).Tm, Math.abs(p_fwd.get(i).Tm - p_rv.get(j).Tm), p_fwd.get(i).GC,
                            p_rv.get(j).GC);
                    primers_list.add(pmatch);
                }
            }
        }
        return primers_list;
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
