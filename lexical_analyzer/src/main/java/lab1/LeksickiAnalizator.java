package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

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
		if (currentIndex < data.length) {
			while (data[currentIndex] == ' ' || data[currentIndex] == '\t') {
				currentIndex++;
				if (currentIndex == data.length)
					break;

			}
		}

	}

	public boolean charactherIsDigit() {
		String tokenName = "";

		while (Character.isDigit(data[currentIndex])) {
			tokenName += data[currentIndex];
			currentIndex++;
			if (currentIndex == data.length)
				break;
			token = new Token("BROJ", tokenName, numberOfRow);
		}
		eliminateSpaces();
		return true;
	}

	public boolean charactherIsVariable() {
		String tokenName = "";

		while (Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex])
				|| data[currentIndex] == '_') {
			tokenName += data[currentIndex];
			currentIndex++;
			if (currentIndex >= data.length)
				break;
		}

		switch (tokenName) {
		case "za":
			token = new Token("KR_ZA", tokenName, numberOfRow);
			break;
		case "od":
			token = new Token("KR_OD", tokenName, numberOfRow);
			break;
		case "do":
			token = new Token("KR_DO", tokenName, numberOfRow);
			break;
		case "az":
			token = new Token("KR_AZ", tokenName, numberOfRow);
			break;
		default:

			token = new Token("IDN", tokenName, numberOfRow);
			break;
		}

		eliminateSpaces();

		return tokenName.length() != 0;
	}

	public boolean charachterIsOperator() {
		char charElem = data[currentIndex];
		currentIndex++;
		switch (charElem) {
		case '+':
			token = new Token("OP_PLUS", charElem, numberOfRow);
			break;
		case '-':
			token = new Token("OP_MINUS", charElem, numberOfRow);
			break;
		case '*':
			token = new Token("OP_PUTA", charElem, numberOfRow);
			break;
		case '/':
			token = new Token("OP_DIJELI", charElem, numberOfRow);
			break;
		case '(':
			token = new Token("L_ZAGRADA", charElem, numberOfRow);
			break;
		case ')':
			token = new Token("D_ZAGRADA", charElem, numberOfRow);
			break;
		default:
			token = new Token("OPERATOR", charElem, numberOfRow);
			break;

		}
		eliminateSpaces();
		return charElem != ' ';
	}

	public void checkForEnd() {
		if (currentIndex < data.length) {
			while (data[currentIndex] == ' ' || data[currentIndex] == '\n') {
				if (data[currentIndex] == '\n') {
					numberOfRow++;
				}
				if (currentIndex == data.length - 1) {
					break;
				}
				currentIndex++;
			}
		}

	}

	public void forMode() {
		if (Character.isLetter(data[currentIndex])) {
			charactherIsVariable();
			checkForEnd();
		} else if (Character.isDigit(data[currentIndex])) {
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
		if (currentIndex == data.length) {
			token = new Token("EOF", null, numberOfRow);
		}

		else {
			if (data[currentIndex] == '/' && currentIndex < data.length - 1 && data[currentIndex + 1] == '/') {
				eliminateSpaces();
				String tokenName = "";
				while (data[currentIndex] != '\n') {
					currentIndex++;
					tokenName += data[currentIndex];
				}
				token = new Token("KOMENTAR", tokenName, numberOfRow);
				checkForEnd();
			} else {
				if (data[currentIndex] == '=') {
					char charElem1 = data[currentIndex];
					token = new Token("OP_PRIDRUZI", charElem1, numberOfRow);
					currentIndex++;
					eliminateSpaces();
					checkForEnd();
				} else {
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

		public Token(String type, Object value, int row) {
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

	public static void main(String[] args) throws FileNotFoundException {
		String a = "C:/Users/Tomislav Lovrencic/Desktop/prim.txt";

		File f = new File(a);
		@SuppressWarnings("resource")
		Scanner s = new Scanner(f);
		String text = "";
		while (s.hasNextLine()) {
			text += s.nextLine() + "\n";
		}

		LeksickiAnalizator lexer = new LeksickiAnalizator(text);

		while (true) {

			Token actual = lexer.nextToken();
			if (actual.getType() == "EOF" || actual.getType() == "OPERATOR") {
				break;
			}
			if (actual.getType() != "KOMENTAR") {
				System.out.println(actual.getType() + " " + actual.row + " " + actual.getValue());
			}

		}
	}

}