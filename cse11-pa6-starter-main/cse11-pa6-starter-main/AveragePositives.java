public class AveragePositives {
    public static void main(String[] args){
        double mean = 0;
        double count = 0;
        for (String x : args){
            if (Double.parseDouble(x) > 0){
                mean += Double.parseDouble(x);
                count += 1;
            }
        }
        if (count == 0){System.out.println(0);}
        else{
            mean /= count;
            System.out.println(mean);
        }
    }
}
