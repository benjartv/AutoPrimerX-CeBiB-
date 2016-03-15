package primerg2Domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.ArrayUtils;

public class G2Utils {

	public static double funcionTM(String secuencia) {
		int A = 0;
		int G = 0;
		int C = 0;
		int T = 0;
		double Tm;
		for (int i = 0; i < secuencia.length(); i++) {
			if (secuencia.charAt(i) == 'A')
				A = A + 1;
			if (secuencia.charAt(i) == 'C')
				C = C + 1;
			if (secuencia.charAt(i) == 'G')
				G = G + 1;
			if (secuencia.charAt(i) == 'T')
				T = T + 1;
		}
		if (secuencia.length() < 14) {
			Tm = (A + T) * 2 + (C + G) * 4;
		} else {
			Tm = 64.9 + 41 * (C + G - 16.4) / (A + T + C + G);
		}
		return Tm;
	}

	public static String complemento(String secuencia) {
		String strComplemento = "";
		ArrayList<Character> array = new ArrayList<Character>();
		for (int i = 0; i < secuencia.length(); i++) {
			if (secuencia.charAt(i) == 'C')
				array.add('G');
			if (secuencia.charAt(i) == 'G')
				array.add('C');
			if (secuencia.charAt(i) == 'A')
				array.add('T');
			if (secuencia.charAt(i) == 'T')
				array.add('A');
		}
		for (int i = 0; i < array.size(); i++) {
			strComplemento = strComplemento + array.get(i);
		}
		return strComplemento;
	}

	public static double molecularWeight(String secuencia) {
		int A = 0;
		int G = 0;
		int C = 0;
		int T = 0;
		double molWeight;
		for (int i = 0; i < secuencia.length(); i++) {
			if (secuencia.charAt(i) == 'A')
				A = A + 1;
			if (secuencia.charAt(i) == 'C')
				C = C + 1;
			if (secuencia.charAt(i) == 'G')
				G = G + 1;
			if (secuencia.charAt(i) == 'T')
				T = T + 1;
		}
		molWeight = ((A * 313.21) + (T * 304.2) + (C * 289.18) + (G * 329.21) - 61.96);

		return molWeight;
	}

	public static double funcionTMajustada(String secuencia) {
		int A = 0;
		int G = 0;
		int C = 0;
		int T = 0;
		double TmAjustada;
		double NA = 50;
		for (int i = 0; i < secuencia.length(); i++) {
			if (secuencia.charAt(i) == 'A')
				A = A + 1;
			if (secuencia.charAt(i) == 'C')
				C = C + 1;
			if (secuencia.charAt(i) == 'G')
				G = G + 1;
			if (secuencia.charAt(i) == 'T')
				T = T + 1;
		}
		if (secuencia.length() < 14) {
			TmAjustada = (A + T) * 2 + (G + C) * 4 - 16.6 * Math.log10(0.050) + 16.6 * Math.log10(NA);
		} else {
			TmAjustada = 100.5 + (41 * (G + C) / (A + T + G + C)) - (820 / (A + T + G + C)) + 16.6 * Math.log10(NA);
		}
		return TmAjustada;
	}

	public static double porcentajeGC(String secuencia) {
		double GC;
		double cantidadGC = 0;
		for (int i = 0; i < secuencia.length(); i++) {
			if (secuencia.charAt(i) == 'C' || secuencia.charAt(i) == 'G') {
				cantidadGC = cantidadGC + 1;
			}
		}
		GC = (cantidadGC / secuencia.length()) * 100.00;
		GC = (double) Math.round(GC * 100d) / 100d;
		return GC;
	}

	public static String forward(String secuencia, double TM) {
		ArrayList<Character> arregloFWD = new ArrayList<Character>();
		ArrayList<Character> arreglo = new ArrayList<Character>();
		double tmAux = 0;
		char[] arr = secuencia.toCharArray();
		String forwd = "";
		// char[] to ArrayList
		for (int i = 0; i < arr.length; i++) {
			arreglo.add(arr[i]);
		}
		// Recorro la secuencia entera
		for (int i = 0; i < secuencia.length(); i++) {
			// Cuando la temperatura actual sea menor a la máxima.
			if (tmAux <= TM) {
				/*
				 * Agrego en arraylist el char y calculo su forwd en base a su
				 * Tm se verifica si sirve o no
				 * 
				 */
				arregloFWD.add(arreglo.get(i));
				for (int j = 0; j < arregloFWD.size(); j++) {
					forwd = forwd + arregloFWD.get(i);
					tmAux = funcionTM(forwd);
					if (tmAux > TM) {
						break;
					}
					break;
				}

			}
		}

		return forwd;
	}

	public static String reverse(String secuencia, double TM) {
		char[] arr = secuencia.toCharArray();
		char[] arregloRVR = new char[arr.length];
		double tmAux = 0;
		String rvr = "";
		String reversoComplementario = "";
		int largoAux = arr.length - 1;
		for (int i = largoAux; i > 0; i--) {
			if (tmAux < TM) {
				// System.out.println("hol"+i);
				arregloRVR[i] = arr[i];
				// arregloRVR.add(arr[i]);
				for (int j = 0; j < arregloRVR.length; j++) {
					rvr = rvr + arregloRVR[i];
					tmAux = funcionTM(rvr);
					if (tmAux >= TM) {
						break;
					}
					break;
				}
			}
		}
		int largoRVR = rvr.length();
		int largoSEC = secuencia.length();
		int diferencia = largoSEC - largoRVR;
		for (int i = diferencia; i < largoSEC; i++) {
			reversoComplementario = reversoComplementario + arr[i];
		}

		return reversoComplementario;
	}

	public static String reverse_EXT(String secuencia, String rev, double TM) {

		char[] arr = secuencia.toCharArray();
		char[] arregloRVREXT = new char[arr.length];
		double tmAux = 0;
		String rvrEXT = "";
		for (int i = 0; i < arr.length; i++) {
			if (tmAux < TM) {
				arregloRVREXT[i] = arr[i];
				for (int j = 0; j < arregloRVREXT.length; j++) {
					rvrEXT = rvrEXT + arregloRVREXT[i];
					tmAux = funcionTM(rev + rvrEXT);
					if (tmAux >= TM) {
						break;
					}
					break;
				}

			}
		}

		return rvrEXT;
	}

	public static String forward_EXT(String secuencia, String fwd, double TM) {
		char[] arr = secuencia.toCharArray();
		char[] arregloFWDEXT = new char[arr.length];
		double tmAux = 0;
		String fwdEXT = "";
		String forwardExtension = "";
		int largoAux = arr.length - 1;
		for (int i = largoAux; i > 0; i--) {
			if (tmAux < TM) {
				// System.out.println("hol"+i);
				arregloFWDEXT[i] = arr[i];
				// arregloRVR.add(arr[i]);
				for (int j = 0; j < arregloFWDEXT.length; j++) {
					fwdEXT = fwdEXT + arregloFWDEXT[i];
					tmAux = funcionTM(fwdEXT + fwd);
					if (tmAux >= TM) {
						break;
					}
					break;
				}
			}

		}
		int largoFWDEXT = fwdEXT.length();
		int largoSEC = secuencia.length();
		int diferencia = largoSEC - largoFWDEXT;
		for (int i = diferencia; i < largoSEC; i++) {
			forwardExtension = forwardExtension + arr[i];
		}
		return forwardExtension;
		// System.out.println("El frwd extension es: "+ forwardExtension);
	}
	
	public static String reverString(String sequence){
		char[] arr = sequence.toCharArray();
		ArrayUtils.reverse(arr);
		//sequence = arr.toString();
		System.out.println("original " + sequence);
		sequence = String.valueOf(arr);
		System.out.println("revertida " + sequence);
		
		return sequence;
	}

	public static List<Sequence> create_sequence(List<Input> inputs, String option, double TM, double tolerance) {
		List<Sequence> sequences = new ArrayList<>();
		String comp;
		String fwd;
		String rev;
		double tm_fwd;
		double tm_rev;
		for (Input input : inputs) {
			comp = G2Utils.complemento(input.getSequence());
			char[] arr = comp.toCharArray();
			fwd = G2Utils.forward(input.getSequence(), TM);
			rev = G2Utils.reverse(comp, TM);
			tm_fwd = G2Utils.funcionTM(fwd);
			tm_rev = G2Utils.funcionTM(rev);

			if (Math.abs(tm_fwd - tm_rev) <= tolerance) {

				Sequence sequence = new Sequence(input.getName(), input.getSequence(), comp, fwd, rev, tm_fwd, tm_rev);
				sequences.add(sequence);
			}

			else {
				if (fwd.length() + rev.length() < arr.length) {
					for (int i = 1; i < arr.length; i++) {
						// do {
						fwd = fwd + arr[fwd.length() + i];
						rev = rev + arr[input.getSequence().length() - (rev.length() + i)];
						tm_fwd = G2Utils.funcionTM(fwd);
						tm_rev = G2Utils.funcionTM(rev);
						if (Math.abs(tm_fwd - tm_rev) <= tolerance) {

							Sequence sequence = new Sequence(input.getName(), input.getSequence(), comp, fwd, rev,
									tm_fwd, tm_rev);
							sequences.add(sequence);
						}
						// } while (Math.abs(tm_fwd - tm_rev) > 3);
					}
				} else {

					System.out.println("ERROR: No es posible cumplir la condicion de tolerancia");
					FacesContext context;
					context = FacesContext.getCurrentInstance();
					context.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Tolerance is very low"));
				}
			}

		}

		return sequences;
	}

	public static List<SequenceExt> sequences_ext(List<Sequence> pre_sequences, String option, double TMprimer) {
		String rev_ext = "";
		String fwd_ext = "";

		double tm_rev_primer;
		double tm_fwd_primer;

		List<SequenceExt> sequences_ext = new ArrayList<>();
		// String comp_aux;
		if (pre_sequences.size() > 0) {
			for (int i = 0; i < pre_sequences.size(); i++) {
				if (option.equals("lineal")) {

					if (i == 0) {
						rev_ext = G2Utils.reverse_EXT(pre_sequences.get(i + 1).getComp(), pre_sequences.get(i).getRev(),
								TMprimer);

						fwd_ext = "";
						tm_rev_primer = G2Utils.funcionTM(rev_ext + pre_sequences.get(i).getRev());
						SequenceExt sequence = new SequenceExt(pre_sequences.get(i).getName(),
								pre_sequences.get(i).getSequence(), pre_sequences.get(i).getComp(),
								pre_sequences.get(i).getFwd(), pre_sequences.get(i).getRev(), "", rev_ext,
								pre_sequences.get(i).getTm_fwd(), pre_sequences.get(i).getTm_rev(), 0, tm_rev_primer);
						sequences_ext.add(sequence);
					}

					else if (i + 1 == pre_sequences.size()) {
						rev_ext = "";
						fwd_ext = G2Utils.forward_EXT(pre_sequences.get(i - 1).getSequence(),
								pre_sequences.get(i).getFwd(), TMprimer);

						tm_fwd_primer = G2Utils.funcionTM(fwd_ext + pre_sequences.get(i).getFwd());
						SequenceExt sequence = new SequenceExt(pre_sequences.get(i).getName(),
								pre_sequences.get(i).getSequence(), pre_sequences.get(i).getComp(),
								pre_sequences.get(i).getFwd(), pre_sequences.get(i).getRev(), fwd_ext, "",
								pre_sequences.get(i).getTm_fwd(), pre_sequences.get(i).getTm_rev(), tm_fwd_primer, 0);
						sequences_ext.add(sequence);

					}

					else {

						rev_ext = G2Utils.reverse_EXT(pre_sequences.get(i + 1).getComp(), pre_sequences.get(i).getRev(),
								TMprimer);
						fwd_ext = G2Utils.forward_EXT(pre_sequences.get(i - 1).getSequence(),
								pre_sequences.get(i).getFwd(), TMprimer);
						tm_rev_primer = G2Utils.funcionTM(rev_ext + pre_sequences.get(i).getRev());
						tm_fwd_primer = G2Utils.funcionTM(fwd_ext + pre_sequences.get(i).getFwd());
						SequenceExt sequence = new SequenceExt(pre_sequences.get(i).getName(),
								pre_sequences.get(i).getSequence(), pre_sequences.get(i).getComp(),
								pre_sequences.get(i).getFwd(), pre_sequences.get(i).getRev(), fwd_ext, rev_ext,
								pre_sequences.get(i).getTm_fwd(), pre_sequences.get(i).getTm_rev(), tm_fwd_primer,
								tm_rev_primer);
						sequences_ext.add(sequence);
					}

				} else {

					if (i == 0) {
						rev_ext = G2Utils.reverse_EXT(pre_sequences.get(i + 1).getComp(), pre_sequences.get(i).getRev(),
								TMprimer);
						fwd_ext = G2Utils.forward_EXT(pre_sequences.get(pre_sequences.size() - 1).getSequence(),
								pre_sequences.get(i).getFwd(), TMprimer);
						
						tm_rev_primer = G2Utils.funcionTM(rev_ext + pre_sequences.get(i).getRev());
						tm_fwd_primer = G2Utils.funcionTM(fwd_ext + pre_sequences.get(i).getFwd());
						SequenceExt sequence = new SequenceExt(pre_sequences.get(i).getName(),
								pre_sequences.get(i).getSequence(), pre_sequences.get(i).getComp(),
								pre_sequences.get(i).getFwd(), pre_sequences.get(i).getRev(), fwd_ext, rev_ext,
								pre_sequences.get(i).getTm_fwd(), pre_sequences.get(i).getTm_rev(), tm_fwd_primer,
								tm_rev_primer);
						sequences_ext.add(sequence);
					}

					else if (i + 1 == pre_sequences.size()) {
						rev_ext = G2Utils.forward_EXT(pre_sequences.get(0).getComp(), pre_sequences.get(i).getFwd(),
								TMprimer);
						fwd_ext = G2Utils.forward_EXT(pre_sequences.get(i - 1).getSequence(),
								pre_sequences.get(i).getFwd(), TMprimer);
						tm_rev_primer = G2Utils.funcionTM(rev_ext + pre_sequences.get(i).getRev());
						tm_fwd_primer = G2Utils.funcionTM(fwd_ext + pre_sequences.get(i).getFwd());
						SequenceExt sequence = new SequenceExt(pre_sequences.get(i).getName(),
								pre_sequences.get(i).getSequence(), pre_sequences.get(i).getComp(),
								pre_sequences.get(i).getFwd(), pre_sequences.get(i).getRev(), fwd_ext, rev_ext,
								pre_sequences.get(i).getTm_fwd(), pre_sequences.get(i).getTm_rev(), tm_fwd_primer,
								tm_rev_primer);
						sequences_ext.add(sequence);

					}

					else {
						rev_ext = G2Utils.reverse_EXT(pre_sequences.get(i + 1).getComp(), pre_sequences.get(i).getRev(),
								TMprimer);
						fwd_ext = G2Utils.forward_EXT(pre_sequences.get(i - 1).getSequence(),
								pre_sequences.get(i).getFwd(), TMprimer);
						tm_rev_primer = G2Utils.funcionTM(rev_ext + pre_sequences.get(i).getRev());
						tm_fwd_primer = G2Utils.funcionTM(fwd_ext + pre_sequences.get(i).getFwd());
						SequenceExt sequence = new SequenceExt(pre_sequences.get(i).getName(),
								pre_sequences.get(i).getSequence(), pre_sequences.get(i).getComp(),
								pre_sequences.get(i).getFwd(), pre_sequences.get(i).getRev(), fwd_ext, rev_ext,
								pre_sequences.get(i).getTm_fwd(), pre_sequences.get(i).getTm_rev(), tm_fwd_primer,
								tm_rev_primer);
						sequences_ext.add(sequence);

					}

				}
			}
		} else {
			return null;
		}
		return sequences_ext;
	}

	public static List<List<Ligamiento>> results(List<SequenceExt> sequences_ext, String option, double TM, double TMh,
			double TMprimer, double tolerance) {

		List<Ligamiento> homologys = new ArrayList<>();
		List<Ligamiento> primers_fwd = new ArrayList<>();
		List<Ligamiento> primers_rev = new ArrayList<>();
		List<Ligamiento> last_case = new ArrayList<>();
		List<List<Ligamiento>> results = new ArrayList<>();

		for (int i = 0; i < sequences_ext.size(); i++) {
			String fwd_ext = "";
			String rev_ext = "";
			String homology = "";
			String homology_name = "";
			double tm_primer_fwd_1 = 0;
			double tm_primer_rev_1 = 0;
			double tm_primer_fwd_2 = 0;
			double tm_primer_rev_2 = 0;
			double tm_homology = 0;
			if (option.equals("lineal")) {
				// if (i + 1 < sequences_ext.size()) {

				if (i == 0) {
					char[] arr = sequences_ext.get(i).getRev_ext().toCharArray();
					char[] arr2 = sequences_ext.get(i + 1).getFwd_ext().toCharArray();

					ArrayUtils.reverse(arr);
					ArrayUtils.reverse(arr2);

					int length = arr.length;

					if (length > arr2.length) {
						length = arr2.length;
					}

					for (int j = 0; j < length; j++) {

						rev_ext = rev_ext + arr[arr.length - 1 - j];
						fwd_ext = fwd_ext + arr2[j];
						homology = fwd_ext + rev_ext;
						tm_homology = funcionTM(homology);
						tm_primer_fwd_2 = funcionTM(fwd_ext + sequences_ext.get(i + 1).getFwd());
						tm_primer_rev_1 = funcionTM(rev_ext + sequences_ext.get(i).getRev());

						if (tm_homology >= TMh) {

							homology_name = sequences_ext.get(i).getName() + " " + sequences_ext.get(i + 1).getName();

							sequences_ext.get(i).setRev_ext(rev_ext);
							sequences_ext.get(i + 1).setFwd_ext(reverString(fwd_ext));

							Ligamiento hom = new Ligamiento(homology_name, homology, rev_ext, reverString(fwd_ext));
							homologys.add(hom);
							Ligamiento primer_fwd = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), "", sequences_ext.get(i).getFwd());
							primers_fwd.add(primer_fwd);
							Ligamiento primer_rev = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getRev(),
									sequences_ext.get(i).getRev_ext());
							primers_rev.add(primer_rev);
							results.add(homologys);
							results.add(primers_fwd);
							results.add(primers_rev);
							break;
						}
					}

				} else if (i + 1 == sequences_ext.size()) {
					Ligamiento primer_fwd = new Ligamiento(sequences_ext.get(i).getName(),
							sequences_ext.get(i).getSequence(), sequences_ext.get(i).getFwd_ext(),
							sequences_ext.get(i).getFwd());
					primers_fwd.add(primer_fwd);
					Ligamiento primer_rev = new Ligamiento(sequences_ext.get(i).getName(),
							sequences_ext.get(i).getSequence(), sequences_ext.get(i).getRev(), "");
					primers_rev.add(primer_rev);
					results.add(primers_fwd);
					results.add(primers_rev);

				}

				else {
					char[] arr = sequences_ext.get(i).getRev_ext().toCharArray();
					char[] arr2 = sequences_ext.get(i + 1).getFwd_ext().toCharArray();

					ArrayUtils.reverse(arr);
					ArrayUtils.reverse(arr2);

					int length = arr.length;

					if (length > arr2.length) {
						length = arr2.length;
					}

					for (int j = 0; j < length; j++) {

						rev_ext = rev_ext + arr[arr.length - 1 - j];
						fwd_ext = fwd_ext + arr2[j];

						homology = fwd_ext + rev_ext;
						tm_homology = funcionTM(homology);

						tm_primer_rev_1 = funcionTM(rev_ext + sequences_ext.get(i).getRev());
						tm_primer_fwd_1 = funcionTM(sequences_ext.get(i).getFwd() + sequences_ext.get(i).getFwd_ext());

						// if (tm_homology >= TMh &&
						// Math.abs(tm_primer_rev_1 - tm_primer_fwd_1) <=
						// tolerance) {
						if (tm_homology >= TMh) {

							homology_name = sequences_ext.get(i).getName() + sequences_ext.get(i + 1).getName();

							sequences_ext.get(i).setRev_ext(rev_ext);
							sequences_ext.get(i + 1).setFwd_ext(reverString(fwd_ext));

							Ligamiento hom = new Ligamiento(homology_name, homology, rev_ext, reverString(fwd_ext));
							homologys.add(hom);
							Ligamiento primer_fwd = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getFwd_ext(),
									sequences_ext.get(i).getFwd());
							primers_fwd.add(primer_fwd);
							Ligamiento primer_rev = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getRev(),
									sequences_ext.get(i).getRev_ext());
							primers_rev.add(primer_rev);

							results.add(homologys);
							results.add(primers_fwd);
							results.add(primers_rev);
							break;
						}

					}

				}

				// }
			}
			// caso circular

			else {

				if (i == 0) {
					char[] arr = sequences_ext.get(i).getRev_ext().toCharArray();
					char[] arr2 = sequences_ext.get(i + 1).getFwd_ext().toCharArray();
					
					
					//String secuenciaprueba = "";
					ArrayUtils.reverse(arr);
					ArrayUtils.reverse(arr2);
					//for (int j = 0; j < arr2.length; j++) {
					//	secuenciaprueba = secuenciaprueba + arr2[j];

					//}
					//System.out.println("secuencia prueba" + secuenciaprueba);

					int length = arr.length;

					if (length > arr2.length) {
						length = arr2.length;
					}

					for (int j = 0; j < length; j++) {

						rev_ext = rev_ext + arr[arr.length - 1 - j];
						fwd_ext = fwd_ext + arr2[j];
						homology = fwd_ext + rev_ext;
						tm_homology = funcionTM(homology);
						tm_primer_fwd_2 = funcionTM(fwd_ext + sequences_ext.get(i + 1).getFwd());
						tm_primer_rev_1 = funcionTM(rev_ext + sequences_ext.get(i).getRev());

						if (tm_homology >= TMh) {

							homology_name = sequences_ext.get(i).getName() + sequences_ext.get(i + 1).getName();

							sequences_ext.get(i).setRev_ext(rev_ext);
							sequences_ext.get(i + 1).setFwd_ext(reverString(fwd_ext));
							//System.out.println("Secuencia original 2 " + fwd_ext);
							
							;
							//System.out.println("secuencia revertida 2 " + fwd_ext);

							Ligamiento hom = new Ligamiento(homology_name, homology, rev_ext, reverString(fwd_ext));
							homologys.add(hom);
							Ligamiento primer_fwd = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getFwd_ext(),
									sequences_ext.get(i).getFwd());
							primers_fwd.add(primer_fwd);
							Ligamiento primer_rev = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getRev(),
									sequences_ext.get(i).getRev_ext());
							primers_rev.add(primer_rev);
							results.add(homologys);
							results.add(primers_fwd);
							results.add(primers_rev);
							break;
						}
					}

				}

				else if (i + 1 == sequences_ext.size()) {
					// if (trap) {
					char[] arr = sequences_ext.get(i).getRev_ext().toCharArray();
					char[] arr2 = sequences_ext.get(0).getFwd_ext().toCharArray();

					ArrayUtils.reverse(arr);
					ArrayUtils.reverse(arr2);

					int length = arr.length;

					if (length > arr2.length) {
						length = arr2.length;
					}

					for (int j = 0; j < length; j++) {

						rev_ext = rev_ext + arr[arr.length - 1 - j];
						fwd_ext = fwd_ext + arr2[j];
						homology = fwd_ext + rev_ext;
						tm_homology = funcionTM(homology);
						tm_primer_fwd_2 = funcionTM(fwd_ext + sequences_ext.get(0).getFwd());
						tm_primer_rev_1 = funcionTM(rev_ext + sequences_ext.get(i).getRev());

						// if (tm_homology >= TMh && Math.abs(tm_primer_rev_1 -
						// tm_primer_fwd_1) <= tolerance) {
						if (tm_homology >= TMh) {
							homology_name = sequences_ext.get(i).getName() + sequences_ext.get(0).getName();

							sequences_ext.get(i).setRev_ext(rev_ext);
							sequences_ext.get(0).setFwd_ext(reverString(fwd_ext));
							//Ligamiento last_case = new Ligamiento(sequences_ext.get(i).getName(), sequences_ext.get(i).getSequence(),
							//		fwd_ext, "");
							//last_case.add(fwd_ext);

							Ligamiento hom = new Ligamiento(homology_name, homology, rev_ext, reverString(fwd_ext));
							homologys.add(hom);
							Ligamiento primer_fwd = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getFwd_ext(),
									sequences_ext.get(i).getFwd());
							primers_fwd.add(primer_fwd);
							Ligamiento primer_rev = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getRev(),
									sequences_ext.get(i).getRev_ext());
							primers_rev.add(primer_rev);
							results.add(homologys);
							results.add(primers_fwd);
							results.add(primers_rev);

							break;
						}
					}

				}

				// caso de ser el ultimo

				// caso de cualquier otro
				else {
					char[] arr = sequences_ext.get(i).getRev_ext().toCharArray();
					char[] arr2 = sequences_ext.get(i + 1).getFwd_ext().toCharArray();

					ArrayUtils.reverse(arr);
					ArrayUtils.reverse(arr2);
					int length = arr.length;

					if (length > arr2.length) {
						length = arr2.length;
					}

					for (int j = 0; j < length; j++) {

						rev_ext = rev_ext + arr[arr.length - 1 - j];
						fwd_ext = fwd_ext + arr2[j];
						homology = fwd_ext + rev_ext;
						tm_homology = funcionTM(homology);

						tm_primer_rev_1 = funcionTM(rev_ext + sequences_ext.get(i).getRev());
						tm_primer_fwd_1 = funcionTM(sequences_ext.get(i).getFwd() + sequences_ext.get(i).getFwd_ext());

						// if (tm_homology >= TMh && Math.abs(tm_primer_rev_1 -
						// tm_primer_fwd_1) <= tolerance) {
						if (tm_homology >= TMh) {

							homology_name = sequences_ext.get(i).getName() + sequences_ext.get(i + 1).getName();

							sequences_ext.get(i).setRev_ext(rev_ext);
							sequences_ext.get(i + 1).setFwd_ext(reverString(fwd_ext));

							Ligamiento hom = new Ligamiento(homology_name, homology, rev_ext, reverString(fwd_ext));
							homologys.add(hom);
							Ligamiento primer_fwd = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getFwd_ext(),
									sequences_ext.get(i).getFwd());
							primers_fwd.add(primer_fwd);
							Ligamiento primer_rev = new Ligamiento(sequences_ext.get(i).getName(),
									sequences_ext.get(i).getSequence(), sequences_ext.get(i).getRev(),
									sequences_ext.get(i).getRev_ext());
							primers_rev.add(primer_rev);

							results.add(homologys);
							results.add(primers_fwd);
							results.add(primers_rev);
							break;
						}
					}

				}
			}

		}

		return results;
	}

}
