package cc.moondust.repository.mongo;

import cc.moondust.entity.mongo.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MIKU on 2017/3/18.
 */
@Repository
public interface QuestionRepository extends MongoRepository<Question,String> {

    List<Question> findBySubjectIdAndGradeIdAndQuestionType(String subjectId, String gradeId, Enum questionType);

    List<Question> findByGradeIdAndSubjectId(String gradeId, String subjectId);

    Question findByQuestionId(String questionId);
}
