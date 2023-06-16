import tester.*;
class Pair{
    int a;
    int b;

    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
}

public class PairSelect {
    int[] getAs(Pair[] arr){
        int count = 0;
        for (Pair x : arr){
            count += 1;
        }
        int[] forReturn = new int[count];
        for (int i = 0; i < count; i++){
            forReturn[i] = arr[i].a;
        }
        return forReturn;
    }

    void testPairSelect(Tester t){
        Pair[] arr1 = {new Pair(1,2),new Pair(3,4),new Pair(5,6)};
        int[] exp1 = {1,3,5};
        Pair[] arr2 = {new Pair(7,3),new Pair(0,0),new Pair(4,5),new Pair(10,15)};
        int[] exp2 = {7,0,4,10};
        Pair[] arr3 = {new Pair(2,1),new Pair(4,1),new Pair(4,2)};
        int[] exp3 = {2,4,4};
        Pair[] arr4 = {new Pair(0,0),new Pair(1,1),new Pair(5,5)};
        int[] exp4 = {0,1,5};
        t.checkExpect(getAs(arr1),exp1);
        t.checkExpect(getAs(arr2),exp2);
        t.checkExpect(getAs(arr3),exp3);
        t.checkExpect(getAs(arr4),exp4);
    }
}
