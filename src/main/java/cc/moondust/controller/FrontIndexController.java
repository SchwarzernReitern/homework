package cc.moondust.controller;

import cc.moondust.entity.mongo.Question;
import cc.moondust.exception.ParamsException;
import cc.moondust.exception.UnKnowException;
import cc.moondust.service.SendMsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MIKU on 2017/3/18.
 */
@Controller

public class FrontIndexController {

    @Autowired
    SendMsmService sendMsmService;

    @RequestMapping("/api/in")
    @ResponseBody
    @Transactional()
    public String index() throws ParamsException, UnKnowException {

        sendMsmService.sendMsmCode("15655251016", "123456");
        return "OK";
    }
}
