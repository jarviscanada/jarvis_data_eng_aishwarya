package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hashtag {

  private int[] indices = new int[2];
  private String text;
  private String tweet;

  public Hashtag(String tweet) {
    this.tweet=tweet;
  }

  /**
   * Used to identify the #hashtags in the post/tweets.
   */
  public List<Hashtag> getHashTagsComponents() {
    Pattern pattern = Pattern.compile("(#\\w+)\\b", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(this.tweet);
    List<Hashtag> hashtags = new ArrayList<>();
    while (matcher.find()) {
      Hashtag hashtag = new Hashtag(tweet);
      hashtag.indices[0] = this.tweet.indexOf(matcher.group(1));
      hashtag.indices[1] = (hashtag.indices[0] + matcher.group(1).length()) - 1;
      hashtag.text = matcher.group(1).substring(1);
      hashtags.add(hashtag);
    }
    return hashtags;
  }

  public int[] getIndices() {
      return indices;
  }

  public String getText() {
      return text;
  }

  public void setText(String text) {
    this.text = text;
  }

//  @Override
//  public String toString() {
//    return "\n\t\t\t{\n"
//        + "\t\t\t\t\t\"text\":\"" + getText()
//        + "\",\n\t\t\t\t\t\"indices\":" + getIndices()
//        + "\n\t\t\t}";
//  }

//  public static void main(String[] args) {
//    String tweet = "My #Hello and #Aish";
//    Hashtag hashtag = new Hashtag(tweet);
//    System.out.println(hashtag.getHashTagsComponents());
//  }
}