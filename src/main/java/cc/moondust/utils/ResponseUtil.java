package cc.moondust.utils;

import cc.moondust.exception.UnKnowException;
import cc.moondust.exception.resolver.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MIKU on 2017/3/25.
 */
public class ResponseUtil {

    private ResponseUtil() {
    }

    public static Object buildRes(Object obj) throws UnKnowException {
        if (obj == null) {
            throw new UnKnowException(500, "responsebody is empty");
        }
        ResponseEntity entity = new ResponseEntity("success", obj);
        return entity;
    }
}
