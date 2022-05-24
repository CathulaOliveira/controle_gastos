package br.edu.utfpr.pb.server.controller;

import br.edu.utfpr.pb.server.model.Transaction;
import br.edu.utfpr.pb.server.service.CrudService;
import br.edu.utfpr.pb.server.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController extends CrudController<Transaction, Long> {

    @Autowired
    private TransactionService transactionService;

    @Override
    protected CrudService<Transaction, Long> getService() {
        return this.transactionService;
    }
}
