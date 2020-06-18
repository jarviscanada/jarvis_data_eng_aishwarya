package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao twitterDao;

  @Before
  public void setup() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    //set up dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    this.twitterDao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() throws Exception {
    String hashTag = "#abcd";
    String text = "@someone some text " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = Tweet.tweetBuild(text, lon, lat);
    System.out.println(JsonParser.toJson(postTweet, true, false));

    Tweet tweet = twitterDao.create(postTweet);
    System.out.println(JsonParser.toJson(tweet, true, false));
    // assertEquals(text,tweet.getText());
    //assertNotNull(tweet.getCoordinates());
    //assertEquals(2,tweet.getCoordinates().getCoordinates().size());
    //assertEquals(lon,tweet.getCoordinates().getCoordinates().get(0));
    //assertEquals(lat,tweet.getCoordinates().getCoordinates().get(1));
    // assertTrue(hashTag.contains(tweet.getEntities().getHashtag().get(0).getText()));
  }

  @Test
  public void findById() throws Exception {
    String hashTag = "#abcd";
    String text = "New test to find" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = Tweet.tweetBuild(text, lon, lat);
    Tweet tweet = twitterDao.create(postTweet);
    Tweet getTweet = twitterDao.findById(tweet.getIdStr());
    System.out.println(JsonParser.toJson(getTweet, true, false));
  }


  @Test
  public void deleteById() throws Exception {
    String hashTag = "#abcd";
    String text = "Last test to be deleted" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = Tweet.tweetBuild(text, lon, lat);
    Tweet tweet = twitterDao.create(postTweet);
    Tweet getTweet = twitterDao.deleteById(tweet.getIdStr());
    System.out.println(JsonParser.toJson(getTweet, true, false));
  }
}