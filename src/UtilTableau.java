import java.util.Random;

class UtilTableau {
	private MachineTrace m;
	int delai;
	private int comparaisons;

	public UtilTableau() {
		m = new MachineTrace(800,400,false);
		delai = 100;
		RAZComparaisons();
	}

	public void fixerDelai(int d) {
		delai = d;
	}

	public void afficherTableau(int [] T) {
		m.attendre(delai);
		m.effacerTout();
		m.lever();
		m.placer(-T.length*2, -100);
		for (int i=0; i<T.length; i++) {
			int line = Math.min(T[i]*5, 280);
			m.baisser();
			m.orienter(90);
			m.avancer(line);
			m.lever();
			m.avancer(-line);				
			m.orienter(0);
			m.avancer(4);
		}
		m.rafraichir();
	}

	public void echanger(int [] T, int x, int y) {
		if (x != y) {
			int tmp = T[y];
			T[y] = T[x];
			T[x] = tmp;
			afficherTableau(T);
		}
	}

	public void RAZComparaisons() {
		comparaisons=0;
	}

	public int comparaisons() {
		return comparaisons;
	}

	public int compare(int a, int b) {
		comparaisons++;
		if (a<b) return -1;
		if (a==b) return 0;
		return 1;
	}

	private static void effectuerUnTri(Tri t, Random generateur) {
		UtilTableau util = new UtilTableau();
		int [] T;

		T = ES.genererTableau(generateur);
		Validateur valid = new Validateur(T);

		t.trier(util, T);
		valid.verifieTri(T);

		System.out.println("Tableau trié");
		ES.afficherTableau(System.out, T);
		System.out.println();
		System.out.println("Comparaisons effectuées : "+util.comparaisons());
	}

	public static void effectuerUnTri(Tri t, int graine) {
		Random generateur = new Random(graine);
		effectuerUnTri(t, generateur);
	}

	public static void effectuerUnTri(Tri t) {
		Random generateur = new Random();
		effectuerUnTri(t, generateur);
	}

}
