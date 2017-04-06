package cc.moondust.entity.mongo;

import cc.moondust.entity.User;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by huang.yufeng on 2017/3/29.
 */
public class Answer {

    @Id
    private String answerId;

    private String questionId;

    private String userId;

    private String paperId;

    private List<String> userAnswers;

    private boolean isWrongFlg = true;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public boolean isWrongFlg() {
        return isWrongFlg;
    }

    public void setWrongFlg(boolean wrongFlg) {
        isWrongFlg = wrongFlg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<String> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
