package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

public interface HttpHelper {

  /**
   * Execute a HTTP Post call.
   *
   * @param uri uri that specifies the location of the post to be created
   * @return
   */
  HttpResponse httpPost(URI uri);

  /**
   * Execute a HTTP Get call.
   *
   * @param uri uri that specifies location from where post is retrieved
   * @return
   */
  HttpResponse httpGet(URI uri);
}