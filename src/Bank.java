import bankpeople.BankPeople;
import bankview.bankView;
import java.util.ArrayList;
public class Bank {
    public static void main(String[] args) {
        ArrayList<BankPeople> bankPeople = new ArrayList<>();
        bankPeople.add(new BankPeople("张三","123456","123456789",5000));
        bankView.showMenu(bankPeople);
    }
}
