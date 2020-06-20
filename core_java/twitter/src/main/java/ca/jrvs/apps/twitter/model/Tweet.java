package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorite",
    "retweeted"
})
public class Tweet {

  @JsonProperty("created_at")
  private String createAt;
  @JsonProperty("id")
  private long id;
  @JsonProperty("id_str")
  private String idStr;
  @JsonProperty("text")
  private String text;
  @JsonProperty("entities")
  private Entities entities;
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("retweet_count")
  private int retweetCount;
  @JsonProperty("favorite_count")
  private Integer favoriteCount;
  @JsonProperty("favorited")
  private Boolean favorited;
  @JsonProperty("retweeted")
  private Boolean retweeted;

  private double longitude;
  private double latitude;

  /**
   * Used to build a tweet from given parameters.
   * @param text tweet to be posted
   * @param lon longitude
   * @param lat latittude
   * @return
   */
  public static Tweet tweetBuild(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    List<Double> coord = new ArrayList<>();
    coord.add(0, lon);
    coord.add(1, lat);
    tweet.setText(text);
    tweet.coordinates = new Coordinates();
    tweet.coordinates.setCoordinates(coord);
    tweet.entities = new Entities();
    tweet.entities.setTweet(text);
    System.out.println(tweet);
    return tweet;
  }

  @JsonProperty("created_at")
  public String getCreateAt() {
    return createAt;
  }

  @JsonProperty("created_at")
  public void setCreateAt(String createAt) {
    this.createAt = createAt;
  }

  @JsonProperty("id")
  public long getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(long id) {
    this.id = id;
  }

  @JsonProperty("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("id_str")
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("entities")
  public Entities getEntities() {
    return entities;
  }

  @JsonProperty("entities")
  public void setEntities(Entities entities) {
    this.entities = entities;
    this.entities.setTweet(this.getText());
  }

  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonProperty("retweet_count")
  public int getRetweetCount() {
    return retweetCount;
  }

  @JsonProperty("retweet_count")
  public void setRetweetCount(int retweetCount) {
    this.retweetCount = retweetCount;
  }

  @JsonProperty("favorite_count")
  public Integer getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favorite_count")
  public void setFavoriteCount(Integer favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  @JsonProperty("favorited")
  public Boolean getFavorited() {
    return favorited;
  }

  @JsonProperty("favorited")
  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  @JsonProperty("retweeted")
  public Boolean getRetweeted() {
    return retweeted;
  }

  @JsonProperty("retweeted")
  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }

}