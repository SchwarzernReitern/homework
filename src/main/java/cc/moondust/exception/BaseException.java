package cc.moondust.exception;

/**
 * Created by tc949 on 2017/3/21.
 */
public abstract class BaseException extends Exception {
    private int code;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }
    private BaseException() {
    }

    public int getCode() {
        return code;
    }
}
