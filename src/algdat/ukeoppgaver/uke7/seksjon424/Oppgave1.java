package algdat.ukeoppgaver.uke7.seksjon424;

import algdat.hjelpeklasser.EnkeltLenketListe;
import algdat.hjelpeklasser.Ko;

public class Oppgave1 {
    public static void main(String[] args) {
        Ko<Integer> ko = new EnkeltLenketListe<>();

        for (int i = 1; i <= 10; i++) ko.leggInn(i);

        while (!ko.tom()) System.out.print(ko.taUt() + " ");
    }
}
