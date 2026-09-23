package consolehandler;

import java.util.Scanner;

public class Main {
    public static void main (String [] args) {
        CommandRouter router = new CommandRouter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Бот 'Где поесть?' запущен!");
        System.out.println("Доступные комманды: \n 1 - /about \n 2 - /exit");
        System.out.println("---------------------------");
        while (true) {
            System.out.println(">");
            String input = scanner.nextLine().trim();
            if ("/exit".equalsIgnoreCase(input)) {
                System.out.println("До свидания! Приятного аппетита!");
                break;
            }
            if (input.isEmpty()) {
                continue;
            }
            String response = router.route(input);
            System.out.println(response);
        }
        scanner.close();
    }
}
