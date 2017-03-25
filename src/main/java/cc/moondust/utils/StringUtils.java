package cc.moondust.utils;

/**
 * Created by tc949 on 2017/3/21.
 */
public class StringUtils {


    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        str = str.trim();
        if (str.length() == 0)
            return true;
        return false;
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }
}
