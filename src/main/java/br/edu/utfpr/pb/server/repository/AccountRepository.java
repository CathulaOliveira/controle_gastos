package br.edu.utfpr.pb.server.repository;

import br.edu.utfpr.pb.server.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
