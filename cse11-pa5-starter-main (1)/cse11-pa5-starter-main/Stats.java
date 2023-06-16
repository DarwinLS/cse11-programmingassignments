public class Stats {
    public static void main(String[] args){
        String option = args[0];
            if (option.equals("--product")){
                double product = 1;
                for (int i = 1; i < args.length; i++){
                    product *= Double.parseDouble(args[i]);
                }
                System.out.println(Double.toString(product));
            }
            else if (option.equals("--mean")){
                double mean = 0;
                for (int i = 1; i < args.length; i++){
                    mean += Double.parseDouble(args[i]);
                }
                mean /= args.length-1;
                System.out.println(Double.toString(mean));
            }
            else if (option.equals("--total")){
                double total = 0;
                for (int i = 1; i < args.length; i++){
                    total += Double.parseDouble(args[i]);
                }
                System.out.println(Double.toString(total));
            }
            else if (option.equals("--max")){
                double max = Double.parseDouble(args[1]);
                for (int i = 2; i < args.length; i++){
                    if (Double.parseDouble(args[i]) > max){max = Double.parseDouble(args[i]);}
                }
                System.out.println(Double.toString(max));
            }
            else if (option.equals("--min")){
                double min = Double.parseDouble(args[1]);
                for (int i = 2; i < args.length; i++){
                    if (Double.parseDouble(args[i]) < min){min = Double.parseDouble(args[i]);}
                }
                System.out.println(Double.toString(min));
            }
        else{
            System.out.println("Bad option " + args[0]);
        }
    }
}
