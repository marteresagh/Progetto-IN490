import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Grafo g=new Grafo();
		
		/*
		for(int i=1; i<80; i++) {
			System.out.println("sto nel giro"+ i);
			g.random(20);
		}
		*/
		
		/*
		g.random1(3,1);
		g.random1(7,6);
		g.random1(6,3);
		g.random1(4,5);
		g.random1(2,3);
		g.random1(5,6);
		g.random1(4,2);
		g.random1(8,5);
		g.random1(5,4);
		g.random1(4,3);
		g.random1(1,2);
		g.random1(8,8);
		g.random1(8,7);
		g.random1(6,7);
		
		
		
		g.random1(15,9);
		g.random1(12,3);
		g.random1(4,2);
		g.random1(4,1);
		g.random1(4,3);
		g.random1(3,5);
		
		g.random1(9,3);
		g.random1(5,9);
		g.random1(2,3);
		g.random1(6,1);
		g.random1(6,7);
		g.random1(7,5);
		g.random1(5,2);
		g.random1(8,7);
		g.random1(8,6);
		g.random1(2,6);
		g.random1(3,1);
		g.random1(1,2);
		g.random1(12,9);
		g.random1(10,9);
		g.random1(12,4);
		g.random1(5,7);
		g.random1(8,11);
		g.random1(7,10);
		g.random1(7,9);
		g.random1(10,9);
		g.random1(10,11);
		g.random1(7,11);
		
		
		
		
		g.random1(1,2);
		g.random1(1,3);
		g.random1(4,5);
		g.random1(4,6);
		g.random1(7,9);
		g.random1(9,5);
		g.random1(9,6);		
		
		g.random1(6,3);
		
		g.random1(6,2);		
		g.random1(3,7);
		
		
		g.random1(1,2);
		g.random1(1,3);
		g.random1(3,4);
		
		g.random1(4,2);
		g.random1(2,1);
		
		
		g.random1(4,1);
		g.random1(1,2);
		g.random1(5,2);
		g.random1(4,5);
		g.random1(4,2);
		g.random1(3,3);
		g.random1(1,3);
		g.random1(3,4);
		g.random1(5,3);
	
		
		g.random1(1,2);
		g.random1(2,3);
		g.random1(3,4);
		g.random1(1,5);
		g.random1(1,3);
		Nodo n1=g.trovaV(1);
		System.out.println(n1.getOutLink());
		//non crea loop ne pointers 
		 
		
		
		g.random1(1,2);
		g.random1(2,3);
		g.random1(3,4);
		g.random1(5,6);
		g.random1(1,5);
		g.random1(3,5);
		Nodo n1=g.trovaV(1);
		System.out.println(n1.getOutPointers());
		
		
	 */
		
		g.random1(9,3);
		g.random1(5,9);
		g.random1(2,3);
		g.random1(6,1);
		g.random1(6,7);
		g.random1(7,5);
		g.random1(5,2);
		g.random1(8,7);
		g.random1(8,6);
		g.random1(2,6);
		g.random1(3,1);
		g.random1(1,2);
		g.random1(12,9);
		g.random1(10,9);
		g.random1(12,4);
		g.random1(5,7);
		g.random1(8,11);
		g.random1(7,10);
		g.random1(7,9);
		g.random1(10,9);
		g.random1(10,11);
		g.random1(7,11);
	
		Collection<Nodo> l=g.getElenco().values();
		System.out.println(l);
		Set<Integer> k=g.getElenco().keySet();
		System.out.println(k);
	
		
	}
		
}
