/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerg1Domain;

import entities.Enzyme;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.primefaces.util.ArrayUtils;

/**
 *
 * @author MacBookPro
 */
public class PrimerEnzRes {
    PrimerRF primerPar;
    PrimerRF primerFirstCycle;
    Enzyme enzymeRes;
    Enzyme enzymeRes2;
    
    private static final int NEITHER     = 0;
    private static final int UP          = 1;
    private static final int LEFT        = 2;
    private static final int UP_AND_LEFT = 3;
    
    private static int ids = 0;
    
    private PrimerEnzRes(PrimerRF primerPar, PrimerRF primerFirstCycle, Enzyme enzymeRes, Enzyme enzymeRes2){
        this.primerPar = primerPar;
        this.primerFirstCycle = primerFirstCycle;
        this.enzymeRes = enzymeRes;
        this.enzymeRes2 = enzymeRes2;
    }
    
    public PrimerEnzRes(){
    }

    public Enzyme getEnzymeRes2() {
        return enzymeRes2;
    }

    public void setEnzymeRes2(Enzyme enzymeRes2) {
        this.enzymeRes2 = enzymeRes2;
    }

    public PrimerRF getPrimerFirstCycle() {
        return primerFirstCycle;
    }

    public void setPrimerFirstCycle(PrimerRF primerFirstCycle) {
        this.primerFirstCycle = primerFirstCycle;
    }

    public PrimerRF getPrimerPar() {
        return primerPar;
    }

    public void setPrimerPar(PrimerRF primerPar) {
        this.primerPar = primerPar;
    }

    public Enzyme getEnzymeRes() {
        return enzymeRes;
    }

    public void setEnzymeRes(Enzyme enzymeRes) {
        this.enzymeRes = enzymeRes;
    }
    
    public List<PrimerEnzRes> addEnzyme(List<PrimerRF> primers, Enzyme enzyme, Enzyme enzyme2,  String seq, String com){
        System.out.println("Primers size: " + primers.size());
        
        String sequence_back = new StringBuilder(complemento(enzyme2.getDnaTarget())).reverse().toString();
        System.out.println("Enzyme 2: " + sequence_back);
        
        List<PrimerEnzRes> primers_list = new ArrayList<PrimerEnzRes>();
        for (int i = 0; i < primers.size(); i++) {
            String seq_fwd = primers.get(i).pri_seq_fw;
            String seq_rev = primers.get(i).pri_seq_rv;
            seq_fwd = enzyme.getDnaTarget() + seq_fwd;
            seq_rev = sequence_back + seq_rev;
            List<Primer> primerext_fwd = new ArrayList<Primer>();
            List<Primer> primerext_rev = new ArrayList<Primer>();
            
            char[] chars = "GACT".toCharArray();
            
            String seq_ext_fwd = new String();
            String seq_ext_rev = new String();
            
            //Primer's Enz1 bp: 1
            
            if (enzyme.getNumBasePair() == 1 && enzyme2.getNumBasePair() == 1) {
                for ( char f: chars )
                {
                    seq_ext_fwd = "" + f + seq_fwd;
                    seq_ext_rev = "" + f + seq_rev;
                    double Tmf;
                    double GCf;
                    int largof;
                    double Tmr;
                    double GCr;
                    int largor;
                    Tmr = calculateTm(seq_ext_rev);
                    GCr = calculateGC(seq_ext_rev);
                    largor = seq_ext_rev.length();
                    Tmf = calculateTm(seq_ext_fwd);
                    GCf = calculateGC(seq_ext_fwd);
                    largof = seq_ext_fwd.length();

                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                    
                    primerext_fwd.add(newPrimer_fwd);
                    primerext_rev.add(newPrimer_rev);
                }
            }
            if (enzyme.getNumBasePair() == 1 && enzyme2.getNumBasePair() == 2) {
                for ( char e: chars )
                {
                    seq_ext_fwd = "" + e + seq_fwd;
                    double Tmf;
                    double GCf;
                    int largof;
                    Tmf = calculateTm(seq_ext_fwd);
                    GCf = calculateGC(seq_ext_fwd);
                    largof = seq_ext_fwd.length();
                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                    primerext_fwd.add(newPrimer_fwd);

                    for ( char f: chars )
                    {
                        seq_ext_rev = "" + e + f + seq_rev;
                        double Tmr;
                        double GCr;
                        int largor;
                        Tmr = calculateTm(seq_ext_rev);
                        GCr = calculateGC(seq_ext_rev);
                        largor = seq_ext_rev.length();
                        Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                        primerext_rev.add(newPrimer_rev);
                    }
                }
            }
            if (enzyme.getNumBasePair() == 1 && enzyme2.getNumBasePair() == 3) {
                for ( char d: chars )
                {
                    seq_ext_fwd = "" + d + seq_fwd;
                    double Tmf;
                    double GCf;
                    int largof;
                    Tmf = calculateTm(seq_ext_fwd);
                    GCf = calculateGC(seq_ext_fwd);
                    largof = seq_ext_fwd.length();
                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                    primerext_fwd.add(newPrimer_fwd);
                    for ( char e: chars )
                     {
                         for ( char f: chars )
                         {
                            seq_ext_rev = "" + d + e + f + seq_rev;
                            double Tmr;
                            double GCr;
                            int largor;
                            Tmr = calculateTm(seq_ext_rev);
                            GCr = calculateGC(seq_ext_rev);
                            largor = seq_ext_rev.length();
                            Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                            primerext_rev.add(newPrimer_rev);
                         }
                     }
                }
            }
            if (enzyme.getNumBasePair() == 1 && enzyme2.getNumBasePair() == 4) {
                for ( char c : chars )
               {
                   seq_ext_fwd = "" + c + seq_fwd;
                   double Tmf;
                   double GCf;
                   int largof;
                   Tmf = calculateTm(seq_ext_fwd);
                   GCf = calculateGC(seq_ext_fwd);
                   largof = seq_ext_fwd.length();
                   Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                   primerext_fwd.add(newPrimer_fwd);
                   for ( char d: chars )
                   {
                       for ( char e: chars )
                        {
                            for ( char f: chars )
                            {
                                seq_ext_rev = "" + c + d + e + f + seq_rev;
                                double Tmr;
                                double GCr;
                                int largor;
                                Tmr = calculateTm(seq_ext_rev);
                                GCr = calculateGC(seq_ext_rev);
                                largor = seq_ext_rev.length();
                                Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                primerext_rev.add(newPrimer_rev);
                            }
                        }
                   }
               }
            }
            if (enzyme.getNumBasePair() == 1 && enzyme2.getNumBasePair() == 5) {
                for ( char b: chars )
                {
                    seq_ext_fwd = "" + b + seq_fwd;
                    double Tmf;
                    double GCf;
                    int largof;
                    Tmf = calculateTm(seq_ext_fwd);
                    GCf = calculateGC(seq_ext_fwd);
                    largof = seq_ext_fwd.length();
                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                    primerext_fwd.add(newPrimer_fwd);
                    for ( char c : chars )
                    {
                        for ( char d: chars )
                        {
                            for ( char e: chars )
                             {
                                 for ( char f: chars )
                                 {
                                    seq_ext_rev = "" + b + c + d + e + f + seq_rev;
                                    double Tmr;
                                    double GCr;
                                    int largor;
                                    Tmr = calculateTm(seq_ext_rev);
                                    GCr = calculateGC(seq_ext_rev);
                                    largor = seq_ext_rev.length();
                                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                    primerext_rev.add(newPrimer_rev);
                                }
                             }
                        }
                    }
                }
            }
            
            //PRIMER'S Enz1 bp: 2
            
            if (enzyme.getNumBasePair() == 2 && enzyme2.getNumBasePair() == 1) {
                for ( char e: chars )
                {
                    seq_ext_rev = "" + e + seq_rev;
                    double Tmr;
                    double GCr;
                    int largor;
                    Tmr = calculateTm(seq_ext_rev);
                    GCr = calculateGC(seq_ext_rev);
                    largor = seq_ext_rev.length();
                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                    primerext_rev.add(newPrimer_rev);
                    for ( char f: chars )
                    {
                        seq_ext_fwd = "" + e + f + seq_fwd;
                        double Tmf;
                        double GCf;
                        int largof;
                        Tmf = calculateTm(seq_ext_fwd);
                        GCf = calculateGC(seq_ext_fwd);
                        largof = seq_ext_fwd.length();
                        Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                        primerext_fwd.add(newPrimer_fwd);
                    }
                }
            }
            if (enzyme.getNumBasePair() == 2 && enzyme2.getNumBasePair() == 2) {
                for ( char e: chars )
                {
                    for ( char f: chars )
                    {
                        seq_ext_fwd = "" + e + f + seq_fwd;
                        seq_ext_rev = "" + e + f + seq_rev;
                        double Tmf;
                        double GCf;
                        int largof;
                        double Tmr;
                        double GCr;
                        int largor;
                        Tmr = calculateTm(seq_ext_rev);
                        GCr = calculateGC(seq_ext_rev);
                        largor = seq_ext_rev.length();
                        Tmf = calculateTm(seq_ext_fwd);
                        GCf = calculateGC(seq_ext_fwd);
                        largof = seq_ext_fwd.length();

                        Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                        Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                        
                        primerext_fwd.add(newPrimer_fwd);
                        primerext_rev.add(newPrimer_rev);
                    }
                }
            }
            if (enzyme.getNumBasePair() == 2 && enzyme2.getNumBasePair() == 3) {
                for ( char d: chars )
                {
                    for ( char e: chars )
                     {
                        seq_ext_fwd = "" + d + e + seq_fwd;
                        double Tmf;
                        double GCf;
                        int largof;
                        Tmf = calculateTm(seq_ext_fwd);
                        GCf = calculateGC(seq_ext_fwd);
                        largof = seq_ext_fwd.length();
                        Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                        primerext_fwd.add(newPrimer_fwd);
                         for ( char f: chars )
                         {
                            seq_ext_rev = "" + d + e + f + seq_rev;
                            double Tmr;
                            double GCr;
                            int largor;
                            Tmr = calculateTm(seq_ext_rev);
                            GCr = calculateGC(seq_ext_rev);
                            largor = seq_ext_rev.length();
                            Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                            primerext_rev.add(newPrimer_rev);
                         }
                     }
                }
            }
            if (enzyme.getNumBasePair() == 2 && enzyme2.getNumBasePair() == 4) {
                for ( char c : chars )
               {
                   for ( char d: chars )
                   {
                       seq_ext_fwd = "" + c + d + seq_fwd;
                       double Tmf;
                       double GCf;
                       int largof;
                       Tmf = calculateTm(seq_ext_fwd);
                       GCf = calculateGC(seq_ext_fwd);
                       largof = seq_ext_fwd.length();
                       Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                       primerext_fwd.add(newPrimer_fwd);
                       for ( char e: chars )
                        {
                            for ( char f: chars )
                            {
                                seq_ext_rev = "" + c + d + e + f + seq_rev;
                                double Tmr;
                                double GCr;
                                int largor;
                                Tmr = calculateTm(seq_ext_rev);
                                GCr = calculateGC(seq_ext_rev);
                                largor = seq_ext_rev.length();
                                Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                primerext_rev.add(newPrimer_rev);
                            }
                        }
                   }
               }
            }
            if (enzyme.getNumBasePair() == 2 && enzyme2.getNumBasePair() == 5) {
                for ( char b: chars )
                {
                    for ( char c : chars )
                    {
                        seq_ext_fwd = "" + b + c + seq_fwd;
                        double Tmf;
                        double GCf;
                        int largof;
                        Tmf = calculateTm(seq_ext_fwd);
                        GCf = calculateGC(seq_ext_fwd);
                        largof = seq_ext_fwd.length();
                        Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                        primerext_fwd.add(newPrimer_fwd);
                        for ( char d: chars )
                        {
                            for ( char e: chars )
                             {
                                 for ( char f: chars )
                                 {
                                    seq_ext_rev = "" + b + c + d + e + f + seq_rev;
                                    double Tmr;
                                    double GCr;
                                    int largor;
                                    Tmr = calculateTm(seq_ext_rev);
                                    GCr = calculateGC(seq_ext_rev);
                                    largor = seq_ext_rev.length();
                                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                    primerext_rev.add(newPrimer_rev);
                                }
                             }
                        }
                    }
                }
            }
            
            //PRIMER'S Enz1 bp: 3
            
            if (enzyme.getNumBasePair() == 3 && enzyme2.getNumBasePair() == 1) {
                for ( char d: chars )
                {
                    seq_ext_rev = "" + d + seq_rev;
                    double Tmr;
                    double GCr;
                    int largor;
                    Tmr = calculateTm(seq_ext_rev);
                    GCr = calculateGC(seq_ext_rev);
                    largor = seq_ext_rev.length();
                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                    primerext_rev.add(newPrimer_rev);
                    for ( char e: chars )
                     {
                         for ( char f: chars )
                         {
                            seq_ext_fwd = "" + d + e + f + seq_fwd;
                            double Tmf;
                            double GCf;
                            int largof;
                            Tmf = calculateTm(seq_ext_fwd);
                            GCf = calculateGC(seq_ext_fwd);
                            largof = seq_ext_fwd.length();
                            Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                            primerext_fwd.add(newPrimer_fwd);
                         }
                     }
                }
            }
            if (enzyme.getNumBasePair() == 3 && enzyme2.getNumBasePair() == 2) {
                for ( char d: chars )
                {
                    for ( char e: chars )
                     {
                        seq_ext_rev = "" + d + e + seq_rev;
                        double Tmr;
                        double GCr;
                        int largor;
                        Tmr = calculateTm(seq_ext_rev);
                        GCr = calculateGC(seq_ext_rev);
                        largor = seq_ext_rev.length();
                        Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                        primerext_rev.add(newPrimer_rev);
                         for ( char f: chars )
                         {
                            seq_ext_fwd = "" + d + e + f + seq_fwd;
                            double Tmf;
                            double GCf;
                            int largof;
                            Tmf = calculateTm(seq_ext_fwd);
                            GCf = calculateGC(seq_ext_fwd);
                            largof = seq_ext_fwd.length();
                            Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                            primerext_fwd.add(newPrimer_fwd);
                         }
                     }
                }
            }
            if (enzyme.getNumBasePair() == 3 && enzyme2.getNumBasePair() == 3) {
                for ( char d: chars )
                {
                    for ( char e: chars )
                     {
                         for ( char f: chars )
                         {
                            seq_ext_rev = "" + d + e + f + seq_rev;
                            seq_ext_fwd = "" + d + e + f + seq_fwd;
                            double Tmf;
                            double GCf;
                            int largof;
                            double Tmr;
                            double GCr;
                            int largor;
                            Tmr = calculateTm(seq_ext_rev);
                            GCr = calculateGC(seq_ext_rev);
                            largor = seq_ext_rev.length();
                            Tmf = calculateTm(seq_ext_fwd);
                            GCf = calculateGC(seq_ext_fwd);
                            largof = seq_ext_fwd.length();

                            Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                            Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");

                            primerext_fwd.add(newPrimer_fwd);
                            primerext_rev.add(newPrimer_rev);
                         }
                     }
                }
            }
            if (enzyme.getNumBasePair() == 3 && enzyme2.getNumBasePair() == 4) {
                for ( char c : chars )
               {
                   for ( char d: chars )
                   {
                       for ( char e: chars )
                        {
                            seq_ext_fwd = "" + c + d + e + seq_fwd;
                            double Tmf;
                            double GCf;
                            int largof;
                            Tmf = calculateTm(seq_ext_fwd);
                            GCf = calculateGC(seq_ext_fwd);
                            largof = seq_ext_fwd.length();
                            Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                            primerext_fwd.add(newPrimer_fwd);
                            for ( char f: chars )
                            {
                                seq_ext_rev = "" + c + d + e + f + seq_rev;
                                double Tmr;
                                double GCr;
                                int largor;
                                Tmr = calculateTm(seq_ext_rev);
                                GCr = calculateGC(seq_ext_rev);
                                largor = seq_ext_rev.length();
                                Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                primerext_rev.add(newPrimer_rev);
                            }
                        }
                   }
               }
            }
            if (enzyme.getNumBasePair() == 3 && enzyme2.getNumBasePair() == 5) {
                for ( char b: chars )
                {
                    for ( char c : chars )
                    {
                        for ( char d: chars )
                        {
                            seq_ext_fwd = "" + b + c + d + seq_fwd;
                            double Tmf;
                            double GCf;
                            int largof;
                            Tmf = calculateTm(seq_ext_fwd);
                            GCf = calculateGC(seq_ext_fwd);
                            largof = seq_ext_fwd.length();
                            Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                            primerext_fwd.add(newPrimer_fwd);
                            for ( char e: chars )
                             {
                                 for ( char f: chars )
                                 {
                                    seq_ext_rev = "" + b + c + d + e + f + seq_rev;
                                    double Tmr;
                                    double GCr;
                                    int largor;
                                    Tmr = calculateTm(seq_ext_rev);
                                    GCr = calculateGC(seq_ext_rev);
                                    largor = seq_ext_rev.length();
                                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                    primerext_rev.add(newPrimer_rev);
                                }
                             }
                        }
                    }
                }
            }
            
            //PRIMER'S Enz1 bp: 4
            
            if (enzyme.getNumBasePair() == 4 && enzyme2.getNumBasePair() == 1) {
                for ( char c : chars )
               {
                    seq_ext_rev = "" + c + seq_rev;
                    double Tmr;
                    double GCr;
                    int largor;
                    Tmr = calculateTm(seq_ext_rev);
                    GCr = calculateGC(seq_ext_rev);
                    largor = seq_ext_rev.length();
                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                    primerext_rev.add(newPrimer_rev);
                   for ( char d: chars )
                   {
                       for ( char e: chars )
                        {
                            for ( char f: chars )
                            {
                                seq_ext_fwd = "" + c + d + e + f + seq_fwd;
                                double Tmf;
                                double GCf;
                                int largof;
                                Tmf = calculateTm(seq_ext_fwd);
                                GCf = calculateGC(seq_ext_fwd);
                                largof = seq_ext_fwd.length();
                                Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                primerext_fwd.add(newPrimer_fwd);
                            }
                        }
                   }
               }
            }
            if (enzyme.getNumBasePair() == 4 && enzyme2.getNumBasePair() == 2) {
                for ( char c : chars )
               {
                   for ( char d: chars )
                   {
                        seq_ext_rev = "" + c + d + seq_rev;
                        double Tmr;
                        double GCr;
                        int largor;
                        Tmr = calculateTm(seq_ext_rev);
                        GCr = calculateGC(seq_ext_rev);
                        largor = seq_ext_rev.length();
                        Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                        primerext_rev.add(newPrimer_rev);
                       for ( char e: chars )
                        {
                            for ( char f: chars )
                            {
                                seq_ext_fwd = "" + c + d + e + f + seq_fwd;
                                double Tmf;
                                double GCf;
                                int largof;
                                Tmf = calculateTm(seq_ext_fwd);
                                GCf = calculateGC(seq_ext_fwd);
                                largof = seq_ext_fwd.length();
                                Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                primerext_fwd.add(newPrimer_fwd);
                            }
                        }
                   }
               }
            }
            if (enzyme.getNumBasePair() == 4 && enzyme2.getNumBasePair() == 3) {
                for ( char c : chars )
               {
                   for ( char d: chars )
                   {
                       for ( char e: chars )
                        {
                            seq_ext_rev = "" + c + d + e + seq_rev;
                            double Tmr;
                            double GCr;
                            int largor;
                            Tmr = calculateTm(seq_ext_rev);
                            GCr = calculateGC(seq_ext_rev);
                            largor = seq_ext_rev.length();
                            Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                            primerext_rev.add(newPrimer_rev);
                            for ( char f: chars )
                            {
                                seq_ext_fwd = "" + c + d + e + f + seq_fwd;
                                double Tmf;
                                double GCf;
                                int largof;
                                Tmf = calculateTm(seq_ext_fwd);
                                GCf = calculateGC(seq_ext_fwd);
                                largof = seq_ext_fwd.length();
                                Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                primerext_fwd.add(newPrimer_fwd);
                            }
                        }
                   }
               }
            }
            if (enzyme.getNumBasePair() == 4 && enzyme2.getNumBasePair() == 4) {
                for ( char c : chars )
               {
                   for ( char d: chars )
                   {
                       for ( char e: chars )
                        {
                            for ( char f: chars )
                            {
                                seq_ext_fwd = "" + c + d + e + f + seq_fwd;
                                seq_ext_rev = "" + c + d + e + f + seq_rev;
                                double Tmf;
                                double GCf;
                                int largof;
                                double Tmr;
                                double GCr;
                                int largor;
                                Tmr = calculateTm(seq_ext_rev);
                                GCr = calculateGC(seq_ext_rev);
                                largor = seq_ext_rev.length();
                                Tmf = calculateTm(seq_ext_fwd);
                                GCf = calculateGC(seq_ext_fwd);
                                largof = seq_ext_fwd.length();

                                Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");

                                primerext_fwd.add(newPrimer_fwd);
                                primerext_rev.add(newPrimer_rev);
                            }
                        }
                   }
               }
            }
            if (enzyme.getNumBasePair() == 4 && enzyme2.getNumBasePair() == 5) {
                for ( char b: chars )
                {
                    for ( char c : chars )
                    {
                        for ( char d: chars )
                        {
                            for ( char e: chars )
                             {
                                seq_ext_fwd = "" + b + c + d + e + seq_fwd;
                                double Tmf;
                                double GCf;
                                int largof;
                                Tmf = calculateTm(seq_ext_fwd);
                                GCf = calculateGC(seq_ext_fwd);
                                largof = seq_ext_fwd.length();
                                Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                primerext_fwd.add(newPrimer_fwd);
                                 for ( char f: chars )
                                 {
                                    seq_ext_rev = "" + b + c + d + e + f + seq_rev;
                                    double Tmr;
                                    double GCr;
                                    int largor;
                                    Tmr = calculateTm(seq_ext_rev);
                                    GCr = calculateGC(seq_ext_rev);
                                    largor = seq_ext_rev.length();
                                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                    primerext_rev.add(newPrimer_rev);
                                }
                             }
                        }
                    }
                }
            }
            
            //PRIMER'S Enz1 bp: 5
            
            if (enzyme.getNumBasePair() == 5 && enzyme2.getNumBasePair() == 1) {
                for ( char b: chars )
                {
                    seq_ext_rev = "" + b + seq_rev;
                    double Tmr;
                    double GCr;
                    int largor;
                    Tmr = calculateTm(seq_ext_rev);
                    GCr = calculateGC(seq_ext_rev);
                    largor = seq_ext_rev.length();
                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                    primerext_rev.add(newPrimer_rev);
                    for ( char c : chars )
                    {
                        for ( char d: chars )
                        {
                            for ( char e: chars )
                             {
                                 for ( char f: chars )
                                 {
                                    seq_ext_fwd = "" + b + c + d + e + f + seq_fwd;
                                    double Tmf;
                                    double GCf;
                                    int largof;
                                    Tmf = calculateTm(seq_ext_fwd);
                                    GCf = calculateGC(seq_ext_fwd);
                                    largof = seq_ext_fwd.length();
                                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                    primerext_fwd.add(newPrimer_fwd);
                                }
                             }
                        }
                    }
                }
            }
            if (enzyme.getNumBasePair() == 5 && enzyme2.getNumBasePair() == 2) {
                for ( char b: chars )
                {
                    for ( char c : chars )
                    {
                        seq_ext_rev = "" + b + c + seq_rev;
                        double Tmr;
                        double GCr;
                        int largor;
                        Tmr = calculateTm(seq_ext_rev);
                        GCr = calculateGC(seq_ext_rev);
                        largor = seq_ext_rev.length();
                        Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                        primerext_rev.add(newPrimer_rev);
                        for ( char d: chars )
                        {
                            for ( char e: chars )
                             {
                                 for ( char f: chars )
                                 {
                                    seq_ext_fwd = "" + b + c + d + e + f + seq_fwd;
                                    double Tmf;
                                    double GCf;
                                    int largof;
                                    Tmf = calculateTm(seq_ext_fwd);
                                    GCf = calculateGC(seq_ext_fwd);
                                    largof = seq_ext_fwd.length();
                                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                    primerext_fwd.add(newPrimer_fwd);
                                }
                             }
                        }
                    }
                }
            }
            if (enzyme.getNumBasePair() == 5 && enzyme2.getNumBasePair() == 3) {
                for ( char b: chars )
                {
                    for ( char c : chars )
                    {
                        for ( char d: chars )
                        {
                            seq_ext_rev = "" + b + c + d + seq_rev;
                            double Tmr;
                            double GCr;
                            int largor;
                            Tmr = calculateTm(seq_ext_rev);
                            GCr = calculateGC(seq_ext_rev);
                            largor = seq_ext_rev.length();
                            Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                            primerext_rev.add(newPrimer_rev);
                            for ( char e: chars )
                             {
                                 for ( char f: chars )
                                 {
                                    seq_ext_fwd = "" + b + c + d + e + f + seq_fwd;
                                    double Tmf;
                                    double GCf;
                                    int largof;
                                    Tmf = calculateTm(seq_ext_fwd);
                                    GCf = calculateGC(seq_ext_fwd);
                                    largof = seq_ext_fwd.length();
                                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                    primerext_fwd.add(newPrimer_fwd);
                                }
                             }
                        }
                    }
                }
            }
            if (enzyme.getNumBasePair() == 5 && enzyme2.getNumBasePair() == 4) {
                for ( char b: chars )
                {
                    for ( char c : chars )
                    {
                        for ( char d: chars )
                        {
                            for ( char e: chars )
                             {
                                seq_ext_rev = "" + b + c + d + e + seq_rev;
                                double Tmr;
                                double GCr;
                                int largor;
                                Tmr = calculateTm(seq_ext_rev);
                                GCr = calculateGC(seq_ext_rev);
                                largor = seq_ext_rev.length();
                                Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");
                                primerext_rev.add(newPrimer_rev);
                                 for ( char f: chars )
                                 {
                                    seq_ext_fwd = "" + b + c + d + e + f + seq_fwd;
                                    double Tmf;
                                    double GCf;
                                    int largof;
                                    Tmf = calculateTm(seq_ext_fwd);
                                    GCf = calculateGC(seq_ext_fwd);
                                    largof = seq_ext_fwd.length();
                                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                    primerext_fwd.add(newPrimer_fwd);
                                }
                             }
                        }
                    }
                }
            }
            if (enzyme.getNumBasePair() == 5 && enzyme2.getNumBasePair() == 5) {
                for ( char b: chars )
                {
                    for ( char c : chars )
                    {
                        for ( char d: chars )
                        {
                            for ( char e: chars )
                             {
                                 for ( char f: chars )
                                 {
                                    seq_ext_rev = "" + b + c + d + e + f + seq_rev;
                                    seq_ext_fwd = "" + b + c + d + e + f + seq_fwd;
                                    double Tmf;
                                    double GCf;
                                    int largof;
                                    double Tmr;
                                    double GCr;
                                    int largor;
                                    Tmr = calculateTm(seq_ext_rev);
                                    GCr = calculateGC(seq_ext_rev);
                                    largor = seq_ext_rev.length();
                                    Tmf = calculateTm(seq_ext_fwd);
                                    GCf = calculateGC(seq_ext_fwd);
                                    largof = seq_ext_fwd.length();

                                    Primer newPrimer_fwd = new Primer(seq_ext_fwd, Tmf, GCf, largof, "LCSseq", "LCSrev");
                                    Primer newPrimer_rev = new Primer(seq_ext_rev, Tmr, GCr, largor, "LCSseq", "LCSrev");

                                    primerext_fwd.add(newPrimer_fwd);
                                    primerext_rev.add(newPrimer_rev);
                                }
                             }
                        }
                    }
                }
            }
            
            
           
            System.out.println("Primer's generados");
            System.out.println("Calculando mejores primers...");
            List<PrimerRF> parcial_primer_list = new ArrayList<PrimerRF>();
            parcial_primer_list = findbestPrimers(primerext_fwd, primerext_rev, seq, com);
            PrimerEnzRes primer_ext_final1 = new PrimerEnzRes(parcial_primer_list.get(0), primers.get(i), enzyme, enzyme2);
            PrimerEnzRes primer_ext_final2 = new PrimerEnzRes(parcial_primer_list.get(1), primers.get(i), enzyme, enzyme2);
            PrimerEnzRes primer_ext_final3 = new PrimerEnzRes(parcial_primer_list.get(2), primers.get(i), enzyme, enzyme2);
            
            primers_list.add(primer_ext_final1);
            primers_list.add(primer_ext_final2);
            primers_list.add(primer_ext_final3);
        }
        
        return primers_list;
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
    
    public double calculateGC(String seq){
        double GC;
        double cantidadGC = 0;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == 'C' || seq.charAt(i) == 'G') {
                cantidadGC = cantidadGC + 1;
            }
        }
        GC = (cantidadGC / seq.length())*100.00;
        GC = (double)Math.round(GC * 100d) / 100d;
        return GC;
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
    
    
    
    
    public static char complemNUCL(char nucleotido){
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
    
    

    public static List<PrimerRF> findbestPrimers(List<Primer> p_fwd, List<Primer> p_rv, String seq, String com) {
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
                    PrimerRF pmatch = new PrimerRF(ids, p_fwd.get(i).seq, p_rv.get(j).seq, p_fwd.get(i).largo, p_rv.get(j).largo,
                            p_fwd.get(i).Tm, p_rv.get(j).Tm, TmD, TmP, p_fwd.get(i).GC,
                            p_rv.get(j).GC, alignPrimer(seq, p_fwd.get(i).seq), alignPrimer(com, p_fwd.get(i).seq), 
                            alignPrimer(seq, p_rv.get(j).seq), alignPrimer(com, p_rv.get(j).seq), LCSAlgorithm(p_fwd.get(i).seq, p_rv.get(j).seq));
                    primers_list.add(pmatch);
                    ids += 1;
                }
            }
        }
        
        Collections.sort(primers_list, new Comparator<PrimerRF>() {
	@Override
	public int compare(PrimerRF p1, PrimerRF p2) {
		return Double.compare(p1.TmD, p2.TmD);
	}
        });
        
        return primers_list;    
    }
    public static String alignPrimer(String seq, String primer){
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
        return match;
    }
    
 
    
}
