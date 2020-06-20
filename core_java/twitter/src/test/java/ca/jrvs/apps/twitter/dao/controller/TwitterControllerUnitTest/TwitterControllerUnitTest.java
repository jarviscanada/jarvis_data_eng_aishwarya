package ca.jrvs.apps.twitter.dao.controller.TwitterControllerUnitTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void postTweet(){
    when(service.postTweet(any())).thenReturn(new Tweet());
    String[] userInputs = {"post", "Hey controller testing with Unit test", "50.0:1.0"};
    controller.postTweet(userInputs);
  }

  @Test
  public void showTweet(){
    when(service.showTweet(anyString(),any())).thenReturn(new Tweet());
    String[] user_showinputs = {"show", "123"};
    controller.showTweet(user_showinputs);
  }

  @Test
  public void deleteTest(){
    when(service.deleteTweets(any())).thenReturn(new ArrayList<Tweet>());
    String[] user_deleteinputs = {"delete", "1,2,3"};
    controller.deleteTweet(user_deleteinputs);
  }

}
