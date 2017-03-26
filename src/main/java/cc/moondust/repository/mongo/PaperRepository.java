package cc.moondust.repository.mongo;

import cc.moondust.entity.mongo.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiang on 2017/3/26.
 */
@Repository
public interface PaperRepository extends MongoRepository<Paper, String> {

}
