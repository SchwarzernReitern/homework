package cc.moondust.controller;

import cc.moondust.entity.mongo.Question;
import cc.moondust.exception.ParamsException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MIKU on 2017/3/18.
 */
@Controller

public class FrontIndexController {
    @RequestMapping("/")
    @ResponseBody
    @Transactional()
    public String index() throws ParamsException {
        throw new ParamsException(400, 1230123 + "");
    }
}
