package com.mori.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "age")
    private Integer age;

    //（多对多关系）创建另一个表的集合对象
    @ManyToMany(targetEntity = Role.class , cascade = CascadeType.ALL) //配置多对多关系 (targetEntity = 对方的字节码)
    @JoinTable(name = "sys_user_role",//配置中间表 (name = 中间表名)
            joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")}, //joinColumns 当前对象在中间表中的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")}) //inverseJoinColumns 对方对象在中间表的外键
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}