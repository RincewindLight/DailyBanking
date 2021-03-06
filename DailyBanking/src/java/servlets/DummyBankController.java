package servlets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import shared.Account;
import shared.BankDataController;
import shared.BusinessController;
import shared.Customer;

/**
 *
 * @author Aaron
 */
public class DummyBankController implements BankDataController {

    private static DummyBankController instance = new DummyBankController();
    private Map<Long, Customer> customers = new HashMap();
    private Map<Long, Account> accounts = new HashMap();

    private DummyBankController() {

        Customer cust = new Customer("Donald", "Duck", "test@gmail.com");
        Account account1 = new Account("Basic Account", 0);
        cust.addAccount(account1);
        account1.createTransaction(20000, "Salary");
        account1.createTransaction(-8000, "Morgage payment");
        account1.createTransaction(-1000, "To savings");
        account1.createTransaction(-1500, "Car Loan");
        account1.createTransaction(-1000, "Ensurance");
        customers.put(cust.getCustomerId(), cust);
        accounts.put(account1.getAccountId(), account1);

        cust = new Customer("Peter", "Pan", "peter@pan.com");
        account1 = new Account("Basic Account", 0);
        cust.addAccount(account1);
        account1.createTransaction(20000, "Salary");
        account1.createTransaction(-8000, "Morgage payment");
        account1.createTransaction(-1000, "To savings");
        account1.createTransaction(-1500, "Car Loan");
        account1.createTransaction(-1000, "Ensurance");
        customers.put(cust.getCustomerId(), cust);
        accounts.put(account1.getAccountId(), account1);
        
        cust = new Customer("MR", "BankMan", "test@dailybank.com");
        customers.put(cust.getCustomerId(), cust);
        
         newTransfer(1000,5000,5001,50,"blah");
    }

    public static DummyBankController getInstance() {
        return instance;
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    @Override
    public Customer getCustomer(long id) {
        return customers.get(id);
    }
    
    @Override
    public Customer getCustomerByEmail(String email) {
        for(long id : customers.keySet() ){
            if(customers.get(id).getEmail().equals(email)){
                return customers.get(id);
            }
        }
        return null;
    }

    @Override
    public Account getAccount(long id) {
        return accounts.get(id);
    }

    @Override
    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customers.values();
    }
    @Override
    public void addAccount(Long id, Account acc) {
        customers.get(id).addAccount(acc);
        accounts.put(acc.getAccountId(), acc);
    }
    
    @Override
    public void newTransfer(long custID, long fromID, long toID, double amount, String message) {
        /*
         * Concurrency issues may arise. Where should they be handled?
         */
        if (accounts.get(fromID).getBalance() >= amount) {
            accounts.get(toID).createTransaction(amount, message);
            accounts.get(fromID).createTransaction(-amount, message);
        } else {
//DO SOMETHING!
            if (!customers.get(custID).getAccounts().contains(accounts.get(fromID))) {
                System.out.println("Customer does not have this account!");
            }
            if (accounts.get(fromID).getBalance() < amount) {
                System.out.println("There is not enought money in the account!");
            }
        }

    }
    
    @Override
    public void saveEditedCustomer(Customer cust, Customer temp){
        if(customers.containsKey(cust.getCustomerId())){
            
//            temp.setCustomerId(cust.getCustomerId());
//            customers.remove(cust.getCustomerId());
//            customers.put(temp.getCustomerId(), temp);
            
            customers.get(cust.getCustomerId()).setFirstName(temp.getFirstName());
            customers.get(cust.getCustomerId()).setLastName(temp.getLastName());
            customers.get(cust.getCustomerId()).setEmail(temp.getEmail());
        }
    }
}
