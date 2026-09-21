package consolehandler;

import consolehandler.handlers.TextHandler;

import java.util.Scanner;

public class ConsoleReader {
    private final TextHandler handler; //подсказала нейронка, для доп безопасности кода
    public ConsoleReader(TextHandler handler){
        this.handler = handler;
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст (используйте exit для выхода)");
        while (true){
            System.out.println(">");
            String line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line)) break;
            System.out.println(handler.process(line));
        }
        scanner.close();
    }
}
