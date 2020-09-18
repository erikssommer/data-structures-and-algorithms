package algdat;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell {
    private Tabell() {
    }

    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int[] randPerm(int n) {
        Random r = new Random();
        int[] a = new int[n];

        Arrays.setAll(a, i -> i + 1);

        for (int k = n - 1; k > 0; k--) {
            int i = r.nextInt(k + 1);
            bytt(a, k, i);
        }
        return a;
    }

    public static void randPerm(int[] a) {
        Random r = new Random();

        for (int k = a.length - 1; k > 0; k--) {
            int i = r.nextInt(k + 1); // tilfeldig tall fra [0,k]
            bytt(a, k, i);
        }
    }

    public static int maks(int[] a, int fra, int til) {

        fratilKontroll(a.length, fra, til);

        int m = fra;
        int maksverdi = a[fra];

        for (int i = fra + 1; i < til; i++) {
            if (a[i] > maksverdi) {
                m = i;
                maksverdi = a[m];
            }
        }
        return m;
    }

    public static int maks(int[] a) {
        return maks(a, 0, a.length);
    }

    public static int min(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length || fra >= til) {
            throw new IllegalArgumentException("Illegalt intervall");
        }

        int m = fra;
        int minvedi = a[fra];

        for (int i = fra + 1; i < til; i++) {
            if (a[i] < minvedi) {
                m = i;
                minvedi = a[m];
            }
        }
        return m;

    }

    public static int min(int[] a) {
        return min(a, 0, a.length);
    }

    public static void bytt(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static void skriv(int[] a, int fra, int til) {
        for (int i = fra; i <= til; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void skriv(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    public static void skrivln(int[] a, int fra, int til) {
        skriv(a, fra, til);
        System.out.println();
    }

    public static void skrivln(int[] a) {
        skriv(a);
        System.out.println();
    }

    public static void fratilKontroll(int tabellengde, int fra, int til) {

        if (tabellengde == 0) {
            throw new IllegalArgumentException("Tabellengden er null");
        }

        if (fra < 0) {
            throw new ArrayIndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        }

        if (til > tabellengde) {
            throw new ArrayIndexOutOfBoundsException("til(" + til + ") > tablengde(" + tabellengde + ")");
        }

        if (fra > til) {
            throw new ArrayIndexOutOfBoundsException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }

        if (fra == til) {
            throw new NoSuchElementException("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");
        }
    }

    public static void vhKontroll(int tablengde, int v, int h) {
        if (v < 0) {
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");
        }
        if (h >= tablengde) {
            throw new ArrayIndexOutOfBoundsException("h(" + h + ") >= tablengde(" + tablengde + ")");
        }

        if (v > h + 1) {
            throw new IllegalArgumentException("v = " + v + ", h = " + h);
        }
    }

    public static int[] nestMaks(int[] a) {
        int n = a.length;

        if (n < 2) throw new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = maks(a);

        int nm;

        if (m == 0) {
            nm = maks(a, 1, n);
        } else if (m == n - 1) {
            nm = maks(a, 0, n - 1);
        } else {
            int mv = maks(a, 0, m);
            int mh = maks(a, m + 1, n);
            nm = a[mh] > a[mv] ? mh : mv;
        }
        return new int[]{m, nm};
    }

    public static int[] nestMaksSF(int[] a) {

        int n = a.length;

        if (n < 2) throw new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = maks(a);

        bytt(a, 0, m);

        int nm = maks(a, 1, n);

        bytt(a, m, 0);

        return new int[]{m, nm};
    }

    public static int[] nestMaksSB(int[] a) {

        int n = a.length;

        if (n < 2) throw new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = maks(a);

        bytt(a, m, n - 1);

        int nm = maks(a, 0, n - 2);

        bytt(a, m, n - 1);

        return new int[]{m, nm};
    }

    public static void sortering(int[] a) {

        int n = a.length;

        if (n < 2) throw new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        for (int i = n - 1; i > 0; i--) {

            int m = maks(a, 0, i);

            bytt(a, m, i);

            int nm = maks(a, 0, i);

            bytt(a, nm, i - 1);
        }
    }

    public static int[] nestMaksTR(int[] a) { //Turneringstre
        int n = a.length;

        if (n < 2) throw new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int[] b = new int[2 * n];
        //System.arraycopy(a,0,b,n,n);
        kopier(a, 0, b, n, n);

        for (int k = 2 * n - 2; k > 1; k -= 2) {
            b[k / 2] = Math.max(b[k], b[k + 1]);
        }

        int maksverdi = b[1];
        int nestmaksverdi = Integer.MIN_VALUE;

        for (int m = 2 * n - 1, k = 2; k < m; k *= 2) {
            int temp = b[k + 1];
            if (maksverdi != b[k]) {
                temp = b[k];
                k++;
            }
            if (temp > nestmaksverdi) {
                nestmaksverdi = temp;
            }
        }
        return new int[]{maksverdi, nestmaksverdi};
    }

    public static void kopier(int[] a, int i, int[] b, int j, int ant) {
        int aIndex = i;
        for (int k = j; k < ant * 2; k++) {
            if (aIndex < a.length) {
                b[k] = a[aIndex];
                aIndex++;
            }
        }
    }

    public static void snu(int[] a, int v, int h) {
        while (v < h) {
            bytt(a, v++, h--);
        }
    }

    public static void snu(int[] a, int v) {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a) {
        snu(a, 0, a.length - 1);
    }

    public static boolean nestePermutasjon(int[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] > a[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = a.length - 1;
        while (a[j] < a[i]) {
            j--;
        }
        bytt(a, i, j);
        snu(a, i + 1);
        return true;
    }

    public static int binarySearch(int verdi, int[] a, int v, int h) {
        int pivot = (v + h) / 2;

        if (h - v == 0) {
            if (a[pivot] == verdi) {
                return pivot;
            } else {
                return -pivot;
            }
        }

        if (a[pivot] <= verdi) {
            if (a[pivot] == verdi) {
                return pivot;
            }
            return binarySearch(verdi, a, pivot + 1, h);
        } else {
            return binarySearch(verdi, a, v, pivot - 1);
        }
    }

    public static int inversjoner(int[] a){
        int antall = 0;
        for (int i = 0; i < a.length-1; i++){
            for (int j = i+1;j < a.length; j++){
                if (a[i] > a[j]){
                    antall++;
                }
            }
        }
        return antall;
    }

    public static void utvalgssortering(int[] a){
        for (int i = 0; i < a.length-1; i++){
            bytt(a, i, min(a,i,a.length));
        }
    }

    public static void utvalgssortering(int[] a, int fra, int til){

        fratilKontroll(a.length, fra, til);

        for (int i = fra; i < a.length - 1; i++) {
            int index = i;
            for (int j = til + 1; j < a.length; j++){
                if (a[j] < a[index]){
                    index = j;
                }
            }
            int smallerNumber = a[index];
            a[index] = a[i];
            a[i] = smallerNumber;
        }
    }

    //Hentet fra https://www.javatpoint.com/selection-sort-in-java
    public static void selectionSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[index]){
                    index = j; //searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }

    public static int usortertsok(int[] a, int verdi){
        for (int i = 0; i < a.length; i++){
            if (a[i] == verdi){
                return i;
            }
        }
        return -1;
    }

    public static int linearsok(int[] a, int verdi){
        if (a.length == 0 || verdi > a[a.length-1]){
            return -(a.length +1);
        }
        int i = 0;
        for ( ; a[i] < verdi; i++);

        return verdi == a[i] ? i : -(i +1);
    }

    public static int linearsokReturSisteVerdi(int[] a, int verdi){
        if (a.length == 0 || verdi > a[a.length-1]){
            return -(a.length +1);
        }
        int i = a.length-1;
        for ( ; a[i] > verdi; i--);

        return verdi == a[i] ? i : -(i +1);
    }

    //TODO denne trenger nok litt mer jobb
    public static int linearsok(int[] a, int k, int verdi){
        if (a.length == 0 || verdi > a[a.length-1]){
            return -(a.length +1);
        }
        if (k < 0){
            throw new IllegalArgumentException("Hoppelengden kan ikke være negativ");
        }

        for (int i = 0; i < i + k && i < a.length; i += k){
            if (verdi < a[i]){
                for (int j = i-k; j < i + k; j++){
                    if (a[j] == verdi){
                        return j;
                    }
                }
            }
        }
        return -1;
    }

    public static int kvadratrotsok(int[] a, int verdi){
        int k = (int) Math.sqrt(a.length);
        if (verdi < a[k]){
            for (int i = 0; i < k; i++){
                if (verdi == a[i]){
                    return i;
                }
            }
        }else {
            for (int i = k; i < a.length; i++){
                if (verdi == a[i]){
                    return i;
                }
            }
        }
        return -1;
    }

    //Returnerer siste index
    public static int binaersokV1(int[] a, int fra, int til, int verdi){
        fratilKontroll(a.length, fra, til);
        int v = fra;
        int h = til -1;

        while (v <= h){
            int m = (v + h)/2;
            int midtverdi = a[m];

            if (verdi == midtverdi){
                return m;
            }else if (verdi > midtverdi){
                v = m + 1;
            }else {
                h = m -1;
            }
        }
        return -(v + 1);
    }

    //Returnerer siste index
    public static int binaersokV2(int[] a, int fra, int til, int verdi){
        fratilKontroll(a.length, fra, til);
        int v = fra;
        int h = til -1;

        while (v <= h){
            int m = (v + h)/2;
            int midtverdi = a[m];

            if (verdi > midtverdi){
                v = m + 1;
            }else if (verdi < midtverdi){
                h = m - 1;
            }else {
                return m;
            }
        }
        return -(v + 1);
    }

    //Returnerer første index
    public static int binaersokV3(int[] a, int fra, int til, int verdi){
        fratilKontroll(a.length, fra, til);
        int v = fra;
        int h = til -1;

        while (v < h){
            int m = (v + h)/2;

            if (verdi > a[m]){
                v = m + 1;
            }else {
                h = m;
            }
        }

        if (h < v || verdi < a[v]){
            return -(v + 1);
        }else if (verdi == a[v]){
            return v;
        }else {
            return -(v + 2);
        }
    }

    public static int binaersokV1(int[] a, int verdi){
        return binaersokV1(a, 0, a.length, verdi);
    }

    public static int binaersokV2(int[] a, int verdi){
        return binaersokV2(a, 0, a.length, verdi);
    }

    public static int binaersokV3(int[] a, int verdi){
        return binaersokV3(a, 0, a.length, verdi);
    }

    public static void innsettingssortering(int[] a){
        for (int i = 1; i < a.length; i++){
            int verdi = a[i];
            int j = i - 1;
            for (; j >= 0 && verdi < a[j]; j--){
                a[j+1] = a[j];
            }
            a[j +1] = verdi;
        }
    }

    public static void innsettingssortering(int[] a, int fra, int til){
        for (int i = fra; i <= til; i++){
            int verdi = a[i];
            int j = i - 1;
            for (; j >= fra && verdi < a[j]; j--){
                a[j+1] = a[j];
            }
            a[j +1] = verdi;
        }
    }

    public static void innsettingssorteringV2(int[] a){
        for (int i = 1; i < a.length; i++){
            int temp = a[i];
            for (int j = i - 1; j >= 0 && temp <a [j]; j--) {
                Tabell.bytt(a, j, j + 1);
            }
        }
    }

    public static void shell(int[] a, int k) {
        for (int i = k; i < a.length; i++) {
            int temp= a[i];
            int j = i - k;
            for (; j >= 0 && temp < a[j]; j -= k) {
                a[j + k] = a[j];
                a[j + k] = temp;
            }
        }
    }

    public static boolean erSortert(int[] a){
        for (int i = 1; i < a.length; i++){
            if (a[i-1] > a[i]){
                return false;
            }
        }
        return true;
    }

}
