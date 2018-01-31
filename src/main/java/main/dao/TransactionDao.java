package main.dao;

import main.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface TransactionDao extends CrudRepository<Transaction, Long> {

   // @Query(value = "select t from Transaction t where t.user.name = :name")
    List<Transaction> findByUserName(String name);
}
