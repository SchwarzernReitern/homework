package cc.moondust.service;

import cc.moondust.entity.User;
import cc.moondust.entity.mongo.Answer;
import cc.moondust.entity.mongo.Paper;
import cc.moondust.entity.mongo.Question;
import cc.moondust.repository.mongo.AnswerRepository;
import cc.moondust.repository.mongo.PaperRepository;
import cc.moondust.repository.mongo.QuestionRepository;
import cc.moondust.utils.CheckAnswerUtil;
import cc.moondust.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by huang.yufeng on 2017/3/29.
 */
@Service
public class PaperService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private AnswerRepository answerRepository;

    //生成一张试卷，模拟考试
    public Paper getPaperByGradeIdAndSubjectForSimulationTest(User user, String gradeId, String subjectId, String paperName){
        String paperId = "";
        //get questionId by gradeId and subjectId
        List<Question> questionList = questionRepository.findByGradeIdAndSubjectId(gradeId, subjectId);
        //单选
        List<String> allChooseSingle = new ArrayList<>();
        //多选
        List<String> allChooseMulti = new ArrayList<>();
        //填空
        List<String> allBlankFull = new ArrayList<>();
        //阅读理解
        List<String> allJudge = new ArrayList<>();
        //解答
        List<String> allComprehension = new ArrayList<>();
        for(Question question:questionList){
            if(question.getQuestionType().equals(Question.QuestionType.CHOOSE_SINGLE)){
                allChooseSingle.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(Question.QuestionType.CHOOSE_MULTI)){
                allChooseMulti.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(Question.QuestionType.BLANK_FILL)){
                allBlankFull.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(Question.QuestionType.JUDGE)){
                allJudge.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(Question.QuestionType.COMPREHENSION)){
                allComprehension.add(question.getQuestionId());
            }
        }
        //单选
        List<String> selectChooseSingle = new ArrayList<>();
        //多选
        List<String> selectChooseMulti = new ArrayList<>();
        //填空
        List<String> selectBlankFull = new ArrayList<>();
        //阅读理解
        List<String> selectJudge = new ArrayList<>();
        //解答
        List<String> selectComprehension = new ArrayList<>();
        //随机获取10题单选
        Set<Integer> set = RandomUtil.getRandomSet(allChooseSingle.size(), 1);
        for(Integer i:set){
            selectChooseSingle.add(allChooseSingle.get(i-1));
        }
        //随机获取5题多选题
        for(Integer i:RandomUtil.getRandomSet(allChooseMulti.size(), 1)){
            selectChooseMulti.add(allChooseMulti.get(i-1));
        }
        //随机获取5题填空题
        for(Integer i:RandomUtil.getRandomSet(allBlankFull.size(), 1)){
            selectBlankFull.add(allBlankFull.get(i-1));
        }
        //随机获取1题阅读理解
        for(Integer i:RandomUtil.getRandomSet(allJudge.size(), 1)){
            selectJudge.add(allJudge.get(i-1));
        }
        //随机获取1题解答题
        for(Integer i:RandomUtil.getRandomSet(allComprehension.size(), 1)){
            selectComprehension.add(allComprehension.get(i-1));
        }
        Paper paper = new Paper();
        paper.setPaperName(paperName);
        paper.addPaperQuestions(Question.QuestionType.CHOOSE_SINGLE.toString(), selectChooseSingle);
        paper.addPaperQuestions(Question.QuestionType.CHOOSE_MULTI.toString(), selectChooseMulti);
        paper.addPaperQuestions(Question.QuestionType.BLANK_FILL.toString(), selectBlankFull);
        paper.addPaperQuestions(Question.QuestionType.JUDGE.toString(), selectJudge);
        paper.addPaperQuestions(Question.QuestionType.COMPREHENSION.toString(), selectComprehension);
        paper = paperRepository.insert(paper);

        List<String> paperQuestionIdList = new ArrayList<>();
        paperQuestionIdList.addAll(selectChooseSingle);
        paperQuestionIdList.addAll(selectChooseMulti);
        paperQuestionIdList.addAll(selectBlankFull);
        paperQuestionIdList.addAll(selectJudge);
        paperQuestionIdList.addAll(selectComprehension);
        for (String questionId:paperQuestionIdList){
            Answer answer = new Answer();
            answer.setPaperId(paper.getPaperId());
            answer.setUserId(user.getUserId());
            answer.setQuestionId(questionId);
            answerRepository.insert(answer);
        }

        return paper;
    }
    //生成一张试卷，自定义测试
    public Paper getPaperByGradeIdAndSubjectByUserTest(User user, String gradeId, String subjectId, List<Integer> questionTypeNumList, String paperName){
        //get questionId by gradeId and subjectId
        List<Question> questionList = questionRepository.findByGradeIdAndSubjectId(gradeId, subjectId);
        //单选
        List<String> allChooseSingle = new ArrayList<>();
        //多选
        List<String> allChooseMulti = new ArrayList<>();
        //填空
        List<String> allBlankFull = new ArrayList<>();
        //阅读理解
        List<String> allJudge = new ArrayList<>();
        //解答
        List<String> allComprehension = new ArrayList<>();
        for(Question question:questionList){
            if(question.getQuestionType().equals(0)){
                allChooseSingle.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(1)){
                allChooseMulti.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(2)){
                allBlankFull.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(3)){
                allJudge.add(question.getQuestionId());
            }else if(question.getQuestionType().equals(4)){
                allComprehension.add(question.getQuestionId());
            }
        }
        //单选
        List<String> selectChooseSingle = new ArrayList<>();
        //多选
        List<String> selectChooseMulti = new ArrayList<>();
        //填空
        List<String> selectBlankFull = new ArrayList<>();
        //阅读理解
        List<String> selectJudge = new ArrayList<>();
        //解答
        List<String> selectComprehension = new ArrayList<>();
        //随机获取指定题数单选
        if(questionTypeNumList.get(0) != 0){
            for(Integer i:RandomUtil.getRandomSet(allChooseSingle.size(), questionTypeNumList.get(0))){
                selectChooseSingle.add(allChooseSingle.get(i));
            }
        }
        //随机获取指定题数多选题
        if(questionTypeNumList.get(1) != 0){
            for(Integer i:RandomUtil.getRandomSet(allChooseMulti.size(), 1)){
                selectChooseMulti.add(allChooseMulti.get(i));
            }
        }
        //随机获取指定题数填空题
        if(questionTypeNumList.get(2) != 0){
            for(Integer i:RandomUtil.getRandomSet(allBlankFull.size(), 1)){
                selectBlankFull.add(allBlankFull.get(i));
            }
        }
        //随机获取指定题数阅读理解
        if(questionTypeNumList.get(3) != 0){
            for(Integer i:RandomUtil.getRandomSet(allJudge.size(), 1)){
                selectJudge.add(allJudge.get(i));
            }
        }
        //随机获取指定题数解答题
        if(questionTypeNumList.get(4) != 0){
            for(Integer i:RandomUtil.getRandomSet(allComprehension.size(), 1)){
                selectComprehension.add(allComprehension.get(i));
            }
        }

        Paper paper = new Paper();
        paper.setPaperName(paperName);
        paper.addPaperQuestions(Question.QuestionType.CHOOSE_SINGLE.toString(), selectChooseSingle);
        paper.addPaperQuestions(Question.QuestionType.CHOOSE_MULTI.toString(), selectChooseMulti);
        paper.addPaperQuestions(Question.QuestionType.BLANK_FILL.toString(), selectBlankFull);
        paper.addPaperQuestions(Question.QuestionType.JUDGE.toString(), selectJudge);
        paper.addPaperQuestions(Question.QuestionType.COMPREHENSION.toString(), selectComprehension);
        paper = paperRepository.insert(paper);

        List<String> paperQuestionIdList = new ArrayList<>();
        paperQuestionIdList.addAll(selectChooseSingle);
        paperQuestionIdList.addAll(selectChooseMulti);
        paperQuestionIdList.addAll(selectBlankFull);
        paperQuestionIdList.addAll(selectJudge);
        paperQuestionIdList.addAll(selectComprehension);

        for (String questionId:paperQuestionIdList){
            Answer answer = new Answer();
            answer.setPaperId(paper.getPaperId());
            answer.setUserId(user.getUserId());
            answer.setQuestionId(questionId);
            answerRepository.insert(answer);
        }
        return paper;
    }
    //根据题目id获取题目
    public Question getQuestionByQuestionId(String questionId){
        return questionRepository.findByQuestionId(questionId);
    }

    //根据userId 和 Paper Id 删除我的试卷收藏
    public void deleteByUserIdAndPaperId(User user, String paperId){
//        Answer answer = new Answer();
//        answer.setPaperId(paperId);
//        answer.setUserId(user.getUserId());
        answerRepository.deleteByUserIdAndPaperId(user.getUserId(), paperId);
    }

    //提交题目，把用户答案存入数据库
    public Answer registerUserAnswerInToAnswer(User user, String paperId, String questionId, List<String> userAnswerList){
        Answer answer = new Answer();
        answer.setUserId(user.getUserId());
        answer.setPaperId(paperId);
        answer.setQuestionId(questionId);
        answer.setUserAnswers(userAnswerList);
        //获取正确答案
        Set<String> standardAnswer = questionRepository.findByQuestionId(questionId).getTrueOption();
        answer.setWrongFlg(CheckAnswerUtil.checkAnswer(userAnswerList, standardAnswer));
        answerRepository.insert(answer);

        return answer;
    }

    //根据用户和paper Id获取用户试卷答案
    public List<Answer> getUserAnswerByUserIdAndPaperId(String userId, String paperId){
        return answerRepository.findAllByUserIdAndPaperId(userId, paperId);
    }
    //根据paperId获取整张试卷的答案
    public List<Question> getQuestionListByPaperId(String paperId){
        Collection<List<String>> collections = paperRepository.findOne(paperId).getPaperQuestions().values();
        List<String> questionIdList = new ArrayList<>();
        for(List<String> list:collections){
            questionIdList.addAll(list);
        }
        List<Question> questionList = new ArrayList<>();
        for(String questionId:questionIdList){
            questionList.add(questionRepository.findByQuestionId(questionId));
        }
        return questionList;
    }

    //根据用户，paper Id， question Id获取用户一题的答案
    public Answer getUserAnswerByUserIdAndPaperIdAndQuestionId(String userId, String paperId, String questionId){
        return answerRepository.findOneByUserIdAndPaperIdAndQuestionId(userId, paperId, questionId);
    }
}
