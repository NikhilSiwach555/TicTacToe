import java.util.*;
import java.util.Locale;
import java.util.Random;
import java.text.NumberFormat;

public class BankAcc {
    public static boolean nameChecker(String s){
        s=s.trim();
        String [] s1=s.split(" ");
        int count=0;
        for(int i=0;i< s1.length;i++){
            if((s1[i].charAt(0))>=65&&(s1[i].charAt(0))<=90&&s1[i].substring(1).equals(s1[i].substring(1).toLowerCase())){
                count+=1;
            }
        }
        if(count== s1.length){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Locale locale;
        ResourceBundle rb;
        NumberFormat cf;

        System.out.println("Please choose your language");
        System.out.println("Press 1 for English");
        System.out.println("Press 2 for Hindi");

        int langChoice = sc.nextInt();
        sc.nextLine();
        if (langChoice == 1) {
            try{
                locale = new Locale("en", "US");
                cf= NumberFormat.getCurrencyInstance(locale);
            } catch (MissingResourceException e) {
                System.out.println("resourse bundle not found \n default using english");
                locale = new Locale("en", "US");
                cf = NumberFormat.getCurrencyInstance(locale);
            }
        } else if (langChoice == 2) {
            try{
                locale = new Locale("hi", "IN");
                cf = NumberFormat.getCurrencyInstance(locale);
            } catch (MissingResourceException e) {
                System.out.println("resourse bundle not found ");
                System.out.println("resourse bundle not found \n default using english");
                locale = new Locale("en", "US");
                cf = NumberFormat.getCurrencyInstance(locale);
            }
        }
        else {
            System.out.println("Invalid Choice. default using English");
            locale = new Locale("en", "US");
            cf = NumberFormat.getCurrencyInstance(locale);
        }
        rb = ResourceBundle.getBundle("ResourceBundle", locale);

        System.out.println(rb.getString("welcome"));
        System.out.println(rb.getString("createornot"));
        String s=sc.nextLine();
        if (s.equals("yes")) {
            System.out.println(rb.getString("entername"));
            String name1=sc.nextLine();
            Bank c1;
            if(nameChecker(name1)){
                c1=new Bank(name1,locale,rb,cf);
            }
            else{
                boolean a=false;
                while(a!=true){
                    System.out.println(rb.getString("invalidname"));
                    name1=sc.nextLine();
                    a=nameChecker(name1);
                }
                c1=new Bank(name1,locale,rb,cf);
            }
            System.out.println(rb.getString("chooseoption"));
            int n=sc.nextInt();
            sc.nextLine();
            while(true){
                if(n==1){
                    c1.deposit();
                }
                if(n==2){
                    c1.withdraw();
                }
                if(n==3){
                    c1.passbook();
                }
                if(n==4){
                    c1.displayDetails();
                }
                if(n==5){
                    c1.changeName();
                }
                if(n==6){
                    System.out.println(rb.getString("exitmsg"));
                    break;
                }
                System.out.println(rb.getString("chooseoption"));
                n=sc.nextInt();
                sc.nextLine();
            }
        }
        else return;

    }
}
class Bank {
    String name;
    int accountNO=0;
    int totalAmount = 0;
    Scanner sc = new Scanner(System.in);
    Random rn=new Random();
    Locale locale;
    ResourceBundle rb;
    NumberFormat cf;

    Bank(String name,Locale locale,
         ResourceBundle rb,NumberFormat cf) {
        this.name = name;
        this.accountNO=rn.nextInt(1,100);
        this.locale=locale;
        this.rb=rb;
        this.cf=cf;
        System.out.println(rb.getString("hey")+ name + rb.getString("accountCreated")+"\n");
    }

    public void withdraw() {
        System.out.println(rb.getString("enterWithdrawAmount"));
        int amount1 = sc.nextInt();
        sc.nextLine();
        if (amountChecker(amount1)&&amount1<=this.totalAmount) {
            this.totalAmount = this.totalAmount - amount1;
        }
        else {
            boolean a1 = false;
            while (a1 != true) {
                System.out.println(rb.getString("invalidAmount")+"\n");
                amount1 = sc.nextInt();
                sc.nextLine();
                a1 = amountChecker(amount1);
            }
            this.totalAmount = this.totalAmount - amount1;
        }
        System.out.println(cf.format(amount1) + rb.getString("debited") + rb.getString("totalAmount") + cf.format(totalAmount));
    }

    public void deposit() {
        System.out.println(rb.getString("enterDepositAmount"));
        int amount = sc.nextInt();
        sc.nextLine();
        if (amountChecker(amount)) {
            this.totalAmount = this.totalAmount + amount;
        } else {
            boolean a1 = false;
            while (a1 != true) {
                System.out.println(rb.getString("invalidAmount")+"\n");
                amount = sc.nextInt();
                sc.nextLine();
                a1 = amountChecker(amount);
            }
            this.totalAmount = this.totalAmount + amount;
        }
        System.out.println(cf.format(amount) + rb.getString("deposited") + rb.getString("totalAmount") + cf.format(totalAmount));
    }

    public void changeName() {
        System.out.println(rb.getString("enterNewName"));
        String name1 = sc.nextLine();
        if (nameChecker(name1)) {
            this.name = name1;
        } else {
            boolean a = false;
            while (a != true) {
                System.out.println(rb.getString("invalidName"));
                name1 = sc.nextLine();
                a = nameChecker(name1);
            }
            this.name = name1;
        }
        System.out.println(rb.getString("nameChanged") + this.name);
    }

    public void passbook() {
        System.out.println(rb.getString("yourName") + this.name);
        System.out.println(rb.getString("yourTotalAmount") + cf.format(this.totalAmount));
    }

    public void displayDetails() {
        System.out.println(rb.getString("yourName") + this.name);
        System.out.println(rb.getString("yourAccountId") + this.accountNO);
        System.out.println(rb.getString("yourTotalAmount") + cf.format(this.totalAmount));
    }

    public static boolean nameChecker(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }
        String[] s1 = s.split(" ");
        int count = 0;
        for (int i = 0; i < s1.length; i++) {
            if ((s1[i].charAt(0)) >= 65 && (s1[i].charAt(0)) <= 90 && s1[i].substring(1).equals(s1[i].substring(1).toLowerCase())) {
                count += 1;
            }
        }
        if (count == s1.length) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean amountChecker(int amount) {
        return amount < 20000 || amount == 20000;
    }
}
