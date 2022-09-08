package com.mori.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    //（多对多关系）创建另一个表的集合对象
    //@ManyToMany(targetEntity = User.class) //配置多对多关系 (targetEntity = 对方的字节码)
    /*@JoinTable(name = "sys_user_role",//配置中间表 (name = 中间表名)
            joinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")}, //joinColumns 当前对象在中间表中的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")}) //inverseJoinColumns 对方对象在中间表的外键*/
    @ManyToMany(mappedBy = "roles") //放弃中间表维护权 @ManyToMany(mappedBy = 对方映射的属性)
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
