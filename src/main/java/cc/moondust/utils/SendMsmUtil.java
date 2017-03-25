package cc.moondust.utils;

import java.util.Random;

/**
 * Created by MIKU on 2017/3/24.
 */
public class SendMsmUtil {

    /**
     * 获取随机6位验证码
     * @return
     */
    public static String getRandNum() {
        Random random = new Random();
        String randNum = "";
        for (int i = 0; i < 6; i++) {
            randNum += String.valueOf(random.nextInt(10));
        }
        return randNum;
    }

}
