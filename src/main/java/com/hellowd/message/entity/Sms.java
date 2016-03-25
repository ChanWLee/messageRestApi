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
@Table(name="SC_LOG")
public class Sms extends Message{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TR_NUM")
    private long id;

    @Column(name = "TR_SENDDATE")
    private String senddate = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()+(2*1000));
    @Column(name = "TR_SENDSTAT")
    private String sendstat = "0";
    @Column(name = "TR_RSLTSTAT")
    private String rsltstat = "00";
    @Column(name = "TR_PHONE")
    private String phone;
    @Column(name = "TR_CALLBACK")
    private String callback;
    @Column(name = "TR_MSG")
    private String msg;
    @Column(name = "TR_REALSENDDATE")
    private String realsenddate;
    @Column(name = "TR_MSGTYPE")
    private String msgType = "0";


    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRsltstat() {
        return rsltstat;
    }

    public void setRsltstat(String rsltstat) {
        this.rsltstat = rsltstat;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getSendstat() {
        return sendstat;
    }

    public void setSendstat(String sendstat) {
        this.sendstat = sendstat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRealsenddate() {
        return realsenddate;
    }

    public void setRealsenddate(String realSenddate) {
        this.realsenddate = realSenddate;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Sms(){}
    public Sms(Message message) {
        this.phone = message.getPhone();
        this.callback = message.getCallback();
        this.msg = message.getMsg();
    }

    @Override
    public String toString() {
        return "Sms{" +
                "callback='" + callback + '\'' +
                ", id=" + id +
                ", senddate='" + senddate + '\'' +
                ", sendstat='" + sendstat + '\'' +
                ", rsltstat='" + rsltstat + '\'' +
                ", phone='" + phone + '\'' +
                ", msg='" + msg + '\'' +
                ", realsenddate='" + realsenddate + '\'' +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
