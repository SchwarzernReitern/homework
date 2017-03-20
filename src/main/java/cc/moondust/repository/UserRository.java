package cc.moondust.repository;

import cc.moondust.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MIKU on 2017/3/18.
 */
@Repository
public interface UserRository extends JpaRepository<User,String>{

    User findOneByUsername(String username);

}
