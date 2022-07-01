package br.edu.utfpr.pb.pw26s.server.repository;

import br.edu.utfpr.pb.pw26s.server.enums.TypeTransaction;
import br.edu.utfpr.pb.pw26s.server.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateBetweenAndAccountOrigin_Id(
            LocalDate dateStart,
            LocalDate dateFinish,
            Long accountOriginId
    );
    List<Transaction> findByDateBetweenAndAccountDestination_Id(
            LocalDate dateStart,
            LocalDate dateFinish,
            Long accountDestinationId
    );
    List<Transaction> findByDateBetweenAndTypeAndAccountOrigin_Id(
            LocalDate dateStart,
            LocalDate dateFinish,
            TypeTransaction type,
            Long accountOriginId
    );
    List<Transaction> findByDateBetweenAndTypeAndAccountDestination_Id(
            LocalDate dateStart,
            LocalDate dateFinish,
            TypeTransaction type,
            Long accountDestinationId
    );
    List<Transaction> findByAccountOrigin_User_Id(
            Long accountOriginUserId
    );
}
