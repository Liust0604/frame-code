package com.mori.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户实现类
 */
@Entity //声明实体类
@Table(name = "cst_customer") //1、指定实体类和表的映射关系;name指定数据库表名
public class Customer {

    @Id //声明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键生成策略，自增
    @Column(name = "cust_id") //2、指定类属性和表字段的映射关系；name指定数据库表的字段名
    private Long custId; //客户主键

    @Column(name = "cust_name")
    private String custName; //客户名称

    @Column(name = "cust_source")
    private String custSource; //客户来源

    @Column(name = "cust_industry")
    private String custIndustry; //客户行业

    @Column(name = "cust_level")
    private String custLevel; //客户级别

    @Column(name = "cust_address")
    private String custAddress; //客户地址

    @Column(name = "cust_phone")
    private String custPhone; //客户电话

    //（一对多关系）主表包含一个从表的集合（推荐用set）
    //@OneToMany(targetEntity = LinkMan.class) //配置一对多关系 (targetEntity = 对方的字节码)
    //@JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id") //配置外键（name = 外键字段名,referencedColumnName = 参照主表的主键名）
    //上方配置，会使得主表拥有主键维护权利，会多发update语句。
    // 应当主表放弃主键维护权，只用声明关系即可
    //放弃主键维护权(mappedBy = 被外键关联的对象)
    //只有linkMan中@JoinColumn单方面配置了外键的指向，对应的属性名为customer，和当前Customer的mappedBy = "customer"形成映射关系
    //级联操作的主体需要加上cascade属性
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<LinkMan> linkMans = new HashSet<>();

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", linkMans=" + linkMans +
                '}';
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }
}
