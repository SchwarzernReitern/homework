package cc.moondust.entity.mongo;


import org.springframework.data.annotation.Id;

/**
 * Created by MIKU on 2017/3/18.
 */
public class Question {

    @Id
    private String questionId;


    private String questionContent;


}
