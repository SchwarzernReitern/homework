package cc.moondust.service;

import cc.moondust.entity.User;
import cc.moondust.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tc949 on 2017/3/21.
 */
@Service
public class UserDetailService {
    @Autowired
    UserRepository userRepository;
    @Transactional
    public User register(User user) {
        User save = userRepository.save(user);
        return save;
    }

    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    public User findUser(String lastName){
        System.out.println("无缓存的时候调用这里");
        return userRepository.findOneByUsername(lastName);
    }

    public User findUserByName(String username){
        return userRepository.findOneByUsername(username);
    }
}
