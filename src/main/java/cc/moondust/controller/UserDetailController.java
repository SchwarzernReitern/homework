package cc.moondust.controller;

import cc.moondust.entity.User;
import cc.moondust.exception.ParamsException;
import cc.moondust.exception.UnKnowException;
import cc.moondust.service.SendMsmService;
import cc.moondust.service.UserDetailService;
import cc.moondust.utils.SendMsmUtil;
import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MIKU on 2017/3/19.
 */
@Controller
@RequestMapping("/user_detail")
public class UserDetailController {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    SendMsmService sendMsmService;

//    @Autowired
//    RedisTemplate<String, String> redisTemplate;

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param code
     * @return
     * @throws ParamsException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public User insertUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("code") String code) throws ParamsException {
        //验证码存入缓存
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        String tureCode = valueOperations.get(username);
        if (code.equals("")) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            User register = userDetailService.register(user);
            register.setUserProfile(null);
            return register;
        } else {
            throw new ParamsException(402, "code error");
        }
    }

    /**
     * 获取验证码
     *
     * @return
     */
    @RequestMapping(value = "/verificationCode", method = RequestMethod.POST)
    @ResponseBody
    public Object getVerificationCode(String phone) throws UnKnowException, ParamsException {
        String code = SendMsmUtil.getRandNum();
        //验证码存入缓存
        if (phone.length() != 11) {
            throw new ParamsException(510, "phone error");
        }
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(phone, code, 10 * 60 * 1000);
        boolean res = sendMsmService.sendMsmCode(phone, code);
        return res;
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     * @throws ParamsException
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object userLogin(String username, String password) throws ParamsException {
        User user = userDetailService.findUserByName(username);
        if (!ObjectUtils.isEmpty(user) && password.equals(user.getPassword())) {
            return "login success";
        } else {
            throw new ParamsException(402, "password is error");
        }
    }

    @RequestMapping("/error")
    @ResponseBody
    public Object getError() throws Exception {
        return null;
    }
}
