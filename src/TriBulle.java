class TriBulle implements Tri {
    public void trier(UtilTableau util, int [] T) {
	// Décommenter la ligne suivante pour accélérer l'animation
	 util.fixerDelai(1);
    	util.afficherTableau(T);
		int maxi=0;
		int min=0;
		boolean pasfini=true;
		int longueur=T.length;
		System.out.println("min="+ min +"max"+ maxi + "longueur " + longueur);
		util.afficherTableau(T);
		while (pasfini==true) {
			pasfini=remplacer(util,T);
		}
		/*if (min != (maxi-2)) {
			separer(util,T,min,maxi);
		}*/
		
    }
    public boolean remplacer(UtilTableau util, int [] T) {
    	boolean echange=false;
    	int valeurD=T.length-1;
    	for (int i=0; i<valeurD;i++) {
    	 if (T[i]>T[i+1]) {
    		 util.echanger(T, i, i+1);
    		 if (echange==false){
    			 echange=true;
    		 }
    	 }
    	}
	return echange;
    }
    	
    
      


		
        
    public static void main(String [] args) {
	

	if (args.length >= 1) {
	    int graine=new Integer(args[0]);
	    System.out.println("Utilisation de la graine "+graine);
	    UtilTableau.effectuerUnTri(new TriBulle(), graine);
	} else {
	    UtilTableau.effectuerUnTri(new TriBulle());
	}
    }
}
