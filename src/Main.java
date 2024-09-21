import com.klepon46.atm.model.Atm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input;

        Atm atm = new Atm();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            input = scanner.nextLine();
            String[] command = input.split(" ");

            switch (command[0]) {
                case "login":
                    atm.login(command[1]);
                    break;
                case "deposit":
                    atm.deposit(Integer.parseInt(command[1]));
                    break;
                case "withdraw":
                    atm.withdraw(Integer.parseInt(command[1]));
                    break;
                case "transfer":
                    atm.transfer(command[1], Integer.parseInt(command[2]));
                    break;
                case "logout":
                    atm.logout();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong command");

            }
        }
    }
}