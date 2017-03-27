package cc.moondust.service;

import cc.moondust.entity.User;
import cc.moondust.exception.UnKnowException;
import cc.moondust.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang.yufeng on 2017/3/21.
 */
@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public String register (User user) throws UnKnowException {
        try {
            User user1 = userRepository.saveAndFlush(user);
        }catch (Exception e){
           throw new UnKnowException(500,e.getMessage());
        }
        return null;
    }

}
