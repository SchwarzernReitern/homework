package cc.moondust.controller;

import cc.moondust.entity.User;
import cc.moondust.repository.UserRository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MIKU on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRository userRository;

    @RequestMapping("/insert")
    @ResponseBody
    public User insertUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRository.save(user);
        return user;
    }
}
