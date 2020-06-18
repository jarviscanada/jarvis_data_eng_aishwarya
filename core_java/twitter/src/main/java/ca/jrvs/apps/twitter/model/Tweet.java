package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "create_at",
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

//  public Tweet(String text, double longitude, double latitude) {
//    this.text = text;
//    this.longitude = longitude;
//    this.latitude = latitude;
//  }

  public static Tweet tweetBuild(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    tweet.setEntities(text);
    tweet.setCoordinates(lon,lat);
    return tweet;
  }

//  public void tweetBuild() {
//   // Tweet tweet = new Tweet(this.text,this.longitude,this.latitude);
//    this.setEntities(this.text);
//    this.setCoordinates(this.longitude,this.latitude);
//  }

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
  public void setEntities(String text) {
    this.entities = new Entities(text);
  }

  @JsonProperty("coordinates")
  public List<Coordinates> getCoordinates() {
    return this.coordinates.getCoordinatesComponents();
  }

  @JsonProperty("coordinates")
  public void setCoordinates(double longitude,double latitude) {
    this.coordinates = new Coordinates(longitude,latitude);
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

//  @Override
//  public String toString() {
//    return "{\n\t\"created_at\":\"" + getCreateAt()
//        + "\",\n\t\"id\":\"" + getId()
//        + "\",\n\t\"id_str\":\"" + getIdStr()
//        + "\",\n\t\"text\":\"" + getText()
//        + "\",\n\t\"entities\":" + getEntities()
//        + ",\n\t\"coordinates\":" + getCoordinates().get(0)
//        + ",\n\t\"retweet_count\":" + getRetweetCount()
//        + ",\n\t\"favorite_count\":" + getFavoriteCount()
//        + ",\n\t\"favorited\":" + getFavorited()
//        + ",\n\t\"retweeted\":" + getRetweeted()
//        + "\n}";
//  }

//  public static void main(String[] args) {
//    String tweet = "@someone and @holaother lets test it #code #test";
//    Tweet postTweet = new Tweet(tweet,1d,-1d);
//    postTweet.tweetBuild();
//    System.out.println(postTweet);
//  }
}
