package main.dao.mapper;

import main.entity.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getLong("id"));
        transaction.setAmount(resultSet.getInt("amount"));
        transaction.setDate(resultSet.getDate("date"));
        transaction.setDescription(resultSet.getString("description"));
        transaction.setUser(null);
        transaction.setBill(null);
        //TODO create Object mapping
        return transaction;
    }
}
