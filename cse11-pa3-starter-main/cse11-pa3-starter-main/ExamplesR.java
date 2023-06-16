class R{
    String field1;
    R field2;

    R(String f1,R f2){
        field1 = f1;
        field2 = f2;
    }
}

class ExamplesR{
    R obj1 = new R("sample string",R);
}
//1. I could not construct the R object because it requires another object of type R to be constructed, but none can be constructed since it needs one for construction.
//2. I could construct an example of a reply to a reply. The original tweet doesn't require another tweet as part of its construction, the reply tweet can be constructed using the original tweet in its parameters, and the reply to a reply would just need the first reply tweet as one of the parameters for construction.
//   The class and constructor would look like as follows:
/*
class ReplyToReplyTweet{
    ReplyTweet replyTo;
    String contents;
    int likes;

    ReplyToReplyTweet(ReplyTweet r, String c, int l){
        replyTo = r;
        contents = c;
        likes = l;
    }
}
*/