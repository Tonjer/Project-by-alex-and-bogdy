//package consolehandler;
//
//import consolehandler.handlers.TextHandler;
//
//import java.util.Scanner;
//
//public class ConsoleReader {
//    private final TextHandler handler; //подсказала нейронка, для доп безопасности кода
//    public ConsoleReader(TextHandler handler){
//        this.handler = handler;
//    }
//    public void run(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Введите текст (используйте exit для выхода)");
//        while (true){
//            System.out.println(">");
//            String line = scanner.nextLine();
//            if ("exit".equalsIgnoreCase(line)) break;
//            System.out.println(handler.process(line));
//        }
//        scanner.close();
//    }
//}

package consolehandler;

import java.util.Scanner;

public class ConsoleReader {
    private final CommandRouter router;

    public ConsoleReader(CommandRouter router) {
        this.router = router;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Бот 'Где поесть?' запущен!");
        System.out.println("Введите /about, /help или /exit");
        System.out.println("---------------------------");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if ("/exit".equalsIgnoreCase(line) || "exit".equalsIgnoreCase(line)) {
                System.out.println("До свидания! Приятного аппетита!");
                break;
            }
            if (line.isEmpty()) continue;
            System.out.println(router.route(line));
        }
        scanner.close();
    }
}
