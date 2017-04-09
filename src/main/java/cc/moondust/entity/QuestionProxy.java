package cc.moondust.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by tc949 on 2017/3/21.
 */
@Entity(name = "question_proxy")
public class QuestionProxy {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2") //这个是hibernate的注解
    @GeneratedValue(generator = "idGenerator") //使用uuid的生成策略
    @Column(name = "question_proxy_id", length = 32)
    private String questionId;

    @Column(name = "question_mongo_mapping_id")
    private String questionMapping;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "question_tip",
            joinColumns = {@JoinColumn(name = "question_id")},
            inverseJoinColumns = {@JoinColumn(name = "tip_id")})
    private Set<Tip> tips;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, optional = true)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, optional = true)
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
