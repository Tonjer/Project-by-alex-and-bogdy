package consolehandler.handlers;

public class RemoveSpaces implements TextHandler {

    @Override
    public String process(String text) {
        return text.replace(" ", "");
    }
}