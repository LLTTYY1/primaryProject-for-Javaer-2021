package bankservice;

import bankpeople.BankPeople;
import bankview.bankView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@SuppressWarnings({"all"})
public class BankService {
    //登录
    public static void login(ArrayList<BankPeople> bankPeople, Scanner scanner) {
        if (bankPeople.size() == 0) {
            System.out.println("目前无用户,请你先注册");
            bankView.showMenu(bankPeople);
        } else {
            int i = 1;//用于记录输错密码的次数
            System.out.print("请输入你的账号:");
            String cardId = scanner.next();
            BankPeople bankPeople1 = getBankPeople(cardId, bankPeople);
            if (bankPeople1 == null) {//判断该对象是否为空
                System.out.println("账号错误,该银行目前没有该账户");
                System.out.println("请你再次登录");
                bankView.showMenu(bankPeople);
            } else {
                while (true) {
                    System.out.print("请输入你的密码:");
                    String password = scanner.next();
                    if (bankPeople1.getPassWord().equals(password)) {
                        System.out.println("登陆成功");
                        Date date = new Date();
                        DateFormat dateInstance = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒"+"\n");
                        System.out.print(dateInstance.format(date));
                        bankView.showUserCommand(bankPeople,new Scanner(System.in),bankPeople1);
                        return;
                    }
                    else {
                        if (i <= 3) {
                            i++;
                            System.out.println("密码错误,你还剩" + (5 - i) + "次机会");
                        } else if(i==4) {
                            return ;//登录机会用完,直接结束方法
                        }
                    }
                }
            }
        }
    }

    //通过cardId去得到相应的对象
    public static BankPeople getBankPeople(String cardId, ArrayList<BankPeople> bankPeople) {
        for (int i = 0; i < bankPeople.size(); i++) {
            BankPeople bankPeople1 = bankPeople.get(i);
            if (bankPeople1.getCardId().equals(cardId)) {
                return bankPeople1;
            }
        }
        return null;
    }

    public static void register(ArrayList<BankPeople> bankPeople, Scanner scanner) {
        System.out.print("输入你的名字:");
        String name = scanner.next();
            System.out.print("设置你的密码:");
            String password = scanner.next();
        if (password.length() <=3 || password.length()> 6){//判断密码
            System.out.println("密码过于简单再次设置,要求在3到6位之间");
        }else {
            while (true) {
                System.out.print("再次输入你的密码:");
                String password1 = scanner.next();
                if (password.equals(password1)) {
                    System.out.println("设置成功");
                    String cardId = registerid();
                    System.out.print("你获得了一个账号:"+cardId+"\n");
                    System.out.print("输入你想要存入的金额:");
                    double money = scanner.nextDouble();
                    bankPeople.add(new BankPeople(name,cardId,password,money));
                    System.out.println("恭喜"+name+"注册成功");
                    break;
                } else {
                    System.out.println("两次输入的密码必须一致");
                }
            }
        }
    }
    public static String registerid() {//用随机数随即获取一个账号(8位数)
        Random random = new Random();
        String idcard = "";
        for (int i = 0; i < 8; i++) {
            idcard += random.nextInt(10);
        }
        return idcard;
    }
    public static int quit(Scanner scanner){//退出
        System.out.println("你是否真的要退出(Y/N)");
        char key = scanner.next().toUpperCase().charAt(0);
        if(key == 'Y'){
            System.out.println("你已经安全退出");
            return 1;
        }else if(key == 'N'){
            System.out.println("欢迎回到页面");
            return -1;
        }
        System.out.println("你输入的字符不对,重新选择");
        return 0;
    }
    public static void Inquire(BankPeople bankPeople1){
         String name = bankPeople1.getName();
         String cardId = bankPeople1.getCardId();
         double money = bankPeople1.getMoney();
        System.out.print("名字\t\t"+"卡号\t\t"+"余额\t\t"+"\n"+name+"\t\t"+cardId+"\t\t"+money+"\t\t");
    }
    public static void saveMoney(BankPeople bankPeople1,Scanner scanner){//取钱
        System.out.println("请输入你存的钱:");
        double money = scanner.nextDouble();
        if(money >0 ) {
            bankPeople1.setMoney(bankPeople1.getMoney() + money);
            System.out.println("存款完成");
        }else{
            System.out.println("钱必须大于0");
        }
    }
    public static void withdrawMoney(BankPeople bankPeople1,Scanner scanner){
        System.out.println("请输入你想要存的钱");
        double money = scanner.nextDouble();
        if(money > 0 ) {
            bankPeople1.setMoney(bankPeople1.getMoney() - money);
            System.out.println("取款完成");
        }else{
            System.out.println("钱必须大于0");
        }
    }
    public static void changePasswrod(BankPeople bankPeople,Scanner scanner){
        System.out.println("你确定要修改密码吗?(Y/N)大写");
        char key = scanner.next().charAt(0);
        if(key == 'Y'){
            System.out.print("请输入你想要修改的密码:");
            String password = scanner.next();
            System.out.print("请再次输入你的新密码:");
            String password1 = scanner.next();
            if(password.equals(password1)){
                System.out.println("修改成功");
                bankPeople.setPassWord(password);
            }else{
                System.out.println("2次密码必须一致");
            }
        }else{
            return;
        }
    }
    public static int exit(Scanner scanner){
        System.out.println("你是否真的要退出(Y/N)");
        char key = scanner.next().toUpperCase().charAt(0);
        if(key == 'Y'){
            System.out.println("你已经安全退出");
            return 1;
        }else if(key == 'N'){
            System.out.println("欢迎回到页面");
            return -1;
        }else {
            System.out.println("你输入的字符不对,重新选择");
            return 0;
        }
    }
    public static int logout(Scanner scanner,BankPeople bankPeople1,ArrayList<BankPeople> bankPeople){
        System.out.println("你是否真的要注销(Y/N)");
        char key = scanner.next().toUpperCase().charAt(0);
        if(key == 'Y'){
            bankPeople.remove(bankPeople1);
            System.out.println("注销成功");
            return 1;
        }else if(key == 'N'){
            System.out.println("已取消注销");
            return -1;
        }else{
            System.out.println("你输入的字符不对,重新选择");
            return 0;
        }
    }
    public static void Transfer(Scanner scanner,BankPeople bankPeople1,ArrayList<BankPeople> bankPeople){
        System.out.println("请输入转账人的卡号:");
        String cardId = scanner.next();
        BankPeople bankPeople2 = getBankPeople(cardId,bankPeople);
        if(bankPeople2 == null){
            System.out.println("无该用户,请重新确认卡号");
            return;
        }else{
            System.out.print("请输入你要转账的钱:");
            double money = scanner.nextDouble();
            bankPeople1.setMoney(bankPeople1.getMoney() - money);//转账后从当前账户扣除相应的钱
            bankPeople2.setMoney(bankPeople2.getMoney() + money);//收款方的钱相应增加
            System.out.println("转账成功");
        }
    }
}

