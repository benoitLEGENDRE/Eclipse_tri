class TriSelection implements Tri {
    public void trier(UtilTableau util, int [] T) {
	// Décommenter la ligne suivante pour accélérer l'animation
	// util.fixerDelai(1);
    	util.afficherTableau(T);
	int max=0;
	for  (int j=T.length;j>0;j--) {
		max=maximum(util, T,j);
		util.echanger(T, j-1, max);
        /*for (int i=1; i<T.length; i++) {
            int j = i;
            while ((j>0) && (util.compare(T[j-1], T[j])>0)) {
                util.echanger(T, j, j-1);
                j--;
            }*/
        }
    }

	public int maximum (UtilTableau util, int [] T, int longueur) {
		int max=0;
		for  (int i=1;i<longueur;i++) {
			if (util.compare(T[i], T[max])>0) {
				max=i;
			}
		}
		return max;
	}
		
        
    public static void main(String [] args) {
	

	if (args.length >= 1) {
	    int graine=new Integer(args[0]);
	    System.out.println("Utilisation de la graine "+graine);
	    UtilTableau.effectuerUnTri(new TriSelection(), graine);
	} else {
	    UtilTableau.effectuerUnTri(new TriSelection());
	}
    }
}
