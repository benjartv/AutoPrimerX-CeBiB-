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

/**
 *
 * @author MacBookPro
 */


/**
 * 
 * objeto para primers con enzimas de restriccion
 * primerPar, mejores pares de primers
 * primerFirstCycle mejores pares de primers de los primeros ciclos (antes de usar enzimas)
 * enzymeRes(2) enzimas de restricción
 */

public class PrimerEnzRes {
    PrimerRF primerPar;
    PrimerRF primerFirstCycle;
    Enzyme enzymeRes;
    Enzyme enzymeRes2;
    
    
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
    
    /**
     * 
     * @param primers
     * Lista de mejores primers de primeros ciclos
     * @param enzyme
     * enzima de restriccion para fwd
     * @param enzyme2
     * enzima de restriccion para rev
     * @param seq
     * secuencia objetivo
     * @param com
     * complemento de secuencia objetivo
     * @return 
     * Lista con mejores primers usando enzimas de restricción
     * 
     * A partir de las enzimas de restriccion seleccionadas, comienza a generar los primers por pasos:
     *  1. agregar la secuencia de la enzima a cada primer
     *  2. dependiendo de la enzima agregar las bases extra que la enzima requiera a la secuencia
     *      genera todas las combinaciones
     *  3. Función findbestPrimers para encontrar los mejores pares entre los conjuntos de primer fwd
     * y de primer rev
     * Solo guarda los mejores 3 pares para cada par de primer de los primeros ciclos
     */
    
    
    public List<PrimerEnzRes> addEnzyme(List<PrimerRF> primers, Enzyme enzyme, Enzyme enzyme2,  String seq, String com){
        System.out.println("Primers size: " + primers.size());
        
        String sequence_back = new StringBuilder(complemento(enzyme2.getDnaTarget())).reverse().toString();
        System.out.println("Enzyme 2: " + sequence_back);
        
        List<PrimerEnzRes> primers_list = new ArrayList<PrimerEnzRes>();
        for (PrimerRF primer : primers) {
            String seq_fwd = primer.pri_seq_fw;
            String seq_rev = primer.pri_seq_rv;
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
            PrimerEnzRes primer_ext_final1 = new PrimerEnzRes(parcial_primer_list.get(0), primer, enzyme, enzyme2);
            PrimerEnzRes primer_ext_final2 = new PrimerEnzRes(parcial_primer_list.get(1), primer, enzyme, enzyme2);
            PrimerEnzRes primer_ext_final3 = new PrimerEnzRes(parcial_primer_list.get(2), primer, enzyme, enzyme2);
            primers_list.add(primer_ext_final1);
            primers_list.add(primer_ext_final2);
            primers_list.add(primer_ext_final3);
        }
        
        return primers_list;
    }
    
    /**
     * 
     * @param seq
     * secuencia a la que se quiere calcular la Tm
     * @return
     * Tm de la secuencia respectiva
     * 
     * Aplica las formulas de Tm para calcular la tm de la respectiva secuencia
     */
    
    public static double calculateTm(String seq){
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
    
    /**
     * 
     * @param seq
     * secuencia objetivo
     * @return 
     * calcula el % de bases G-C en la secuencia
     */
    
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
    
    /**
     * 
     * @param nucleotido
     * @return 
     * calcula la base respectiva a cada nucleotido
     * A-T
     * C-G
     */
    
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
    
    /**
     * 
     * @param seq
     * secuencia objetivo
     * @return 
     * Calcula la secuencia complementaria a la secuencia objetivo
     */
    
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
    
    /**
     * 
     * @param p_fwd
     * Lista de primers fwd
     * @param p_rv
     * Lista de primers fwd
     * @param seq
     * secuencia objetivo
     * @param com
     * complemento secuencia objetivo
     * @return 
     * lista con los mejores pares de primers
     * Realiza una combinación entre los 2 conjuntos de primers y compara las temperaturas,
     * solo guarda los pares cuya diferencia de tm sea menor a 1.
     * Para los primers seleccionados realiza diferentes alinemientos:
     *      Primer fwd con secuencia objetivo, complemento, primer fwd y primer rev
     *      Primer rev con secuencia objetivo y complemento, primer fwd y primer rev
     * ordena la lista por Tm.
     */

    public static List<PrimerRF> findbestPrimers(List<Primer> p_fwd, List<Primer> p_rv, String seq, String com) {
        List<PrimerRF> primers_list = new ArrayList<PrimerRF>();
        for (Primer p_fwd1 : p_fwd) {
            for (Primer p_rv1 : p_rv) {
                //System.out.println("Tm fwd: "+ p_fwd.get(i).Tm);
                //System.out.println("Tm rev: "+ p_rv.get(j).Tm);
                //System.out.println("Tm delta: " + Math.abs(p_fwd.get(i).Tm - p_rv.get(j).Tm));
                if (Math.abs(p_fwd1.Tm - p_rv1.Tm) <= 1.00) {
                    //System.out.println("MATCH");
                    double TmP = p_fwd1.Tm + p_rv1.Tm;
                    TmP = TmP / 2;
                    TmP = (double)Math.round(TmP * 100d) / 100d;
                    double TmD = Math.abs(p_fwd1.Tm - p_rv1.Tm);
                    TmD = (double)Math.round(TmD * 100d) / 100d;
                    String pfwd_rev = new StringBuilder(p_fwd1.seq).reverse().toString();
                    String prev_rev = new StringBuilder(p_rv1.seq).reverse().toString();
                    PrimerRF pmatch = new PrimerRF(ids, p_fwd1.seq, p_rv1.seq, p_fwd1.largo, p_rv1.largo, p_fwd1.Tm,
                            p_rv1.Tm, TmD, TmP, p_fwd1.GC, p_rv1.GC, 
                            alignPrimer(com, p_fwd1.seq), alignPrimer2(seq, p_fwd1.seq),
                            alignPrimer(seq, p_rv1.seq), alignPrimer2(com, p_rv1.seq),
                            alignPrimer2(pfwd_rev, p_fwd1.seq), alignPrimer2(prev_rev, p_rv1.seq),
                            alignPrimer2(p_fwd1.seq, p_rv1.seq));
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
    
    /**
     * 
     * @param seq
     * secuencia objetivo
     * @param primer
     * primer o secuencia que se desea alinear
     * @return 
     * subsecuencia con mayor tm
     * realiza un alinamiento base a base entre las 2 secuencias y busca la subsecuencia con mayor tm
     * reporta dicha secuencia.
     * elimina de la secuencia objetivo las primeras bases, para evitar que se reporte la zona de alinamiento
     * ej Primer fwd con secuencia complementaria
     */
    
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
                    if (calculateTm(match) < calculateTm(match2)) {
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
    
    /**
     * 
     * @param seq
     * secuencia objetivo
     * @param primer
     * primer o secuencia que se desea alinear
     * @return 
     * subsecuencia con mayor tm
     * realiza un alinamiento base a base entre las 2 secuencias y busca la subsecuencia con mayor tm
     * reporta dicha secuencia.
     * no elimina bases de ninguna secuencia, utilizada par alinear secuencias y primers que no deberían
     * alinearse en el PCR
     */
    
    public static String alignPrimer2(String seq, String primer){
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
                    if (calculateTm(match) < calculateTm(match2)) {
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
    
 
    
}
