package cc.moondust.controller;

import cc.moondust.entity.User;
import cc.moondust.entity.mongo.Cat;
import cc.moondust.repository.UserRository;
import cc.moondust.repository.mongo.CatRepository;
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
    UserRository userRository;

    @Autowired
    CatRepository catRepository;

    @RequestMapping("/")
    @ResponseBody
    @Transactional()
    public Cat index() {
        Cat cat = new Cat();
        cat.setId(444);
        cat.setName("uuu");
        Cat save = catRepository.save(cat);
        return save;
    }

}
