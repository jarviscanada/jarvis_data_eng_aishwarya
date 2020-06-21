package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TwitterService implements Service {

  private CrdDao dao;

  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  /**
   * Validate and post a user input Tweet.
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    //Business logic: tweet exceeds 140 characters or lat/lon out of range
    //e.g. text length, lat/lon range, id format

    try {
      validatePostTweet(tweet);
    } catch (IllegalArgumentException ex) {
      throw new RuntimeException("Invalid input", ex);
    }
    //create tweet via dao
    return (Tweet) dao.create(tweet);
  }

  private void validatePostTweet(Tweet tweet) throws IllegalArgumentException {
    if (tweet.getText().length() > 140) {
      throw new IllegalArgumentException("More than 140 characters");
    }
    if ((tweet.getCoordinates().getCoordinates().get(0) < -90d
        && tweet.getCoordinates().getCoordinates().get(0) > 90d) || (
        tweet.getCoordinates().getCoordinates().get(1) < -180d
            && tweet.getCoordinates().getCoordinates().get(1) > 180d)) {
      throw new IllegalArgumentException("lon/lat out of range");
    }
  }

  /**
   * Search a tweet by ID.
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    String[] top_fields = {"created_at", "id", "id_str", "text", "entities", "coordinates", "retweet_count", "favorite_count", "favorite", "retweeted"};
    try {
      Tweet tweet = (Tweet) dao.findById(id);
      for(String field:top_fields){
        if(!Arrays.asList(fields).contains(field)){
          switch (field){
            case "created_at":
              tweet.setCreateAt(null);
              break;
            case "id":
              tweet.setId(null);
              break;
            case "id_str":
              tweet.setIdStr(null);
              break;
            case "text":
              tweet.setText(null);
              break;
            case "entities":
              tweet.setEntities(null);
              break;
            case "coordinates":
              tweet.setCoordinates(null);
              break;
            case "retweet_count":
              tweet.setRetweetCount(null);
              break;
            case "favorite_count":
              tweet.setFavoriteCount(null);
              break;
            case "favorite":
              tweet.setFavorited(null);
              break;
            case "retweeted":
              tweet.setRetweeted(null);
              break;
          }

        }
      }
      return tweet;
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("id or fields param in valid", ex);
    }
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweetList = new ArrayList<>();
    Stream.of(ids).forEach(id -> {
      try {
        tweetList.add((Tweet) dao.deleteById(id));
      } catch (IllegalArgumentException ex) {
        throw new IllegalArgumentException("Invalid ids", ex);
      }
    });
    return tweetList;
  }
}