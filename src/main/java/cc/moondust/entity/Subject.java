package cc.moondust.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tc949 on 2017/3/21.
 */
@Entity(name = "subject")
public class Subject {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2") //这个是hibernate的注解
    @GeneratedValue(generator = "idGenerator") //使用uuid的生成策略
    @Column(name = "subject_id", length = 32)
    private String subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
