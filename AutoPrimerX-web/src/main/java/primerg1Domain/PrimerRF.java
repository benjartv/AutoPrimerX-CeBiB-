/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg1Domain;

import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.value;

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
    String frLCS;
    
    
    private static final int NEITHER     = 0;
    private static final int UP          = 1;
    private static final int LEFT        = 2;
    private static final int UP_AND_LEFT = 3;
    
    PrimerRF(int id, String pri_seq_fw, String pri_seq_rv, int length_fw, 
            int length_rv, double Tmf, double Tmr, double TmD, double TmP, double GCf, double GCr, 
            String fwdseqLCS, String fwdcomLCS, String revseqLCS, String revcomLCS, String frLCS){
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
        this.frLCS = frLCS;
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
                    double TmP = p_fwd.get(i).Tm + p_rv.get(j).Tm;
                    TmP = TmP / 2;
                    TmP = (double)Math.round(TmP * 100d) / 100d;
                    double TmD = Math.abs(p_fwd.get(i).Tm - p_rv.get(j).Tm);
                    TmD = (double)Math.round(TmD * 100d) / 100d;
                    PrimerRF pmatch = new PrimerRF(0, p_fwd.get(i).seq, p_rv.get(j).seq, p_fwd.get(i).largo, p_rv.get(j).largo,
                            p_fwd.get(i).Tm, p_rv.get(j).Tm, TmD, TmP, p_fwd.get(i).GC,
                            p_rv.get(j).GC, p_fwd.get(i).maxAF, p_fwd.get(i).maxAR, p_rv.get(j).maxAF, p_rv.get(j).maxAR, 
                            LCSAlgorithm(p_fwd.get(i).seq, p_rv.get(j).seq));
                    primers_list.add(pmatch);
                }
            }
        }
        return primers_list;
    }
    
    
    public static String LCSAlgorithm(String a, String b) {
		int n = a.length();
		int m = b.length();
		int S[][] = new int[n+1][m+1];
		int R[][] = new int[n+1][m+1];
		int ii, jj;

		// It is important to use <=, not <.  The next two for-loops are initialization
		for(ii = 0; ii <= n; ++ii) {
			S[ii][0] = 0;
			R[ii][0] = UP;
		}
		for(jj = 0; jj <= m; ++jj) {
			S[0][jj] = 0;
			R[0][jj] = LEFT;
		}

		// This is the main dynamic programming loop that computes the score and
		// backtracking arrays.
		for(ii = 1; ii <= n; ++ii) {
			for(jj = 1; jj <= m; ++jj) { 
	
				if( a.charAt(ii-1) == b.charAt(jj-1) ) {
					S[ii][jj] = S[ii-1][jj-1] + 1;
					R[ii][jj] = UP_AND_LEFT;
				}

				else {
					S[ii][jj] = S[ii-1][jj-1] + 0;
					R[ii][jj] = NEITHER;
				}

				if( S[ii-1][jj] >= S[ii][jj] ) {	
					S[ii][jj] = S[ii-1][jj];
					R[ii][jj] = UP;
				}

				if( S[ii][jj-1] >= S[ii][jj] ) {
					S[ii][jj] = S[ii][jj-1];
					R[ii][jj] = LEFT;
				}
			}
		}

		// The length of the longest substring is S[n][m]
		ii = n; 
		jj = m;
		int pos = S[ii][jj] - 1;
		char lcs[] = new char[ pos+1 ];

		// Trace the backtracking matrix.
		while( ii > 0 || jj > 0 ) {
			if( R[ii][jj] == UP_AND_LEFT ) {
				ii--;
				jj--;
				lcs[pos--] = a.charAt(ii);
			}
	
			else if( R[ii][jj] == UP ) {
				ii--;
			}
	
			else if( R[ii][jj] == LEFT ) {
				jj--;
			}
		}

		return new String(lcs);
	}
    
    
    public List alignPrimer(String seq, String primer){
        List matchList = new ArrayList();
        for (int i = primer.length(); i < (seq.length()-primer.length()); i++) {
            int LCS = 0;
            int match = 0;
            boolean flag = true;
            for (int j = 0; j < primer.length(); j++) {
                if (seq.charAt(i+j) == complemNUCL(primer.charAt(j))){
                    if (flag) {
                        match = match + 1;
                    }
                    else{
                        if (match > LCS) {
                            LCS = match;
                        }
                        match = 1;
                    }
                }
                else{
                    flag = false;
                }
            }
            matchList.add(LCS);
        }
        return matchList;
    }
    
    public List alignPrimer2(String seq, String seq2){
        List matchList = new ArrayList();
        
        /*if (seq2.length() >= seq.length()) {
            for (int i = 0; i < seq.length(); i++) {
                int LCS = 0;
                int match = 0;
                boolean flag = true;
                if (seq.charAt(i) == complemNUCL(seq2.charAt(i))) {
                    if (flag) {
                        match = match + 1;
                    }
                    else{
                        if (match > LCS) {
                            LCS = match;
                        }
                        match = 1;
                    }
                }
                else{
                    flag = false;
                }
                matchList.add(LCS);
            }
        }
        else{
            for (int i = 0; i < seq2.length(); i++) {
                int LCS = 0;
                int match = 0;
                boolean flag = true;
                if (seq2.charAt(i) == complemNUCL(seq.charAt(i))) {
                    if (flag) {
                        match = match + 1;
                    }
                    else{
                        if (match > LCS) {
                            LCS = match;
                        }
                        match = 1;
                    }
                }
                else{
                    flag = false;
                }
                matchList.add(LCS);
            }
        }*/
        
        return matchList;
    }
    
    public int maxValue(List lista){
        int max = (int) lista.get(0);
        for (int i = 0; i < lista.size(); i++) {
            if ((int) lista.get(i) > max){
                max = (int) lista.get(i);
            }
        }
        return max;
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
