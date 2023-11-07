package common2.exceptions;

public class BrokenRuleException extends Exception {
    public BrokenRuleException() {
        super("Not your turn");
    }
}

