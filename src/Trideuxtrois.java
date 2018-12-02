import java.util.Random;

class Trideuxtrois implements Tri {

	Random alea;

	boolean estTrie(int[] T) {
		for (int i = 1; i < T.length; i++)
			if (T[i - 1] > T[i])
				return false;
		return true;
	}

	public void trier(UtilTableau util, int[] T) {
		alea = new Random();
		//util.fixerDelai(1);
		util.afficherTableau(T);
		while (!estTrie(T)) {
			/*int i = alea.nextInt(T.length);
			int j = alea.nextInt(T.length);
			if ((i < j) && (util.compare(T[i], T[j]) > 0) || (i > j) && (util.compare(T[i], T[j]) < 0))
				util.echanger(T, i, j);*/
			trier2(util,T,T.length);
			//trier3(util,T);
			//trierseq(util,T);
		}
	}
	public void trier2(UtilTableau util, int[] T,int longueur) {
		if (longueur>4) {
			trier2(util,T,(longueur/2));
		}
		else
		{
			for(int i=0;i<((T.length-2)/2);i=i+2) {
				if  (util.compare(T[i], T[i+2]) > 0) {
					util.echanger(T, i, i+2);
				}
			}
		}
		for(int i=0;i<((T.length-2)/2);i=i+2) {
			if  (util.compare(T[i], T[i+2]) > 0) {
				util.echanger(T, i, i+2);
			}
		}
	}
	public void trier3(UtilTableau util, int[] T) {
		for(int i=0;i<T.length-3;i=i+3) {
			if  (util.compare(T[i], T[i+3]) > 0) {
				util.echanger(T, i, i+3);
			}
		}
	}
	public void trierseq(UtilTableau util, int[] T) {
		for(int i=0;i<T.length-1;i=i+1) {
			if  (util.compare(T[i], T[i+1]) > 0) {
				util.echanger(T, i, i+1);
			}
		}
	}
	public static void main(String[] args) {
		// A récupérer tel quel en mettant :
		// - le bon nom de classe après 'new'
		// - une graine pour le générateur aléatoire (optionel)
		// - un délai en millisecondes entre chaque mise à jour de l'affichage (optionel)
		UtilTableau.effectuerUnTri(new Trideuxtrois(), 100);
	}
}
