package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entities {

  private Hashtag hashtag;
  private UserMention userMention;
  private String tweet;


  public Entities(String tweet) {
    this.hashtag = new Hashtag(tweet);
    this.userMention = new UserMention(tweet);
    this.tweet = tweet;
  }

  public List<Hashtag> getHashtag() {
    return this.hashtag.getHashTagsComponents();
  }

  public List<UserMention> getUserMention() {
    return this.userMention.userMentionComponents();
  }

//  @Override
//  public String toString() {
//    return "{\n"
//        + "\t\t\"hashtags\":" + getHashtag()
//        + ",\n\t\t\"user_mentions\":" + getUserMention()
//        + "\n\t}";
//  }

//  public static void main(String[] args) {
//    String tweet = "My dear @someone and @HappySigh. #Best #Coding";
//    Entities entities = new Entities(tweet);
//    System.out.println(entities);
//  }
}
