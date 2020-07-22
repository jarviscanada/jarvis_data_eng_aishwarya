package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.QuoteData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<QuoteData, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation
   * might have changed the entity instance completely.
   *
   * @param quote data that needs to be inserted to table
   * @return Quote return the Quote data referring to row in the Quote table
   * @throws DataAccessException for unexpected SQL result or SQL execution failure
   */
  @Override
  public QuoteData save(QuoteData quote) {
    if (existsById(quote.getTicker())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * helper method that saves one quote.
   */
  private void addOne(QuoteData quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * helper method that updates one quote.
   */
  private int updateOne(QuoteData quote) {
    String updateSql = "UPDATE " + TABLE_NAME + " SET last_price=?, bid_price=?, "
        + "bid_size=?, ask_price=?, ask_size=? WHERE " + ID_COLUMN_NAME + "=?";
    return jdbcTemplate.update(updateSql, makeUpdateValue(quote));
  }

  /**
   * helper method that makes sql update values objects.
   *
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValue(QuoteData quote) {
    if (!existsById(quote.getTicker())) {
      throw new RuntimeException("Ticker not found:" + quote.getTicker());
    }
    Object[] values = new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
        quote.getAskPrice(), quote.getAskSize(), quote.getTicker()};

    return values;
  }

  /**
   * Saves all given entities.
   *
   * @param quotes must not be {@literal null}.
   * @return the saved entities will never be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public <S extends QuoteData> List<S> saveAll(Iterable<S> quotes) {
    String updateSql = "UPDATE " + TABLE_NAME + " SET last_price=?, bid_price=?, "
        + "bid_size=?, ask_price=?, ask_size=? WHERE " + ID_COLUMN_NAME + "=?";
    List<Object[]> batch = new ArrayList<>();
    quotes.forEach(quote -> {
      if (!existsById(quote.getTicker())) {
        logger.debug("Can't find ticker: " + quote.getTicker());
      }
      Object[] values = new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
          quote.getAskPrice(), quote.getAskSize(), quote.getTicker()};
      batch.add(values);
    });
    int[] rows = jdbcTemplate.batchUpdate(updateSql, batch);
    int totalRow = Arrays.stream(rows).sum();
    if (totalRow != quotes.spliterator().estimateSize()) {
      throw new IncorrectResultSizeDataAccessException("Number of rows",
          (int) quotes.spliterator().estimateSize(), totalRow);
    }
    return (List<S>) quotes;
  }

  /**
   * Returns all quotes.
   *
   * @return all entities
   * @throws DataAccessException if failed to update
   */
  @Override
  public List<QuoteData> findAll() {
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    List<QuoteData> quoteDataList = jdbcTemplate
        .query(selectSql, BeanPropertyRowMapper.newInstance(QuoteData.class));

    return quoteDataList;
  }

  /**
   * Find a quote by its ticker.
   *
   * @param ticker name
   * @return quote or Optional.empty() if none found
   */
  @Override
  public Optional<QuoteData> findById(String ticker) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
    QuoteData quoteData = jdbcTemplate.queryForObject(selectSql,
        BeanPropertyRowMapper.newInstance(QuoteData.class), ticker);
    return Optional.ofNullable(quoteData);
  }

  /**
   * Returns whether an entity with the given id exists.
   *
   * @param ticker must not be {@literal null}.
   * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public boolean existsById(String ticker) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
    QuoteData quote = null;
    try {
      quote = jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(QuoteData.class), ticker);
    } catch (EmptyResultDataAccessException ex) {
      logger.debug("Can't find ticker: " + ticker, ex);
    }
    return !(quote == null);
  }

  /**
   * Deletes the quote with the given ticker.
   *
   * @param ticker must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(String ticker) {
    if (ticker == null) {
      throw new IllegalArgumentException("ID can't be null");
    }
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE ticker=?";
    jdbcTemplate.update(deleteSql, ticker);
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<QuoteData> findAllById(Iterable<String> strings) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(QuoteData entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends QuoteData> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }
}