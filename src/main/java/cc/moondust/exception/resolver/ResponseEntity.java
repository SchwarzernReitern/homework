package cc.moondust.exception.resolver;

import java.util.Date;

/**
 * Created by MIKU on 2017/3/25.
 */
public class ResponseEntity {

    private int code = 200;

    private String message;

    private Object responseBody;

    private String errorStack;

    private long timestamp = new Date().getTime();

    /**
     * 错误返回对象
     *
     * @param code
     * @param message
     * @param errorStack
     */
    public ResponseEntity(int code, String message, String errorStack) {
        this.code = code;
        this.message = message;
        this.errorStack = errorStack;
    }

    /**
     * 正确返回对象
     *
     * @param message
     * @param responseBody
     */
    public ResponseEntity(String message, Object responseBody) {
        this.message = message;
        this.responseBody = responseBody;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", responseBody=" + responseBody +
                ", errorStack='" + errorStack + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
