package lab1;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class SemantickiAnalizator {
	
	
	public static class Pair {
		private String ime;
		private List<Integer> lista;
		public Pair(String ime ,List<Integer> lista) {
			this.ime = ime;
			this.lista = lista;
		}
		public String getIme() {
			return ime;
		}
		public void setIme(String ime) {
			this.ime = ime;
		}
		public List<Integer> getLista() {
			return lista;
		}
		public void setLista(List<Integer> lista) {
			this.lista = lista;
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
	
		File f3 = new File("ulaz.txt");
		@SuppressWarnings("resource")
		Scanner s3 = new Scanner(System.in);
		@SuppressWarnings("resource")
		List<String> text2 = new ArrayList<String>();
		
		List<Pair> mapa1 = new ArrayList<>();
		List<Pair> mapaPetlja1 = new ArrayList<>();
		

		while (s3.hasNextLine()) {
			String b = s3.nextLine().trim();
			if(b.contains("<za_petlja>") || b.contains("KR_ZA") || b.contains("<naredba_pridruzivanja>")
					|| b.contains("IDN") || b.contains("KR_AZ")) {
				text2.add(b);

			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		boolean unutar = false;
		int brojac = 1;
		boolean tr = false;
		
		
		for(int i=0;i<text2.size();i++) {
			String line = text2.get(i).trim();
			
		
			if(line.equals("<za_petlja>")) {
				unutar = true;
			}
			
			if( i> 0 && unutar && text2.get(i-1).trim().split(" ")[0].equals("KR_ZA")) {
				
				List<Integer> li = new ArrayList<>();
				li.add(Integer.parseInt(line.split(" ")[1]));
				
				for(int j=0;j<mapaPetlja1.size();j++) {
					if(mapaPetlja1.get(j).lista.get(1) == brojac) {
						tr = true;
					}
				}
				
				if(tr) {
					brojac += 1;
					li.add(brojac);
					tr = false;
				}
				else {
					li.add(brojac);

				}
				
				mapaPetlja1.add(new Pair(line.split(" ")[2],li));
						
				
			}
			
			else if(i > 0 && text2.get(i-1).trim().equals("<naredba_pridruzivanja>")) {
				boolean d = false;
				String indf = line.split(" ")[2];
				for(int j =0;j<mapa1.size();j++) {
					if(mapa1.get(j).ime.equals(indf)) {
						d = true;
					}
				}
				if(d == false) {
					List<Integer> li = new ArrayList<>();
					if(unutar) {
						boolean k = false;
						for(int j =0;j<mapaPetlja1.size();j++) {
							if(mapaPetlja1.get(j).ime.equals(indf)) {
								k = true;
							}
						}
						if(k == false) {
							li.add(Integer.parseInt(line.split(" ")[1]));
							li.add(brojac);
							mapaPetlja1.add(new Pair(line.split(" ")[2],li));
						}
						
					}
					else{
						li.add(Integer.parseInt(line.split(" ")[1]));
						li.add(0);
						mapa1.add(new Pair(line.split(" ")[2],li));
					}
				}
			}
			else {
				
				
				if(line.split(" ")[0].equals("IDN")) {
					
					boolean d = false;
					for(int j=mapaPetlja1.size()-1;j>=0;j--) {
						if(mapaPetlja1.get(j).ime.equals(line.split(" ")[2])) {
							d = true;
						}
					}
					
					if(d == false) {
						int a = 0;
						for(int j=mapa1.size()-1;j>=0;j--) {
							if(mapa1.get(j).ime.equals(line.split(" ")[2])) {
								a = mapa1.get(j).lista.get(0);
								break;
							}
						}
						if(Integer.parseInt(line.split(" ")[1]) == a) {
							sb.append("err " +line.split(" ")[1]+" "+line.split(" ")[2]);
							break;
						}
						if(a == 0) {
							sb.append("err " +line.split(" ")[1]+" "+line.split(" ")[2]);
							break;
						}
						
						sb.append(line.split(" ")[1] + " "+ a +" "+ line.split(" ")[2]+"\n");
					}
					else {
						int a = 0;
						for(int j=mapaPetlja1.size()-1;j>=0;j--) {
							if(mapaPetlja1.get(j).ime.equals(line.split(" ")[2])) {
								a = mapaPetlja1.get(j).lista.get(0);
								break;
							}
						}
						if(Integer.parseInt(line.split(" ")[1]) == a) {
							sb.append("err " +line.split(" ")[1]+" "+line.split(" ")[2]);
							break;
						}
						if(a ==0) {
							sb.append("err " +line.split(" ")[1]+" "+line.split(" ")[2]);
							break;
						}
						
						sb.append(line.split(" ")[1] + " "+ a +" "+ line.split(" ")[2]+"\n");
				
					}
					
			}
			
			if(line.split(" ")[0].equals("KR_AZ")) {
				
				int b = brojac;
				for(int j =mapaPetlja1.size()-1;j>=0;j--) {
					if(mapaPetlja1.get(j).lista.get(1) == brojac) {
						mapaPetlja1.remove(j);
					}
				}
				brojac--;
				
				if(mapaPetlja1.size() == 0) {
					unutar = false;
				}
			}
			
			
		}
		
		
	}
		System.out.println(sb.toString());
}


}