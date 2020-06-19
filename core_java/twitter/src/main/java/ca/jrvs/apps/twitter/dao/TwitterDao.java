package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  /**
   * Getting post URI.
   *
   * @param tweet Tweet entity
   * @return
   * @throws URISyntaxException
   * @throws UnsupportedEncodingException
   */
  public URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    return new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper
        .escape(tweet.getText()) + AMPERSAND + "lat" + EQUAL + tweet.getCoordinates()
        .getCoordinates().get(1) + AMPERSAND + "long" + EQUAL + tweet.getCoordinates()
        .getCoordinates().get(0));
  }

  public URI getTweetUri(String id) throws URISyntaxException, UnsupportedEncodingException {
    return new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
  }

  public URI getDeleteUri(String id) throws URISyntaxException, UnsupportedEncodingException {
    return new URI(API_BASE_URI + DELETE_PATH + id + ".json");
  }

  /**
   * Create an entity(Tweet) to the underlying storage.
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    //Construct URI
    URI uri;
    try {
      uri = getPostUri(entity);
    } catch (URISyntaxException | UnsupportedEncodingException ex) {
      throw new IllegalArgumentException("Invalid tweet input", ex);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and deser response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Check response status code Convert Response Entity to Tweet.
   */
  public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException ex) {
        System.out.println("Response has no entity");
        // throw new RuntimeException("Response has no entity",ex);
      }
      throw new RuntimeException("Unexpected HTTP status:" + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    //Convert Response Entity to string
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException ex) {
      throw new RuntimeException("Failed to convert entity to String", ex);
    }

    //Deser JSON string to Tweet object
    try {
      tweet = JsonParser.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException ex) {
      throw new RuntimeException("Failed to convert JSON str to object", ex);
    }
    return tweet;
  }

  /**
   * Find an entity(Tweet) by its id.
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {
    //Construct URI
    URI uri;
    try {
      uri = getTweetUri(s);
    } catch (URISyntaxException | UnsupportedEncodingException ex) {
      throw new IllegalArgumentException("Invalid tweet input", ex);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpGet(uri);

    //Validate response and deser response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Delete an entity(Tweet) by its ID.
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    //Construct URI
    URI uri;
    try {
      uri = getDeleteUri(s);
    } catch (URISyntaxException | UnsupportedEncodingException ex) {
      throw new IllegalArgumentException("Invalid tweet input", ex);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and deser response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }
}
