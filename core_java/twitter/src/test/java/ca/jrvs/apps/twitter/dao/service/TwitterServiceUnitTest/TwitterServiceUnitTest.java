package ca.jrvs.apps.twitter.dao.service.TwitterServiceUnitTest;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() {
    // when(dao.create(any())).thenReturn(new Tweet());
    service.postTweet(Tweet.tweetBuild("text", 50.0, 0.0));
  }

  @Test
  public void showTweet() {
    when(dao.findById(anyString())).thenReturn(new Tweet());
    service.showTweet(Tweet.tweetBuild("text", 50.0, 0.0).getIdStr(), null);
  }

  @Test
  public void deleteTest() {
    when(dao.deleteById(anyString())).thenReturn(new Tweet());
    String[] ids = {"1", "2", "3"};
    List<Tweet> list = service.deleteTweets(ids);
    assertNotNull(list);
  }
}


