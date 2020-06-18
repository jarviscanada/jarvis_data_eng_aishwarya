package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "indices"
})
public class Hashtag {

  @JsonProperty("indices")
  private List<Integer> indices;
  @JsonProperty("text")
  private String text;
  private String tweet;

  @JsonProperty("indices")
  public List<Integer> getIndices() {
    return indices;
  }

  @JsonProperty("indices")
  public void setIndices(List<Integer> indices) {
    this.indices = indices;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  public String getTweet() {
    return tweet;
  }

  public void setTweet(String tweet) {
    this.tweet = tweet;
  }

}