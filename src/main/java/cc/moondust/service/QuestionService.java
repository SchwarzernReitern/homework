package cc.moondust.service;

import cc.moondust.entity.mongo.Question;
import cc.moondust.repository.mongo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang on 2017/3/27.
 */
@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    /**
     * 根据id查找题目
     * @param questionId
     * @return
     */
    public Question findQuestionById(String questionId){
        return questionRepository.findOne(questionId);
    }

    /**
     * 根据id 集合获取题目集合
     * @param questionIds
     * @return
     */
    public List<Question> findQuestionByIds(List<String> questionIds){
        return null;
    }

}
