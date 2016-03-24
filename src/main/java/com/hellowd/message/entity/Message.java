package com.hellowd.message.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Helloworld
 * User : chanwoo.lee
 * Date : 2015-11-20
 * Time : 오후 3:05
 * 해당 클래스에 대한 기능 설명
 */
@XmlRootElement
public class Message {
    private long id;
    //0: 구분없음, 1: sms, 2:mms
    private int flag = 0;
    private String msg;
    private String phone;
    private String callback;
    private String subject;
    private String status = "0";
    private String realsenddate;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRealsenddate() {
        return realsenddate;
    }

    public void setRealsenddate(String realsenddate) {
        this.realsenddate = realsenddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "callback='" + callback + '\'' +
                ", id=" + id +
                ", flag=" + flag +
                ", msg='" + msg + '\'' +
                ", phone='" + phone + '\'' +
                ", subject='" + subject + '\'' +
                ", status='" + status + '\'' +
                ", realsenddate='" + realsenddate + '\'' +
                '}';
    }
}
