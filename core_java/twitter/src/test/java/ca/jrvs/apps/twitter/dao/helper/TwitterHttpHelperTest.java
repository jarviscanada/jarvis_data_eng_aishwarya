package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  private String consumerKey;
  private String consumerSecret;
  private String accessToken;
  private String tokenSecret;
  HttpHelper httpHelper;

  //@Before
  //public void setUp() {
  //  consumerKey = System.getenv("consumerKey");
  //  consumerSecret= System.getenv("consumerSecret");
  //  accessToken = System.getenv("accessToken");
  //  tokenSecret = System.getenv("tokenSecret");
  //  HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
  //      accessToken, tokenSecret);
  //}

  @Test
  public void httpPost() throws Exception {
    consumerKey = System.getenv("consumerKey");
    consumerSecret = System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    HttpResponse response = httpHelper.httpPost(new URI(
        "https://api.twitter.com/1.1/statuses/update.json?status=Hello_tweet_test_2"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws Exception {
    consumerKey = System.getenv("consumerKey");
    consumerSecret = System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    HttpResponse response = httpHelper.httpGet(
        new URI("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=AParackal"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}