package cc.moondust.repository;

import cc.moondust.entity.Teacher;
import cc.moondust.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by MIKU on 2017/3/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findOneByUsername(String username);

    Teacher findOnetByTeacherLd(String teacherLd);

    User findOneByUserProfile_IdCardNum(String iDCard);
}
