
class TriBitonique implements Tri {
    public void trier(UtilTableau util, int [] T) {
	// Décommenter la ligne suivante pour accélérer l'animation
	util.fixerDelai(1);
    	
 	// étendre le tableau à un taille égale à une puissance de deux
	Etendeur e = new Etendeur(T);
	int [] tabEtendu = e.tableauEtendu();

  	trierBitonique(util,tabEtendu,0,tabEtendu.length-1);

	e.finalise();
        util.afficherTableau(T);

    }
    
    public void trierBitonique(UtilTableau util, int [] T, int borneInf, int borneSup) {
	// Décommenter la ligne suivante pour accélérer l'animation
	// util.fixerDelai(1);
    	   	int compteur=(borneSup+1)/2;//64
        	//System.out.println("compteur= "+compteur);
        	//System.out.println("trie= borneInf "+borneInf+" borneSup "+borneSup+" compteur "+compteur);
        	//System.out.println("k= "+borneInf+" compteur= "+compteur);
        	TriComptage(util,T,borneInf,borneSup,compteur);
            //Fusionner(util,T,borneInf,borneSup,compteur);
        	     
  	
   }
    
    public void Fusionner (UtilTableau util, int [] T, int borneInf, int borneSup,int compteur) {
    	int increment=(borneSup+1)/compteur;//2
    	int k=0;
    	while(k<compteur) {
    		//System.out.println(" fusion K= "+k+"  increment= "+increment);
	    	for(int i=0;i<(increment/2);i++) {
	    		MinMax(util,T,k*increment+i,k*increment+i+(increment/2));
	    	}
	    	k++;
    	}
    	if (increment>2) {
    		//System.out.println("********** fusion compteur "+(compteur/2)+ " borne inf. "+borneInf+ " borne sup. " + borneSup+" ***********");
    		Fusionner(util,T,borneInf,borneSup,compteur*2);
    		
    	}
    }
    
    public void TriComptage (UtilTableau util, int [] T, int borneInf, int borneSup,int compteur) {
    	//util.echanger(T, borneInf, borneSup);
    	int increment=(borneSup+1)/(compteur);//2
    	int k=0;
    	//System.out.println(" TRI borneSup+1 "+(borneSup+1)+"  increment= "+increment+ " car compteur "+compteur);
	    while(k<compteur) {
	    	//System.out.println(" TRI K= "+k+"  increment= "+increment);
	    	
	    	for(int i=0;i<(increment/2);i++) {
	    		//System.out.print(".TRI. K= "+k+"  i= "+i);
	    		MinMax(util,T,k*increment+i,(k*increment)+(increment-i)-1);
	    		
	    	}
	    	k++;
	    }
	    Fusionner(util,T,borneInf,borneSup,(compteur)*2);
	    if (increment<borneSup) {
	    	compteur=compteur/2;
	    	//System.out.println("********** on relance la fonction avec le compteur "+compteur+ " borne inf�rieure "+borneInf+ " borne sup�rieure " + borneSup+" ***********");
	    	TriComptage(util,T,borneInf,borneSup,compteur);
	    }
    	
    }
    
    public void MinMax (UtilTableau util, int [] T, int borneInf, int borneSup) {
    	//System.out.println("etat borneinf= "+borneInf+"  borneSup= "+borneSup);
    	if(util.compare(T[borneInf],T[borneSup])>0) {
    		
    		util.echanger(T, borneInf, borneSup);
    	}
    }

    public static void main(String [] args) {
	if (args.length >= 1) {
	    int graine=new Integer(args[0]);
	    System.out.println("Utilisation de la graine "+graine);
	    UtilTableau.effectuerUnTri(new TriBitonique(), graine);
	} else {
	    UtilTableau.effectuerUnTri(new TriBitonique());
	}
    }
}
