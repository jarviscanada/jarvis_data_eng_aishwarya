package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})
public class Entities {

  @JsonProperty("hashtags")
  private List<Hashtag> hashtag;
  @JsonProperty("user_mentions")
  private List<UserMention> userMention;
  @JsonIgnore
  private String tweet;

  @JsonProperty("hashtags")
  public List<Hashtag> getHashtag() {
    return hashtag;
  }

  //private List<Hashtag> getHashtagList() {
  // Pattern pattern = Pattern.compile("(#\\w+)\\b", Pattern.CASE_INSENSITIVE);
  // Matcher matcher = pattern.matcher(getTweet());
  // this.hashtag = new ArrayList<>();
  // while (matcher.find()) {
  //  Hashtag hashtag = new Hashtag();
  //  List<Integer> index = new ArrayList<>();
  //  index.add(this.tweet.indexOf(matcher.group(1)));
  //  index.add((index.get(0) + matcher.group(1).length()) - 1);
  //  hashtag.setIndices(index);
  // hashtag.setText(matcher.group(1).substring(1));
  // this.hashtag.add(hashtag);
  // }
  // return this.hashtag;
  //}

  @JsonProperty("hashtags")
  public void setHashtag(List<Hashtag> hashtag) {
    this.hashtag = hashtag;
  }

  @JsonProperty("user_mentions")
  public List<UserMention> getUserMention() {
    return userMention;
  }

  //public List<UserMention> getUserMentionList() {
  //  Pattern pattern = Pattern.compile("(@\\w+)\\b", Pattern.CASE_INSENSITIVE);
  //  Matcher matcher = pattern.matcher(this.tweet);
  //  this.userMention=new ArrayList<>();
  //  while (matcher.find()) {
  //   UserMention userMention = new UserMention();
  //   List<Integer> index = new ArrayList<>();
  //   index.add(this.tweet.indexOf(matcher.group(1)));
  //   index.add((index.get(0) + matcher.group(1).length()));
  //  userMention.setIndices(index);
  //  userMention.setScreenName(matcher.group(1).substring(1));
  //  this.userMention.add(userMention);
  //}
  //return this.userMention;
  //}

  @JsonProperty("user_mentions")
  public void setUserMention(List<UserMention> userMention) {
    this.userMention = userMention;
  }

  public String getTweet() {
    return tweet;
  }

  public void setTweet(String tweet) {
    this.tweet = tweet;
  }

}