package ru.Shniros;

import ru.Shniros.DAO.Impl.AccountDAO;
import ru.Shniros.DAO.domain.Account;
import ru.Shniros.DAO.jdbc.SingleConnectionManager;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.update(
                new Account().setId(4).setName("RNCB").setBalance(BigDecimal.valueOf(3500)).setPirsonId(3),
                SingleConnectionManager.getConnection()));

       /* PersonServiceImpl user = new PersonServiceImpl();
        Scanner in = new Scanner(System.in);
        Person person = null;
        do{
            System.out.println("[1] login\n[2] registration\n");
            String ans = in.nextLine();
            System.out.println("Enter e-mail:");
            String email = in.nextLine();
            System.out.println("Enter password:");
            String password = in.nextLine();
            if (ans.equals("1")) {
                person = user.login(email, password);
            } else if (ans.equals("2")) {
                user.registration(new Person(email, password));
            }else {
                new Exception();
            }
        }while(person == null);
        System.out.println("Your data:\n" + person.toString());*/
    }
}
