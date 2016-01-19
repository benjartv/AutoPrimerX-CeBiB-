/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg3Domain;

import java.util.ArrayList;

public class CondonUsage {
    
        private ArrayList <Codon> A;
        private ArrayList <Codon> C;
        private ArrayList <Codon> D;
        private ArrayList <Codon> E;
        private ArrayList <Codon> F;
        private ArrayList <Codon> G;
        private ArrayList <Codon> H;
        private ArrayList <Codon> I;
        private ArrayList <Codon> K;
        private ArrayList <Codon> L;
        private ArrayList <Codon> M;
        private ArrayList <Codon> N;
        private ArrayList <Codon> P;
        private ArrayList <Codon> Q;
        private ArrayList <Codon> R;
        private ArrayList <Codon> S;
        private ArrayList <Codon> T;
        private ArrayList <Codon> V;
        private ArrayList <Codon> W;
        private ArrayList <Codon> Y;

    // Esta variable guarda los nombres que significan los id de los aminoácidos
    public static final String [][] sinngleletter = {
        {"A", "Ala", "Alanine"},
        {"C", "Cys", "Cysteine"},
        {"D", "Asp", "Aspartic Acid"},
        {"E", "Glu", "Glutamic Acid"},
        {"F", "Phe", "Phenylalanine"},
        {"G", "Gly", "Glycine"},
        {"H", "His", "Histidine"},
        {"I", "Ile", "Isoleucine"},
        {"K", "Lys", "Lysine"},
        {"L", "Leu", "Leucine"},
        {"M", "Met", "Methionine"},
        {"N", "Asn", "Asparagine"},
        {"P", "Pro", "Proline"},
        {"Q", "Gln", "Glutamine"},
        {"R", "Arg", "Arginine"},
        {"S", "Ser", "Serine"},
        {"T", "Thr", "Threonine"},
        {"V", "Val", "Valine"},
        {"W", "Trp", "Tryptophan"},
        {"Y", "Tyr", "Tyrosine"}
    };

  
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

    public CondonUsage(ArrayList<Codon> A, ArrayList<Codon> C, ArrayList<Codon> D, ArrayList<Codon> E, ArrayList<Codon> F, ArrayList<Codon> G, ArrayList<Codon> H, ArrayList<Codon> I, ArrayList<Codon> K, ArrayList<Codon> L, ArrayList<Codon> M, ArrayList<Codon> N, ArrayList<Codon> P, ArrayList<Codon> Q, ArrayList<Codon> R, ArrayList<Codon> S, ArrayList<Codon> T, ArrayList<Codon> V, ArrayList<Codon> W, ArrayList<Codon> Y) {
        this.A = A;
        this.C = C;
        this.D = D;
        this.E = E;
        this.F = F;
        this.G = G;
        this.H = H;
        this.I = I;
        this.K = K;
        this.L = L;
        this.M = M;
        this.N = N;
        this.P = P;
        this.Q = Q;
        this.R = R;
        this.S = S;
        this.T = T;
        this.V = V;
        this.W = W;
        this.Y = Y;
    }

    public ArrayList<Codon> getA() {
        return A;
    }

    public void setA(ArrayList<Codon> A) {
        this.A = A;
    }

    public ArrayList<Codon> getC() {
        return C;
    }

    public void setC(ArrayList<Codon> C) {
        this.C = C;
    }

    public ArrayList<Codon> getD() {
        return D;
    }

    public void setD(ArrayList<Codon> D) {
        this.D = D;
    }

    public ArrayList<Codon> getE() {
        return E;
    }

    public void setE(ArrayList<Codon> E) {
        this.E = E;
    }

    public ArrayList<Codon> getF() {
        return F;
    }

    public void setF(ArrayList<Codon> F) {
        this.F = F;
    }

    public ArrayList<Codon> getG() {
        return G;
    }

    public void setG(ArrayList<Codon> G) {
        this.G = G;
    }

    public ArrayList<Codon> getH() {
        return H;
    }

    public void setH(ArrayList<Codon> H) {
        this.H = H;
    }

    public ArrayList<Codon> getI() {
        return I;
    }

    public void setI(ArrayList<Codon> I) {
        this.I = I;
    }

    public ArrayList<Codon> getK() {
        return K;
    }

    public void setK(ArrayList<Codon> K) {
        this.K = K;
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

    public ArrayList<Codon> getN() {
        return N;
    }

    public void setN(ArrayList<Codon> N) {
        this.N = N;
    }

    public ArrayList<Codon> getP() {
        return P;
    }

    public void setP(ArrayList<Codon> P) {
        this.P = P;
    }

    public ArrayList<Codon> getQ() {
        return Q;
    }

    public void setQ(ArrayList<Codon> Q) {
        this.Q = Q;
    }

    public ArrayList<Codon> getR() {
        return R;
    }

    public void setR(ArrayList<Codon> R) {
        this.R = R;
    }

    public ArrayList<Codon> getS() {
        return S;
    }

    public void setS(ArrayList<Codon> S) {
        this.S = S;
    }

    public ArrayList<Codon> getT() {
        return T;
    }

    public void setT(ArrayList<Codon> T) {
        this.T = T;
    }

    public ArrayList<Codon> getV() {
        return V;
    }

    public void setV(ArrayList<Codon> V) {
        this.V = V;
    }

    public ArrayList<Codon> getW() {
        return W;
    }

    public void setW(ArrayList<Codon> W) {
        this.W = W;
    }

    public ArrayList<Codon> getY() {
        return Y;
    }

    public void setY(ArrayList<Codon> Y) {
        this.Y = Y;
    }

    
}









