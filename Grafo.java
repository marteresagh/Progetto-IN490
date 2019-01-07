
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Grafo {
		
	private LinkedList<Nodo> listaNodi; //lista ordinata di tutti i nodi del grafo
    
	//costruttore
	public Grafo() {
		this.setListaNodi(new LinkedList<Nodo>());
	}	
	
	//getter e setters di listaNodi
	public LinkedList<Nodo> getListaNodi() {
		return listaNodi;
	}
	
	public void setListaNodi(LinkedList<Nodo> listaNodi) {
		this.listaNodi = listaNodi;
	}
	
	public void addListaNodi(Nodo Nodo) {
		this.listaNodi.addLast(Nodo);
	}
	
	
	//dati i nodi v e w verifica se v>w (vero se v é più giovane di w (v>=w)) 
	private boolean threadPrecedes(Nodo w,Nodo v) {
		int a=this.getListaNodi().indexOf(v);
		int b=this.getListaNodi().indexOf(w);
		if(a<0 || b<0) return false;
		else return a>=b;
	}
	
		
	//cerca nella ListaNodi se c'è un vertice di nome v
	private Nodo trovaV(int v) {
		Nodo n=null;
		boolean b=false;
		ListIterator<Nodo> i=listaNodi.listIterator();
		while( b==false && i.hasNext()){
			n=i.next();
			b=n.equals(v);
		} 
		if(b==false)
			n=null;
		return n;
	}
	
	// data una lista ordinata di nodi la cerca in ListaNodo e la elimina
	private void eliminaLista(LinkedList<Nodo> lista ) {
		Nodo n=lista.peekFirst();
		int a=this.getListaNodi().indexOf(n);
		n=lista.peekLast();
		int b=this.getListaNodi().indexOf(n);
		this.getListaNodi().subList(a, b+1).clear();		
	}
	
	//dato un nodo inserisce il suo sotto-albero nelle ListaNodo ordinatamente
	private void ordinaLista(Nodo v) {
		Nodo padre = v.getInLink();
		Nodo foglia=padre.ultimaFoglia();
		LinkedList<Nodo> lista= v.listaRootToLeaf();
		if(this.getListaNodi().contains(v))
			this.eliminaLista(lista);
		int indice=this.getListaNodi().indexOf(foglia);
		this.getListaNodi().addAll(indice+1, lista);		
	}
	
	//scorre un albero dalla radice (padre) e ordina in una lista
	public static LinkedList<Nodo> toLeaf(LinkedList<Nodo> lista,Nodo padre){
		lista.add(padre);
		if(padre.getOutLink()==null) {
			return lista;
		}else {
			for(Nodo item : padre.getOutLink() ) {
				Grafo.toLeaf(lista,item);	
			}
			return lista;
		}
	}
	
	//crea l'arco (v,w)
	//e dopo che viene assegnato il nuovo padre e prima che vengano assegnati i nuovi figli richiama ordinalista
	private void insertTree(Nodo v,Nodo w) {
		w.setInLink(v);
		this.ordinaLista(w);
		v.addOutLink(w);
	}
	
	private Nodo rootOf(Nodo base,Nodo nodo) {
		Nodo y=null;
		Nodo n=null;
		Iterator<Nodo> x=base.getOutLink().descendingIterator();
		while(y==null && x.hasNext()) {
			n=x.next();
			if(threadPrecedes(n,nodo))
				y=n;				
		}
		return y;
	}
	
	// Scorre il ramo da base a nodo e trasforma base in una componente connessa
	public void collapsePath(Nodo base,Nodo nodo) {
		Nodo collapse=null;
  		collapse=this.rootOf(base,nodo);
			while (collapse!=null) {
				base.addTocomponent(collapse);
				System.out.println("trovata componente fortemente connessa" +base.getCfc());
				base.removeNodo(collapse,this);
				this.getListaNodi().remove(collapse);
				collapse=this.rootOf(base, nodo);
			}	
		
	}
	
	private void moveSubTree(Nodo base, Nodo nodo) {
		try{
			Nodo padre=nodo.getInLink();
			nodo.removeSubTree();
			if(base.testLoop(padre) && padre!=null)
			padre.removePointers(nodo);
		}catch(java.lang.NullPointerException Exe) {}
		this.insertTree(base,nodo);
		LinkedList<Nodo> A=this.getListaNodi();
		List<Nodo> SubList=null;
		SubList=new LinkedList<Nodo>(A.subList(A.indexOf(nodo),A.indexOf(nodo.ultimaFoglia())+1));
		for(Nodo n: SubList) {
			this.controllaPointers(n);
		}
	}

	// controlla i puntatori uscenti dei nodi dell'albero spostato con moveSubTree
	private void controllaPointers(Nodo n) {	
		ListIterator<Nodo> itr =  n.getOutPointers().listIterator();
		Nodo m=null;
		Boolean a=false;
		while(itr.hasNext() && a==false) {
			m=itr.next();
			if (this.threadPrecedes(n,m) || (n.testLoop(m)) ) {
				itr.remove();
				m.getInPointers().remove(n);
				if(!(n.testLoop(m)) && n!=m && !m.testLoop(n)) {
					this.moveSubTree(n,m);
					itr=n.getOutPointers().listIterator();
				}else if(n.testLoop(m) && n!=m) {
					a=true;
					this.collapsePath(m,n);
					this.controllaPointers(m);
				}
			}			
		}
	}
	
	private void processBacklink(Nodo v, Nodo w) {
		if(v.testLoop(w))
			this.collapsePath(w,v);			
		else if(!(w.testLoop(v))) {
				if(this.threadPrecedes(w,v) ) {
					v.pushPointer(w);
				}else this.moveSubTree(v,w);
		}			
	}
	
	//genera una coppia di interi (v,w) e fa CheckPath
	public void random(int n) {
		Random random = new Random();
		int	v= random.nextInt(n);
		int	w = random.nextInt(n);
		System.out.println("creo l'arco ("+v+","+w+")");
		Nodo nodov = this.trovaV(v);
		if(nodov==null) {
			nodov =new Nodo(v,null);			
			this.addListaNodi(nodov);
		}		
		Nodo nodow = this.trovaV(w);
		if(nodow==null) {
			nodow = new Nodo(w,nodov);
			this.ordinaLista(nodow);
			nodov.addOutLink(nodow);			
		}else if(nodow.getInLink()!=nodov && nodov!=nodow && !nodow.getInPointers().contains(nodov))
				this.processBacklink(nodov,nodow); 
	}
	
	//funzione random modificata per la costruzione di un grafo specifico 
	public void random1(int v,int w) {
		System.out.println("creo l'arco ("+v+","+w+")");
		Nodo nodov = this.trovaV(v);
		if(nodov==null) {
			nodov =new Nodo(v,null);			
			this.addListaNodi(nodov);		
		}		
		Nodo nodow = this.trovaV(w);
		if(nodow==null) {
			nodow = new Nodo(w,nodov);
			this.ordinaLista(nodow);
			nodov.addOutLink(nodow);			
		}else if(nodow.getInLink()!=nodov && nodov!=nodow && !nodow.getInPointers().contains(nodov))
				this.processBacklink(nodov,nodow); 	
	}
	
}

