package cc.moondust.controller;

import cc.moondust.entity.User;
import cc.moondust.exception.BusinessException;
import cc.moondust.exception.ParamsException;
import cc.moondust.repository.UserRepository;
import cc.moondust.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MIKU on 2017/3/19.
 */
@Controller
@RequestMapping("/user_detail")
public class UserDetailController {

    @Autowired
    UserDetailService userDetailService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public User insertUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User register = userDetailService.register(user);
        return register;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteUser(@PathVariable("userId") String userId) throws BusinessException {
        throw new BusinessException(123, 10 + "");
    }


    @ResponseBody
    @RequestMapping("/exception")
    public String exceptionTest() throws ParamsException {

        throw new ParamsException(200, "asd");
    }
}
