class TextTweet{
    String contents;
    int likes;

    TextTweet(String c, int l){
        contents = c;
        likes = l;
    }

    boolean hasMention(String username){
        String usern = "@" + username;
        if ((this.contents.indexOf(usern) != -1)){
            if ((this.contents.indexOf(usern) + usern.length() + 1) > this.contents.length()){
                return true;
            }
            String charafter = this.contents.substring(this.contents.indexOf(usern) + usern.length(),this.contents.indexOf(usern) + usern.length() + 1);
            if (charafter.equals(" ") || charafter.equals(".")){
                return true;
            }
        }
        return false;
    }

    boolean hasLike(){
        if (this.likes >= 1){
            return true;
        }
        return false;
    }

    String firstMention(){
        int indOfAt = this.contents.indexOf("@");
        if (indOfAt != -1){
            int indOfSpace = this.contents.indexOf(" ",indOfAt);
            if (indOfSpace != -1){
                return this.contents.substring(indOfAt+1,indOfSpace);
            }
        }
        return "";    
    }

}
class ReplyTweet{
    TextTweet replyTo;
    String contents;
    int likes;

    ReplyTweet(TextTweet r, String c, int l){
        replyTo = r;
        contents = c;
        likes = l;
    }

    boolean morePopularReply(){
        if (this.likes > replyTo.likes){
            return true;
        }
        return false;
    }

    int allLikes(){
        return this.likes + replyTo.likes;
    }

    boolean hasMention(String username){
        if (replyTo.hasMention(username)){
            return true;
        }
        String usern = "@" + username;
        if ((this.contents.indexOf(usern) != -1)){
            if ((this.contents.indexOf(usern) + usern.length() + 1) > this.contents.length()){
                return true;
            }
            String charafter = this.contents.substring(this.contents.indexOf(usern) + usern.length(),this.contents.indexOf(usern) + usern.length() + 1);
            if (charafter.equals(" ") || charafter.equals(".")){
                return true;
            }
        }
        return false;
    }

}

class Drill3{
    TextTweet example1 = new TextTweet("Wow that is so crazy wow @bro",10);
    TextTweet example2 = new TextTweet("Wow @bro that is so crazy",0);
    TextTweet example3 = new TextTweet("Wow man @bro3 that is crazy",14);
    boolean answer1 = example1.hasMention("bro");
    boolean answer2 = example2.hasMention("bro");
    boolean answer3 = example3.hasMention("bro");
    boolean answer4 = example1.hasLike();
    boolean answer5 = example2.hasLike();
    String answer6 = example1.firstMention();
    String answer7 = example3.firstMention();
    ReplyTweet example4 = new ReplyTweet(example1,"bruh you troll",3);
    ReplyTweet example5 = new ReplyTweet(example3,"fam @bro why man",27);
    boolean answer8 = example4.morePopularReply();
    boolean answer9 = example5.morePopularReply();
    int answer10 = example4.allLikes();
    boolean answer11 = example5.hasMention("bro");
    
}