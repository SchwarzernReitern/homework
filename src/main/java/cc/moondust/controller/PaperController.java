package cc.moondust.controller;

import cc.moondust.entity.User;
import cc.moondust.exception.UnKnowException;
import cc.moondust.service.PaperService;
import cc.moondust.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by huang.yufeng on 2017/3/29.
 */
@Controller
@RequestMapping("/api/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;
    @Autowired
    UserDetailService userDetailService;

    /**
     * 生成模拟试卷
     */
    @RequestMapping(value = "/createSimulationPaper", method = RequestMethod.POST)
    @ResponseBody
    public Object getPaperByGradeIdAndSubjectIdForSimulationTest(@RequestParam("username") String username,
                                                                 @RequestParam("grade_id") String gradeId,
                                                                 @RequestParam("subject_id") String subjectId,
                                                                 @RequestParam("paper_name") String paperName) {
        User user = userDetailService.findUserByName(username);
        return paperService.getPaperByGradeIdAndSubjectForSimulationTest(user, gradeId, subjectId, paperName);
    }

    /**
     * 生成自定义试卷
     */
    @RequestMapping(value = "/createPaper", method = RequestMethod.POST)
    @ResponseBody
    public Object getPaperByGradeIdAndSubjectId(@RequestParam("username") String username,
                                                @RequestParam("grade_id") String gradeId,
                                                @RequestParam("subject_id") String subjectId,
                                                @RequestParam("question_type_num") List<Integer> questionTypeNumList,
                                                @RequestParam("paper_name") String paperName) {
        User user = userDetailService.findUserByName(username);
        return paperService.getPaperByGradeIdAndSubjectByUserTest(user, gradeId, subjectId, questionTypeNumList, paperName);
    }

    /**
     * 获取试题
     */
    @RequestMapping(value = "/getQuestion", method = RequestMethod.GET)
    @ResponseBody
    public Object getQuestionByQuestionId(@RequestParam("question_id") String questionId) {
        return paperService.getQuestionByQuestionId(questionId);
    }

    /**
     * 从我的试卷中删除`
     */
    @RequestMapping(value = "/deletePaper", method = RequestMethod.POST)
    @ResponseBody
    public Object deletePaperByPaperId(@RequestParam(value = "username", defaultValue = "none") String username, @RequestParam("paper_id") List<String> paperIdList) throws UnKnowException {
        User user = userDetailService.findUserByName(username);
        try {
            for (String paperId : paperIdList) {
                paperService.deleteByUserIdAndPaperId(user, paperId);
            }
        } catch (Exception e) {
            throw new UnKnowException(500, e.getMessage());
        }
        return "delete success";
    }

    /**
     * 保存用户答案
     */
    @RequestMapping(value = "/registerUserAnswer", method = RequestMethod.POST)
    @ResponseBody
    public Object registerUserAnswer(@RequestParam("username") String username,
                                     @RequestParam("paper_id") String paperId,
                                     @RequestParam("question_id") String questionId,
                                     @RequestParam("user_answer") List<String> userAnswerList) throws UnKnowException {
        User user = userDetailService.findUserByName(username);
        try {
            paperService.registerUserAnswerInToAnswer(user, paperId, questionId, userAnswerList);
        } catch (Exception e) {
            throw new UnKnowException(500, e.getMessage());
        }
        return "register success";
    }

    /**
     * 获取整张试卷标准答案
     */
    @RequestMapping(value = "getStandardAnswer", method = RequestMethod.GET)
    @ResponseBody
    public Object getPaperAnswer(@RequestParam("paper_id") String paperId) {
        return paperService.getQuestionListByPaperId(paperId);
    }

    /**
     * 获取整张试卷的用户答案
     */
    @RequestMapping(value = "getUserPaperAnswer", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserPaperAnswer(@RequestParam("username") String username, @RequestParam("paper_id") String paperId) {
        User user = userDetailService.findUserByName(username);
        return paperService.getUserAnswerByUserIdAndPaperId(user.getUserId(), paperId);
    }

    /**
     * 获取每一题的答案
     */
    @RequestMapping(value = "getQuestionAnswer", method = RequestMethod.GET)
    @ResponseBody
    public Object getQuestionAnswer(@RequestParam("question_id") String questionId) {
        return paperService.getQuestionByQuestionId(questionId);
    }

    /**
     * 获取用户的每一题答案
     */
    @RequestMapping(value = "getUserAnswer", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserAnswer(@RequestParam("paper_id") String paperId,
                                @RequestParam("username") String username,
                                @RequestParam("question_id") String questionId) {
        User user = userDetailService.findUserByName(username);
        return paperService.getUserAnswerByUserIdAndPaperIdAndQuestionId(user.getUserId(), paperId, questionId);
    }

}