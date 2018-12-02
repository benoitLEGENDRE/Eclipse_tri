import java.util.Arrays;

public class Etendeur {
	int [] original;
	int [] etendu;

	public Etendeur(int [] T) {
		int longueur = T.length;
		int extension = 1;
		original = T;

		while (extension < longueur) {
			extension *= 2;
		}
		if (extension > longueur) {
			etendu = Arrays.copyOf(T, extension);
			for (int i=original.length; i<etendu.length; i++)
				etendu[i] = Integer.MAX_VALUE;
		}
	}

	public int [] tableauEtendu() {
		return etendu;
	}

	public void finalise() {
		if (etendu.length > original.length) {
			for (int i=0; i<original.length; i++) {
				original[i] = etendu[i];
			}
		}
	}
}