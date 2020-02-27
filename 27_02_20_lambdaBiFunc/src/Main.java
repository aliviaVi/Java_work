
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {
        BiFunction <Integer,Integer,Integer> biFunction= (a,b)-> a*b;
        System.out.println(biFunction.apply(3,6));

        BiFunction<Integer,Integer,String> function=(a,b)->{
            int mult=1;
            for (int i = a; i <=b ; i++) {
                mult*=i;
            }
            return String.valueOf(mult);
        };

        System.out.println(function.apply(3,6));



        List<String> text=Arrays.asList("new", "apple", "new", "snow", "snow");

        Function<List<String>, Set<String>> textFunc=(t)->{
            Set<String> res=new HashSet<>();
            res.addAll(t);
            return res;
        };


        System.out.println("text Function");
        System.out.println(textFunc.apply(text));

        List<Account> account = new ArrayList<>();

        Account account4 = new Account("12344", 35000L, false);
        Account account1 = new Account("34567", -12000L, false);
        Account account2 = new Account("19085", 0L, true);
        Account account3 = new Account("35781", 2000L, true);

        account.add(account1);
        account.add(account2);
        account.add(account3);
        account.add(account4);


        System.out.println("Accounts");
        AccountFilter accountFilter=new AccountFilter();
        System.out.println(accountFilter.filter(account));


    }
   public static Predicate<Account> checkIsLocked=new Predicate<Account>() {
        @Override
        public boolean test(Account account) {
            return account.isLocked();
        }

    };

    public static Predicate<Account> checkingNotZero=new Predicate<Account>() {
        @Override
        public boolean test(Account account) {
            return account.getBalance()>0;
        }
    };

}