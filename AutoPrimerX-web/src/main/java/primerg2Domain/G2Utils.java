package primerg2Domain;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("MW:" + molWeight + "g/mol");
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
		System.out.println("El forward es: " + forwd);
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
		System.out.println("El reverso complementario es: " + reversoComplementario);
		return reversoComplementario;
	}

	public static String reverse_EXT(String secuencia, double TM) {
		char[] arr = secuencia.toCharArray();
		char[] arregloRVREXT = new char[arr.length];
		double tmAux = 0;
		String rvrEXT = "";
		for (int i = 0; i < arr.length; i++) {
			if (tmAux < TM) {
				arregloRVREXT[i] = arr[i];
				for (int j = 0; j < arregloRVREXT.length; j++) {
					rvrEXT = rvrEXT + arregloRVREXT[i];
					tmAux = funcionTM(rvrEXT);
					if (tmAux >= TM) {
						break;
					}
					break;
				}

			}
		}
		System.out.println("El reverso de extension es: " + rvrEXT);
		return rvrEXT;
	}

	public static String forward_EXT(String secuencia, double TM) {
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
					tmAux = funcionTM(fwdEXT);
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

	public static List<Ligamiento> forwards_lineal(List<Sequence> sequences) {
		List<Ligamiento> forwards = new ArrayList<>();
		for (int i = 1; i < sequences.size(); i++) {
			Ligamiento primer = new Ligamiento(sequences.get(i).getName(), sequences.get(i).getSequence(),
					sequences.get(i).getFwd_ext(), sequences.get(i).getFwd());
			forwards.add(primer);
		}
		return forwards;
	}

	public static List<Ligamiento> forwards_circular(List<Sequence> sequences) {
		List<Ligamiento> forwards = new ArrayList<>();
		for (int i = 0; i < sequences.size(); i++) {
			Ligamiento primer = new Ligamiento(sequences.get(i).getName(), sequences.get(i).getSequence(),
					sequences.get(i).getFwd_ext(), sequences.get(i).getFwd());
			forwards.add(primer);
		}
		return forwards;
	}

	public static List<Ligamiento> revs_lineal(List<Sequence> sequences) {
		List<Ligamiento> revs = new ArrayList<>();
		for (int i = 0; i < sequences.size(); i++) {
			Ligamiento primer = new Ligamiento(sequences.get(i).getName(), sequences.get(i).getSequence(),
					sequences.get(i).getRev(), sequences.get(i).getRev_ext());
			revs.add(primer);
		}
		return revs;
	}

	public static List<Ligamiento> revs_circular(List<Sequence> sequences) {
		List<Ligamiento> revs = new ArrayList<>();
		for (int i = 0; i < sequences.size(); i++) {
			Ligamiento primer = new Ligamiento(sequences.get(i).getName(), sequences.get(i).getSequence(),
					sequences.get(i).getRev(), sequences.get(i).getRev_ext());
			revs.add(primer);
		}
		return revs;
	}

	public static List<Ligamiento> homologys_lineal(List<Sequence> sequences) {
		List<Ligamiento> homologys = new ArrayList<>();
		for (int i = 0; i < sequences.size(); i++) {
			if ((i + 1) < sequences.size()) {
				Ligamiento primer = new Ligamiento(sequences.get(i).getName(), sequences.get(i).getSequence(),
						sequences.get(i).getRev_ext(), sequences.get(i + 1).getFwd_ext());
				homologys.add(primer);
			}
		}
		return homologys;
	}
	
	public static List<Ligamiento> homologys_circular(List<Sequence> sequences) {
		List<Ligamiento> homologys = new ArrayList<>();
		for (int i = 0; i < sequences.size(); i++) {
			if ((i + 1) < sequences.size()) {
				Ligamiento primer = new Ligamiento(sequences.get(i).getName(), sequences.get(i).getSequence(),
						sequences.get(i).getRev_ext(), sequences.get(i + 1).getFwd_ext());
				homologys.add(primer);
			}
			
			if(i == (sequences.size()-1)){
				Ligamiento primer = new Ligamiento(sequences.get(i).getName(), sequences.get(i).getSequence(),
						sequences.get(i).getRev_ext(), sequences.get(0).getFwd_ext());
				homologys.add(primer);
				
			}
		}
		return homologys;
	}

}
