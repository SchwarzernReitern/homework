package cc.moondust.repository.mongo;

import cc.moondust.entity.mongo.Cat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MIKU on 2017/3/18.
 */
@Repository
public interface CatRepository extends MongoRepository<Cat,Integer> {
}
