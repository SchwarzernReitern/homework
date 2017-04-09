package cc.moondust.entity;

import cc.moondust.utils.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by MIKU on 2017/3/18.
 */
@Entity(name = "base_user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public class User implements UserDetails {
    static String split = ";";
    @Id
    //jpa (hibernate实现)的UUID生成主键策略    //eclipse会提示错误，但程序可以执行
    @GenericGenerator(name = "idGenerator", strategy = "uuid2") //这个是hibernate的注解
    @GeneratedValue(generator = "idGenerator") //使用uuid的生成策略
    @Column(name = "user_id", length = 32)
    private String userId;

    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 32)
    private String password;


    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;


    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Column(name = "is_enable")
    private boolean enabled = true;


    @Column(name = "create_datetime")
    private Date createTime = new Date();


    @Column(name = "last_updatetime")
    private Date lastUpdateTime = new Date();

    @Column(name = "auths")
    private String auths;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_profile_id", unique = true, nullable = true)
    private UserProfile userProfile;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        if (StringUtils.notEmpty(auths)) {
            String[] aus = this.auths.split(User.split);
            for (String au : aus) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(au));
            }
        }
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
