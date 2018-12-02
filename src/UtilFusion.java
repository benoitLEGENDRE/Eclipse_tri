class UtilFusion {
// Version avec indices et tailles	
    public static void echangerBlocs(UtilTableau util, int[] T, int a, int b, int n) {
        for (int i = 0; i < n; i++) {
            util.echanger(T, a++, b++);
        }
    }

    public static void renverserBloc(UtilTableau util, int[] T, int b, int n) {
        for (int i = 0; i < (n / 2); i++) {
            util.echanger(T, i, n - i - 1);
        }
    }

    public static void decalerBloc(UtilTableau util, int[] T, int b, int n, int d) {
        int begin, step;

        if (d < 0) {
            begin = b;
            step = 1;
        } else {
            begin = b + n - 1;
            step = -1;
        }
        if (d != 0) {
            for (int i = 0; i < n; i++) {
                util.echanger(T, begin, begin + d);
                begin += step;
            }
        }
    }

    public static int interclasser(UtilTableau util, int[] T, int buffer, int A, int tailleA, int B, int tailleB) {
        while (tailleA > 0) {
            if ((tailleB > 0) && (util.compare(T[B], T[A]) < 0)) {
                util.echanger(T, buffer++, B++);
                tailleB--;
            } else {
                util.echanger(T, buffer++, A++);
                tailleA--;
            }
        }
        return tailleB;
    }

    public static void trierBlocs(UtilTableau util, int[] T, int first, int s, int nb) {
        for (int i = 0; i < nb - 1; i++) {
            int current = first;
            int best = first;
            int bestKey = T[first + s - 1];
            for (int j = i + 1; j < nb; j++) {
                current = current + s;
                int key = T[current + s - 1];
                if (util.compare(key, bestKey) < 0) {
                    best = current;
                    bestKey = key;
                }
            }
            echangerBlocs(util, T, first, best, s);
            first += s;
        }
    }

    public static void fusionEnPlace(UtilTableau util, int[] T, int F, int L1, int G, int L2) {
        // Algorithme de Huang et Langston - "Practical In-Place Merging", Communications of the ACM, vol. 31 n. 3, 1988
        // on suppose que les deux séquences sont contigues (imposé à partir de l'étape de fusion de F et G en H et I)
        int n = L1 + L2;
        int s = (int) Math.floor(Math.sqrt(n));
        if ((L1 >= s) && (L2 >= s)) {
            // Trouvons les blocs A et B de taille s1 et s2
            int A = F + L1;
            int B = G + L2;
            int s2 = 0;
            for (int i = 0; i < s; i++) {
                if (util.compare(T[A - 1], T[B - 1]) < 0) {
                    B--;
                    s2++;
                } else {
                    A--;
                }
            }
            // On définit les blocs C et D
            int C = A - s2;
            int tailleD = (L2 - s2) % s;
            int D = B - tailleD;
            // On constitue le buffer
            echangerBlocs(util, T, B, C, s2);
            int buffer = C;
            C = B;
            // On utilise le buffer pour interclasser C et D et former E 
            echangerBlocs(util, T, buffer, D, tailleD);
            interclasser(util, T, D, buffer, tailleD, C, s2);
            // Blocs F et G à fusionner en H et I
            int tailleF = L1 % s;
            if (tailleF > 0) {
                int sousBuffer = buffer + s - tailleF;
                interclasser(util, T, sousBuffer, F, tailleF, G, s);
                // On met H à sa place finale
                echangerBlocs(util, T, sousBuffer, F, tailleF);
            }
            // On met le buffer à sa place finale
            echangerBlocs(util, T, F + tailleF, buffer, s);
            buffer = F + tailleF;
            // Tri des blocs restants par clé
            // Dans ce qui reste on ne compte pas E, H et le buffer
            int reste = (n - tailleD - s2 - tailleF - s) / s;
            int series1 = F + tailleF + s;
            trierBlocs(util, T, series1, s, reste);
            // Interclassement
            int end = G + L2;
            int taille1 = Math.min(s, end - series1);
            while (series1 + taille1 < end) {
                // On cherche les deux series à interclasser
                int series2 = series1 + taille1;
                while ((series2 < end) && (util.compare(T[series2 - 1], T[series2]) <= 0)) {
                    taille1 = Math.min(taille1 + s, end - series1);
                    series2 = series1 + taille1;
                }
                // On interclasse en gardant trace des éléments restant en fin de series2
                if (series2 < end) {
                    int taille2 = Math.min(s, end - series2);
                    taille1 = interclasser(util, T, buffer, series1, taille1, series2, taille2);
                    series1 = series2 + taille2 - taille1;
                    buffer = series1 - s;
                }
            }
            // On décale le reste de séquence
            if (taille1 > 0) {
                decalerBloc(util, T, series1, taille1, -s);
                buffer += taille1;
            }
            // On trie le buffer
            trierBlocs(util, T, buffer, 1, s);
            // That's all folks
        }
    }
}
