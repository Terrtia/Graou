package graou.algo;

/**
 * Classe de création de file de priorité
 *
 */

public class Heap {
	
	private Node[] tas;

	/**
	 * Constructeur - crée une file de priorité contenant les entiers de 0 à N-1 avec priorité +infini
	 * @param N : nombre d'entiers dans la file
	 */
	public Heap(int N) {
		
		this.tas = new Node[N];
		for(int i=0; i<N; i++){
			tas[i] = new Node(i, +1, i-1);
		}
	}

	/**
	 * Supprime de la file l'élément ayant la plus faible priorité et le renvoie
	 * @return l'élément supprimé
	 */
	public int pop() {
		
		return 0;
	}

	/**
	 * @param x - entier dans la file ou non
	 * @return la priorité de l'élément x
	 */
	public int priority(int x) {
		
		return 0;
	}

	/**
	 * Change la priorité de l'élément x à la valeur p (erreur si p > priority(x))
	 * @param x - élément dont on veut changer la priorité
	 * @param p - nouvelle priorité ( < priority(x) )
	 */
	public void decreaseKey(int x, int p) {
		
	}
}
