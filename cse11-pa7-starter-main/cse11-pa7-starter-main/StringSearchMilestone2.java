import java.nio.file.*;
import java.util.concurrent.FutureTask;
import java.io.File;
import java.io.IOException;
class FileHelper {
    static String[] getLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
        }
        catch(IOException e) {
            System.err.println("Error reading file " + path + ": " + e);
            return new String[]{"Error reading file " + path + ": " + e};
        }
    }
}

class ContainsQuery{
    String content;

    ContainsQuery(String s){
        content = s;
    }

    boolean matches(String s){
        return s.contains(content);
    }

}

class StringSearch{
    public static void main(String[] args) throws IOException{
        if (args.length == 1){
            String[] fileContents = FileHelper.getLines(args[0]);
            for (String x : fileContents){System.out.println(x);}
        }
        if (args.length == 2){
            String[] fileContents = FileHelper.getLines(args[0]);

            if (args[1].startsWith("not")){
                String[] queryData1 = args[1].split("(");
                String[] queryData2 = queryData1[1].split("=");
                String query = queryData2[1].substring(1,queryData2[1].length()-2);
                ContainsQuery contQuery = new ContainsQuery(query);
                if (queryData2[0].equals("length")){
                    for (String x : fileContents){
                        if (x.length() != Integer.parseInt(query)){System.out.println(x);}
                    }
                }
                if (queryData2[0].equals("greater")){
                    for (String x : fileContents){
                        if (x.length() <= Integer.parseInt(query)){System.out.println(x);}
                    }
                }
                if (queryData2[0].equals("less")){
                    for (String x : fileContents){
                        if (x.length() >= Integer.parseInt(query)){System.out.println(x);}
                    }
                }
                if (queryData2[0].equals("contains")){
                    for (String x : fileContents){
                        if (contQuery.matches(x) == false){System.out.println(x);}
                    }
                }
                if (queryData2[0].equals("starts")){
                    for (String x : fileContents){
                        if (x.startsWith(query) == false){System.out.println(x);}
                    }
                }
                if (queryData2[0].equals("ends")){
                    for (String x : fileContents){
                        if (x.endsWith(query) == false){System.out.println(x);}
                    }
                }
            }
            else{
                String[] queryData = args[1].split("=");
                String query = queryData[1].substring(1,queryData[1].length()-1);
                ContainsQuery contQuery = new ContainsQuery(query);

                if (queryData[0].equals("length")){
                    for (String x : fileContents){
                        if (x.length() == Integer.parseInt(query)){System.out.println(x);}
                    }
                }
                if (queryData[0].equals("greater")){
                    for (String x : fileContents){
                        if (x.length() > Integer.parseInt(query)){System.out.println(x);}
                    }
                }
                if (queryData[0].equals("less")){
                    for (String x : fileContents){
                        if (x.length() < Integer.parseInt(query)){System.out.println(x);}
                    }
                }
                if (queryData[0].equals("contains")){
                    for (String x : fileContents){
                        if (contQuery.matches(x)){System.out.println(x);}
                    }
                }
                if (queryData[0].equals("starts")){
                    for (String x : fileContents){
                        if (x.startsWith(query)){System.out.println(x);}
                    }
                }
                if (queryData[0].equals("ends")){
                    for (String x : fileContents){
                        if (x.endsWith(query)){System.out.println(x);}
                    }
                }
            }
        }
    }
}
