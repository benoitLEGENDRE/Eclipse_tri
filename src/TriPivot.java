class TriPivot implements Tri {
    public void trier(UtilTableau util, int [] T) {
	// Décommenter la ligne suivante pour accélérer l'animation
	// util.fixerDelai(1);
    	util.afficherTableau(T);
		int maxi=0;
		int min=0;
		int droite=0;
		int longueur=T.length;
		util.afficherTableau(T);
		(droite)=separer2(util,T,0,((longueur-1)/2));
		System.out.println("droite +1 = "+ droite +1 );
		System.out.println("droite = "+ droite );
		
		while (droite>0) {
			
			(droite)=separer2(util,T,0,droite);
			(droite)=separer2(util,T,droite+1,T.length-1);
		}
		/*if (min != (maxi-2)) {
			separer(util,T,min,maxi);
		}*/
		
    }
    public int separer2(UtilTableau util, int [] T,int valeurG,int valeurD) {
    	int i,j,piv;
    	piv=T[valeurD];
    	System.out.println("valeurG="+ valeurG +"valeurD"+ valeurD);
    	i=valeurG;
    	j=valeurD;
    	int compteur=0;
    	System.out.println("piv="+ piv);
    	while(i<j) {
	    	while (T[i]<piv) {
	    		i++;
	    		System.out.print(" i= "+ i );
	    	}
	    	while (T[j]>piv) {
	    		j--;
	    		System.out.print(" j= "+ j );
	    	}
	    	if (T[i]!=T[j]) {
	    		util.echanger(T, i, j);
	    		compteur++;
	    	}
	    	else {
	    		i++;
	    	}
	    	
	    	System.out.println(" sortie et echange de"+ " i= "+ i +" j= "+ j + " T[i]"+T[i]+" T[j]"+T[j]);
	    	
	    	}
    	if (compteur==0) {
    		for  (int k=0;k<(T.length-1);k++) {
    			if (T[k]>T[k+1]) {
    				compteur=1;
    			}
    		}
    	}
    	return (compteur);
    	
    	
    	
    }
    
   

    


		
        
    public static void main(String [] args) {
	

	if (args.length >= 1) {
	    int graine=new Integer(args[0]);
	    System.out.println("Utilisation de la graine "+graine);
	    UtilTableau.effectuerUnTri(new TriPivot(), graine);
	} else {
	    UtilTableau.effectuerUnTri(new TriPivot());
	}
    }
}
