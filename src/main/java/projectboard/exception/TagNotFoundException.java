package projectboard.exception;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String msg){
        super(msg);
    }
}
