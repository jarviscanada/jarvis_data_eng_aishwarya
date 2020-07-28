package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private QuoteDao quoteDao;
  private PositionDao positionDao;

  @Autowired
  public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao, PositionDao positionDao){
    this.accountDao = accountDao;
    this.securityOrderDao =securityOrderDao;
    this.quoteDao =quoteDao;
    this.positionDao=positionDao;
  }

  /**
   * Execute a market order
   *
   * Assumption: traderId is present for the
   *
   * - validate the order (e.g. size, and ticker)
   * - Create a securityOrder ( for security_order table)
   * - Handle buy or sell order
   *    - buy order : check amount balance (calls helper method)
   *    - sell order : check position for the ticker/symbol (calls helper method)
   *    - (please don't forget to update securityOrder.status)
   * - SAve and return securityOrder
   *
   * NOTE: you will need to some helper methods (protected or private)
   *
   * @param  orderDto market order
   * @return SecurityOrder from security_order table
   * @throws org.springframework.dao.DataAccessException if unable to get data from DAO
   * @throws IllegalArgumentException for invalid input
   */
  public SecurityOrder executeMarketOrder(MarketOrderDto orderDto){
    if (orderDto.getSize()==0){
      throw new IllegalArgumentException("Invalid order size");
    }
    if (!quoteDao.existsById(orderDto.getTicker())){
      throw new IllegalArgumentException("Invalid ticker id: " +orderDto.getTicker());
    }

    Account account = accountDao.findById(orderDto.getAccountId()).orElseThrow(()->new IllegalArgumentException("Invalid account id: " +orderDto.getAccountId()));

    SecurityOrder securityOrder = new SecurityOrder();
    securityOrder.setSize(orderDto.getSize());
    securityOrder.setAccountId(orderDto.getAccountId());
    securityOrder.setTicker(orderDto.getTicker());
    securityOrder.setStatus("PENDING");
    securityOrder.setNotes("Submitting");
    securityOrder.setPrice(0.0);

    if(orderDto.getSize()>0) {
      //Buy MarketOrder
      securityOrder.setPrice(quoteDao.findById(orderDto.getTicker()).get().getBidPrice());
      handleBuyMarketOrder(orderDto,securityOrder,account);
    } else {
      //Sell MarketOrder
      securityOrder.setPrice(quoteDao.findById(orderDto.getTicker()).get().getAskPrice());
      handleSellMarketOrder(orderDto,securityOrder,account);
    }
    return securityOrder;
  }

  /**
   * Helper method that execute a buy order
   * @param marketOrderDto user order
   * @param securityOrder to be saved in data database
   * @param account account
   */
  protected void handleBuyMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder, Account account){

    if (account.getAmount()>=securityOrder.getPrice()*marketOrderDto.getSize()){
      securityOrder.setStatus("FILLED");
      securityOrder.setNotes("Successful");
    } else {
      securityOrder.setNotes("Insufficient account balance");
      securityOrder.setStatus("CANCELLED");
    }
    securityOrderDao.save(securityOrder);
  }

  /**
   * Helper method that execute a sell order
   * @param marketOrderDto user order
   * @param securityOrder to be saved in data database
   * @param account account
   */
  protected void handleSellMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder, Account account){
    List<Position> positionList = positionDao.getPositions(account.getId());
    for(Position position : positionList) {
      if (position.getTicker() == marketOrderDto.getTicker() && position.getPosition() >= Math
          .abs(marketOrderDto
              .getSize())) {
        ;
        securityOrder.setStatus("FILLED");
        securityOrder.setNotes("Successful");
      } else {
        securityOrder.setStatus("CANCELLED");
        securityOrder.setNotes("Insufficient positions");
      }
    }
    securityOrderDao.save(securityOrder);
  }
}
