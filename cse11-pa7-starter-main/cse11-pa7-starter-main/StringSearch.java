import java.nio.file.*;

import javax.swing.TransferHandler;

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
    String transform(String line);
}

class upperTransform implements Transform{
    public String transform(String line){
        return line.toUpperCase();
    }
}

class lowerTransform implements Transform{
    public String transform(String line){
        return line.toLowerCase();
    }
}

class firstTransform implements Transform{
    String content;
    
    firstTransform(String s){
        content = s;
    }
    public String transform(String line){
        if (line.length() < Integer.parseInt(content)){
            return line;
        }
        return line.substring(0,Integer.parseInt(content));
    }
}

class lastTransform implements Transform{
    String content;

    lastTransform(String s){
        content = s;
    }
    public String transform(String line){
        if (line.length() < Integer.parseInt(content)){
            return line;
        }
        return line.substring(line.length()-Integer.parseInt(content));
    }
}

class replaceTransform implements Transform{
    String content1;
    String content2;
    
    replaceTransform(String first, String second){
        content1 = first;
        content2 = second;
    }
    public String transform(String line){
        return line.replaceAll(content1,content2);
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

    static Transform readTransform(String t){
        String[] tData1 = t.split("=");
        if (tData1[0].equals("upper")){
            return new upperTransform();
        }
        if (tData1[0].equals("lower")){
            return new lowerTransform();
        }
        if (tData1[0].equals("first")){
            return new firstTransform(tData1[1]);
        }
        if (tData1[0].equals("last")){
            return new lastTransform(tData1[1]);
        }
        String[] tData2 = tData1[1].split(";");
        String[] tData3 = new String[tData2.length];
        for (int i = 0; i < tData2.length; i++){
            tData3[i] = tData2[i].substring(1,tData2[i].length()-1);
        }
        return new replaceTransform(tData3[0],tData3[1]);

    }
    
    static boolean matchesAll(Query[] qs, String s){
        boolean include = true;
        for (Query x : qs){
            if (x.matches(s) == false){include = false;}
        }
        return include;
    }

    static String applyAll(Transform[] ts, String s){
        String temp = s;
        for (Transform x : ts){
            temp = x.transform(temp);
        }
        return temp;
    }

    public static void main(String[] args) throws IOException{
        if (args.length == 1){
            String[] fileContents = FileHelper.getLines(args[0]);
            for (String x : fileContents){System.out.println(x);}
        }
        if (args.length == 2){
            String[] fileContents = FileHelper.getLines(args[0]);
            String[] queries = args[1].split("&");
            Query[] queries2 = new Query[queries.length];
            for (int i = 0; i < queries.length; i++){
                queries2[i] = readQuery(queries[i]);
            }
            for (String x : fileContents){
                if (matchesAll(queries2, x)){
                    System.out.println(x);
                }
            }
        }
        if (args.length == 3){
            String[] fileContents = FileHelper.getLines(args[0]);
            String[] queries = args[1].split("&");
            Query[] queries2 = new Query[queries.length];
            for (int i = 0; i < queries.length; i++){
                queries2[i] = readQuery(queries[i]);
            }
            String[] transforms = args[2].split("&");
            Transform[] transforms2 = new Transform[transforms.length];
            for (int i = 0; i < transforms.length; i++){
                transforms2[i] = readTransform(transforms[i]);
            }
            for (String x : fileContents){
                if (matchesAll(queries2, x)){
                    System.out.println(applyAll(transforms2,x));
                }
            }
        }
    }
}
