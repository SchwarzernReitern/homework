package cc.moondust.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by tc949 on 2017/3/21.
 */

@Entity(name = "student")
@DiscriminatorValue("student")
public class Student extends User {
    @Column(name = "student_ld")
    private String studentLd;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private Grade grade;
}
