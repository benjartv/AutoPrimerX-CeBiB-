/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import primerg3Domain.Codon;
import primerg3Domain.NomenclaturaIUPAC;
import primerg3Domain.CondonUsage;

@Named(value = "primerG3")
@ManagedBean
public class PrimerG3 {
    private String sequences;
    private String log;
    public ArrayList<NomenclaturaIUPAC> iupac;
    //public CondonUsage codonusage;
    private Integer conservado;
    private Integer tamanoPrimer;
    public ArrayList <String> resultado=new ArrayList <String>();
    public ArrayList <String> consensos=new ArrayList <String>();
    public selectCodon codons = new selectCodon();
    public ArrayList <String> secuenciasNucleotidos = new ArrayList <String>();

    public ArrayList<String> getSecuenciasNucleotidos() {
        return secuenciasNucleotidos;
    }

    public void setSecuenciasNucleotidos(ArrayList<String> secuenciasNucleotidos) {
        this.secuenciasNucleotidos = secuenciasNucleotidos;
    }
        
    public Integer getConservado() {
        return conservado;
    }

    public void setConservado(Integer conservado) {
        this.conservado = conservado;
    }

    public Integer getTamanoPrimer() {
        return tamanoPrimer;
    }

    public void setTamanoPrimer(Integer tamanoPrimer) {
        this.tamanoPrimer = tamanoPrimer;
    }
    
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    
    public String getSequences() {
        return sequences;
    }

    public void setSequences(String sequences) {
        this.sequences = sequences;
    }
    
    public void submit(){
        int sitioConservado = conservado;
        
        // SE CARGA EL ABECEDARIO
        createIUPAC();
        // SE CARGA EL USO DE CODONES
        createCodonUsage();
        
        //Cada línea de las secuencias de entrada se guarda de manera individual en un arreglo
        String [] sequencesSplit = sequences.split("\n");
        for (int i = 0; i < sequencesSplit.length; i++) {
            sequencesSplit[i] = sequencesSplit[i].trim();
        }
        
        Integer largo = sequencesSplit[0].length();
        boolean validate = validateSequence(sequencesSplit[0]);
        if(!validate){
            log = "La secuencia 1 "+sequencesSplit[0]+" es inválida";
        }
        boolean prueba=false;
        // SE COMPRUEBA QUE LAS SECUENCIAS TENGAN EL MISMO LARGO
        for (int i = 1; i < sequencesSplit.length && validate; i++) {
            if(!validateSequence(sequencesSplit[i])){
                log = "La secuencia "+(i+1)+" "+sequencesSplit[i]+" es inválida";
                break;
            }
            if(largo != sequencesSplit[i].length()) {
                log = "Las secuencias deben tener el mismo largo";
                break;
            } 
            else{
                log = "OK";
                prueba=true;
                resultado = resultado=determinarConservado(sequencesSplit);
                consensos = determinarConsenso(resultado, conservado, tamanoPrimer);
                
            }
        }
        //////////////////////////////////////////////////////////////
        for (int h = 0; h < consensos.size(); h++) {
            secuenciasNucleotidos.add(nucleotidSequence(consensos.get(h)));
        }
    
    }
    
    public boolean validateSequence(String seq){
        boolean validate = true;
        
        for(int i=0; i<seq.length()-1; i++){
            if(!(seq.substring(i, i+1).equals("-") || seq.substring(i, i+1).equals("A") || seq.substring(i, i+1).equals("C") || seq.substring(i, i+1).equals("D") || seq.substring(i, i+1).equals("E") || seq.substring(i, i+1).equals("F") || seq.substring(i, i+1).equals("G") || seq.substring(i, i+1).equals("H") || seq.substring(i, i+1).equals("I") || seq.substring(i, i+1).equals("K") || seq.substring(i, i+1).equals("L") || seq.substring(i, i+1).equals("M") || seq.substring(i, i+1).equals("N") || seq.substring(i, i+1).equals("P") || seq.substring(i, i+1).equals("Q") || seq.substring(i, i+1).equals("R") || seq.substring(i, i+1).equals("S") || seq.substring(i, i+1).equals("T") || seq.substring(i, i+1).equals("V") || seq.substring(i, i+1).equals("W") || seq.substring(i, i+1).equals("Y"))){
                validate = false;
                break;
            }
        }
        
        return validate;
    }
    
    public ArrayList<String> determinarConservado(String[] sequences) {
        ArrayList<String> sitios = new ArrayList<String>();
        String[][] matrizSequence = new String[sequences[0].length()][sequences.length];
        for (int i = 0; i < sequences.length; i++) {
            for (int j = 0; j < sequences[0].length(); j++) {
                matrizSequence[j][i] = sequences[i].substring(j, j + 1);
                //System.out.println(sequences[i].length());
            }
        }
        
        ArrayList<String> temporal = new ArrayList<>();
        for (int i = 0; i < sequences[0].length(); i++) {
            for (int j = 0; j < sequences.length; j++) {
                if (j == 0) {
                    temporal.add(matrizSequence[i][j]);
                } else if (temporal.contains(matrizSequence[i][j]) == false) {
                    temporal.add(matrizSequence[i][j]);
                }
            }
            //System.out.println(temporal);
            String temp = "";
            for (int k = 0; k < temporal.size(); k++) {
                temp = temp.concat(temporal.get(k));
            }
            sitios.add(temp);
            //System.out.println(sitios.get(i));
            temporal.clear();
        }

        return sitios;
    }
    
    public ArrayList<String> determinarConsenso(ArrayList<String> sitios, Integer sitioConservados, Integer tam) {
        ArrayList<String> consenso = new ArrayList<String>();
        ArrayList<String> tipo = new ArrayList<String>();
        for (int i = 0; i < sitios.size(); i++) {
            if (sitios.get(i).length() == 1) {
                tipo.add("I");
                //System.out.println("tamI " +sitios.get(i).length());
            }
            if (sitios.get(i).length() <= sitioConservados && sitios.get(i).length() > 1) {
                tipo.add("C");
                //System.out.println("tamC " +sitios.get(i).length());
            }
            if (sitios.get(i).length() > sitioConservados) {
                tipo.add("X");
                //System.out.println("tamX " +sitios.get(i).length());
            }
        }
        while (tipo.indexOf("I") != -1) {
            int posicion = tipo.indexOf("I");
            int posicionInf = 0;
            int posicionSup = 0;
            int puntaje = 0;
            Integer[] puntajeMax = {0, 0, 0};//puntaje, inicio, fin
            for (int i = 0; i < tam; i++) {
                posicionInf = (posicion - (tam - 1)) + i;
                posicionSup = posicion + i;
                //System.out.println("entre for");
                if (posicionInf >= 0 && posicionSup < tipo.size()) {
                    //System.out.println("entre if");
                    //System.out.println("posicioninf "+posicionInf +" " +posicionSup);
                    for (int j = posicionInf; j <= posicionSup; j++) {
                        // System.out.println("entre 2 for");
                        if (tipo.get(j).equals("C") == true) {
                            puntaje = puntaje + 2;
                            // System.out.println("puntajeC: "+puntaje);
                        }
                        if (tipo.get(j).equals("I") == true) {
                            puntaje = puntaje + 4;
                            //System.out.println("puntajeI: "+puntaje);
                        }
                        if (tipo.get(j).equals("X") == true) {
                            puntaje = puntaje + 1;
                            //System.out.println("puntajeX: "+puntaje);
                        }
                    }
                    //System.out.println("puntaje: "+puntaje);
                    if (puntaje > puntajeMax[0]) {
                        //System.out.println("puntaje: "+puntaje);
                        puntajeMax[0] = puntaje;
                        puntajeMax[1] = posicionInf;
                        puntajeMax[2] = posicionSup;
                    }
                    puntaje = 0;
                }
            }
            String temporal = "";
            for (int i = 0; i < tam; i++) {
                //temporal = temporal.concat("[");
                temporal = temporal.concat(sitios.get(puntajeMax[1] + i));
                tipo.set(puntajeMax[1] + i, "N");
                //temporal = temporal.concat("]");
                if(i != tam -1)
                    temporal = temporal.concat("-");
            }
            puntajeMax[0] = 0;
            consenso.add(temporal);
        }
        return consenso;
    }
    
    //Recibe Strings separados por un guión '-'
    public String nucleotidSequence(String seq){
        String nucleotidSeq = "";
        String [] consenso = seq.split("-");
        codons.init();
        //System.out.println("Max codon F: "+codons.getProbabilidadMayor(codons.getCodonusage().getF()).getcodon());
        
        for (String cons : consenso){
            if(cons.length() == 1){
                String aux = getCodon(cons).getcodon();
                System.out.println("______"+aux);
                nucleotidSeq = nucleotidSeq.concat(aux);
            }
            else {
                String nucleotidoIupac = "";
                ArrayList <String> codones = new ArrayList<String>();
                for(int i=0; i<cons.length(); i++){
                    codones.add(getCodon(cons.substring(i, i+1)).getcodon());
                }
                
                int largo = 3;
                System.out.println("LARGO: "+largo);
                
                
                for(int i=0; i<largo; i++){
                    int sumaT = 0;
                    int sumaA = 0;
                    int sumaG = 0;
                    int sumaC = 0;
                    for(int j=0; j<codones.size(); j++){
                        String aux = codones.get(j).substring(i, i+1);
                        switch(aux){
                            case "T":
                                if(sumaT < 1){
                                    sumaT += 1;
                                }
                                break;
                            case "U":
                                if(sumaT < 1){
                                    sumaT += 1;
                                }
                                break;
                            case "A":
                                if(sumaA < 1000){
                                    sumaA += 1000;
                                }
                                break;
                            case "C":
                                if(sumaC < 100){
                                    sumaC += 100;
                                }
                                break;
                            case "G":
                                if(sumaG < 10){
                                    sumaG += 10;
                                }
                                break;
                        }
                    }
                    int suma = sumaT + sumaA + sumaC + sumaG;
                
                    switch(suma){
                        case 1000:
                            nucleotidoIupac = nucleotidoIupac.concat("A");
                            break;
                        case 100:
                            nucleotidoIupac = nucleotidoIupac.concat("C");
                            break; 
                        case 10:
                            nucleotidoIupac = nucleotidoIupac.concat("G");
                            break;
                        case 1:
                            nucleotidoIupac = nucleotidoIupac.concat("T");
                            break;
                        case 1010:
                            nucleotidoIupac = nucleotidoIupac.concat("R");
                            break;
                        case 101:
                            nucleotidoIupac = nucleotidoIupac.concat("Y");
                            break;
                        case 110:
                            nucleotidoIupac = nucleotidoIupac.concat("S");
                            break;
                        case 1001:
                            nucleotidoIupac = nucleotidoIupac.concat("W");
                            break;
                        case 11:
                            nucleotidoIupac = nucleotidoIupac.concat("K");
                            break;
                        case 1100:
                            nucleotidoIupac = nucleotidoIupac.concat("M");
                            break;
                        case 111:
                            nucleotidoIupac = nucleotidoIupac.concat("B");
                            break;
                        case 1011:
                            nucleotidoIupac = nucleotidoIupac.concat("D");
                            break;
                        case 1101:
                            nucleotidoIupac = nucleotidoIupac.concat("H");
                            break;
                        case 1110:
                            nucleotidoIupac = nucleotidoIupac.concat("v");
                            break;
                        case 1111:
                            nucleotidoIupac = nucleotidoIupac.concat("N");
                            break;
                        default:
                            System.out.println("DEFAULT");
                            break;
                    }
                }
                System.out.println("++_______"+nucleotidoIupac);
                nucleotidSeq = nucleotidSeq.concat(nucleotidoIupac);
            }
        }
        return nucleotidSeq;
    }
    
    public Codon getCodon(String amino){
        Codon aux = new Codon();
        
        switch (amino) {
            case "A":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getA());
                break;
            case "C":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getC());
                break;
            case "D":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getD());
                break;
            case "E":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getE());
                break;
            case "F":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getF());
                break;
            case "G":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getG());
                break;
            case "H":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getH());
                break;
            case "I":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getI());
                break;
            case "K":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getK());
                break;
            case "L":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getL());
                break;
            case "M":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getM());
                break;
            case "N":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getN());
                break;
            case "P":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getP());
                break;
            case "Q":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getQ());
                break;
            case "R":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getR());
                break;
            case "S":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getS());
                break;
            case "T":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getT());
                break;
            case "V":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getV());
                break;
            case "Y":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getY());
                break;
            case "W":
                aux = codons.getProbabilidadMayor(codons.getCodonusage().getW());
                break;
            default:
                break;
        }
        
        return aux;
    }
    
    public void createIUPAC(){
            iupac = new ArrayList();
                /*
    A Adenina
    C Citocina
    G Guanina
    T o U Timina o Uracilo
    R A o G
    Y C o T
    S G o C
    W A o T
    K G o T
    M A o C
    B C o G o T
    D A o G o T
    H A o C o T
    V A o C o G
    N Cualquiera
    */
            iupac.add(new NomenclaturaIUPAC("A",1,0,0,0));
            iupac.add(new NomenclaturaIUPAC("C",0,1,0,0));
            iupac.add(new NomenclaturaIUPAC("G",0,0,1,0));
            iupac.add(new NomenclaturaIUPAC("ToU",0,0,0,1));
            iupac.add(new NomenclaturaIUPAC("R",1,0,1,0));
            iupac.add(new NomenclaturaIUPAC("Y",0,1,0,1));
            iupac.add(new NomenclaturaIUPAC("S",0,1,1,0));
            iupac.add(new NomenclaturaIUPAC("W",1,0,0,1));
            iupac.add(new NomenclaturaIUPAC("K",0,0,1,1));
            iupac.add(new NomenclaturaIUPAC("M",1,1,0,0));
            iupac.add(new NomenclaturaIUPAC("B",0,1,1,1));
            iupac.add(new NomenclaturaIUPAC("D",1,0,1,1));
            iupac.add(new NomenclaturaIUPAC("H",1,1,0,1));
            iupac.add(new NomenclaturaIUPAC("V",1,1,1,0));
            iupac.add(new NomenclaturaIUPAC("N",1,1,1,1));
            
    }
    
    public void createCodonUsage(){
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
    }
    
}
