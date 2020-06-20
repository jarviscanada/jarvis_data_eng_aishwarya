package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterHttpHelperTest {
  final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);

  private String consumerKey;
  private String consumerSecret;
  private String accessToken;
  private String tokenSecret;
  HttpHelper httpHelper;

  @Before
  public void setUp() {
    consumerKey = System.getenv("consumerKey");
    consumerSecret= System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    this.httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
  }

  @Test
  public void httpPost() throws Exception {
    HttpResponse response = httpHelper.httpPost(new URI(
        "https://api.twitter.com/1.1/statuses/update.json?status=Hello_tweet_test_2"));
    logger.info(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws Exception {
    HttpResponse response = httpHelper.httpGet(
        new URI("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=AParackal"));
    logger.info(EntityUtils.toString(response.getEntity()));
  }
}