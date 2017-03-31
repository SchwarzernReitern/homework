package cc.moondust.repository;

import cc.moondust.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiang on 2017/3/29.
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade,String> {
}
