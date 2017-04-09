package cc.moondust.controller;

import cc.moondust.entity.mongo.Question;
import cc.moondust.exception.BusinessException;
import cc.moondust.exception.ParamsException;
import cc.moondust.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MIKU on 2017/3/25.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /**
     * 获取题目
     * @param questionId
     * @return
     * @throws ParamsException
     */
    @RequestMapping("/getquestion")
    @ResponseBody
    public Object getQuestionById(String questionId) throws ParamsException {
        Question question = questionService.findQuestionById(questionId);
        if (!ObjectUtils.isEmpty(question)){
            return  question;
        }else {
            throw new ParamsException(402,"题目不存在");
        }
    }

    @RequestMapping(value = "/recordquestion", method = RequestMethod.POST)
    @ResponseBody
    public Object recordQuestion(@RequestParam(name = "question", required = false)Question question){


        return null;
    }

}
