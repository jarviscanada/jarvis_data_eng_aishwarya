package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import ca.jrvs.apps.trading.service.QuoteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/quote")
public class QuoteController {

  private QuoteService quoteService;

  @Autowired
  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping(path = "/iex/ticker/{ticker}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public IexQuote getQuote(@PathVariable String ticker) {
    try {
      return quoteService.findIexQuoteByTicker(ticker);
    } catch (Exception ex) {
      throw ResponseExceptionUtil.getResponseStatusException(ex);
    }
  }

  @PutMapping(path = "/iexMarketData")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<QuoteData> updateMarketData() {
    List<QuoteData> quote;
    try {
      quote = quoteService.updateMarketData();
    } catch (Exception ex) {
      throw ResponseExceptionUtil.getResponseStatusException(ex);
    }
    return quote;
  }

  @PutMapping(path = "/")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public QuoteData putQuote(@RequestBody QuoteData quote) {
    QuoteData quoteData;
    try {
      quoteData = quoteService.saveQuote(quote);
    } catch (Exception ex) {
      throw ResponseExceptionUtil.getResponseStatusException(ex);
    }
    return quoteData;
  }

  @PostMapping(path = "/tickerId/{tickerId}")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public QuoteData createQuote(@PathVariable String tickerId) {
    QuoteData quoteData;
    try {
      quoteData = quoteService.saveQuote(tickerId);
    } catch (Exception ex) {
      throw ResponseExceptionUtil.getResponseStatusException(ex);
    }
    return quoteData;
  }

  @GetMapping(path = "/dailyList")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<QuoteData> getDailyList() {
    List<QuoteData> quoteList;
    try {
      quoteList = quoteService.findAllQuotes();
    } catch (Exception ex) {
      throw ResponseExceptionUtil.getResponseStatusException(ex);
    }
    return quoteList;
  }
}