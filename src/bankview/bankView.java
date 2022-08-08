package bankview;
import bankpeople.BankPeople;
import bankservice.BankService;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings({"all"})
public class bankView {
    public static void showMenu(ArrayList<BankPeople> bankPeople){
        System.out.println("================欢迎进入首页界面===============");
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("请输入您想要进行的操作:");
            System.out.println("1.登录");
            System.out.println("2.开户");
            System.out.println("3 退出");
            int key = sc.nextInt();
            switch (key){
                case 1:
                    //登录
                    BankService.login(bankPeople,new Scanner(System.in));
                    loop = false;
                    break;
                case 2:
                    //开户
                    BankService.register(bankPeople,new Scanner(System.in));
                    break;
                case 3:
                    //退出
                    int i = BankService.quit(new Scanner(System.in));
                    if(i ==1) {
                        loop = false;
                    }else if(i == -1){
                        loop = true;
                    }else if(i == 0) {
                        loop = true;
                    }
                    break;
                default:
                    System.out.println("您的输入有误!");
            }
        }
    }
    public static void showUserCommand(ArrayList<BankPeople> bankPeople,Scanner scanner,BankPeople bankPeople1) {
        boolean loop = true;
        while (loop) {
            System.out.println("\n==================用户操作界面===================");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");
            System.out.print("请您输入：");
            int key = scanner.nextInt();
            switch (key) {
                case 1:
                    // 查询账户
                    BankService.Inquire(bankPeople1);
                    break;
                case 2:
                    BankService.saveMoney(bankPeople1, new Scanner(System.in));
                    // 存款
                    break;
                case 3:
                    BankService.withdrawMoney(bankPeople1, new Scanner(System.in));
                    // 取款
                    break;
                case 4:
                    // 转账
                    BankService.Transfer(new Scanner(System.in),bankPeople1,bankPeople);
                    break;
                case 5:
                    // 修改密码
                    BankService.changePasswrod(bankPeople1, new Scanner(System.in));
                    break; // 结束当前操作的方法
                case 6:
                    // 退出
                    int i = BankService.exit(new Scanner(System.in));
                    if(i == 1){
                        System.out.println("欢迎下次光临！");
                        showMenu(bankPeople);
                        loop = false;
                        break;
                    }
                case 7:
                    // 注销账户
                    int i2 = BankService.logout(new Scanner(System.in),bankPeople1,bankPeople);
                    if(i2 == 1) {
                        showMenu(bankPeople);
                        loop = false;
                        break;
                    }else if(i2 == -1){
                        showUserCommand(bankPeople,new Scanner(System.in),bankPeople1);
                    }else{
                        System.out.println("您的命令输入有误~~~");
                        BankService.logout(new Scanner(System.in),bankPeople1,bankPeople);
                    }
                default:
                    System.out.println("您的命令输入有误~~~");
            }
        }
        }

    }
