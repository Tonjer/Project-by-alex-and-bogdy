package consolehandler;

import consolehandler.handlers.ToLowerCase;

public class Main {
    void main(String[] args){
        var handler = new ToLowerCase();
        var reader =  new ConsoleReader(handler);
        reader.run();
    }
}
