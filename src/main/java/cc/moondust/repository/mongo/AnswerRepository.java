package cc.moondust.repository.mongo;

import cc.moondust.entity.mongo.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huang.yufeng on 2017/3/29.
 */
@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {
    List<Answer> findAllByUserIdAndPaperId(String userId, String paperId);

    List<Answer> deleteByUserIdAndPaperId(String userId, String paperId);

    Answer findOneByUserIdAndPaperIdAndQuestionId(String userId, String paperId, String questionId);
}
