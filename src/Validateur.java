import java.util.Arrays;

public class Validateur {
	private int [] Copie;
	
	public Validateur(int [] T) {
		Copie = Arrays.copyOf(T, T.length);
	}
	public void verifieTri(int [] T) {
		boolean trie = true;
		boolean permutation = true;
		boolean [] marque;
		
		marque = new boolean[T.length];
		for (int i=0; i<T.length; i++) {
			if (i>0) {
				trie &= (T[i-1] <= T[i]);
			}
			int j;
			for (j=0; (j<marque.length) && (marque[j] || (T[i] != Copie[j])); j++) {
			}
			if (j<marque.length) {
				marque[j] = true;
			} else {
				permutation = false;
			}	
		}
		if (!trie) {
			System.err.println("*** BUG : Tableau non trié ***");
		}
		if (!permutation) {
			System.err.println("*** BUG : Elements du tableau changés ***");			
		}
	}
	

}
