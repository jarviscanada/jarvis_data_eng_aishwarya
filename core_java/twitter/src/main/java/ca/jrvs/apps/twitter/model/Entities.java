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

  @JsonProperty("hashtags")
  public List<Hashtag> getHashtag() {
    return hashtag;
  }

  @JsonProperty("hashtags")
  public void setHashtag(List<Hashtag> hashtag) {
    this.hashtag = hashtag;
  }

  @JsonProperty("user_mentions")
  public List<UserMention> getUserMention() {
    return userMention;
  }

  @JsonProperty("user_mentions")
  public void setUserMention(List<UserMention> userMention) {
    this.userMention = userMention;
  }
}