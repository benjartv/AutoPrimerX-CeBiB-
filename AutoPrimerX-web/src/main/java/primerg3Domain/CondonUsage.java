/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg3Domain;

import java.util.ArrayList;

public class CondonUsage {
    
        private ArrayList <Codon> G;
        private ArrayList <Codon> A;
        private ArrayList <Codon> L;
        private ArrayList <Codon> M;
        private ArrayList <Codon> F;
        private ArrayList <Codon> W;
        private ArrayList <Codon> K;
        private ArrayList <Codon> E;
        private ArrayList <Codon> S;
        private ArrayList <Codon> V;
        private ArrayList <Codon> I;
        private ArrayList <Codon> C;
        private ArrayList <Codon> Y;
        private ArrayList <Codon> H;
        private ArrayList <Codon> R;
        private ArrayList <Codon> N;
        private ArrayList <Codon> D;
        private ArrayList <Codon> T;
    // Esta variable guarda los nombres que significan los id de los aminoácidos
    public static final String [][] sinngleletter = {
        {"G","Glycine","Gly"},
        {"A","Alanine","Ala"},
        {"L","Leucine","Leu"},
        {"M","Methionine","Met"},
        {"F","Phenylalanine","Phe"},
        {"W","Tryptophan","Trp"},
        {"K","Lysine","Lys"},
        {"E","Glutamic Acid","Glu"},
        {"S","Serine","Ser"},
        {"V","Valine","Val"},
        {"I","Isoleucine","Ile"},
        {"C","Cysteine","Cys"},
        {"Y","Tyrosine","Tyr"},
        {"H","Histidine","His"},
        {"R","Arginine","Arg"},
        {"N","Asparagine","Asn"},
        {"D","Aspartic Acid","Asp"},
        {"T","Threonine","Thr"}
    };

    public CondonUsage(ArrayList<Codon> G, ArrayList<Codon> A, ArrayList<Codon> L, ArrayList<Codon> M, ArrayList<Codon> F, ArrayList<Codon> W, ArrayList<Codon> K, ArrayList<Codon> E, ArrayList<Codon> S, ArrayList<Codon> V, ArrayList<Codon> I, ArrayList<Codon> C, ArrayList<Codon> Y, ArrayList<Codon> H, ArrayList<Codon> R, ArrayList<Codon> N, ArrayList<Codon> D, ArrayList<Codon> T) {
        this.G = G;
        this.A = A;
        this.L = L;
        this.M = M;
        this.F = F;
        this.W = W;
        this.K = K;
        this.E = E;
        this.S = S;
        this.V = V;
        this.I = I;
        this.C = C;
        this.Y = Y;
        this.H = H;
        this.R = R;
        this.N = N;
        this.D = D;
        this.T = T;
    }

  
/*
G GGG 16.47
G GGA 16.47
G GGT 10.75
G GGC 22.22
 
E GAG 39.59
E GAA 28.96
D GAT 21.78
D GAC 25.10
 
V GTG 28.12
V GTA 7.08
V GTT 11.03
V GTC 14.46
 
A GCG 7.37
A GCA 15.82
A GCT 18.45
A GCC 27.73
 
R AGG 11.96
R AGA 12.17
S AGT 12.13
S AGC 19.46
 
K AAG 31.86
K AAA 24.44
N AAT 16.96
N AAC 19.10
 
M ATG 22.04
I ATA 7.49
I ATT 16.00
I ATC 20.82
 
T ACG 6.05
T ACA 15.11
T ACT 13.12
T ACC 18.89
 
W TGG 13.17
C TGT 10.58
C TGC 12.62
 
Y TAT 12.19
Y TAC 15.31
 
L TTG 12.93
L TTA 7.67
F TTT 17.57
F TTC 20.28
 
S TCG 4.41
S TCA 12.21
S TCT 15.22
S TCC 17.68
 
R CGG 11.42
R CGA 6.17
R CGT 4.54
R CGC 10.42
 
Q CAG 34.23
Q CAA 12.34
H CAT 10.86
H CAC 15.09
 
L CTG 39.64
L CTA 7.15
L CTT 13.19
L CTC 19.59
 
P CCG 6.92      
P CCA 16.92      
P CCT 17.54 
P CCC 19.79
*/  
    public ArrayList<Codon> getG() {
        return G;
    }

    public void setG(ArrayList<Codon> G) {
        this.G = G;
    }

    public ArrayList<Codon> getA() {
        return A;
    }

    public void setA(ArrayList<Codon> A) {
        this.A = A;
    }

    public ArrayList<Codon> getL() {
        return L;
    }

    public void setL(ArrayList<Codon> L) {
        this.L = L;
    }

    public ArrayList<Codon> getM() {
        return M;
    }

    public void setM(ArrayList<Codon> M) {
        this.M = M;
    }

    public ArrayList<Codon> getF() {
        return F;
    }

    public void setF(ArrayList<Codon> F) {
        this.F = F;
    }

    public ArrayList<Codon> getW() {
        return W;
    }

    public void setW(ArrayList<Codon> W) {
        this.W = W;
    }

    public ArrayList<Codon> getK() {
        return K;
    }

    public void setK(ArrayList<Codon> K) {
        this.K = K;
    }

    public ArrayList<Codon> getE() {
        return E;
    }

    public void setE(ArrayList<Codon> E) {
        this.E = E;
    }

    public ArrayList<Codon> getS() {
        return S;
    }

    public void setS(ArrayList<Codon> S) {
        this.S = S;
    }

    public ArrayList<Codon> getV() {
        return V;
    }

    public void setV(ArrayList<Codon> V) {
        this.V = V;
    }

    public ArrayList<Codon> getI() {
        return I;
    }

    public void setI(ArrayList<Codon> I) {
        this.I = I;
    }

    public ArrayList<Codon> getC() {
        return C;
    }

    public void setC(ArrayList<Codon> C) {
        this.C = C;
    }

    public ArrayList<Codon> getY() {
        return Y;
    }

    public void setY(ArrayList<Codon> Y) {
        this.Y = Y;
    }

    public ArrayList<Codon> getH() {
        return H;
    }

    public void setH(ArrayList<Codon> H) {
        this.H = H;
    }

    public ArrayList<Codon> getR() {
        return R;
    }

    public void setR(ArrayList<Codon> R) {
        this.R = R;
    }

    public ArrayList<Codon> getN() {
        return N;
    }

    public void setN(ArrayList<Codon> N) {
        this.N = N;
    }

    public ArrayList<Codon> getD() {
        return D;
    }

    public void setD(ArrayList<Codon> D) {
        this.D = D;
    }

    public ArrayList<Codon> getT() {
        return T;
    }

    public void setT(ArrayList<Codon> T) {
        this.T = T;
    }
    
    
    
}









