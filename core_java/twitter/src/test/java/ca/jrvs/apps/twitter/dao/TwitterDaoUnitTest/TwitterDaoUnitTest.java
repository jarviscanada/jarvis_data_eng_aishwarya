package ca.jrvs.apps.twitter.dao.TwitterDaoUnitTest;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Test
  public void showTweet() throws Exception {
    //test failed request
    String hashTag = "#abcd";
    String text = "Last test to be deleted" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;

    //exception is expected here
    when(mockHelper.httpPost((isNotNull()))).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(Tweet.tweetBuild(text, lon, lat));
      fail();
    } catch (RuntimeException ex) {
      assertTrue(true);
    }

    //Test happy path
    //however, we don't want to call parseResponseBody.
    //we will make a spyDao which can fake parseResponseBody return value
    String tweetJsonStr = "{\n"
        + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "   \"id\":1097607853932564480,\n"
        + "   \"id_str\":\"1097607853932564480\",\n"
        + "   \"text\":\"test with loc223\",\n"
        + "   \"entities\":{\n"
        + "        \"hashtags\":[],\n"
        + "        \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinates\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorite_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(Tweet.tweetBuild(text, lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void postTweet() throws Exception {
    //test failed request
    String hashTag = "#abcd";
    String text = "Last test to be deleted" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;

    //exception is expected here
    when(mockHelper.httpPost((isNotNull()))).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(Tweet.tweetBuild(text, lon, lat));
      fail();
    } catch (RuntimeException ex) {
      assertTrue(true);
    }

    //Test happy path
    //however, we don't want to call parseResponseBody.
    //we will make a spyDao which can fake parseResponseBody return value
    String tweetJsonStr = "{\n"
        + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "   \"id\":1097607853932564480,\n"
        + "   \"id_str\":\"1097607853932564480\",\n"
        + "   \"text\":\"test with loc223\",\n"
        + "   \"entities\":{\n"
        + "        \"hashtags\":[],\n"
        + "        \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinates\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorite_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(Tweet.tweetBuild(text, lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void deleteTweet() throws Exception {
    //test failed request
    String hashTag = "#abcd";
    String text = "Last test to be deleted" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;

    //exception is expected here
    when(mockHelper.httpPost((isNotNull()))).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(Tweet.tweetBuild(text, lon, lat));
      fail();
    } catch (RuntimeException ex) {
      assertTrue(true);
    }

    //Test happy path
    //however, we don't want to call parseResponseBody.
    //we will make a spyDao which can fake parseResponseBody return value
    String tweetJsonStr = "{\n"
        + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "   \"id\":1097607853932564480,\n"
        + "   \"id_str\":\"1097607853932564480\",\n"
        + "   \"text\":\"test with loc223\",\n"
        + "   \"entities\":{\n"
        + "        \"hashtags\":[],\n"
        + "        \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinates\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorite_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(Tweet.tweetBuild(text, lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }
}
