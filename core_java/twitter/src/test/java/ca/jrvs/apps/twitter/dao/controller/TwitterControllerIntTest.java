package ca.jrvs.apps.twitter.dao.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.controller.TwitterController;
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

public class TwitterControllerIntTest {

  TwitterController controller;

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
    TwitterService twitterService = new TwitterService(twitterDao);
    this.controller = new TwitterController(twitterService);
  }

  @Test
  public void postTweet() throws Exception {

    String[] userInputs = {"post", "Hey controller testing", "50.0:1.0"};
    Tweet tweet = controller.postTweet(userInputs);
    System.out.println(JsonParser.toJson(tweet, true, false));
  }

  @Test
  public void showTweet() throws Exception {

    String[] userInputs = {"post", "Get controller 4", "50.0:1.0"};
    Tweet tweet = controller.postTweet(userInputs);
    System.out.println(JsonParser.toJson(tweet, true, false));
    String tweet_id = tweet.getIdStr();
    String[] user_showinputs = {"show", tweet_id};
    Tweet showTweet = controller.showTweet(user_showinputs);
    System.out.println(JsonParser.toJson(showTweet, true, false));
  }

  @Test
  public void deleteTweet() {
    List<String> ids = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      String[] userInputs = {"post", "bye controller" + String.valueOf(i + 10), "50.0:1.0"};
      Tweet tweet = controller.postTweet(userInputs);
      ids.add(tweet.getIdStr());
    }
    String[] user_deleteinputs = {"delete", String.join(",", ids)};
    List<Tweet> delTweet = controller.deleteTweet(user_deleteinputs);
    assertEquals(3, delTweet.size());
    delTweet.forEach(tweet -> {
      try {
        System.out.println(JsonParser.toJson(tweet, true, false));
      } catch (Exception ex) {
        throw new RuntimeException("Conversion to Json error", ex);
      }
    });
  }
}