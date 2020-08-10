package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * https://iexcloud.io/docs/api/#quote
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "quote"
})
public class IexQuote {

  @JsonProperty("quote")
  private Quote quote;

  @JsonProperty("quote")
  public Quote getQuote() {
    return quote;
  }

  @JsonProperty("quote")
  public void setQuote(Quote quote) {
    this.quote = quote;
  }
}