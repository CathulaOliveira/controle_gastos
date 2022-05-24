package br.edu.utfpr.pb.server.repository;

import br.edu.utfpr.pb.server.enums.TypeTransaction;
import br.edu.utfpr.pb.server.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateBetween(LocalDate dateStart, LocalDate dateFinish);
    List<Transaction> findByDateBetweenAndType(LocalDate dateStart, LocalDate dateFinish, TypeTransaction type);
}
