package br.edu.utfpr.pb.server.aspect;

import br.edu.utfpr.pb.server.model.Transaction;
import br.edu.utfpr.pb.server.service.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class AccountAspectService {

    private final AccountServiceImpl accountService;

    @After(value = "execution(* br.edu.utfpr.pb.server.service.impl.TransactionServiceImpl.save(..)) && args(transaction)")
    public void beforeTransactionSave(JoinPoint joinPoint, Transaction transaction) {
        accountService.atualizarSaldoContaEmInclusaoDeTranferencia(transaction);
    }

    @After(value = "execution(* br.edu.utfpr.pb.server.service.impl.TransactionServiceImpl.delete(..)) && args(transaction)")
    public void afterTransactionDelete(JoinPoint joinPoint, Transaction transaction) {
        accountService.atualizarSaldoContaEmExclusaoDeTranferencia(transaction);
    }
}
