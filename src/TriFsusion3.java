class TriFusion3 implements Tri {
	public void trier(UtilTableau util, int [] T) {
		util.afficherTableau(T);
		System.out.println("coucou");
		trierRec(util, T, 0, T.length-1);
	}

	public void trierRec(UtilTableau util, int [] T, int debut, int fin) {
		if (debut < fin) {
			int milieu = (debut+fin)/2;
			trierRec(util, T, debut, milieu);
			trierRec(util, T, milieu+1, fin);

			UtilFusion.fusionEnPlace(util, T, debut, milieu-debut+1, milieu+1, fin-milieu);
		}
	}

	public static void main(String [] args) {
		UtilTableau.effectuerUnTri(new TriFusion3());
	}
}