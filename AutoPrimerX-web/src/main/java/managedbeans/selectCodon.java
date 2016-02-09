/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.CodonUsage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import primerg3Domain.Codon;
import primerg3Domain.CondonUsage;
 
@ManagedBean
@ViewScoped
public class selectCodon implements Serializable {
     
    private static CondonUsage codonusage;
    private static String Aglobal;
    private static String Cglobal;
    private static String Dglobal;
    private static String Eglobal;
    private static String Fglobal;
    private static String Gglobal;
    private static String Hglobal;
    private static String Iglobal;
    private static String Kglobal;
    private static String Lglobal;
    private static String Mglobal;
    private static String Nglobal;
    private static String Pglobal;
    private static String Qglobal;
    private static String Rglobal;
    private static String Sglobal;
    private static String Tglobal;
    private static String Vglobal;
    private static String Wglobal;
    private static String Yglobal;
    
    /*
    @PostConstruct
    public void init() {
        ArrayList <Codon> G= new ArrayList<>();
            G.add(new Codon("GGG",16.47));
            G.add(new Codon("GGA",16.47));
            G.add(new Codon("GGT",10.75));
            G.add(new Codon("GGC",22.22));
            Collections.sort(G, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> A= new ArrayList<>();
            A.add(new Codon("GCG",7.37));
            A.add(new Codon("GCA",15.82));
            A.add(new Codon("GCT",18.45));
            A.add(new Codon("GCC",27.73));
            Collections.sort(A, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> L= new ArrayList<>();
            L.add(new Codon("TTG",12.93));
            L.add(new Codon("TTA",7.67));
            L.add(new Codon("CTG",39.64));
            L.add(new Codon("CTA",7.15));
            L.add(new Codon("CTT",13.19));
            L.add(new Codon("CTC",19.59));
            Collections.sort(L, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> M= new ArrayList<>();
            M.add(new Codon("ATG",22.04));
        ArrayList <Codon> F= new ArrayList<>();
            F.add(new Codon("TTT",17.57));
            F.add(new Codon("TTC",20.28));
            Collections.sort(F, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> W= new ArrayList<>();
            W.add(new Codon("TGG",13.17));
        ArrayList <Codon> K= new ArrayList<>();
            K.add(new Codon("AAG",31.86));
            K.add(new Codon("AAA",24.44));
            Collections.sort(K, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> E= new ArrayList<>();
            E.add(new Codon("GAG",39.59));
            E.add(new Codon("GAA",28.96));
            Collections.sort(E, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> S= new ArrayList<>();
            S.add(new Codon("AGT",12.13));
            S.add(new Codon("AGC",19.46));
            S.add(new Codon("TCG",4.41));
            S.add(new Codon("TCA",12.21));
            S.add(new Codon("TCT",15.22));
            S.add(new Codon("TCC",17.68));
            Collections.sort(S, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> V= new ArrayList<>();
            V.add(new Codon("GTG",28.12));
            V.add(new Codon("GTA",7.08));
            V.add(new Codon("GTT",11.03));
            V.add(new Codon("GTC",14.46));
            Collections.sort(V, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> I= new ArrayList<>();
            I.add(new Codon("ATA",7.49));
            I.add(new Codon("ATT",16.00));
            I.add(new Codon("ATC",20.82));
            Collections.sort(I, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> C= new ArrayList<>();
            C.add(new Codon("TGT",10.58));
            C.add(new Codon("TGC",12.62));
            Collections.sort(C, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> Y= new ArrayList<>();
            Y.add(new Codon("TAT",12.19));
            Y.add(new Codon("TAC",15.31));
            Collections.sort(Y, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> H= new ArrayList<>();
            H.add(new Codon("CAT",10.86));
            H.add(new Codon("CAC",15.09));
            Collections.sort(H, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> R= new ArrayList<>();
            R.add(new Codon("AGG",11.96));
            R.add(new Codon("AGA",12.17));
            R.add(new Codon("CGG",11.42));
            R.add(new Codon("CGA",6.17));
            R.add(new Codon("CGT",4.54));
            R.add(new Codon("CGC",10.42));
            Collections.sort(R, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> N= new ArrayList<>();
            N.add(new Codon("AAT",16.96));
            N.add(new Codon("AAC",19.10));
            Collections.sort(N, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> D= new ArrayList<>();
            D.add(new Codon("GAT",21.78));
            D.add(new Codon("GAC",25.10));
            Collections.sort(D, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> T= new ArrayList<>();
            T.add(new Codon("ACG",6.05));
            T.add(new Codon("ACA",15.11));
            T.add(new Codon("ACT",13.12));
            T.add(new Codon("ACC",18.89));
            Collections.sort(T, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> P= new ArrayList<>();
            P.add(new Codon("CCU",17.5));
            P.add(new Codon("CCC",19.8));
            P.add(new Codon("CCA",16.9));
            P.add(new Codon("CCG",6.9));
            Collections.sort(P, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> Q= new ArrayList<>();
            Q.add(new Codon("CAA",12.3));
            Q.add(new Codon("CAG ", 34.2));
            Collections.sort(Q, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        codonusage = new CondonUsage(A, C, D, E, F, G, H, I, K, L, M, N , P, Q, R, S, T, V, W, Y);
    }*/
    
    public CondonUsage getCodonusage() {
        return codonusage;
    }

    public void setCodonusage(CondonUsage codonusage) {
        this.codonusage = codonusage;
    }

    public String getA() {
        return Aglobal;
    }

    public void setA(String A) {
        this.Aglobal = A;
    }

    public String getAglobal() {
        return Aglobal;
    }

    public void setAglobal(String Aglobal) {
        this.Aglobal = Aglobal;
    }

    public String getCglobal() {
        return Cglobal;
    }

    public void setCglobal(String Cglobal) {
        this.Cglobal = Cglobal;
    }

    public String getDglobal() {
        return Dglobal;
    }

    public void setDglobal(String Dglobal) {
        this.Dglobal = Dglobal;
    }

    public String getEglobal() {
        return Eglobal;
    }

    public void setEglobal(String Eglobal) {
        this.Eglobal = Eglobal;
    }

    public String getFglobal() {
        return Fglobal;
    }

    public void setFglobal(String Fglobal) {
        this.Fglobal = Fglobal;
    }

    public String getGglobal() {
        return Gglobal;
    }

    public void setGglobal(String Gglobal) {
        this.Gglobal = Gglobal;
    }

    public String getHglobal() {
        return Hglobal;
    }

    public void setHglobal(String Hglobal) {
        this.Hglobal = Hglobal;
    }

    public String getIglobal() {
        return Iglobal;
    }

    public void setIglobal(String Iglobal) {
        this.Iglobal = Iglobal;
    }

    public String getKglobal() {
        return Kglobal;
    }

    public void setKglobal(String Kglobal) {
        this.Kglobal = Kglobal;
    }

    public String getLglobal() {
        return Lglobal;
    }

    public void setLglobal(String Lglobal) {
        this.Lglobal = Lglobal;
    }

    public String getMglobal() {
        return Mglobal;
    }

    public void setMglobal(String Mglobal) {
        this.Mglobal = Mglobal;
    }

    public String getNglobal() {
        return Nglobal;
    }

    public void setNglobal(String Nglobal) {
        this.Nglobal = Nglobal;
    }

    public String getPglobal() {
        return Pglobal;
    }

    public void setPglobal(String Pglobal) {
        this.Pglobal = Pglobal;
    }

    public String getQglobal() {
        return Qglobal;
    }

    public void setQglobal(String Qglobal) {
        this.Qglobal = Qglobal;
    }

    public String getRglobal() {
        return Rglobal;
    }

    public void setRglobal(String Rglobal) {
        this.Rglobal = Rglobal;
    }

    public String getSglobal() {
        return Sglobal;
    }

    public void setSglobal(String Sglobal) {
        this.Sglobal = Sglobal;
    }

    public String getTglobal() {
        return Tglobal;
    }

    public void setTglobal(String Tglobal) {
        this.Tglobal = Tglobal;
    }

    public String getVglobal() {
        return Vglobal;
    }

    public void setVglobal(String Vglobal) {
        this.Vglobal = Vglobal;
    }

    public String getWglobal() {
        return Wglobal;
    }

    public void setWglobal(String Wglobal) {
        this.Wglobal = Wglobal;
    }

    public String getYglobal() {
        return Yglobal;
    }

    public void setYglobal(String Yglobal) {
        this.Yglobal = Yglobal;
    }
    
    public Codon getProbabilidadMayor(ArrayList amino){
        Codon max = (Codon) amino.get(0);
        
        for(int i=1; i<amino.size(); i++){
            Codon aux = (Codon) amino.get(i);
            if(max.getProbabilidad() < aux.getProbabilidad()){
                max = aux;
            }
        }
        
        return max;
    }
    
    public String getlabel(Codon codon){
        return codon.getcodon() + " : " + codon.getProbabilidad();
    }
    
}