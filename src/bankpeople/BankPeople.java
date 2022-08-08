package bankpeople;
@SuppressWarnings({"all"})
public class BankPeople {
    private String name;//名字
    private String cardId;//卡号
    private String passWord;//密码
    private double money;

    public BankPeople(String name, String cardId, String passWord, double money) {//有参构造器
        this.name = name;
        this.cardId = cardId;
        this.passWord = passWord;
        this.money = money;
    }
    public BankPeople(){//无参构造器

    }
    //提供GetSet方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
//  重写toString方法
    @Override
    public String toString() {
        return  name +"\t\t"+
                cardId+"\t\t"+
                passWord+"\t\t"+
                money +"\t\t";
    }
}
