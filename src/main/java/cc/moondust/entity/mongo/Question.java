package cc.moondust.entity.mongo;


import org.springframework.data.annotation.Id;

import java.util.*;

/**
 * Created by MIKU on 2017/3/18.
 */
public class Question{

    @Id
    private String questionId;

    //题目内容
    private String questionContent;

    //选项
    private Map<String,String> options = new HashMap<>();

    //正确选项
    private Set<String> trueOption = new HashSet<>();

    //问题类型(单选/多选..)
    private QuestionType questionType = QuestionType.CHOOSE_SINGLE;

    //是否子题目
    private Boolean isSub = false;

    //子题目号
    private List<String> subQuestions;

    //科目
    private String subjectId;

    //年级
    private String gradeId;

    //标签
    private List<String> tips;

    //创建人
    private String createBy;

    //问题解析
    private String questionParse;

    public String getQuestionParse() {
        return questionParse;
    }

    public void setQuestionParse(String questionParse) {
        this.questionParse = questionParse;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public List<String> getTips() {
        return tips;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public List<String> getSubQuestions() {
        return subQuestions;
    }

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

    public Question addTip(String tip){
        if(tips == null){
            tips = new ArrayList<>();
        }
        tips.add(tip);
        return this;
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
