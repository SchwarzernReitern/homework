package cc.moondust.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * Created by tc949 on 2017/3/21.
 */

@Entity(name = "teacher")
@DiscriminatorValue("teacher")
public class Teacher extends User {
    @Column(name = "teacher_ld")
    private String teacherLd;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_subject",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    public Set<Subject> subjects;


    public String getTeacherLd() {
        return teacherLd;
    }

    public void setTeacherLd(String teacherLd) {
        this.teacherLd = teacherLd;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

}
