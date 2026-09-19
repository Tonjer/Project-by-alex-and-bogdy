package consolehandler.handlers;

public class ToLowerCase implements TextHandler{
    @Override
    public String process(String input){
        return input.toLowerCase();
    }
}
