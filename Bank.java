import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bank {
    private List<Customer> customerList = new ArrayList<>();

    public Bank() {
    }

    public Bank(List<Customer> customerList) {
        customerList = customerList;
    }

    /**
     * Read text.
     */
    public void readCustomerList(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        Customer customer = new Customer();
        Account account;
        try {
            while (reader.ready()) {
                line = reader.readLine();
                if (line.contains("CHECKING")) {
                    account = new CheckingAccount();
                    account.accountNumber = Long.parseLong(line.substring(0, 10));
                    account.balance = Double.parseDouble(line.substring(line.indexOf("CHECKING")
                            + 9, line.length()));

                    customer.getAccountList().add(account);
                    if (!reader.ready()) {
                        customerList.add(customer);
                    }
                } else if (line.contains("SAVING")) {
                    account = new SavingsAccount();

                    account.accountNumber = Long.parseLong(line.substring(0, 10));
                    account.balance = Double.parseDouble(line.substring(line.indexOf("SAVING")
                            + 7, line.length()));

                    customer.getAccountList().add(account);
                    if (!reader.ready()) {
                        customerList.add(customer);
                    }
                } else {
                    if (customer.getFullName() != null && !customer.getFullName().isEmpty()) {
                        customerList.add(customer);
                    }
                    customer = new Customer();
                    customer.setIdNumber(Long.parseLong(line.substring(line.length()
                            - 9, line.length())));
                    customer.setFullName(line.substring(0, line.length() - 9));
                    if (!reader.ready()) {
                        customerList.add(customer);
                    }
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Sắp xếp theo ID.
     */
    public String getCustomersInfoByIdOrder() {

        customerList.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getIdNumber() > o2.getIdNumber() ? 1 : -1;
            }
        });
        String result = "";
        for (int i = 0; i < customerList.size(); i++) {
            if (i == customerList.size() - 1) {
                result += customerList.get(i).getCustomerInfo();
            } else {
                result += customerList.get(i).getCustomerInfo() + "\n";
            }
        }
        return result;
    }

    /**
     * Sắp xếp theo tên.
     */
    public String getCustomersInfoByNameOrder() {
        customerList.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        String result = "";
        for (int i = 0; i < customerList.size(); i++) {
            if (i == customerList.size() - 1) {
                result += customerList.get(i).getCustomerInfo();
            } else {
                result += customerList.get(i).getCustomerInfo() + "\n";
            }
        }
        return result;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
