package lab1;




import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;








public class LeksickiAnalizator {
	
	
	private int numberOfRow;
	
	private char[] data;     

	private Token token;   
	
	private int currentIndex; 

	private String state;
	
	

	public LeksickiAnalizator(String text) {
		numberOfRow = 1;
		data = text.toCharArray();
		state = "TEXT";
		currentIndex = 0;
	}
	
	
	public void eliminateSpaces() {
		if(currentIndex < data.length) {
			while(data[currentIndex] == ' ' || data[currentIndex] == '\t') {
				currentIndex++;
				if(currentIndex == data.length) break;

			}
		}
		
	}

	public boolean charactherIsDigit() {
		String tokenName = "";
		
		while(Character.isDigit(data[currentIndex])) {
			tokenName+= data[currentIndex];
			currentIndex++;
			if(currentIndex == data.length) break;
			token = new Token("BROJ",tokenName,numberOfRow);
		}
		eliminateSpaces();
		return true;
	}
	
	
	public boolean charactherIsVariable() {
		String tokenName = "";
		
		while(Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex]) || data[currentIndex] == '_') {
				tokenName+= data[currentIndex];
				currentIndex++;	
			if(currentIndex >= data.length) break;
		}
		
		switch(tokenName) {
			case "za":
				token = new Token("KR_ZA",tokenName,numberOfRow);
				break;
			case "od":
				token = new Token("KR_OD",tokenName,numberOfRow);
				break;
			case "do":
				token = new Token("KR_DO",tokenName,numberOfRow);
				break;
			case "az":
				token = new Token("KR_AZ",tokenName,numberOfRow);
				break;
			default:
				
				token = new Token("IDN",tokenName,numberOfRow);
				break;
		}
		
		eliminateSpaces();
	
		return tokenName.length() != 0;
	}
	
	


	public boolean charachterIsOperator() {
		char charElem = data[currentIndex];
		currentIndex++;
		switch(charElem) {
			case '+':
				token = new Token("OP_PLUS",charElem,numberOfRow);
				break;
			case '-':
				token = new Token("OP_MINUS",charElem,numberOfRow);
				break;
			case '*':
				token = new Token("OP_PUTA",charElem,numberOfRow);
				break;
			case '/':
				token = new Token("OP_DIJELI",charElem,numberOfRow);
				break;
			case '(':
				token = new Token("L_ZAGRADA",charElem,numberOfRow);
				break;
			case ')':
				token = new Token("D_ZAGRADA",charElem,numberOfRow);
				break;
			default:
				token = new Token("OPERATOR",charElem,numberOfRow);
				break;
				
		}
		eliminateSpaces();
		return charElem != ' ';	
		}
	
	
	public void checkForEnd() {
		if(currentIndex < data.length) {
			while(data[currentIndex] == ' ' || data[currentIndex] == '\n') {
				if(data[currentIndex] == '\n') {
					numberOfRow++;
				}
				if(currentIndex == data.length-1) {
					break;
				}
				currentIndex++;
			}
		}
		
	}
	
	

	
	public void forMode() {
		 if(Character.isLetter(data[currentIndex])){
			charactherIsVariable();
			checkForEnd();
		}
		else if(Character.isDigit(data[currentIndex])) {
			charactherIsDigit();
			checkForEnd();

		}
		
		else {
			charachterIsOperator();
			checkForEnd();
		}
		
	}
	
	public void workAsINSIDETAGS() {
	
		
		eliminateSpaces();
		checkForEnd();
		if(currentIndex == data.length) {
			token = new Token("EOF",null,numberOfRow);
		}
		
		else {
			if(data[currentIndex] == '/' && currentIndex < data.length - 1 && data[currentIndex+1] == '/') {
				eliminateSpaces();
				String tokenName ="";
				while(data[currentIndex] != '\n') {
					currentIndex++;
					tokenName+=data[currentIndex];
				}
				token = new Token("KOMENTAR",tokenName,numberOfRow);
				checkForEnd();
			}
			else {
				if(data[currentIndex] == '=') {
					char charElem1 = data[currentIndex];
					token = new Token("OP_PRIDRUZI",charElem1,numberOfRow);
					currentIndex++;
					eliminateSpaces();
					checkForEnd();
				}
				else {
					forMode();

				}
			}
		}
	}
	

	

	public Token nextToken() {
		workAsINSIDETAGS();
		return token;
		
	}
		

	public Token getToken() {
		return token;
	}
	

	public void setState(String state) {
		Objects.requireNonNull(state);
		this.state = state;
	}
	
	
	public String getState() {
		return this.state;
	}
	
	
	public class Token {
		
		
		private int row;
	
		private String type;
		
		
		private Object obj;

		
		public Token(String type, Object value,int row) {
			this.row = row;
			this.type = type;
			this.obj = value;
		}
		
		public Object getValue() {
			return obj;
		}
		
		
	
		public String getType() {
			return type;
		}
		
		
	}
	
	public static String function1(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<lista_naredbi>");
		return "";
	}
	public static String function2(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<lista_naredbi>");
		stack.push(number+1 + "->" + "<naredba>");
		return "";
			
	}
	public static String function3(LeksickiAnalizator lexer,Stack<String> stack) {
		stack.pop();
		return "$";
		
	}
	public static String function4(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<naredba_pridruzivanja>");
		return "";
		
	}
	public static String function5(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<za_petlja>");
		return "";
		
	}
	public static String function6(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<E>");
		stack.push(number+1 +  "->" + "OP_PRIDRUZI");
		String ret = lexer.getToken().getType() +" "+ lexer.getToken().row +" "+ lexer.getToken().getValue().toString();
		lexer.nextToken();
		return ret;
		
	}
	public static String function7(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "KR_AZ");
		stack.push(number+1 + "->" + "<lista_naredbi>");
		stack.push(number+1 + "->" + "<E>");
		stack.push(number+1 + "->" + "KR_DO");
		stack.push(number+1 + "->" + "<E>");
		stack.push(number+1 + "->" + "KR_OD");
		stack.push(number+1 + "->" + "IDN");
		String ret = lexer.getToken().getType() +" "+ lexer.getToken().row +" "+ lexer.getToken().getValue().toString();

		lexer.nextToken();
		return ret;
	}
	public static String function8(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<E_lista>");
		stack.push(number+1 + "->" + "<T>");
		return "";
		
	}
	public static String function9(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<E>");
		String ret = lexer.getToken().getType() +" "+ lexer.getToken().row +" "+ lexer.getToken().getValue().toString();

		lexer.nextToken();
		return ret;
		
	}
	public static String function10(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<T_lista>");
		stack.push(number+1 + "->" + "<P>");
		return "";
		
	}
	public static String function11(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 +"->" + "<T>");
		String ret = lexer.getToken().getType() +" "+ lexer.getToken().row +" "+ lexer.getToken().getValue().toString();

		lexer.nextToken();
		return ret;
		
	}
	public static String function12(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "<P>");
		String ret = lexer.getToken().getType() +" "+ lexer.getToken().row +" "+ lexer.getToken().getValue().toString();

		lexer.nextToken();
		return "";
		
	}
	public static String function13(LeksickiAnalizator lexer,Stack<String> stack) {
		String s = stack.pop();
		s+="DA";
		stack.push(s);
		int number = Integer.parseInt(stack.peek().split("->")[0].strip());
		stack.push(number+1 + "->" + "D_ZAGRADA");
		stack.push(number+1 + "->" + "<E>");
		String ret = lexer.getToken().getType() +" "+ lexer.getToken().row +" "+ lexer.getToken().getValue().toString();

		lexer.nextToken();
		return ret;
		
	}
	public static String function14(LeksickiAnalizator lexer,Stack<String> stack) {
		stack.pop();
		String ret = lexer.getToken().getType() +" "+ lexer.getToken().row +" "+ lexer.getToken().getValue().toString();
		lexer.nextToken();
		return ret;
		
	}

	

	

	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File f = new File("C:\\Users\\Tomislav Lovrencic\\Desktop\\database.txt");
		File f1 = new File("C:\\Users\\Tomislav Lovrencic\\Desktop\\gramatika.txt");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(f);
		@SuppressWarnings("resource")
		Scanner s1 = new Scanner(f1);
			
		String text = "";
		while (s.hasNextLine()) {
			text+=s.nextLine()+"\n";
		}
		
		String[] lexerZnakovi = {"IDN","BROJ", "OP_PRIDRUZI", "OP_PLUS" , "OP_MINUS" ,"OP_PUTA",
				"OP_DIJELI", "L_ZAGRADA", "D_ZAGRADA","KR_ZA","KR_OD", "KR_DO", "KR_AZ" };
		
		Set<String> nezavrsniZnakovi = new HashSet<>();
		
		Set<String> zavrsniZnakovi =  new HashSet<>();
		
		Map<String,List<String>> produkcije = new HashMap<>();
		
		while (s1.hasNextLine()) {
			String[] poljeStringova = s1.nextLine().split("::=");
			String lijevo = poljeStringova[0].strip();
			String[] desno = poljeStringova[1].split("=");
			String primjeniElementi = desno[1].substring(2, desno[1].length()-1);
			
			String[] skupZavrsnihINezavrsnih = desno[0].split(" ");
			
			List<String> pomoc = new ArrayList<>();
			String desnaStranaProdukcije = desno[0] + "->" +primjeniElementi;
			
			pomoc.add(desnaStranaProdukcije);
			if(produkcije.containsKey(lijevo)) {
				pomoc = produkcije.get(lijevo);
				pomoc.add(desnaStranaProdukcije);
			}
			produkcije.put(lijevo, pomoc);
					
			nezavrsniZnakovi.add(lijevo);
			
			
			for(String elem : skupZavrsnihINezavrsnih) {
				if(Arrays.asList(lexerZnakovi).contains(elem)) {
					zavrsniZnakovi.add(elem);
				}
			}
	
		}
		
		nezavrsniZnakovi.add("KR_AZ");
		nezavrsniZnakovi.add("D_ZAGRADA");
		nezavrsniZnakovi.add("pocetakStoga");
		zavrsniZnakovi.add("⏊");
		
	//System.out.println(produkcije.toString());
//		
//		
//		
//		System.out.println(nezavrsniZnakovi);
//		System.out.println(zavrsniZnakovi);
		
		
		
		String[][] tablica = new String[14+1][14+1];
		

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
		tablica[0][14] = "⏊";
		
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
		tablica[2][1] = "2"; //KR_ZA
		tablica[2][10] = "2"; //OP_PLUS
		tablica[2][13] = "3"; // IDN
		tablica[2][14] = "3"; //broj
		tablica[3][1] = "4"; // OP_MINUS
		tablica[3][10] = "5"; // L_ZAGRADA
		tablica[4][1] = "6";
		tablica[5][10] = "7";
		tablica[6][1] = "8";
		tablica[6][2] = "8";
		tablica[6][4] = "8";
		tablica[6][5] = "8";
		tablica[6][8] = "8";
		tablica[7][1] = "10"; //KR_AZ
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
		tablica[14][14] = "prihvati"; //⏊
	
		
		String tex = "";
		for(int i=0;i<nezavrsniZnakovi.size()+1;i++) {
			for(int j=0;j<zavrsniZnakovi.size()+1;j++) {
				tex+=tablica[i][j]+" ";
			}
			tex+="\n";
		}
		
		//System.out.println(tex);
		
		
		
		
		
		
		LeksickiAnalizator lexer = new LeksickiAnalizator(text);
		lexer.nextToken();
		Stack<String> stack = new Stack<String>();
		stack.push("1 -> <program>");
				
		while(true) {
			
			
			
			Token actual = lexer.getToken();
		
			if(stack.isEmpty()) break;
			
			int razmak = Integer.parseInt(stack.peek().split("->")[0].strip());
			String currentNezavrsniZnak = stack.peek().split("->")[1].strip();
			
			List<String> values = produkcije.get(currentNezavrsniZnak);
			
			
			int row = 1;
			int column = 1;
			boolean imaPrijelaz = false;
			int found = 0;

			if(values == null) {
				
				if(Arrays.asList(lexerZnakovi).contains(currentNezavrsniZnak)) {
					String bla = function14(lexer,stack);
					
					if(!bla.equals("")) {
						System.out.println(" ".repeat(razmak) + bla);
					}
					continue;
				}
				stack.pop();
				continue;
			
			}
			else {
				for(int i=0;i<values.size();i++) {
					String[] pom = values.get(i).split("->");
					String[] keysPrimjene = pom[1].split(" ");
					
					for(String elem : keysPrimjene) {
						if(elem.equals("⏊")) {
							imaPrijelaz = true;
						}
						if(elem.equals(actual.getType())) {
							for(int k=1;k<15;k++) {
								if(tablica[0][k].equals(elem)) {
									column = k;
									found +=1;
									break;
								}
							}
							for(int k=1;k<15;k++) {
								if(tablica[k][0].equals(currentNezavrsniZnak)) {
									row = k;
									found += 1;
									break;
								}
							}
						}
						
					}
				}
				
			}
			
			if(found != 2) {
				if(imaPrijelaz) {
					column = 14;
					for(int k=1;k<15;k++) {
						if(tablica[k][0].equals(currentNezavrsniZnak)) {
							row = k;
							found += 1;
							break;
						}
					}
				}
			}
			
			
			String num = "";
			if(tablica[row][column] != null) {
				 num = tablica[row][column];
			}
			
			String bla = pronadiFunkciju(Integer.valueOf(num),lexer,stack);
			
			
			if(stack.size() == 0) {
				break;
			}
			if(!Arrays.asList(lexerZnakovi).contains(currentNezavrsniZnak)) {
				System.out.println(" ".repeat(razmak) + currentNezavrsniZnak);

			}
			if(!bla.equals("")) {
				System.out.println(" ".repeat(razmak+1) + bla);
			}
			

		}
	}
	public static String  pronadiFunkciju(int broj,LeksickiAnalizator lexer,Stack<String> stack) {
		switch(broj) {
			case 1:
				 return function1(lexer,stack);
			case 2:
				return function2(lexer,stack);
				
			case 3:
				return function3(lexer,stack);
				
			case 4:
				return function4(lexer,stack);
				
			case 5:
				return function5(lexer,stack);
				
			case 6:
				return function6(lexer,stack);
				
			case 7:
				return function7(lexer,stack);
				
			case 8:
				return function8(lexer,stack);
				
			case 9:
				return function9(lexer,stack);
				
			case 10:
				return function10(lexer,stack);
				
			case 11:
				return function11(lexer,stack);
				
			case 12:
				return function12(lexer,stack);
				
			case 13:
				return function13(lexer,stack);
				
			case 14:
				return function14(lexer,stack);
				
		}
		return "";
	}
	

}