package ca.jrvs.apps.twitter.controller;


import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.util.StringUtils;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  //@Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  /**
   * Parse user argument and post a tweet by calling service classes.
   *
   * @param args user inputs
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {

    if (args.length != 3) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    String tweetText = args[1];
    String geoTag = args[2];
    String[] coord = geoTag.split(COORD_SEP);
    if (coord.length != 2 || StringUtils.isEmpty(tweetText)) {
      throw new IllegalArgumentException(
          "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    Double lat = null;
    Double lon = null;
    try {
      lat = Double.parseDouble(coord[0]);
      lon = Double.parseDouble(coord[1]);
    } catch (Exception ex) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"", ex);
    }

    Tweet postTweet = Tweet.tweetBuild(tweetText, lon, lat);
    return service.postTweet(postTweet);
  }

  /**
   * Parse user argument and search a tweet by calling service classes.
   *
   * @param args user inputs
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {

    if (args.length != 3 && args.length != 2) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp show tweet_id [field1,fields2]");
    }

    String tweetId = args[1];
    String fields = null;
    String[] fieldArray = null;
    if (args.length == 3) {
      fields = args[2];
      fieldArray = fields.split(COMMA);
    }
    if (StringUtils.isEmpty(tweetId)) {
      throw new IllegalArgumentException("\"USAGE: TwitterCLIApp show tweet_id [field1,fields2]");
    }

    return service.showTweet(tweetId, fieldArray);
  }

  /**
   * Parse user argument and delete tweets by calling service classes.
   *
   * @param args user inputs
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {

    if (args.length != 2) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp delete [id1,id2,..]");
    }

    String ids = args[1];
    String[] tweetId = ids.split(COMMA);

    if (StringUtils.isEmpty(ids)) {
      throw new IllegalArgumentException("\"USAGE: TwitterCLIApp delete [id1,id2,..]");
    }

    return service.deleteTweets(tweetId);
  }
}