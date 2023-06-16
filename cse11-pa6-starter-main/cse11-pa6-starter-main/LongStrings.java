import tester.*;
public class LongStrings {
    String[] longStrings(String[] arr, int n){
        int count = 0;
        for (String x : arr){
            if (x.length() >= n){
                count += 1;
            }
        }
        String[] forReturn = new String[count];
        count = 0;
        for (String x : arr){
            if (x.length() >= n){
                forReturn[count] = x;
                count += 1;
            }
        }
        return forReturn;
    }

    void testLongStrings(Tester t){
        String[] arr1 = {"one","two","three","four","five"};
        String[] exp1 = {"three","four","five"};
        String[] arr2 = {"thats","crazy","bro"};
        String[] exp2 = {"thats","crazy"};
        String[] arr3 = {"holy wowzer","what the flip","i love eating pizza"};
        String[] exp3 = {"i love eating pizza"};
        String[] arr4 = {"e","","eb","eba3"};
        String[] exp4 = {"e","eb","eba3"};
        t.checkExpect(longStrings(arr1,4),exp1);
        t.checkExpect(longStrings(arr2,5),exp2);
        t.checkExpect(longStrings(arr3,14),exp3);
        t.checkExpect(longStrings(arr4,1),exp4);
    }
}
