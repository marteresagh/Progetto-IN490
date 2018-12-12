import java.util.LinkedList;
//import java.util.ListIterator;

public class Nodo {
	
	private int nome;
	private LinkedList<Nodo> outLink;// figli
	private Nodo inLink;//padre
	private LinkedList<Nodo> inPointers;//puntatori entranti
	private LinkedList<Nodo> outPointers;//puntatori uscenti
	private LinkedList<Integer> cfc;// elementi della cfc
	
	//costruttore
	public Nodo(int n,Nodo padre) {
		this.setNome(n);
		this.setCfc(new LinkedList<Integer>() );
		this.addCfc(n);
		this.setOutLink(new LinkedList<Nodo>());
		this.setInLink(padre);
		this.setInPointers(new LinkedList<Nodo> ());
		this.setOutPointers(new LinkedList<Nodo> ());
	}
	
	//getters e setters di outPointers
	public LinkedList<Nodo> getOutPointers() {
		return outPointers;
	}
	
	public void setOutPointers(LinkedList<Nodo> outPointers) {
		this.outPointers = outPointers;
	}
	
	public void addOutPointers(Nodo nodo) {
		this.outPointers.add(nodo);
	}
	
	//getters e setters di inPointers
	public LinkedList<Nodo> getInPointers() {
		return inPointers;
	}
	
	public void setInPointers(LinkedList<Nodo> inPointers) {
		this.inPointers = inPointers;
	}
	
	public void addInPointers(Nodo nodo) {
		this.inPointers.add(nodo);
	}
	
	//crea l'arco-puntatore (base,nodo)	
	public void pushPointer(Nodo nodo) {
		this.addOutPointers(nodo);
		nodo.addInPointers(this);
	}
	
	public void removePointers(Nodo nodo) {
		this.getOutPointers().remove(nodo);
		nodo.getInPointers().remove(this);
		
	}

	//getter e setters di nome
	public int getNome() {
		return nome;
	}

	public void setNome(int nome) {
		this.nome = nome;
	}

	//getters e setters di outLink
	public LinkedList<Nodo> getOutLink() {
		return outLink;
	}

	public void setOutLink(LinkedList<Nodo> outLink) {
		this.outLink = outLink;
	}
	
	public void addOutLink(Nodo nodo) {
		this.outLink.addLast(nodo);
	}

	public void removeOutLink(Nodo nodo) {
		this.outLink.remove(nodo);	
	}
	
	//getters e setters di inLink
	public Nodo getInLink() {
		return inLink;
	}

	public void setInLink(Nodo inLink) {
		this.inLink = inLink;
	}


	//getters e setters di cfc
	public LinkedList<Integer> getCfc() {
		return cfc;
	}

	public void setCfc(LinkedList<Integer> cfc) {
		this.cfc = cfc;
	}
	
	public void addCfc(int n) {
		this.cfc.add(n);
	}
	
	//per funzione trovaV di grafo
	public boolean equals(int v) {
		return this.getCfc().contains(v);
	}
	
	//verifica se c'è una cfc
	public boolean testLoop(Nodo w) {
		Nodo x=this.getInLink();
		while(x!=w && x!=null) {
			x=x.getInLink();
		}
		return x==w;
	}
	
	//costruisce la lista ordinata di un sottoalbero con radice questo nodo
	public LinkedList<Nodo> listaRootToLeaf(){
		LinkedList<Nodo> lista= new LinkedList<Nodo>(); 
		Grafo.toLeaf(lista,this);		
		return lista;
	}
	

	// Ricerca ultima foglia a destra 
	public Nodo ultimaFoglia() {
		Nodo v=this.getOutLink().peekLast();
			if(v==null) {
				return this;
			} else {
				return v.ultimaFoglia();
			}
		}
	
	//per stampare la lista trasforma Nome in una stringa
	public String toString() {
		return String.valueOf(this.getCfc());
	}
		
	// rimuove l'albero con radice questo nodo
	public void removeSubTree() {
		this.getInLink().pushPointer(this);
		this.getInLink().removeOutLink(this); 											
	}
		
	// this è base(padre)
	// crea una componente connessa gestendo outlink e inlink
	public void addTocomponent(Nodo collapse) {
		this.getCfc().addAll(collapse.getCfc());
		int i=this.getOutLink().indexOf(collapse);
		this.getOutLink().addAll(i,collapse.getOutLink());
		this.getOutLink().remove(collapse);
		for(Nodo item: collapse.getOutLink()) {
			item.setInLink(this);				
		}
	}
	
	//gestisce i puntatori uscenti nella creazione di una cfc
	private void insertOutPointers( Nodo collapse) {
		collapse.removePointers(this);
		for(Nodo item: collapse.getOutPointers()) {
			if( !this.getOutPointers().contains(item) /*&& !this.getOutLink().contains(item)*/ ) { 
				this.pushPointer(item);
				item.getInPointers().remove(collapse);
			}else{
				item.getInPointers().remove(collapse);
			}
		}	
		
	}

	// gestisce i puntatori nella creazione di una componente connessa
	public void removeNodo(Nodo collapse) {
		this.insertOutPointers(collapse);
		for(Nodo n: collapse.getInPointers()) {	
			if(!this.getInPointers().contains(n) ) {
				n.getOutPointers().remove(collapse);
				n.pushPointer(this);
			}else {
				n.getOutPointers().remove(collapse);
			}
		}
	}
	
}
