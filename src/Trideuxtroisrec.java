import java.util.Random;

class Trideuxtroisrec implements Tri {

	Random alea;

	boolean estTrie(int[] T) {
		for (int i = 1; i < T.length; i++)
			if (T[i - 1] > T[i])
				return false;
		return true;
	}

	public void trier(UtilTableau util, int[] T) {
		alea = new Random();
		util.fixerDelai(1);
		util.afficherTableau(T);
		while (!estTrie(T)) {
			/*int i = alea.nextInt(T.length);
			int j = alea.nextInt(T.length);
			if ((i < j) && (util.compare(T[i], T[j]) > 0) || (i > j) && (util.compare(T[i], T[j]) < 0))
				util.echanger(T, i, j);*/
			trier2(util,T,2);
			trier3(util,T,3);
			trierseq(util,T);
		}
	}
	public void trier2(UtilTableau util, int[] T,int increment) {
		if (increment>1) {
			for(int i=0;i<((T.length-increment));i=i+increment) {
				if  (util.compare(T[i], T[i+increment]) > 0) {
					util.echanger(T, i, i+increment);
					//System.out.println("echange");
				}
				//System.out.println("fait");
			}
		
		
			//System.out.println(increment);
			if((increment*2)!=64){
				trier2(util,T,(increment*2));
			}
		}
		
		else
		{
			trier3(util,T,3);
		}
	}
	
	public void trier3(UtilTableau util, int[] T,int increment) {
		if (increment>1) {
			for(int i=0;i<((T.length-increment));i=i+increment) {
				if  (util.compare(T[i], T[i+increment]) > 0) {
					util.echanger(T, i, i+increment);
					//System.out.println("echange");
				}
				//System.out.println("fait");
			}
		
		
			//System.out.println(increment);
			if((increment*3)<(T.length/3)){
				
				trier3(util,T,increment*3);
				
			}
		}
		
		else
		{
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
		UtilTableau.effectuerUnTri(new Trideuxtroisrec(), 100);
	}
}
