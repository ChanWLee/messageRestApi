package com.hellowd.message.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;

/**
 * Created by Helloworld
 * User : chanwoo.lee
 * Date : 2015-11-20
 * Time : 오전 11:47
 * 해당 클래스에 대한 기능 설명
 */
@Entity
@Table(name="MMS_MSG")
public class MmsSave extends Message{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MSGKEY")
    private long id;

    @Column(name = "REQDATE")
    private String senddate = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "STATUS")
    private String status = "0";
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "CALLBACK")
    private String callback;
    @Column(name = "MSG")
    private String msg;
    @Column(name = "SENTDATE")
    private String sentdate;

    @Override
    public String getCallback() {
        return callback;
    }

    @Override
    public void setCallback(String callback) {
        this.callback = callback;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSentdate() {
        return sentdate;
    }

    public void setSentdate(String sentdate) {
        this.sentdate = sentdate;
    }

    public MmsSave(){}
    public MmsSave(Message message) {
        this.id = message.getId();
        this.phone = message.getPhone();
        this.callback = message.getCallback();
        this.msg = message.getMsg();
        this.subject = message.getSubject();
    }
}
