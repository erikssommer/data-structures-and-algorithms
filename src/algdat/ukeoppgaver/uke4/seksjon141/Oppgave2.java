package algdat.ukeoppgaver.uke4.seksjon141;

import algdat.datastrukturer.Tabell;

public class Oppgave2 {
    public static void main(String[] args) {
        char[] c = "JASMIN".toCharArray();
        int k = Tabell.maks(c);

        System.out.println(c[k]);
    }
}
