package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class SintaksniAnalizator {

	public class Tree {

		private Node node;

		Tree(Node value) {
			this.node = value;
		}

		public Node getRoot() {
			return node;
		}

	}

	class Node {
		String value;
		List<Node> djecaNoda;

		Node(String v) {
			this.value = v;
			this.djecaNoda = new ArrayList<Node>();
		}

		public void setValue(String v) {
			this.value = v;
		}

		public void addDjete(Node value) {
			this.djecaNoda.add(value);
		}

	}

	public static int function1(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node novi = anali.new Node(number + 1 + "->" + "<lista_naredbi>");
		node.addDjete(novi);
		stack.push(novi);
		return brojac;

	}

	public static int function2(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {

		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());

		Node jedan = anali.new Node(number + 1 + "->" + "<lista_naredbi>");
		Node dva = anali.new Node(number + 1 + "->" + "<naredba>");
		stack.push(jedan);
		stack.push(dva);
		node.addDjete(dva);
		node.addDjete(jedan);
		return brojac;

	}

	public static int function3(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node jedan = anali.new Node(number + 1 + "->" + "$");
		node.addDjete(jedan);
		stack.pop();
		return brojac;

	}

	public static int function4(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());

		Node jedan = anali.new Node(number + 1 + "->" + "<naredba_pridruzivanja>");
		stack.push(jedan);
		node.addDjete(jedan);
		return brojac;

	}

	public static int function5(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());

		Node jedan = anali.new Node(number + 1 + "->" + "<za_petlja>");
		stack.push(jedan);
		node.addDjete(jedan);
		return brojac;

	}

	public static int function6(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];

		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node token = anali.new Node(number + 1 + "->" + tokn);
		Node jedan = anali.new Node(number + 1 + "->" + "<E>");
		Node dva = anali.new Node(number + 1 + "->" + "OP_PRIDRUZI");
		stack.push(jedan);
		stack.push(dva);
		stack.push(token);
		node.addDjete(token);
		node.addDjete(dva);
		node.addDjete(jedan);
		brojac++;
		return brojac;

	}

	public static int function7(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];

		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node token = anali.new Node(number + 1 + "->" + tokn);
		Node jedan = anali.new Node(number + 1 + "->" + "<lista_naredbi>");
		Node dva = anali.new Node(number + 1 + "->" + "KR_AZ");
		Node tri = anali.new Node(number + 1 + "->" + "<E>");
		Node cetri = anali.new Node(number + 1 + "->" + "KR_DO");
		Node pet = anali.new Node(number + 1 + "->" + "<E>");
		Node sest = anali.new Node(number + 1 + "->" + "KR_OD");
		Node sedam = anali.new Node(number + 1 + "->" + "IDN");
		stack.push(dva);
		stack.push(jedan);
		stack.push(tri);
		stack.push(cetri);
		stack.push(pet);
		stack.push(sest);
		stack.push(sedam);
		stack.push(token);

		node.addDjete(token);
		node.addDjete(sedam);
		node.addDjete(sest);
		node.addDjete(pet);
		node.addDjete(cetri);
		node.addDjete(tri);
		node.addDjete(jedan);
		node.addDjete(dva);
		brojac++;
		return brojac;
	}

	public static int function8(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());

		Node jedan = anali.new Node(number + 1 + "->" + "<E_lista>");
		Node dva = anali.new Node(number + 1 + "->" + "<T>");
		stack.push(jedan);
		stack.push(dva);
		node.addDjete(dva);
		node.addDjete(jedan);
		return brojac;

	}

	public static int function9(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];

		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node token = anali.new Node(number + 1 + "->" + tokn);
		Node jedan = anali.new Node(number + 1 + "->" + "<E>");
		stack.push(jedan);
		stack.push(token);

		node.addDjete(token);
		node.addDjete(jedan);

		brojac++;
		return brojac;

	}

	public static int function10(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());

		Node jedan = anali.new Node(number + 1 + "->" + "<T_lista>");
		Node dva = anali.new Node(number + 1 + "->" + "<P>");
		stack.push(jedan);
		stack.push(dva);
		node.addDjete(dva);
		node.addDjete(jedan);
		return brojac;

	}

	public static int function11(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];

		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node token = anali.new Node(number + 1 + "->" + tokn);
		Node jedan = anali.new Node(number + 1 + "->" + "<T>");
		stack.push(jedan);
		stack.push(token);

		node.addDjete(token);
		node.addDjete(jedan);
		brojac++;
		return brojac;

	}

	public static int function12(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];

		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node token = anali.new Node(number + 1 + "->" + tokn);
		Node jedan = anali.new Node(number + 1 + "->" + "<P>");
		stack.push(jedan);
		stack.push(token);

		node.addDjete(token);
		node.addDjete(jedan);
		brojac++;
		return brojac;

	}

	public static int function13(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];

		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		stack.push(pop);
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node token = anali.new Node(number + 1 + "->" + tokn);
		Node jedan = anali.new Node(number + 1 + "->" + "D_ZAGRADA");
		Node dva = anali.new Node(number + 1 + "->" + "<E>");
		stack.push(jedan);
		stack.push(dva);
		stack.push(token);

		node.addDjete(token);
		node.addDjete(dva);
		node.addDjete(jedan);
		brojac++;
		return brojac;

	}

	public static int function14(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		Node pop = stack.pop();
		pop.setValue(pop.value + "&");
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];
		stack.push(pop);
		stack.pop();
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		Node token = anali.new Node(number + 1 + "->" + tokn);
		node.addDjete(token);

		brojac++;
		return brojac;

	}

	public static int function17(SintaksniAnalizator anali, List<String> text2, Integer brojac, Stack<Node> stack,
			Tree stablo) {
		Node node = stack.peek();
		stack.pop();
		String tokn = text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
				+ text2.get(brojac).split(" ")[2];
		int number = Integer.parseInt(node.value.split("->")[0].strip());
		node.setValue(number + "->" + tokn);

		brojac++;
		return brojac;

	}

	public static void main(String[] args) throws FileNotFoundException {

		File f1 = new File("gramatika.txt");

		File f3 = new File("C:\\Users\\Tomislav Lovrencic\\Desktop\\ulazDrugiLabos.txt");
		@SuppressWarnings("resource")
		Scanner s3 = new Scanner(System.in);
		@SuppressWarnings("resource")
		Scanner s1 = new Scanner(f1);
		List<String> text2 = new ArrayList<String>();

		while (s3.hasNextLine()) {
			String b = s3.nextLine();
			text2.add(b);
		}

		String[] lexerZnakovi = { "IDN", "BROJ", "OP_PRIDRUZI", "OP_PLUS", "OP_MINUS", "OP_PUTA", "OP_DIJELI",
				"L_ZAGRADA", "D_ZAGRADA", "KR_ZA", "KR_OD", "KR_DO", "KR_AZ" };

		Map<String, List<String>> produkcije = new HashMap<>();

		while (s1.hasNextLine()) {
			String[] poljeStringova = s1.nextLine().split("::=");
			String lijevo = poljeStringova[0].strip();
			String[] desno = poljeStringova[1].split("=");
			String primjeniElementi = desno[1].substring(2, desno[1].length() - 1);

			List<String> pomoc = new ArrayList<>();
			String desnaStranaProdukcije = desno[0] + "->" + primjeniElementi;

			pomoc.add(desnaStranaProdukcije);
			if (produkcije.containsKey(lijevo)) {
				pomoc = produkcije.get(lijevo);
				pomoc.add(desnaStranaProdukcije);
			}
			produkcije.put(lijevo, pomoc);

		}

		String[][] tablica = new String[14 + 1][14 + 1];

		tablica[0][1] = "IDN";
		tablica[0][2] = "BROJ";
		tablica[0][3] = "OP_PRIDRUZI";
		tablica[0][4] = "OP_PLUS";
		tablica[0][5] = "OP_MINUS";
		tablica[0][6] = "OP_PUTA";
		tablica[0][7] = "OP_DIJELI";
		tablica[0][8] = "L_ZAGRADA";
		tablica[0][9] = "D_ZAGRADA";
		tablica[0][10] = "KR_ZA";
		tablica[0][11] = "KR_OD";
		tablica[0][12] = "KR_DO";
		tablica[0][13] = "KR_AZ";
		tablica[0][14] = "#";
		tablica[1][0] = "<program>";
		tablica[2][0] = "<lista_naredbi>";
		tablica[3][0] = "<naredba>";
		tablica[4][0] = "<naredba_pridruzivanja>";
		tablica[5][0] = "<za_petlja>";
		tablica[6][0] = "<E>";
		tablica[7][0] = "<T>";
		tablica[8][0] = "<E_lista>";
		tablica[9][0] = "<P>";
		tablica[10][0] = "<T_lista>";
		tablica[11][0] = "KR_AZ";
		tablica[12][0] = "D_ZAGRADA";
		tablica[13][0] = "OP_PRIDRUZI";
		tablica[14][0] = "pocetakStoga";
		tablica[1][1] = "1";
		tablica[1][10] = "1";
		tablica[1][14] = "1";
		tablica[2][1] = "2"; // KR_ZA
		tablica[2][10] = "2"; // OP_PLUS
		tablica[2][13] = "3"; // IDN
		tablica[2][14] = "3"; // broj
		tablica[3][1] = "4"; // OP_MINUS
		tablica[3][10] = "5"; // L_ZAGRADA
		tablica[4][1] = "6";
		tablica[5][10] = "7";
		tablica[6][1] = "8";
		tablica[6][2] = "8";
		tablica[6][4] = "8";
		tablica[6][5] = "8";
		tablica[6][8] = "8";
		tablica[7][1] = "10"; // KR_AZ
		tablica[7][2] = "10";
		tablica[7][4] = "10";
		tablica[7][5] = "10";
		tablica[7][8] = "10";
		tablica[8][1] = "3";
		tablica[8][4] = "9";
		tablica[8][5] = "9";
		tablica[8][9] = "3";
		tablica[8][10] = "3";
		tablica[8][12] = "3";
		tablica[8][13] = "3";
		tablica[8][14] = "3";
		tablica[9][1] = "14";
		tablica[9][2] = "14";
		tablica[9][4] = "12";
		tablica[9][5] = "12";
		tablica[9][8] = "13";
		tablica[10][1] = "3";
		tablica[10][4] = "3";
		tablica[10][5] = "3";
		tablica[10][6] = "11";
		tablica[10][7] = "11";
		tablica[10][9] = "3";
		tablica[10][10] = "3";
		tablica[10][12] = "3";
		tablica[10][13] = "3";
		tablica[10][14] = "3";
		tablica[11][13] = "14";
		tablica[12][9] = "14";
		tablica[13][3] = "14";
		tablica[14][14] = "prihvati"; // #

		SintaksniAnalizator anali = new SintaksniAnalizator();

		Stack<Node> stack = new Stack<Node>();
		Node node = anali.new Node("1 -> <program>");
		stack.push(node);

		Tree stablo = anali.new Tree(node);
		String error = "";
		boolean dosoJeError = false;

		Integer brojac = 0;
		boolean endShi = false;

		while (true) {

			if (stack.isEmpty()) {
				if (brojac < text2.size() - 1) {
					dosoJeError = true;
					error = "err " + text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
							+ text2.get(brojac).split(" ")[2];
					break;
				}
				break;
			}

			if (brojac == text2.size()) {
				endShi = true;
				brojac -= 1;
			}

			String currentNezavrsniZnak = stack.peek().value.split("->")[1].strip();

			List<String> values = produkcije.get(currentNezavrsniZnak);

			int row = 1;
			int column = 1;
			boolean imaPrijelaz = false;
			int found = 0;

			if (values == null) {
				if (Arrays.asList(lexerZnakovi).contains(currentNezavrsniZnak)) {
					if (text2.get(brojac).split(" ")[0].equals(currentNezavrsniZnak)) {
						brojac = function17(anali, text2, brojac, stack, stablo);
						continue;
					} else {
						dosoJeError = true;
						if (endShi) {
							error = "err kraj";
							break;
						}
						error = "err " + text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
								+ text2.get(brojac).split(" ")[2];
						break;
					}

				}
				stack.pop();
				continue;
			} else {
				for (int i = 0; i < values.size(); i++) {
					String[] pom = values.get(i).split("->");
					String[] keysPrimjene = pom[1].split(" ");

					for (String elem : keysPrimjene) {
						if (elem.equals("#")) {
							imaPrijelaz = true;
						}
						if (elem.equals(text2.get(brojac).split(" ")[0])) {
							for (int k = 1; k < 15; k++) {
								if (tablica[0][k].equals(elem)) {
									column = k;
									found += 1;
									break;
								}
							}
							for (int k = 1; k < 15; k++) {
								if (tablica[k][0].equals(currentNezavrsniZnak)) {
									row = k;
									found += 1;
									break;
								}
							}
						}

					}
				}

			}

			if (found != 2) {
				if (imaPrijelaz) {
					column = 14;
					for (int k = 1; k < 15; k++) {
						if (tablica[k][0].equals(currentNezavrsniZnak)) {
							row = k;
							found += 1;
							break;
						}
					}
				} else {
					dosoJeError = true;
					if (endShi) {
						error = "err kraj";
						break;
					}
					error = "err " + text2.get(brojac).split(" ")[0] + " " + text2.get(brojac).split(" ")[1] + " "
							+ text2.get(brojac).split(" ")[2];
					break;
				}
			}

			String num = "";
			if (tablica[row][column] != null) {
				num = tablica[row][column];
			}

			brojac = pronadiFunkciju(Integer.valueOf(num), anali, text2, brojac, stack, stablo);

			// System.out.println(currentNezavrsniZnak);

		}

		if (!dosoJeError) {

			ispisiStablo(stablo.getRoot(), true);
		} else {
			System.out.println(error);
		}

	}

	public static void ispisiStablo(Node korijen, boolean da) {
		if (da) {
			System.out.println("<program>");
		}
		for (Node elem : korijen.djecaNoda) {
			String dobarDio = elem.value.split("->")[1];
			if (dobarDio.endsWith("&")) {
				dobarDio = dobarDio.substring(0, dobarDio.length() - 1);
			}
			int razmak = Integer.parseInt(elem.value.split("->")[0]);
			System.out.println(" ".repeat(razmak - 1) + dobarDio);
			ispisiStablo(elem, false);
		}

	}

	public static Integer pronadiFunkciju(int broj, SintaksniAnalizator anali, List<String> text2, Integer brojac,
			Stack<Node> stack, Tree stablo) {
		switch (broj) {
		case 1:
			return function1(anali, text2, brojac, stack, stablo);

		case 2:
			return function2(anali, text2, brojac, stack, stablo);

		case 3:
			return function3(anali, text2, brojac, stack, stablo);

		case 4:
			return function4(anali, text2, brojac, stack, stablo);

		case 5:
			return function5(anali, text2, brojac, stack, stablo);

		case 6:
			return function6(anali, text2, brojac, stack, stablo);

		case 7:
			return function7(anali, text2, brojac, stack, stablo);

		case 8:
			return function8(anali, text2, brojac, stack, stablo);

		case 9:
			return function9(anali, text2, brojac, stack, stablo);

		case 10:
			return function10(anali, text2, brojac, stack, stablo);

		case 11:
			return function11(anali, text2, brojac, stack, stablo);

		case 12:
			return function12(anali, text2, brojac, stack, stablo);

		case 13:
			return function13(anali, text2, brojac, stack, stablo);

		case 14:
			return function14(anali, text2, brojac, stack, stablo);

		}
		return brojac;

	}

}