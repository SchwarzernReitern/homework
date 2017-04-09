package cc.moondust.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by tc949 on 2017/3/21.
 */
@Entity(name = "user_profile")
public class UserProfile {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "user_profile_id", length = 32)
    private String userProfileId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "idcard_num")
    private String idCardNum;


    @Column(name = "address")
    private String address;


    @Column(name = "email")
    private String email;


    @Column(name = "mobile")
    private String mobile;


    @JoinColumn(name = "school_id")
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private School school;
}
