import java.nio.file.*;
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

interface Query{
    boolean matches(String line);
}

class lengthQuery implements Query{
    String content;

    lengthQuery(String s){
        content = s;
    }
    public boolean matches(String line){
        if (line.length() == Integer.parseInt(content)){return true;}
        return false;
    }
}
class greaterQuery implements Query{
    String content;

    greaterQuery(String s){
        content = s;
    }
    public boolean matches(String line){
        if (line.length() > Integer.parseInt(content)){return true;}
        return false;
    }
}
class lessQuery implements Query{
    String content;

    lessQuery(String s){
        content = s;
    }
    public boolean matches(String line){
        if (line.length() < Integer.parseInt(content)){return true;}
        return false;
    }
}
class containsQuery implements Query{
    String content;

    containsQuery(String s){
        content = s;
    }
    public boolean matches(String line){
        if (line.contains(content)){return true;}
        return false;
    }
}
class startsQuery implements Query{
    String content;

    startsQuery(String s){
        content = s;
    }
    public boolean matches(String line){
        if (line.startsWith(content)){return true;}
        return false;
    }
}
class endsQuery implements Query{
    String content;

    endsQuery(String s){
        content = s;
    }
    public boolean matches(String line){
        if (line.endsWith(content)){return true;}
        return false;
    }
}
class notQuery implements Query{
    Query content;

    notQuery(Query q){
        content = q;
    }
    public boolean matches(String line){
        if (content.matches(line) == false){return true;}
        return false;
    }
}

interface Transform{
    String transform(String s);
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
    static Query readQuery(String q){
        if (q.startsWith("not")){
            String[] queryData2 = q.substring(4,q.length()-1).split("=");
            if (queryData2[0].equals("length")){
                return new notQuery(new lengthQuery(queryData2[1]));
            }
            if (queryData2[0].equals("greater")){
                return new notQuery(new greaterQuery(queryData2[1]));
            }
            if (queryData2[0].equals("less")){
                return new notQuery(new lessQuery(queryData2[1]));
            }
            String query = queryData2[1].substring(1,queryData2[1].length()-1);
            if (queryData2[0].equals("contains")){
                return new notQuery(new containsQuery(query));
            }
            if (queryData2[0].equals("starts")){
                return new notQuery(new startsQuery(query));
            }
            if (queryData2[0].equals("ends")){
                return new notQuery(new endsQuery(query));
            }
        }
        String[] queryData = q.split("=");
        if (queryData[0].equals("length")){
            return new lengthQuery(queryData[1]);
        }
        if (queryData[0].equals("greater")){
            return new greaterQuery(queryData[1]);
        }
        if (queryData[0].equals("less")){
            return new lessQuery(queryData[1]);
        }
        String query = queryData[1].substring(1,queryData[1].length()-1);
        if (queryData[0].equals("contains")){
            return new containsQuery(query);
        }
        if (queryData[0].equals("starts")){
            return new startsQuery(query);
        }
        return new endsQuery(query);
    }
    
    public static void main(String[] args) throws IOException{
        if (args.length == 1){
            String[] fileContents = FileHelper.getLines(args[0]);
            for (String x : fileContents){System.out.println(x);}
        }
        if (args.length == 2){
            boolean include;
            String[] fileContents = FileHelper.getLines(args[0]);
            String[] queries = args[1].split("&");
            for (String x : fileContents){
                include = true;
                for (String q : queries){
                    if (readQuery(q).matches(x) == false){include = false;}
                }
                if (include){System.out.println(x);}
            }
        }
    }
}
