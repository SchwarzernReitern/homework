package cc.moondust.service;

import cc.moondust.entity.mongo.Question;
import cc.moondust.repository.mongo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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
        Question question = questionRepository.findOne(questionId);
        if (!ObjectUtils.isEmpty(question)){
            return question;
        }else {
            return null;
        }
    }

    /**
     * 根据id 集合获取题目集合
     * @param questionIds
     * @return
     */
    public List<Question> findQuestionByIds(List<String> questionIds){
        ArrayList<Question> questionList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(questionIds)) {
            questionIds.stream().forEach(id -> questionList.add(findQuestionById(id)));
        }
        return questionList;
    }

    /**
     * 根据 科目、年级、类型 获取题目
     * @param subjectId
     * @param gradeId
     * @param questionType
     * @return
     */
    public List<Question> findQuestionByExample(String subjectId, String gradeId, Question.QuestionType questionType){
        List<Question> questionList = questionRepository.findBySubjectIdAndGradeIdAndQuestionType(subjectId, gradeId, questionType);
        return questionList;
    }

}
