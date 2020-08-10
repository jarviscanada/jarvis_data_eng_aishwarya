package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.PortfolioView;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityRow;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DashboardService {

  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;

  @Autowired

  public DashboardService(TraderDao traderDao, PositionDao positionDao,
      AccountDao accountDao, QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }

  /**
   * Create and return a traderAccountView by trader ID. - get trader account by id - get trader
   * info by id - create and return a traderAccountView
   *
   * @param traderId must not be null
   * @return traderAccountView
   * @throws IllegalArgumentException if the traderId is null or not found
   */
  public TraderAccountView getTraderAccount(Integer traderId) {
    Trader trader = traderDao.findById(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
    Account account = accountDao.findByTraderId(traderId);

    return new TraderAccountView(trader, account);
  }

  /**
   * Create and return portfolioView by trader ID. - get account by trader id - get positions by
   * account id - create and return a portfolioView
   *
   * @param traderId must not be null
   * @return portfolioView
   * @throws IllegalArgumentException if the traderId is null or not found
   */
  public PortfolioView getProfileViewByTraderId(Integer traderId) {

    Account account = Optional.of(accountDao.findByTraderId(traderId))
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
    List<Position> positions = positionDao.getPositions(account.getId());
    List<SecurityRow> securityRows = positions.stream().map(
        position -> new SecurityRow(position, quoteDao.findById(position.getTicker()).get(),
            position.getTicker())).collect(Collectors.toList());

    return new PortfolioView(securityRows);
  }

  /**
   * @throws IllegalArgumentException if traderId is not found
   */
  private Account findAccountByTraderId(Integer traderId) {
    return Optional.of(accountDao.findByTraderId(traderId))
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
  }

}