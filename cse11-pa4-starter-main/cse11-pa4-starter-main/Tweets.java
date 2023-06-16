import tester.*;

interface Tweet{
    public boolean isStartOfThreadBy(String author);
    public int totalLikes();
    public String unrollThread();
}

class TextTweet implements Tweet{
    String contents;
    int likes;
    String author;

    TextTweet(String c,int l,String a){
        contents = c;
        likes = l;
        author = a;
    }

    public boolean isStartOfThreadBy(String author){
        return author.equals(this.author);
    }
    public int totalLikes(){
        return likes;
    }
    public String unrollThread(){
        return author+"\n"+Integer.toString(likes)+" likes\n"+contents+"\n";
    }

}

class ReplyTweet implements Tweet{
    String contents;
    int likes;
    String author;
    Tweet replyTo;

    ReplyTweet(String c,int l,String a,Tweet r){
        contents = c;
        likes = l;
        author = a;
        replyTo = r;
    }

    public boolean isStartOfThreadBy(String author){
        return author.equals(this.author) && replyTo.isStartOfThreadBy(author);
    }
    public int totalLikes(){
        return this.likes + replyTo.totalLikes();
    }
    public String unrollThread(){
        return replyTo.unrollThread()+author+"\n"+Integer.toString(likes)+" likes\n"+contents+"\n";
    }
}

class Tweets{
    TextTweet t1 = new TextTweet("Just released my java tutorial", 45, "bingboom");
    ReplyTweet t2 = new ReplyTweet("the tutorial is for beginners btw", 34, "bingboom", t1);
    boolean a1 = t1.isStartOfThreadBy("broman");
    int a2 = t1.totalLikes();
    String a3 = t1.unrollThread();
    boolean a4 = t2.isStartOfThreadBy("bingboom");
    int a5 = t2.totalLikes();
    String a6 = t2.unrollThread();
}