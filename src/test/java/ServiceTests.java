import com.revature.repositories.CustomerDaoImpl;
import com.revature.services.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ServiceTests {

    @Mock
    private CustomerDaoImpl daoMock;

    @InjectMocks
    private CustomerService service;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        service = new CustomerService();
    }

    @Test
    public void testNewCustomer(){
        //service.registerNewCustomer();
    }

    @Test
    public void testBadLogin(){
        service.login("asasdgas", "alksdjfa");
    }


}
