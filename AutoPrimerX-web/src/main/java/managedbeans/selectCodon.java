/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import primerg3Domain.Codon;
import primerg3Domain.CondonUsage;
 
@ManagedBean
@ViewScoped
public class selectCodon implements Serializable {
     
    private CondonUsage codonusage;
     
    @PostConstruct
    public void init() {
        ArrayList <Codon> G= new ArrayList<>();
            G.add(new Codon("GGG",16.47));
            G.add(new Codon("GGA",16.47));
            G.add(new Codon("GGT",10.75));
            G.add(new Codon("GGC",22.22));
        ArrayList <Codon> A= new ArrayList<>();
            A.add(new Codon("GCG",7.37));
            A.add(new Codon("GCA",15.82));
            A.add(new Codon("GCT",18.45));
            A.add(new Codon("GCC",27.73));
        ArrayList <Codon> L= new ArrayList<>();
            L.add(new Codon("TTG",12.93));
            L.add(new Codon("TTA",7.67));
            L.add(new Codon("CTG",39.64));
            L.add(new Codon("CTA",7.15));
            L.add(new Codon("CTT",13.19));
            L.add(new Codon("CTC",19.59));
        ArrayList <Codon> M= new ArrayList<>();
            M.add(new Codon("ATG",22.04));
        ArrayList <Codon> F= new ArrayList<>();
            F.add(new Codon("TTT",17.57));
            F.add(new Codon("TTC",20.28));
        ArrayList <Codon> W= new ArrayList<>();
            W.add(new Codon("TGG",13.17));
        ArrayList <Codon> K= new ArrayList<>();
            K.add(new Codon("AAG",31.86));
            K.add(new Codon("AAA",24.44));
        ArrayList <Codon> E= new ArrayList<>();
            E.add(new Codon("GAG",39.59));
            E.add(new Codon("GAA",28.96));
        ArrayList <Codon> S= new ArrayList<>();
            S.add(new Codon("AGT",12.13));
            S.add(new Codon("AGC",19.46));
            S.add(new Codon("TCG",4.41));
            S.add(new Codon("TCA",12.21));
            S.add(new Codon("TCT",15.22));
            S.add(new Codon("TCC",17.68));
        ArrayList <Codon> V= new ArrayList<>();
            V.add(new Codon("GTG",28.12));
            V.add(new Codon("GTA",7.08));
            V.add(new Codon("GTT",11.03));
            V.add(new Codon("GTC",14.46));
        ArrayList <Codon> I= new ArrayList<>();
            I.add(new Codon("ATA",7.49));
            I.add(new Codon("ATT",16.00));
            I.add(new Codon("ATC",20.82));
        ArrayList <Codon> C= new ArrayList<>();
            C.add(new Codon("TGT",10.58));
            C.add(new Codon("TGC",12.62));
        ArrayList <Codon> Y= new ArrayList<>();
            Y.add(new Codon("TAT",12.19));
            Y.add(new Codon("TAC",15.31));
        ArrayList <Codon> H= new ArrayList<>();
            H.add(new Codon("CAT",10.86));
            H.add(new Codon("CAC",15.09));
        ArrayList <Codon> R= new ArrayList<>();
            R.add(new Codon("AGG",11.96));
            R.add(new Codon("AGA",12.17));
            R.add(new Codon("CGG",11.42));
            R.add(new Codon("CGA",6.17));
            R.add(new Codon("CGT",4.54));
            R.add(new Codon("CGC",10.42));
        ArrayList <Codon> N= new ArrayList<>();
            N.add(new Codon("AAT",16.96));
            N.add(new Codon("AAC",19.10));
        ArrayList <Codon> D= new ArrayList<>();
            D.add(new Codon("GAT",21.78));
            D.add(new Codon("GAC",25.10));
        ArrayList <Codon> T= new ArrayList<>();
            T.add(new Codon("ACG",6.05));
            T.add(new Codon("ACA",15.11));
            T.add(new Codon("ACT",13.12));
            T.add(new Codon("ACC",18.89));
        ArrayList <Codon> P= new ArrayList<>();
            T.add(new Codon("CCU",17.5));
            T.add(new Codon("CCC",19.8));
            T.add(new Codon("CCA",16.9));
            T.add(new Codon("CCG",6.9));
        ArrayList <Codon> Q= new ArrayList<>();
            T.add(new Codon("CAA",12.3));
            T.add(new Codon("CAG ", 34.2));
        codonusage = new CondonUsage(A, C, D, E, F, G, H, I, K, M, N, L , P, Q, R, S, T, V, W, Y);
    }
 
    
}