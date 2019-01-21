
public class Main {

	public static void main(String[] args) {
		Grafo g=new Grafo();
	
		for(int i=1; i<80; i++) {
			System.out.println("sto nel giro"+ i);
			g.random(3);
		}
		
	}
		
}
