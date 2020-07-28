package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.Collections;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

  //capture parameter when calling securityOrderDao.save
  @Captor
  ArgumentCaptor<SecurityOrder> captorSecurityOrder;

  //mock all dependencies
  @Mock
  private AccountDao accountDao;

  @Mock
  private SecurityOrderDao securityOrderDao;

  @Mock
  private QuoteDao quoteDao;

  @Mock
  private PositionDao positionDao;

  //injecting mocked dependencies to the testing class via constructor
  @InjectMocks
  private OrderService orderService;

  @Test
  public void executeMarketOrder() {
    QuoteData quote = new QuoteData();
    quote.setTicker("AAPL");
    quote.setLastPrice(10.2);
    quote.setBidPrice(10.5);
    quote.setBidSize(100);
    quote.setAskPrice(10.0);
    quote.setAskSize(100);

    Account account = new Account();
    account.setId(1);
    account.setAmount(5000.0);
    account.setTraderId(1);

    Position position = new Position();
    position.setAccountId(1);
    position.setPosition(100);
    position.setTicker("AAPL");

    when(quoteDao.existsById(anyString())).thenReturn(true);
    when(quoteDao.findById(anyString())).thenReturn(Optional.of(quote));
    when(accountDao.findById(anyInt())).thenReturn(Optional.of(account));
    when(positionDao.getPositions(anyInt())).thenReturn(Collections.singletonList(position));

    MarketOrderDto marketOrderDto = new MarketOrderDto();
    marketOrderDto.setAccountId(1);
    marketOrderDto.setSize(100);
    marketOrderDto.setTicker("AAPL");
    orderService.executeMarketOrder(marketOrderDto);
    verify(securityOrderDao).save(captorSecurityOrder.capture());
    assertEquals("FILLED", captorSecurityOrder.getValue().getStatus());

    marketOrderDto.setAccountId(1);
    marketOrderDto.setSize(-100);
    marketOrderDto.setTicker("AAPL");
    orderService.executeMarketOrder(marketOrderDto);
    //verify(securityOrderDao).save(captorSecurityOrder.capture());
    assertEquals("FILLED", captorSecurityOrder.getValue().getStatus());
  }
}