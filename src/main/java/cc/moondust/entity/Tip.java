package cc.moondust.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tc949 on 2017/3/21.
 */
@Entity(name = "tip")
public class Tip {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解
    @GeneratedValue(generator = "idGenerator") //使用uuid的生成策略
    @Column(name = "tip_id", length = 32)
    private String tipId;


    @Column(name = "tip_content")
    private String tipContent;


    @Column(name = "question_count")
    private Integer questionCount;





}
