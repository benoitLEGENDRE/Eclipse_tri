class ES
{
    final static int nbMin = 50;
    final static int nbMax = 128;
    final static int valMin = 0;
    final static int valMax = 49;
    
    public static int [] lireTableau(java.util.Scanner in) {
        System.out.println("Saisissez un entier");
        int [] T = new int[in.nextInt()];
        System.out.println(T.length + " autres entiers");
        for (int i=0; i<T.length; i++)
            T[i] = in.nextInt();
        return T;
    }
    
    public static int [] genererTableau(java.util.Random generateur) {
        int [] T = new int[generateur.nextInt(nbMax-nbMin+1)+nbMin];
        for (int i=0; i<T.length; i++)
            T[i] = generateur.nextInt(valMax-valMin+1)+valMin;
        System.out.println("Tableau généré");
        ES.afficherTableau(System.out, T);
        System.out.println();
        return T;
    }
    
    public static int genererEntier(java.util.Random generateur) {
       int x;
        x = generateur.nextInt(valMax-valMin+1)+valMin;
        System.out.println("Element généré");
        System.out.println(x);
        return x;
    }

    public static void afficherTableau(java.io.PrintStream out, int [] T) {
        out.print(java.util.Arrays.toString(T));
    }
}
