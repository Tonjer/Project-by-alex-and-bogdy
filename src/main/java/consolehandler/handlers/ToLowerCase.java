package consolehandler.handlers;

public class ToLowerCase implements TextHandler{
    public String process(String input){
        return input.toLowerCase();
    }
}
