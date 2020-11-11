import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.revature.models.Account;
import com.revature.repositories.AccountDaoImpl;
import com.revature.services.AccountService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AccountTests {
   
    @Mock
    private AccountDaoImpl daoMock;

    @InjectMocks
    private AccountService service;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        service = new AccountService();
    }

    @Test
    public void testWIthdawIfNoFunds(){
        when(daoMock.findByAccId(1)).thenReturn(new Account(1, 1, 0, "Savings", true));
        service.withdraw(1, 1000);
    }

    @Test
    public void testDepositIfNoActive(){
        when(daoMock.findByAccId(1)).thenReturn(new Account(1, 1, 0, "Savings", false));
        service.deposit(1, 100);
    }

}
