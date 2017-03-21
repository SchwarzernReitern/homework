package cc.moondust.exception;

/**
 * Created by tc949 on 2017/3/21.
 */
public class BusinessException extends BaseException {


    public BusinessException(int code, String message) {
        super(code, message);
    }
}
