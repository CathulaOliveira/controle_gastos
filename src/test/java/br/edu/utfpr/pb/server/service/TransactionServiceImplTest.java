package br.edu.utfpr.pb.pw26s.server.service;

import br.edu.utfpr.pb.pw26s.server.enums.TypeAccount;
import br.edu.utfpr.pb.pw26s.server.enums.TypeTransaction;
import br.edu.utfpr.pb.pw26s.server.filter.FilterBalance;
import br.edu.utfpr.pb.pw26s.server.model.Account;
import br.edu.utfpr.pb.pw26s.server.model.Category;
import br.edu.utfpr.pb.pw26s.server.model.Transaction;
import br.edu.utfpr.pb.pw26s.server.model.User;
import br.edu.utfpr.pb.pw26s.server.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TransactionServiceImplTest {

    @Autowired
    TransactionServiceImpl underTest;
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @BeforeEach
    public void cleanup() {
        underTest.deleteAll();
        accountService.deleteAll();
        userService.deleteAll();
        categoryService.deleteAll();
    }

    @Test
    void saveTransaction() {
        authenticate();
        Category category = createCategoryValid();
        Account accountOrigin = createAccountValid();
        Transaction transaction =
                Transaction.builder()
                        .description("Teste")
                        .accountOrigin(accountOrigin)
                        .category(category)
                        .price(50.0)
                        .date(LocalDate.now())
                        .type(TypeTransaction.ENTRADA)
                        .build();
        underTest.save(transaction);
        List<Transaction> transactions = underTest.findAll();
        assertThat(transactions).isNotNull();
    }

    @Test
    void calculateEntry() {
        authenticate();
        Category category = createCategoryValid();
        Account accountOrigin = createAccountValid();
        Transaction transaction =
                Transaction.builder()
                        .description("Teste")
                        .accountOrigin(accountOrigin)
                        .category(category)
                        .price(100.0)
                        .date(LocalDate.now())
                        .type(TypeTransaction.ENTRADA)
                        .build();
        underTest.save(transaction);
        FilterBalance filter = new FilterBalance();
        filter.setMonth(""+LocalDate.now().getMonthValue());
        filter.setYear(""+LocalDate.now().getYear());
        filter.setAccountId(""+accountOrigin.getId());
        Double totalEntry = underTest.calculateEntryByFilterBalance(filter);
        assertThat(totalEntry).isEqualTo(100.0);
    }

    @Test
    void calculateOutput() {
        authenticate();
        Category category = createCategoryValid();
        Account accountOrigin = createAccountValid();
        Transaction transaction =
                Transaction.builder()
                        .description("Teste")
                        .accountOrigin(accountOrigin)
                        .category(category)
                        .price(50.0)
                        .date(LocalDate.now())
                        .type(TypeTransaction.SAIDA)
                        .build();
        underTest.save(transaction);
        FilterBalance filter = new FilterBalance();
        filter.setMonth(""+LocalDate.now().getMonthValue());
        filter.setYear(""+LocalDate.now().getYear());
        filter.setAccountId(""+accountOrigin.getId());
        Double totalOutput = underTest.calculateOutputByFilterBalance(filter);
        assertThat(totalOutput).isEqualTo(50.0);
    }

    @Test
    void transactionsByAccount() {
        authenticate();
        Category category = createCategoryValid();

        Account accountOriginEntry = createAccountValid();
        Transaction transactionEntry =
                Transaction.builder()
                        .description("Teste")
                        .accountOrigin(accountOriginEntry)
                        .category(category)
                        .price(50.0)
                        .date(LocalDate.now())
                        .type(TypeTransaction.ENTRADA)
                        .build();
        underTest.save(transactionEntry);

        Account accountOriginOutput = createAccountValid();
        Transaction transactionOutput =
                Transaction.builder()
                        .description("Teste")
                        .accountOrigin(accountOriginOutput)
                        .category(category)
                        .price(50.0)
                        .date(LocalDate.now())
                        .type(TypeTransaction.SAIDA)
                        .build();
        underTest.save(transactionOutput);

        FilterBalance filter = new FilterBalance();
        filter.setMonth(""+LocalDate.now().getMonthValue());
        filter.setYear(""+LocalDate.now().getYear());
        filter.setAccountId(""+accountOriginEntry.getId());
        List<Transaction> transactionsAccountEntry = underTest.listTransactionsByFilterBalance(filter);
        assertThat(transactionsAccountEntry.size()).isEqualTo(1);
    }

    private Category createCategoryValid() {
        Category category =
                Category.builder()
                        .name("Teste")
                        .build();
        return categoryService.save(category);
    }

    private Account createAccountValid() {
        Account account =
                Account.builder()
                        .number("123")
                        .agency("12")
                        .bank("Teste")
                        .type(TypeAccount.CONTA_CORRENTE)
                        .build();
        return accountService.save(account);
    }

    private void authenticate() {
        User user = userService.save(getValidLoginUser());
        TestSecurityContextHolder
                .getContext()
                .setAuthentication(new TestingAuthenticationToken(user.getUsername(), new ArrayList<GrantedAuthority>()));

    }

    private User getValidLoginUser() {
        User user = new User();
        user.setUsername("test-user");
        user.setPassword("P4ssword");
        return user;
    }

}
