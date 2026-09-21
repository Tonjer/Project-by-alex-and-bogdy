package consolehandler;

import consolehandler.handlers.ToLowerCase;
import consolehandler.handlers.RemoveSpaces;

public class Main {
    void main(String[] args){
        var handler = new ToLowerCase();
        var reader =  new ConsoleReader(handler);
        reader.run();
    }
}
