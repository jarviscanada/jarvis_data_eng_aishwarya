package ca.jrvs.apps.trading.model.domain;

public class TraderAccountView {

  private Trader trader;
  private Account account;

  public TraderAccountView(Trader trader, Account account) {
    this.trader = trader;
    this.account = account;
  }
}