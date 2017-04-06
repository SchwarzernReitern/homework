package cc.moondust.utils;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by huang.yufeng on 2017/3/29.
 */
public class RandomUtil {
    /*
    number1 多少以内的数字
    number2 得到多少个不重复的整数
     */
    public static HashSet<Integer> getRandomSet(int number1, int number2){
        Random random = new Random();
        HashSet<Integer> set = new HashSet<Integer>();
        while(set.size()<number2){
            int randomNumber = random.nextInt(number1)+1;
            set.add(randomNumber);
        }
        return set;
    }
}
