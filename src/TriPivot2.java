class TriPivot2 implements Tri {
    public void trier(UtilTableau util, int [] T) {
	// Décommenter la ligne suivante pour accélérer l'animation
	 //util.fixerDelai(1);
    	util.afficherTableau(T);
		int maxi=0;
		int min=0;
		int droite=0;
		int longueur=T.length;
		System.out.println("longueur "+T.length);
		util.afficherTableau(T);
		
		//System.out.println("droite +1 = "+ droite +1 );
		//System.out.println("droite = "+ droite );
		
		//System.out.println("longueur "+T.length);
			
			(droite)=separer2(util,T,0,T.length-1);
			
		
		/*if (min != (maxi-2)) {
			separer(util,T,min,maxi);
		}*/
		
    }
    public int separer2(UtilTableau util, int [] T,int valeurG,int valeurD) {
    	int j,piv,compteur=1;
    	piv=T[valeurD];
    	//System.out.println("valeurG="+ valeurG +"valeurD"+ valeurD+"piv= "+piv);
    	j=valeurG;
    	for (int i=valeurG;i<(valeurD);i++) {
    		if (T[i]<=piv) {
    			System.out.print(" "+(T[i])+ "/"+(T[j]));
        		util.echanger(T, i, j);
        		j++;
        	}
    		
    	}
    	
       
        	//System.out.println("on echange j en j="+ j+"  " + (T[j]) +"  en="+valeurD +" pivot "+(T[valeurD]));
        	util.echanger(T, j, valeurD);
        
    	int borneG=valeurG;
    	int borneInterG=j-1;
    	int borneInterD=j;
    	int borneD=valeurD;
    	if ((borneInterG-borneG)>1) {
    		separer2(util,T,borneG,borneInterG);
    	}
    	if ((borneD-borneInterD)>1) {
    		separer2(util,T,borneInterD,borneD);
    	}
    		
    	
    	return (compteur);
    	
    	
    	
    }
    
   

    


		
        
    public static void main(String [] args) {
	

	if (args.length >= 1) {
	    int graine=new Integer(args[0]);
	    System.out.println("Utilisation de la graine "+graine);
	    UtilTableau.effectuerUnTri(new TriPivot2(), graine);
	} else {
	    UtilTableau.effectuerUnTri(new TriPivot2());
	}
    }
}
