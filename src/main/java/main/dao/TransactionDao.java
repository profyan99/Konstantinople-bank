package main.dao;

import main.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface TransactionDao{
    List<Transaction> findByUserName(String name);

    Transaction findById(long id);

    List<Transaction> findByBillId(long id);

    List<Transaction> findAll();

    void create(Transaction transaction);

    void delete(long id);
}
