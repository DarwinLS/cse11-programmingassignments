class User{
    private String uName;
    private String fName;
    private int flwrs;
    public User(String u, String fn, int fo){
        uName = u;
        fName = fn;
        flwrs = fo;
    }

    public String toText(){
        return fName + " @" + uName;
    }
}
class Tweet{
    private String text;
    private User usercreated;
    private int likes;
    private String ID;
    public Tweet(String t, User u, int l, String i){
        text = t;
        usercreated = u;
        likes = l;
        ID = i;
    }
    
    public boolean longerThan(Tweet other){
        if (this.text.length() > other.text.length()){
            return true;
        }
        return false;
    }
    
    public boolean moreLikes(Tweet other){
        if (this.likes < other.likes){
            return false;
        }
        return true;
    }

    public String toText(){
        return usercreated.toText() + " : " + text + " : " + String.valueOf(likes) + " Likes";
    }

    public String toLink(){
        return "https://twitter.com/u1/status/" + ID;
    }
}
class ExampleTweets{
    User acm = new User("acmucsd","ACM @ UCSD",54);
    Tweet t1 = new Tweet("Meet us at the Gilman Dr & Myers Dr bus stop at 5pm! We'll be shuttling over at 5:15 and 5:30 to join us for ACM Bit-Byte ice skating! Map included in second image :) https://instagram.com/p/CW1yEpCpNNh/?utm_medium=twitter",acm,2,"1465121704603271168?cxt=HHwWgIC-ubCblNUoAAAA");
    Tweet t2 = new Tweet("Happy Thanksgiving from us here at ACM!  What are you thankful for this Thanksgiving?",acm,2,"1463935676064296961?cxt=HHwWgsCrnbfv-NAoAAAA");
    User cas = new User("cassidoo","Cassidy",185200);
    Tweet t3 = new Tweet("I'd quote tweet this and say 'please subscribe' hahahahahahahaha () hahahahaha  https://cassidoo.co/newsletter/",cas,25,"1617653552046043140?cxt=HHwWiMDTtbfVh_MsAAAA");
    User kp = new User("kprather88","Kimberly Prather, Ph.D.",85900);
    Tweet t4 = new Tweet("You have to actively work to avoid the use of the word 'airborne'-this has to be intentional. Why the heck would a public health agency (@WHO) that is supposed to be protecting public health go out of their way to avoid saying the word that would help save lives?",kp,105,"1616487317543473157?cxt=HHwWisDSodOp9e4sAAAA");

    String answer1 = acm.toString();
    String answer2 = cas.toString();
    boolean answer3 = t1.longerThan(t2);
    boolean answer4 = t2.longerThan(t3);
    boolean answer5 = t3.moreLikes(t1);
    boolean answer6 = t2.moreLikes(t4);
    String answer7 = t4.toText();
    String answer8 = t1.toText();
    String answer9 = t2.toLink();
    String answer10 = t3.toLink();

}
