import tester.*;

class Pair{
    int a;
    int b;

    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
}

public class ArrayExamples {
    String joinWith(String[] a, String b){
        if (a.length == 0){return "";}
        if(a.length == 1){return a[0];}
        String temp = "";
        for (int i = 0; i < a.length-1; i++){
            temp += a[i] + b;
        }
        return temp + a[a.length-1];
    }

    boolean allTrue(boolean[] a){
        if (a.length == 0){return true;}
        for (boolean x : a){
            if (x == false){return false;}
        }
        return true;
    }

    boolean allWithinRange(double[] a, double low, double high){
        if (a.length == 0){return true;}
        for (double x : a){
            if (x < low || x > high){return false;}
        }
        return true;
    }

    Pair maxmin(int[] a){
        int max = a[0];
        int min = a[0];
        for (int x : a){
            if (x < min){min = x;}
            if (x > max){max = x;}
        }
        return new Pair(min,max);
    }

    String earliest(String[] a){
        String earliest = a[0];
        for (String x : a){
            if (earliest.compareTo(x) > 0){earliest = x;}
        }
        return earliest;
    }

    int lookup(String[] keys, int[] values, String key){
        for (int i = 0; i < keys.length; i++){
            if (keys[i].equals(key)){return values[i];}
        }
        return -1;
    }

    void testArrayExamples(Tester t){
        String[] arr1 = {"a","b","c","d"};
        t.checkExpect(joinWith(arr1,":"),"a:b:c:d");
        t.checkExpect(joinWith(arr1,"{}"),"a{}b{}c{}d");
        t.checkExpect(joinWith(arr1,"bruh"),"abruhbbruhcbruhd");

        boolean[] arr2 = {};
        boolean[] arr3 = {true, true, true};
        boolean[] arr4 = {true, true, false};
        t.checkExpect(allTrue(arr2),true);
        t.checkExpect(allTrue(arr3),true);
        t.checkExpect(allTrue(arr4),false);

        double[] arr5 = {5,10,15,20};
        t.checkExpect(allWithinRange(arr5, 2, 29),true);
        t.checkExpect(allWithinRange(arr5, 2, 19),false);
        t.checkExpect(allWithinRange(arr5, 2, 20),true);

        int[] arr6 = {1,2,3,4,5};
        int[] arr7 = {10,5,40,20};
        int[] arr8 = {0,9,0,3};
        t.checkExpect(maxmin(arr6),new Pair(1,5));
        t.checkExpect(maxmin(arr7),new Pair(5,40));
        t.checkExpect(maxmin(arr8),new Pair(0,9));

        String[] arr9 = {"park","bench","basketball"};
        String[] arr10 = {"forty","29","whoa"};
        String[] arr11 = {"wow","bro","crazy"};
        t.checkExpect(earliest(arr9),"basketball");
        t.checkExpect(earliest(arr10),"29");
        t.checkExpect(earliest(arr11),"bro");

        String[] arr12 = {"red","green","blue","purple","orange","yellow"};
        int[] arr13 = {5,10,15,20,25,30};
        t.checkExpect(lookup(arr12,arr13,"blue"),15);
        t.checkExpect(lookup(arr12,arr13,"red"),5);
        t.checkExpect(lookup(arr12,arr13,"yellow"),30);
    }
}

