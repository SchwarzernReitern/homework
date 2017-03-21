package cc.moondust;

import cc.moondust.entity.Student;
import cc.moondust.entity.Teacher;
import cc.moondust.entity.User;
import cc.moondust.repository.UserRepository;
import cc.moondust.utils.AlibabaUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DefaultTaobaoClient defaultTaobaoClient;

    @Test
    public void contextLoads() {

        User user = userRepository.findOneByUserProfile_IdCardNum("json");
        if (user == null) {
            throw new UsernameNotFoundException("没有用户");
        }
        if (user instanceof Teacher) {
            System.out.println("1");
        } else if (user instanceof Student) {
            System.out.println("2");
        } else {
            System.out.println("3");
        }
    }

    @Test
    public  void testApi(){
        AlibabaAliqinFcSmsNumSendRequest request = AlibabaUtil.getRequest("18158911403", "456456");

        try {
            AlibabaAliqinFcSmsNumSendResponse response = defaultTaobaoClient.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }

    }


}
