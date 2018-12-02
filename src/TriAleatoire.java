import java.util.Random;

class TriAleatoire implements Tri {

	Random alea;

	boolean estTrie(int[] T) {
		for (int i = 1; i < T.length; i++)
			if (T[i - 1] > T[i])
				return false;
		return true;
	}

	public void trier(UtilTableau util, int[] T) {
		alea = new Random();

		util.afficherTableau(T);
		while (!estTrie(T)) {
			int i = alea.nextInt(T.length);
			int j = alea.nextInt(T.length);
			if ((i < j) && (util.compare(T[i], T[j]) > 0) || (i > j) && (util.compare(T[i], T[j]) < 0))
				util.echanger(T, i, j);
		}
	}

	public static void main(String[] args) {
		// A récupérer tel quel en mettant :
		// - le bon nom de classe après 'new'
		// - une graine pour le générateur aléatoire (optionel)
		// - un délai en millisecondes entre chaque mise à jour de l'affichage (optionel)
		UtilTableau.effectuerUnTri(new TriAleatoire(), 100);
	}
}
