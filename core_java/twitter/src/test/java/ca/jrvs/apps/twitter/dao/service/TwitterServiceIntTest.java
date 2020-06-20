package ca.jrvs.apps.twitter.dao.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  private TwitterService twitterService;

  @Before
  public void setup() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    //set up dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    TwitterDao twitterDao = new TwitterDao(httpHelper);
    this.twitterService = new TwitterService(twitterDao);
  }

  @Test
  public void postTweet() throws Exception {
    String hashTag = "#abcd";
    String text = "@someone some text " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = Tweet.tweetBuild(text, lon, lat);
    Tweet tweet = twitterService.postTweet(postTweet);
    System.out.println(JsonParser.toJson(tweet, true, false));
  }

  @Test
  public void showTweet() throws Exception {
    String hashTag = "#abcd";
    String text = "@someone some text " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = Tweet.tweetBuild(text, lon, lat);
    Tweet tweet = twitterService.postTweet(postTweet);
    Tweet getTweet = twitterService.showTweet(tweet.getIdStr(), null);
    System.out.println(JsonParser.toJson(getTweet, true, false));
  }

  @Test
  public void deleteTweet() {
    List<String> ids = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      String hashTag = "#abcd";
      String text = "@someone some text " + hashTag + " " + System.currentTimeMillis();
      Double lat = 1d;
      Double lon = -1d;
      Tweet postTweet = Tweet.tweetBuild(text, lon, lat);
      Tweet tweet = twitterService.postTweet(postTweet);
      ids.add(tweet.getIdStr());
    }
    String[] id = new String[ids.size()];
    ids.toArray(id);
    List<Tweet> delTweet = twitterService.deleteTweets(id);
    delTweet.forEach(tweet -> {
      try {
        System.out.println(JsonParser.toJson(tweet, true, false));
      } catch (Exception ex) {
        throw new RuntimeException("Conversion to Json error", ex);
      }
    });
  }
}
