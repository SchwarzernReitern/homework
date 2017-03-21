package cc.moondust.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tc949 on 2017/3/21.
 */
@Entity(name = "school")
public class School {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解
    @GeneratedValue(generator = "idGenerator") //使用uuid的生成策略
    @Column(name = "school_id", length = 32)
    private String schoolId;

    @Column(name = "school_name", length = 50)
    private String schoolName;

}
