package cc.moondust.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by tc949 on 2017/3/21.
 */
@Entity(name = "grade")
public class Grade {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解
    @GeneratedValue(generator = "idGenerator") //使用uuid的生成策略
    @Column(name = "grade_id", length = 32)
    private String gradeId;


    @Column(name = "grade_num", unique = true)
    private Integer gradeNum;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "grade_subject",
            joinColumns = {@JoinColumn(name = "grade_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    public Set<Subject> subjects;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
