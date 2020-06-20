package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables.
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Constructor Setup dependencies using secrets.
   *
   * @param consumerKey    consumer key
   * @param consumerSecret consumer secret
   * @param accessToken    access token
   * @param tokenSecret    token secret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
  }

  //httpClient = new DefaultHttpClient();

  /**
   * Execute a HTTP Post call.
   *
   * @param uri uri that specifies the location of the post to be created
   * @return
   */
  @Override
  public HttpResponse httpPost(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.POST, uri, null);
    } catch (OAuthException | IOException ex) {
      throw new RuntimeException("Failed to execute", ex);
    }
  }

  /**
   * Execute a HTTP Get call.
   *
   * @param uri uri that specifies location from where post is retrieved
   * @return
   */
  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.GET, uri, null);
    } catch (OAuthException | IOException ex) {
      throw new RuntimeException("Failed to execute", ex);
    }
  }

  private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity)
      throws OAuthException, IOException {
    httpClient = HttpClientBuilder.create().build();
    if (method == HttpMethod.GET) {
      HttpGet request = new HttpGet(uri);
      consumer.sign(request);
      return httpClient.execute(request);
    } else if (method == HttpMethod.POST) {
      HttpPost request = new HttpPost(uri);
      if (stringEntity != null) {
        request.setEntity(stringEntity);
      }
      consumer.sign(request);
      return httpClient.execute(request);
    } else {
      throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
    }
  }
}