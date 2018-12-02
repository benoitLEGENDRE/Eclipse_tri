class TriFusion implements Tri {
    public void trier(UtilTableau util, int [] T) {
	// Décommenter la ligne suivante pour accélérer l'animation
	 util.fixerDelai(1);
	int debut=0,fin=T.length-1;
	Etendeur e = new Etendeur(T);
    	util.afficherTableau(T);
	segmenter(util,T,debut,fin);
    }

    public boolean remplacer(UtilTableau util, int [] T,int debut,int fin) {
    	boolean echange=false;
    	for (int i=debut; i<fin;i++) {
    	 if ((util.compare(T[i], T[i+1])>0)) {
    		 util.echanger(T, i, i+1);
    		 echange=true;
    	  }
    	}
		return echange;
    }
    	
    public void segmenter(UtilTableau util, int [] T, int debut,int fin){
	boolean pasfini=true;
	if (util.compare(fin, debut)>0) {
		int milieu = (debut+fin)/2;
		segmenter(util, T, debut, milieu);
		segmenter(util, T, milieu+1, fin);
	
		UtilFusion.fusionEnPlace(util, T, debut, milieu-debut+1, milieu+1, fin-milieu);
		
		}
	}
      


		
        
    public static void main(String [] args) {
	
	
	if (args.length >= 1) {
	    int graine=new Integer(args[0]);
	    System.out.println("Utilisation de la graine "+graine);
	    UtilTableau.effectuerUnTri(new TriFusion(), graine);
	} 
	else {
	    UtilTableau.effectuerUnTri(new TriFusion());
	}
    }
}
