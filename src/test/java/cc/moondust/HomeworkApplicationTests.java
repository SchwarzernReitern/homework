package cc.moondust;

import cc.moondust.entity.Student;
import cc.moondust.entity.Teacher;
import cc.moondust.entity.User;
import cc.moondust.entity.mongo.Paper;
import cc.moondust.entity.mongo.Question;
import cc.moondust.repository.UserRepository;
import cc.moondust.repository.mongo.PaperRepository;
import cc.moondust.repository.mongo.QuestionRepository;
import cc.moondust.service.UserDetailService;
import cc.moondust.utils.AlibabaUtil;
import com.mongodb.util.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    DefaultTaobaoClient defaultTaobaoClient;

//    @Autowired
//    RedisTemplate<String,String> redisTemplate;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    PaperRepository paperRepository;

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

    @Test
    public void testRedis(){
//        User user = new User();
//        user.setUserId("11123421");
//        ValueOperations<String, String> stringUserValueOperations = redisTemplate.opsForValue();
//        stringUserValueOperations.set("hello",user.toString());
        for (int i = 0; i <10 ; i++) {
            userDetailService.findUser("tom");
        }
    }

    @Test
    public void testQuestion(){
        Question question1 = new Question();

        question1.setQuestionContent("1. According to the author, the jealousy emotion is________.");
        question1.addOptions("A","normal").addOptions("B","productive").addOptions("C","xsdd")
                .addTrueOption("B");
        question1.setSub(true);

        Question question2 = new Question();
        question2.setQuestionContent("Which of the following is NOT mentioned as a way to confront your friend when jealousy happens?");
        question2.addOptions("A","normal").addOptions("B","productive").addOptions("C","xsdd")
                .addTrueOption("B");
        question2.setSub(true);

        question1 = questionRepository.save(question1);
        question2 = questionRepository.save(question2);

        Question question = new Question();
        question.setQuestionContent("You know how wonderful you are, and you know that others know how wonderful you are, but what do you do when admiration crosses over the line into jealousy? For most teens there will come a day when you realize that one of your friends is jealous and that this jealousy is hurting your friendship. When this happens it can seem like there is nothing that you can do, but the good news is that there is. Don't let jealousy spoil your relationships. Tackle it headon and you might be back to normal much sooner than you think.    It can be hard to walk up to a friend and ask them what the problem is, but if you want to save your friendship you'll have to do just that. Don't approach them and ask why they are jealous of you (unless of course you want to appear totally conceited), just take some time alone with them and let them know that you've been feeling like there's been something coming between you. If they refuse to respond, then use the opportunity to explain how you have been feeling. Chances are that something you say will strike a nerve and your friend will open up as well.    When you figure out what is annoying your friend, ask him or her what (s)he thinks would make the situation better. If, for example, (s)he says that (s)he feels like (s)he doesn't get to spend any time with you because of your being off with your new friends from tee swim team then maybe you could invite her along the next time or block off one day a week for just the two of you. Remember, though, that whatever solution you decide on should be a compromise. Don't limit your own talents or opportunities simply because your friend is unhappy. Try instead to include him or her in your new life and see how that works out.    Even the best of friendships can be tinged by jealousy. This destructive emotion is rarely productive and can turn best friends into worst enemies. Before taking extreme action, chat with your jealous friend to see if the two of you can work out a compromise.If you can't, be prepared to know exactly how far you will go to keep your friendand how far you won't.");
        question.addSubQuestion(question1.getQuestionId()).addSubQuestion(question2.getQuestionId());
        question.setQuestionType(Question.QuestionType.COMPREHENSION);
        question = questionRepository.save(question);
    }

    @Test
    public void testPaper(){
        Paper paper = new Paper();
        paper.setPaperName("2017合肥一模");
        ArrayList<String> singleList = new ArrayList<>();
        singleList.add("58d758e0104d6410bcebf3c9");
        singleList.add("58d69237104d6425e47f367b");

        ArrayList<String> multipleList = new ArrayList<>();
        multipleList.add("58d6932f104d6418a442f2ca");
        paper.addPaperQuestions("一、单选题",singleList).addPaperQuestions("二、多选题", multipleList);
        Paper save = paperRepository.save(paper);

        Paper one = paperRepository.findOne("58d76039104d640b98ff986d");

    }
}
