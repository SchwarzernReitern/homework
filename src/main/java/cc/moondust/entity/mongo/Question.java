package cc.moondust.entity.mongo;


import org.springframework.data.annotation.Id;

import java.util.*;

/**
 * Created by MIKU on 2017/3/18.
 */
public class Question{

    @Id
    private String questionId;

    private String questionContent;

    private Map<String,String> options = new HashMap<>();

    private Set<String> trueOption = new HashSet<>();

    private QuestionType questionType = QuestionType.CHOOSE_SINGLE;

    private Boolean isSub = false;

    public List<String> getSubQuestions() {
        return subQuestions;
    }

    private List<String> subQuestions;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public Set<String> getTrueOption() {
        return trueOption;
    }

    public Question addOptions(String key, String value){
        this.options.put(key,value);
        return this;
    }

    public Question addTrueOption(String option){
        trueOption.add(option);
        return this;
    }

    public Question addSubQuestion(String questionId){
        if(subQuestions == null){
            subQuestions = new ArrayList<>();
        }
        subQuestions.add(questionId);
        return  this;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Boolean getSub() {
        return isSub;
    }

    public void setSub(Boolean sub) {
        isSub = sub;
    }

    public static enum QuestionType{
        CHOOSE_SINGLE(0),CHOOSE_MULTI(1),BLANK_FILL(2),JUDGE(3),COMPREHENSION(4);

        private Integer type;

        QuestionType(Integer type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type.toString();
        }
    }

}
