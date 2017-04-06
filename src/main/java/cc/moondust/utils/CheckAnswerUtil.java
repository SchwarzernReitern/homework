package cc.moondust.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by huang.yufeng on 2017/3/29.
 */
public class CheckAnswerUtil {
    //返回用户选择的集合
    public static boolean checkAnswer(List<String> userAnswer, Set<String> standardAnswer){
        if(userAnswer.size() != standardAnswer.size()){
            return false;
        }else{
            //用户答案
            String answer1 = "";
            //标准答案
            String answer2 = "";
            for(String str:userAnswer){
                answer1 = answer1 + str;
            }
            for(String str:standardAnswer){
                answer2 = answer2 + str;
            }
            if(answer1.equals(answer2)){
                return true;
            }else{
                return false;
            }
        }
    }


}
