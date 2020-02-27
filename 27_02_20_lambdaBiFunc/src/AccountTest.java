import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    List<Account> account;
    AccountFilter accountFilter;

    @Before
    public void init(){
        account=new ArrayList<>();
        Account account1 = new Account("12344", 35000L, false);
        Account account2 = new Account("34567", 12000L, false);
        Account account3 = new Account("19085", 0L, true);
        Account account4 = new Account("35781", 2000L, true);

       account.add(account1);
       account.add(account2);
       account.add(account3);
       account.add(account4);

       accountFilter=new AccountFilter();
    }

    @Test
    public void accountsFilterTest(){
        Account account1 = new Account("12344", 35000L, false);
        Account account2 = new Account("34567", 12000L, false);

        List<Account> exp= new ArrayList<>();
        exp.add(account1);
        exp.add(account2);

        assertEquals(exp,accountFilter.filter(account));
    }
}
