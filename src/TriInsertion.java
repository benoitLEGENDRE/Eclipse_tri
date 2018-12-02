class TriInsertion implements Tri {
    public void trier(UtilTableau util, int [] T) {
	// Décommenter la ligne suivante pour accélérer l'animation
	// util.fixerDelai(1);
    	util.afficherTableau(T);
        for (int i=1; i<T.length; i++) {
            int j = i;
            while ((j>0) && (util.compare(T[j-1], T[j])>0)) {
                util.echanger(T, j, j-1);
                j--;
            }
        }
    }
        
    public static void main(String [] args) {
	if (args.length >= 1) {
	    int graine=new Integer(args[0]);
	    System.out.println("Utilisation de la graine "+graine);
	    UtilTableau.effectuerUnTri(new TriInsertion(), graine);
	} else {
	    UtilTableau.effectuerUnTri(new TriInsertion());
	}
    }
}
