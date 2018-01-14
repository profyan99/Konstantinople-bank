package main.dao;

import main.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TransactionDao extends CrudRepository<Transaction, Long> {

    List<Transaction> findAllTransactionsByUserName(String name);
}
