/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.CodonUsage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.model.UploadedFile;
import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.template.AlignedSequence;
import org.biojava.nbio.alignment.template.Profile;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.util.ConcurrencyTools;
import primerg3Domain.Codon;
import primerg3Domain.CondonUsage;
import primerg3Domain.NomenclaturaIUPAC;
import primerg3Domain.Aminoacid;

@Named(value = "primerG3")
@SessionScoped
public class PrimerG3  implements Serializable{
    private String sequences;
    private String log;
    private ArrayList<NomenclaturaIUPAC> iupac;
    private Integer conservado = 1;
    private Integer tamanoPrimer = 1;
    private ArrayList <String> resultado=new ArrayList <String>();
    private ArrayList <String> consensos=new ArrayList <String>();
    private selectCodon codons = new selectCodon();
    private ArrayList <String> secuenciasNucleotidos = new ArrayList <String>();
    private Integer identico = 0;
    private UploadedFile file = null;
    private ArrayList<ArrayList<String>> input= new ArrayList<ArrayList<String>>();
    private ArrayList<Integer> posiciones= new ArrayList<Integer>();  
    private String [] sequencesSplit;
    private ArrayList<ArrayList<SequenceShow>> sequencesShow = new ArrayList<ArrayList<SequenceShow>>();
    private String [][] nucleotidSeq;
    private String resultNucleotidSeq;
    private String [] aminoSeqs;
    private int count;
    private int random;
    private List<SelectItem> aminoacidSeq = new ArrayList();
    private String[][] matrizSequence;
    private  ArrayList<ArrayList <Aminoacid>> probabilidades;

    public ArrayList<ArrayList<Aminoacid>> getProbabilidades() {
        return probabilidades;
    }

    public void setProbabilidades(ArrayList<ArrayList<Aminoacid>> probabilidades) {
        this.probabilidades = probabilidades;
    }

    public String[][] getMatrizSequence() {
        return matrizSequence;
    }

    public void setMatrizSequence(String[][] matrizSequence) {
        this.matrizSequence = matrizSequence;
    }
    
    public List<SelectItem> getAminoacidSeq() {
        return aminoacidSeq;
    }

    public void setAminoacidSeq(List<SelectItem> aminoacidSeq) {
        this.aminoacidSeq = aminoacidSeq;
    }

    public int getCount() {
        return count++;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getAminoSeqs() {
        return aminoSeqs;
    }

    public void setAminoSeqs(String[] aminoSeqs) {
        this.aminoSeqs = aminoSeqs;
    }
    
    public String getResultNucleotidSeq() {
        return resultNucleotidSeq;
    }

    public void setResultNucleotidSeq(String resultNucleotidSeq) {
        this.resultNucleotidSeq = resultNucleotidSeq;
    }

    public String[][] getNucleotidSeq() {
        return nucleotidSeq;
    }

    public void setNucleotidSeq(String[][] nucleotidSeq) {
        this.nucleotidSeq = nucleotidSeq;
    }
    
    public ArrayList<String> getConsensos() {
        return consensos;
    }

    public void setConsensos(ArrayList<String> consensos) {
        this.consensos = consensos;
    }
    
    public ArrayList<ArrayList<SequenceShow>> getSequencesShow() {
        return sequencesShow;
    }

    public void setSequencesShow(ArrayList<ArrayList<SequenceShow>> sequencesShow) {
        this.sequencesShow = sequencesShow;
    }

    public String[] getSequencesSplit() {
        return sequencesSplit;
    }

    public void setSequencesSplit(String[] sequencesSplit) {
        this.sequencesSplit = sequencesSplit;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public selectCodon getCodons() {
        return codons;
    }

    public void setCodons(selectCodon codons) {
        this.codons = codons;
    }
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public Integer getIdentico() {
        return identico;
    }

    public void setIdentico(Integer identico) {
        this.identico = identico;
    }
    
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
    
    public void submit(CodonUsage codon) throws IOException, Exception{
       
        if(codons.getAglobal() != null){
            String temp = codons.getAglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getA().size();i++) {
                if(codons.getCodonusage().getA().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getA().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getCglobal() != null){
            String temp = codons.getCglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getC().size();i++) {
                if(codons.getCodonusage().getC().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getC().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getDglobal() != null){
            String temp = codons.getDglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getD().size();i++) {
                if(codons.getCodonusage().getD().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getD().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getEglobal() != null){
            String temp = codons.getEglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getE().size();i++) {
                if(codons.getCodonusage().getE().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getE().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getFglobal() != null){
            String temp = codons.getFglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getF().size();i++) {
                if(codons.getCodonusage().getF().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getF().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getGglobal() != null){
            String temp = codons.getGglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getG().size();i++) {
                if(codons.getCodonusage().getG().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getG().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getHglobal() != null){
            String temp = codons.getHglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getH().size();i++) {
                if(codons.getCodonusage().getH().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getH().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getIglobal() != null){
            String temp = codons.getIglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getI().size();i++) {
                if(codons.getCodonusage().getI().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getI().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getKglobal() != null){
            String temp = codons.getKglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getK().size();i++) {
                if(codons.getCodonusage().getK().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getK().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getLglobal() != null){
            String temp = codons.getLglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getL().size();i++) {
                if(codons.getCodonusage().getL().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getL().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getMglobal() != null){
            String temp = codons.getMglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getM().size();i++) {
                if(codons.getCodonusage().getM().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getM().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getNglobal() != null){
            String temp = codons.getNglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getN().size();i++) {
                if(codons.getCodonusage().getN().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getN().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getPglobal() != null){
            String temp = codons.getPglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getP().size();i++) {
                if(codons.getCodonusage().getP().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getP().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getQglobal() != null){
            String temp = codons.getQglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getQ().size();i++) {
                if(codons.getCodonusage().getQ().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getQ().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getRglobal() != null){
            String temp = codons.getRglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getR().size();i++) {
                if(codons.getCodonusage().getR().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getR().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getSglobal() != null){
            String temp = codons.getSglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getS().size();i++) {
                if(codons.getCodonusage().getS().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getS().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getTglobal() != null){
            String temp = codons.getTglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getT().size();i++) {
                if(codons.getCodonusage().getT().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getT().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getVglobal() != null){
            String temp = codons.getVglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getV().size();i++) {
                if(codons.getCodonusage().getV().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getV().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getWglobal() != null){
            String temp = codons.getWglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getW().size();i++) {
                if(codons.getCodonusage().getW().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getW().get(i).setProbabilidad(0.0);    
                }
            }
        }
        if(codons.getYglobal() != null){
            String temp = codons.getYglobal();
            String [] cdn = temp.split(" : ");
            String tripleta = cdn[0];
            for (int i =0;i< codons.getCodonusage().getY().size();i++) {
                if(codons.getCodonusage().getY().get(i).getcodon().compareToIgnoreCase(tripleta)!=0){
                    codons.getCodonusage().getY().get(i).setProbabilidad(0.0);    
                }
            }
        }
        
        //Función que permite subir archivos al servidor
        /*
        try(InputStream inputFile = file.getInputstream()){
            random = new Random().nextInt(1000);
            Files.copy(inputFile, new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/R/"), random + file.getFileName()).toPath());
        }
        catch(IOException e){
            System.out.println("Error upload file: "+e.getMessage());
        }
        */
        
        BufferedReader br=null;
        try{
        
            br = new BufferedReader(new InputStreamReader(file.getInputstream()));
            if(br == null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un archivo", null));
                return;
            }
            String linea;
            ArrayList<String> identificador= new ArrayList<String>();
            ArrayList<String> secuencia= new ArrayList<String>();
            int contador=-1;
            boolean primero=false;
            while ((linea = br.readLine()) != null) {
                if (linea.length() == 0) {
                
                }
                else{
                    if(linea.charAt(0)=='>'){
                        //poner identificador
                        identificador.add(linea);
                        contador++;
                        primero = true;
                    }
                    else{
                        if (primero == true) {
                            secuencia.add(linea);
                            primero = false;
                        } else {
                            linea = secuencia.get(contador).concat(linea);
                            secuencia.set(contador, linea);
                        }

                    }
                }

            }
            input.add(identificador);
            input.add(secuencia);

        }catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if(br != null){
                    br.close(); 
                }
            } catch (IOException e) {
            }
        }
        
        // SE CARGA EL ABECEDARIO
        createIUPAC();
        // SE CARGA EL USO DE CODONES
        createCodonUsage();        
        
        sequencesSplit = multipleSequenceAlignment(input.get(1));
        
        Integer largo = sequencesSplit[0].length();
        boolean validate = validateSequence(sequencesSplit[0]);
        if(!validate){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La secuencia 1 "+sequencesSplit[0]+" es inválida", null));
            return;
        }
        boolean prueba=false;
        // SE COMPRUEBA QUE LAS SECUENCIAS TENGAN EL MISMO LARGO
        for (int i = 1; i < sequencesSplit.length && validate; i++) {
            if(!validateSequence(sequencesSplit[i])){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La secuencia "+(i+1)+" "+sequencesSplit[i]+" es inválida", null));
                return;
            }
            if(largo != sequencesSplit[i].length()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las secuencias deben tener el mismo largo", null));
                return;
            } 
            else{
                log = "OK";
                prueba=true;
            }
        }
        
        resultado = resultado=determinarConservado(sequencesSplit);
        consensos = determinarConsenso(resultado, conservado, tamanoPrimer);
        probabilidades=calculoProbabilidad(matrizSequence);
        
        marcaSitiosConservados();
        
        for (int h = 0; h < consensos.size(); h++) {
            secuenciasNucleotidos.add(nucleotidSequence(consensos.get(h)));
        }
        
    }
    
    public boolean validateSequence(String seq){
        boolean validate = true;
        
        for(int i=0; i<seq.length(); i++){
            if(!(seq.substring(i, i+1).equals("-") || seq.substring(i, i+1).equals("A") || seq.substring(i, i+1).equals("C") || seq.substring(i, i+1).equals("D") || seq.substring(i, i+1).equals("E") || seq.substring(i, i+1).equals("F") || seq.substring(i, i+1).equals("G") || seq.substring(i, i+1).equals("H") || seq.substring(i, i+1).equals("I") || seq.substring(i, i+1).equals("K") || seq.substring(i, i+1).equals("L") || seq.substring(i, i+1).equals("M") || seq.substring(i, i+1).equals("N") || seq.substring(i, i+1).equals("P") || seq.substring(i, i+1).equals("Q") || seq.substring(i, i+1).equals("R") || seq.substring(i, i+1).equals("S") || seq.substring(i, i+1).equals("T") || seq.substring(i, i+1).equals("V") || seq.substring(i, i+1).equals("W") || seq.substring(i, i+1).equals("Y") || seq.substring(i, i+1).equals("X"))){
                validate = false;
                System.out.println("ERROR en Columna "+ i+1+" - Seq: "+seq.substring(i, i+1));
                break;
            }
        }
        
        return validate;
    }
    
    public ArrayList<String> determinarConservado(String[] sequences) {
        ArrayList<String> sitios = new ArrayList<String>();
        matrizSequence = new String[sequences[0].length()][sequences.length];
        for (int i = 0; i < sequences.length; i++) {
            for (int j = 0; j < sequences[0].length(); j++) {
                matrizSequence[j][i] = sequences[i].substring(j, j + 1);
            }
        }
        
        ArrayList<String> temporal = new ArrayList<>();
        for (int i = 0; i < sequences[0].length(); i++) {
            int contador=0;
            for (int j = 0; j < sequences.length; j++) {
                if(matrizSequence[i][j].equals("-")==true){
                    contador++;
                }
                
                if (j == 0) {
                    temporal.add(matrizSequence[i][j]);
                } else if (temporal.contains(matrizSequence[i][j]) == false) {
                    temporal.add(matrizSequence[i][j]);
                    if(j==sequences.length-1 && contador>= ((sequences.length)/2)){
                        temporal.add("-1");
                        
                    }
                }if (temporal.contains(matrizSequence[i][j]) == true){
                    if (j == sequences.length - 1 && contador >= ((sequences.length) / 2)) {
                    temporal.add("-1");
                   
                }                    
                }
                
            }
            String temp = "";
            for (int k = 0; k < temporal.size(); k++) {
                if(temporal.contains("-1")==true){
                    temporal.remove("-1");
                    if(k==0){
                        temp="ñ";
                    }
                    temp = temp.concat(temporal.get(k));                    
                }
                else{
                    temp = temp.concat(temporal.get(k));
                }
            }
            sitios.add(temp);
            temporal.clear();
        }

        return sitios;
    }
    
    public ArrayList<String> determinarConsenso(ArrayList<String> sitios, Integer sitioConservados, Integer tam) {
        ArrayList<String> consenso = new ArrayList<String>();
        ArrayList<String> tipo = new ArrayList<String>();
        for (int i = 0; i < sitios.size(); i++) {
            if (sitios.get(i).length() <= (identico+1)) {
                if (sitios.get(i).charAt(0) == 'ñ') {//si existen muchos gaps que aminoacidos
                    sitios.set(i, sitios.get(i).substring(1));
                    tipo.add("X");
                } else {                   
                    tipo.add("I");
                }
            }
            if (sitios.get(i).length() <= sitioConservados && sitios.get(i).length() > (identico+1)) {
                if(sitios.get(i).charAt(0)=='ñ'){//si existen muchos gaps que aminoacidos
                    sitios.set(i, sitios.get(i).substring(1));
                    tipo.add("X");
                }
                else{
                    tipo.add("C");
                }
            }
            if (sitios.get(i).length() > sitioConservados) {
                if (sitios.get(i).charAt(0) == 'ñ') {//si existen muchos gaps que aminoacidos
                    sitios.set(i, sitios.get(i).substring(1));
                    tipo.add("X");
                } else {
                    tipo.add("X");
                }
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
                if (posicionInf >= 0 && posicionSup < tipo.size()) {
                    for (int j = posicionInf; j <= posicionSup; j++) {
                        if (tipo.get(j).equals("C") == true) {
                            puntaje = puntaje + 2;
                        }
                        if (tipo.get(j).equals("I") == true) {
                            puntaje = puntaje + 4;
                        }
                        if (tipo.get(j).equals("X") == true) {
                            puntaje = puntaje + 1;
                        }
                    }
                    if (puntaje > puntajeMax[0]) {
                        puntajeMax[0] = puntaje;
                        puntajeMax[1] = posicionInf;
                        puntajeMax[2] = posicionSup;
                    }
                    puntaje = 0;
                }
            }
            String temporal = "";
            boolean esMenor = false; //para determinar si un región conservada es menor a lo solicitado
            for (int i = 0; i < tam; i++) {
                if (tipo.get(puntajeMax[1] + i).equals("N") == true) {
                    esMenor = true;
                }
                if (tipo.get(puntajeMax[1] + i).equals("N") == false) { 
                    if (i > 0 && esMenor == false) {                  
                        temporal = temporal.concat("-");
                        temporal = temporal.concat(sitios.get(puntajeMax[1] + i));
                    }
                    if (i > 0 && esMenor == true) {
                        if ((puntajeMax[1] + i) - 1 < posicion) {
                            temporal = temporal.concat("-");
                            temporal = temporal.concat(sitios.get(puntajeMax[1] + i));
                        }
                    }
                    if (i == 0) {
                        temporal = temporal.concat(sitios.get(puntajeMax[1] + i));
                    }
                    tipo.set(puntajeMax[1] + i, "N");
                }
            }
            puntajeMax[0] = 0;
            if (esMenor == false) {
                consenso.add(temporal);
                posiciones.add(puntajeMax[1]);
                if (puntajeMax[2] >= sitios.size()) {
                    puntajeMax[2] = sitios.size() - 1;
                }
                posiciones.add(puntajeMax[2]);
            }
            if (esMenor == true) {
                int tempTam = consenso.size() - 1;
                consenso.set(tempTam, consenso.get(tempTam).concat(temporal));
                if (puntajeMax[2] >= sitios.size()) {
                    puntajeMax[2] = sitios.size() - 1;
                }
                posiciones.set(posiciones.size() - 1, puntajeMax[2]);
            }
        }
        return consenso;
    }
    
    public Integer count(String arreglo[], String aminoacido) {
        Integer match = 0;
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].equals(aminoacido)==true) {
                match++;
            }
        }
        return match;
    }

    public ArrayList<ArrayList <Aminoacid>>calculoProbabilidad(String matriz [][]){
        ArrayList<ArrayList <Aminoacid>> resultado= new ArrayList<ArrayList<Aminoacid>>() ;
        ArrayList<Aminoacid> conservado;
        Aminoacid aminoacido;
        ArrayList <String> temporal = new ArrayList<String>();
        DecimalFormat df2 = new DecimalFormat(".##");
        for(int i=0;i<matriz.length;i++){
            conservado=new ArrayList<Aminoacid>();            
            for(int j=0;j<matriz[i].length;j++){
                double contador=0;
                int inicio=0, fin=0;
                if(temporal.contains(matriz[i][j])==false && matriz[i][j].equals("-")==false){
                    contador=count(matriz[i],matriz[i][j]);
                    temporal.add(matriz[i][j]); 
                    for (int k=0;k<posiciones.size()/2;k++){
                        inicio = posiciones.get(2*k);
                        fin = posiciones.get(2*k+1);
                        if(inicio<= i && fin >=i){
                            contador = (contador / matriz[i].length) * 100;
                            aminoacido = new Aminoacid(matriz[i][j], df2.format(contador).concat("%"));
                            conservado.add(aminoacido);
                        }
                    }
                }
            }
            if(conservado.isEmpty()==false){
                resultado.add(conservado);
            }
            
            temporal.clear();
        }
        return resultado;
    }
    
    public Aminoacid findAminoacid(String amino){
        for(int i=0; i<probabilidades.size(); i++){
            for(int j=0; j<probabilidades.get(i).size(); j++){
                if(probabilidades.get(i).get(j).getAminoacid().equals(amino)){
                    return probabilidades.get(i).get(j);
                }
            }
        }
        return null;
    }
    
    public Aminoacid [][] joinConsesusWhitProbabilidaes(String seq){
        String [] allConsensos = seq.split("-");
        Aminoacid [][] aminos = new Aminoacid[allConsensos.length][];
        
        for(int i = 0; i < allConsensos.length; i++){
            if(allConsensos[i].length() != 0){
                if(allConsensos[i].length() == 1){
                    if(!allConsensos[i].equals(" ")){
                        aminos[i] = new Aminoacid[1];
                        aminos[i][0] = findAminoacid(allConsensos[i]);
                    }
                }
                else{
                    aminos[i] = new Aminoacid[allConsensos[i].length()];
                    for(int j = 0; j < allConsensos[i].length(); j++){
                        aminos[i][j] = findAminoacid(allConsensos[i].substring(j, j+1));
                    }
                }
            } 
        }
                       
        return aminos;
    }
    
    //Recibe Strings separados por un guión '-'
    public String nucleotidSequence(String seq){
        String nucleotidSeq = "";
        String [] consenso = seq.split("-");
     
        for (String cons : consenso){
            if(cons.length() == 1){
                String aux = getCodon(cons).getcodon();
                nucleotidSeq = nucleotidSeq.concat(aux);
            }
            else {
                String nucleotidoIupac = "";
                ArrayList <String> codones = new ArrayList<String>();
                for(int i=0; i<cons.length(); i++){
                    codones.add(getCodon(cons.substring(i, i+1)).getcodon());
                }
                
                int largo = 3;
                
                
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
                            nucleotidoIupac = nucleotidoIupac.concat("V");
                            break;
                        case 1111:
                            nucleotidoIupac = nucleotidoIupac.concat("N");
                            break;
                        default:
                            break;
                    }
                }
                nucleotidSeq = nucleotidSeq.concat(nucleotidoIupac);
            }
        }
        return nucleotidSeq;
    }
    
    public int getLengthAminoSeq(String consenso){
        return consenso.split("-").length;
    }
    
    public void setLengthAminoSeq(int largo){
        this.aminoSeqs = new String[largo];
        for(String str : this.aminoSeqs){
            str = new String();
        }
    }
    
    public List<SelectItem> setSelectItems(String seq){
        List<SelectItem> listItems = new ArrayList();
        String [] allConsensos = seq.split("-");
        
        List<SelectItemGroup> itemGroup = new ArrayList();
        for(String str : allConsensos){
            itemGroup.add(new SelectItemGroup(str));
        }
        
        for(int i = 0; i < allConsensos.length; i++){
            if(allConsensos[i].length() != 0){
                SelectItem [] select = new SelectItem[allConsensos[i].length()];
                if(allConsensos[i].length() == 1){
                    if(!allConsensos[i].equals(" ")){
                        Aminoacid amino = findAminoacid(allConsensos[i]);
                        select[0] = new SelectItem(i+"-"+0+"-"+allConsensos[i], amino.getAminoacid()+" "+amino.getProbabilidad());
                    }
                }
                else{
                    for(int j = 0; j < allConsensos[i].length(); j++){
                        Aminoacid amino = findAminoacid(allConsensos[i].substring(j, j+1));
                        select[j] = new SelectItem(i+"-"+j+"-"+allConsensos[i].substring(j, j+1), amino.getAminoacid()+" "+amino.getProbabilidad());
                    }
                }
                itemGroup.get(i).setSelectItems(select);
            } 
        }
       
        
        for(SelectItemGroup sg : itemGroup){
            listItems.add(sg);
        }
        
        return listItems;
    }
    
    public void editConsensus() throws IOException{
        String [] options = resultNucleotidSeq.split("-");
        String [][] secuencias = getConsensosSeqs(sequences);
        
        secuencias[Integer.parseInt(options[0])][Integer.parseInt(options[1])] = "";
        
        String result = "";
        
        for(String [] array : secuencias){
            if(array != null){
                for(String str : array){
                    result = result.concat(str);
                }
                result = result.concat("-");
            }
        }
        
        int index = consensos.indexOf(sequences);
        consensos.set(index, result.substring(0, result.length()));
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sequence's edit successfully", null));
    }
        
    public String [][] getConsensosSeqs(String consenso){
        String [] allConsensos = consenso.split("-");
        String [][] aminos = new String[allConsensos.length][];
        
        nucleotidSeq = new String[allConsensos.length][];
        
        for(int i = 0; i < allConsensos.length; i++){
            if(allConsensos[i].length() != 0){
                if(allConsensos[i].length() == 1){
                    if(!allConsensos[i].equals(" ")){
                        aminos[i] = new String[1];
                        nucleotidSeq[i] = new String[1];
                        aminos[i][0] = allConsensos[i];
                    }
                }
                else{
                    aminos[i] = new String[allConsensos[i].length()];
                    nucleotidSeq[i] = new String[allConsensos[i].length()];
                    for(int j = 0; j < allConsensos[i].length(); j++){
                        aminos[i][j] = allConsensos[i].substring(j, j+1);
                    }
                }
            } 
        }
        
        return aminos;
    }
    
    public void getCodonSeq(String [] aminoSeq){
        String sequence= "";
        for(String amino : aminoSeq){
            sequence = sequence.concat(getCodon(amino).getcodon());
        }
    
        resultNucleotidSeq = sequence;
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
            case "X":
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
    
    private String [] multipleSequenceAlignment(ArrayList<String> seqs) throws Exception {
        List<ProteinSequence> lst = new ArrayList<ProteinSequence>();
        for (String seq : seqs) {
            lst.add(new ProteinSequence(seq));
        }
        try{
            Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(lst);
            System.out.printf("Clustalw:%n%s%n", profile);
            ConcurrencyTools.shutdown();

            String [] secuencias = new String[profile.getAlignedSequences().size()];
         
            int i = 0;
            for (AlignedSequence align : profile.getAlignedSequences()){ 
                ArrayList<SequenceShow> aux = new ArrayList<>();
                secuencias[i] = align.getSequenceAsString();
                for(int j=0; j<secuencias[i].length(); j++){
                    aux.add(new SequenceShow(secuencias[i].substring(j, j+1)));
                }
                
                sequencesShow.add(aux);
                i++;
            }
            
            return secuencias;
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", null));
            return null;
        }

        
    }
    
    public void marcaSitiosConservados(){        
        int k;
        int inicio = 0;
        int fin = 0;
        
        for(int i = 0; i < sequencesShow.size(); i++){
            k = 0;
            for(int j = 0; j < sequencesShow.get(i).size(); j++){
                if (2*k < posiciones.size()){
                    inicio = posiciones.get(2*k);
                }
                if(2*k+1 < posiciones.size()){
                    fin = posiciones.get(2*k+1);
                }

                if(j >= inicio && j < fin){
                    sequencesShow.get(i).get(j).setSitioConservado(true);
                }
                else if(j == fin){
                    sequencesShow.get(i).get(j).setSitioConservado(true);
                    k++;
                }
            }
        }     
    }
    
    public void cleanAnswer(){
        secuenciasNucleotidos = new ArrayList<>();
        sequencesSplit = new String[1]; 
        sequencesShow = new ArrayList<>();
        input= new ArrayList<ArrayList<String>>();
        file = null;
        consensos = new ArrayList<>(); 
    }
    
    public ArrayList<String> convertNucleotideSequence(String seq){
        ArrayList<String> secuencias = new ArrayList<>();
        
        try {
            ProteinSequence amino = new ProteinSequence(seq);
            for(AminoAcidCompound cs : amino.getCompoundSet().getAllCompounds()){
                secuencias.add(cs.getShortName());
            }
            
        } catch (CompoundNotFoundException ex) {
            Logger.getLogger(PrimerG3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return secuencias;
    }
    
    public ArrayList<String> convertIUPACtoNormal(String secuencia){
        ArrayList<String> secuencias = new ArrayList<>();
        String aux;
        secuencias.add("");
        int j, largo;
        for(int i = 0; i < secuencia.length()-1; i++){
            aux = secuencia.substring(i, i+1);
            switch (aux) {
                case "A":
                    for(String str : secuencias){
                        str = str.concat("A");
                    }
                    break;
                case "C":
                    for(String str : secuencias){
                        str = str.concat("C");
                    }
                    break;
                case "G":
                    for(String str : secuencias){
                        str = str.concat("G");
                    }
                    break;
                case "T":
                    for(String str : secuencias){
                        str = str.concat("T");
                    }
                    break;
                case "R":
                    largo = secuencias.size();
                    for(int k = 0; k < largo; i++){
                        secuencias.add(secuencias.get(k));
                    }
                    j = 0;
                    for(String str : secuencias){
                        if(j < secuencias.size()/2){
                            str = str.concat("A");
                        }
                        else{
                            str = str.concat("G");
                        }
                        j++;
                    }
                    break;
                case "Y":
                    largo = secuencias.size();
                    for(int k = 0; k < largo; i++){
                        secuencias.add(secuencias.get(k));
                    }
                    j = 0;
                    for(String str : secuencias){
                        if(j < secuencias.size()/2){
                            str = str.concat("C");
                        }
                        else{
                            str = str.concat("T");
                        }
                        j++;
                    }
                    break;
                case "S":
                    largo = secuencias.size();
                    for(int k = 0; k < largo; i++){
                        secuencias.add(secuencias.get(k));
                    }
                    j = 0;
                    for(String str : secuencias){
                        if(j < secuencias.size()/2){
                            str = str.concat("G");
                        }
                        else{
                            str = str.concat("C");
                        }
                        j++;
                    }
                    break;
                case "W":
                    largo = secuencias.size();
                    for(int k = 0; k < largo; i++){
                        secuencias.add(secuencias.get(k));
                    }
                    j = 0;
                    for(String str : secuencias){
                        if(j < secuencias.size()/2){
                            str = str.concat("A");
                        }
                        else{
                            str = str.concat("T");
                        }
                        j++;
                    }
                    break;
                case "K":
                    largo = secuencias.size();
                    for(int k = 0; k < largo; i++){
                        secuencias.add(secuencias.get(k));
                    }
                    j = 0;
                    for(String str : secuencias){
                        if(j < secuencias.size()/2){
                            str = str.concat("G");
                        }
                        else{
                            str = str.concat("T");
                        }
                        j++;
                    }
                    break;
                case "M":
                    largo = secuencias.size();
                    for(int k = 0; k < largo; i++){
                        secuencias.add(secuencias.get(k));
                    }
                    j = 0;
                    for(String str : secuencias){
                        if(j < secuencias.size()/2){
                            str = str.concat("A");
                        }
                        else{
                            str = str.concat("C");
                        }
                        j++;
                    }
                    break;
                case "B":
                    largo = secuencias.size();
                    for(int k = 0; k < largo*2; i++){
                        secuencias.add(secuencias.get(k%largo));
                    }
                    i = 0;
                    for(String str : secuencias){
                        if(i < largo){
                            str = str.concat("C");
                        }
                        else if (i < largo * 2){
                            str = str.concat("G");
                        }
                        else{
                            str = str.concat("T");
                        }
                        i++;
                    }
                    break;
                case "D":
                    largo = secuencias.size();
                    for(int k = 0; k < largo*2; i++){
                        secuencias.add(secuencias.get(k%largo));
                    }
                    i = 0;
                    for(String str : secuencias){
                        if(i < largo){
                            str = str.concat("A");
                        }
                        else if (i < largo * 2){
                            str = str.concat("G");
                        }
                        else{
                            str = str.concat("T");
                        }
                        i++;
                    }
                    break;
                case "H":
                    largo = secuencias.size();
                    for(int k = 0; k < largo*2; i++){
                        secuencias.add(secuencias.get(k%largo));
                    }
                    i = 0;
                    for(String str : secuencias){
                        if(i < largo){
                            str = str.concat("A");
                        }
                        else if (i < largo * 2){
                            str = str.concat("C");
                        }
                        else{
                            str = str.concat("T");
                        }
                        i++;
                    }
                    break;
                case "V":
                    largo = secuencias.size();
                    for(int k = 0; k < largo*2; i++){
                        secuencias.add(secuencias.get(k%largo));
                    }
                    i = 0;
                    for(String str : secuencias){
                        if(i < largo){
                            str = str.concat("C");
                        }
                        else if (i < largo * 2){
                            str = str.concat("G");
                        }
                        else{
                            str = str.concat("A");
                        }
                        i++;
                    }
                    break;
                case "N":
                    largo = secuencias.size();
                    for(int k = 0; k < largo*3; i++){
                        secuencias.add(secuencias.get(k%largo));
                    }
                    i = 0;
                    for(String str : secuencias){
                        if(i < largo){
                            str = str.concat("C");
                        }
                        else if (i < largo * 2){
                            str = str.concat("G");
                        }
                        else if (i < largo * 3){
                            str = str.concat("T");
                        }
                        else{
                            str = str.concat("A");
                        }
                        i++;
                    }
                    break;
                default:
                    break;
            }
        }
        
        return secuencias;
    }
    
    public Process runRScript(){
        try {
            
            System.out.println("Entra en el script. File: "+ FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/R/")+" - "+file.getFileName());
            Process proc = Runtime.getRuntime().exec("\"c:\\Program Files\\R\\R-3.2.2\\bin\\Rscript.exe\" \"c:\\Users\\JAno\\Documents\\Universidad\\Bioinformática\\AutoPrimerX-CeBiB-\\AutoPrimerX-web\\src\\main\\webapp\\resources\\R\\alineamiento.R\" "+ FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/R/") + "\\" + random + file.getFileName());
            try{
                Files.delete(new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/R/") + "/" + random + file.getFileName()).toPath());
            }
            catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }
            
            return proc;
        }
        catch(IOException e){
            System.out.println("ERROR Run Script IO: "+e.getMessage());
            return null;
        }
        catch(NullPointerException e){
            System.out.println("ERROR Run Script Null: "+e.getMessage());
            return null;
        }
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
    
    
        
    
    public void cambiarUsoCodon(CodonUsage selected){
        ArrayList <Codon> G= new ArrayList<>();
            G.add(new Codon("GGG", selected.getGGG_probabilidad()));
            G.add(new Codon("GGA",selected.getGGA_probabilidad()));
            G.add(new Codon("GGT",selected.getGGU_probabilidad()));
            G.add(new Codon("GGC",selected.getGGC_probabilidad()));
        Collections.sort(G, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> A= new ArrayList<>();
            A.add(new Codon("GCG",selected.getGCG_probabilidad()));
            A.add(new Codon("GCA",selected.getGCA_probabilidad()));
            A.add(new Codon("GCT",selected.getGCU_probabilidad()));
            A.add(new Codon("GCC",selected.getGCC_probabilidad()));
        Collections.sort(A, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> L= new ArrayList<>();
            L.add(new Codon("TTG",selected.getUUG_probabilidad()));
            L.add(new Codon("TTA",selected.getUUA_probabilidad()));
            L.add(new Codon("CTG",selected.getCUG_probabilidad()));
            L.add(new Codon("CTA",selected.getCUA_probabilidad()));
            L.add(new Codon("CTT",selected.getCUU_probabilidad()));
            L.add(new Codon("CTC",selected.getCUC_probabilidad()));
        Collections.sort(L, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> M= new ArrayList<>();
            M.add(new Codon("ATG",selected.getAUG_probabilidad()));
        ArrayList <Codon> F= new ArrayList<>();
            F.add(new Codon("TTT",selected.getUUU_probabilidad()));
            F.add(new Codon("TTC",selected.getUUC_probabilidad()));
        Collections.sort(F, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> W= new ArrayList<>();
            W.add(new Codon("TGG",selected.getUGG_probabilidad()));
        ArrayList <Codon> K= new ArrayList<>();
            K.add(new Codon("AAG",selected.getAAG_probabilidad()));
            K.add(new Codon("AAA",selected.getAAA_probabilidad()));
        Collections.sort(K, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> E= new ArrayList<>();
            E.add(new Codon("GAG",selected.getGAG_probabilidad()));
            E.add(new Codon("GAA",selected.getGAA_probabilidad()));
        Collections.sort(E, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> S= new ArrayList<>();
            S.add(new Codon("AGT",selected.getAGU_probabilidad()));
            S.add(new Codon("AGC",selected.getAGC_probabilidad()));
            S.add(new Codon("TCG",selected.getUCG_probabilidad()));
            S.add(new Codon("TCA",selected.getUCA_probabilidad()));
            S.add(new Codon("TCT",selected.getUCU_probabilidad()));
            S.add(new Codon("TCC",selected.getUCC_probabilidad()));
        Collections.sort(S, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> V= new ArrayList<>();
            V.add(new Codon("GTG",selected.getGUG_probabilidad()));
            V.add(new Codon("GTA",selected.getGUA_probabilidad()));
            V.add(new Codon("GTT",selected.getGUU_probabilidad()));
            V.add(new Codon("GTC",selected.getGUC_probabilidad()));
        Collections.sort(V, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> I= new ArrayList<>();
            I.add(new Codon("ATA",selected.getAUA_probabilidad()));
            I.add(new Codon("ATT",selected.getAUU_probabilidad()));
            I.add(new Codon("ATC",selected.getAUC_probabilidad()));
        Collections.sort(I, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> C= new ArrayList<>();
            C.add(new Codon("TGT",selected.getUGU_probabilidad()));
            C.add(new Codon("TGC",selected.getUGC_probabilidad()));
        Collections.sort(C, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> Y= new ArrayList<>();
            Y.add(new Codon("TAT",selected.getUAU_probabilidad()));
            Y.add(new Codon("TAC",selected.getUAC_probabilidad()));
        Collections.sort(Y, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> H= new ArrayList<>();
            H.add(new Codon("CAT",selected.getCAU_probabilidad()));
            H.add(new Codon("CAC",selected.getCAC_probabilidad()));
        Collections.sort(H, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> R= new ArrayList<>();
            R.add(new Codon("AGG",selected.getAGG_probabilidad()));
            R.add(new Codon("AGA",selected.getAGA_probabilidad()));
            R.add(new Codon("CGG",selected.getCGG_probabilidad()));
            R.add(new Codon("CGA",selected.getCGA_probabilidad()));
            R.add(new Codon("CGT",selected.getCGU_probabilidad()));
            R.add(new Codon("CGC",selected.getCGC_probabilidad()));
         Collections.sort(R, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> N= new ArrayList<>();
            N.add(new Codon("AAT",selected.getAAU_probabilidad()));
            N.add(new Codon("AAC",selected.getAAC_probabilidad()));
        Collections.sort(N, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> D= new ArrayList<>();
            D.add(new Codon("GAT",selected.getGAU_probabilidad()));
            D.add(new Codon("GAC",selected.getGAC_probabilidad()));
        Collections.sort(D, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> T= new ArrayList<>();
            T.add(new Codon("ACG",selected.getACG_probabilidad()));
            T.add(new Codon("ACA",selected.getACA_probabilidad()));
            T.add(new Codon("ACT",selected.getACU_probabilidad()));
            T.add(new Codon("ACC",selected.getACC_probabilidad()));
        Collections.sort(T, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> P= new ArrayList<>();
            P.add(new Codon("CCT",selected.getCCU_probabilidad()));
            P.add(new Codon("CCC",selected.getCCC_probabilidad()));
            P.add(new Codon("CCA",selected.getCCA_probabilidad()));
            P.add(new Codon("CCG",selected.getCCG_probabilidad()));
        Collections.sort(P, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        ArrayList <Codon> Q= new ArrayList<>();
            Q.add(new Codon("CAA",selected.getCAA_probabilidad()));
            Q.add(new Codon("CAG", selected.getCAG_probabilidad()));
        Collections.sort(Q, new Comparator<Codon>() {
                @Override
                public int compare(Codon c1,Codon c2)
                {

                    return Double.compare(c2.getProbabilidad(),c1.getProbabilidad());
                }
            });
        codons.setCodonusage(new CondonUsage(A, C, D, E, F, G, H, I, K, L, M, N , P, Q, R, S, T, V, W, Y));
    }
    
}
