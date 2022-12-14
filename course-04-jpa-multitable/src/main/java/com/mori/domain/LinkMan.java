package com.mori.domain;

import javax.persistence.*;

@Entity
@Table(name = "cst_linkman")
public class LinkMan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId; //联系人编号

    @Column(name = "lkm_name")
    private String lkmName; //联系人姓名

    @Column(name = "lkm_gender") //联系人性别
    private String lkmGender;

    @Column(name = "lkm_phone") //联系人办公电话
    private String lkmPhone;

    @Column(name = "lkm_mobile") //联系人手机
    private String lkmMobile;

    @Column(name = "lkm_email") //联系人邮箱
    private String lkmEmail;

    @Column(name = "lkm_position") //联系人职位
    private String lkmPosition;

    @Column(name = "lkm_memo") //联系人备注
    private String lkmMemo;

    //（多对一关系）从表包含一个主表的对象
    @ManyToOne(targetEntity = Customer.class) //配置多对一关系 (targetEntity = 对方的字节码)
    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id") //配置外键
    //(name = "外键名", referencedColumnName = "参照主表的字段名")
    private Customer customer;

    @Override
    public String toString() {
        return "LinkMan{" +
                "lkmId=" + lkmId +
                ", lkmName='" + lkmName + '\'' +
                ", lkmGender='" + lkmGender + '\'' +
                ", lkmPhone='" + lkmPhone + '\'' +
                ", lkmMobile='" + lkmMobile + '\'' +
                ", lkmEmail='" + lkmEmail + '\'' +
                ", lkmPosition='" + lkmPosition + '\'' +
                ", lkmMemo='" + lkmMemo + '\'' +
                '}';
    }

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
