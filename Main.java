

public class Main {

	public static void main(String[] args) { // bisogna passargli in input il numero massimo di vertici ed il numero di archi
		Grafo g=new Grafo();		 
		int n = Integer.parseInt(args[0]);//numero massimo di vertici	
		int m = Integer.parseInt(args[1]);//numero di archi
		for(int i=0; i<m; i++) {
			System.out.println("sto nel giro "+ i);
			g.random(n);
		}
			
			
	}
		
}
