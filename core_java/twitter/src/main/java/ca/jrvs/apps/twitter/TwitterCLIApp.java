package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TwitterCLIApp {

  public static final String USAGE = "USAGE: TwitterCLI post|show|delete [options]";

  private Controller controller;

  //@Autowired
  public TwitterCLIApp(Controller controller) {
    this.controller = controller;
  }

  /**
   * It parses args and calls the controller methods. It also prints tweet(s) returned by controller
   * methods.
   *
   * @param args user inputs
   */
  public void run(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException(USAGE);
    }
    switch (args[0].toLowerCase()) {
      case "post":
        printTweet(controller.postTweet(args));
        break;
      case "show":
        printTweet(controller.showTweet(args));
        break;
      case "delete":
        controller.deleteTweet(args).forEach(this::printTweet);
        break;
      default:
        throw new IllegalArgumentException(USAGE);
    }
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonParser.toJson(tweet, true, false));
    } catch (JsonProcessingException ex) {
      throw new RuntimeException("Unable to convert tweet object to string", ex);
    }
  }

  /**
   * Declare and instantiate all components. Call run method which calls controller methods and
   * print tweet(s).
   *
   * @param args user inputs
   */
  public static void main(String[] args) {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    CrdDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterController(service);
    TwitterCLIApp twitterCLIApp = new TwitterCLIApp(controller);

    twitterCLIApp.run(args);
  }
}