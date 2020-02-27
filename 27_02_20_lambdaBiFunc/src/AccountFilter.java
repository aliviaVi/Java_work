import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public  class AccountFilter {
     Account account=new Account();

    public List<Account> filter(List<Account> accounts){
        List<Account> collect = accounts
                .stream()
                .filter((ac) -> ac.getBalance() > 0)
                .filter(a -> !a.isLocked())
                .collect(Collectors.toList());
        return collect;
    }


}
