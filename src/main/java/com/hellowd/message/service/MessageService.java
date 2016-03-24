package com.hellowd.message.service;

import com.hellowd.message.entity.*;
import com.hellowd.message.repository.MmsRepository;
import com.hellowd.message.repository.MmsSaveRepository;
import com.hellowd.message.repository.SmsRepository;
import com.hellowd.message.repository.SmsSaveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helloworld
 * User : chanwoo.lee
 * Date : 2015-11-19
 * Time : 오후 4:10
 * 해당 클래스에 대한 기능 설명
 */
@Service
@Transactional
public class MessageService {
    @Autowired SmsRepository smsRepository;
    @Autowired MmsRepository mmsRepository;
    @Autowired SmsSaveRepository smsSaveRepository;
    @Autowired MmsSaveRepository mmsSaveRepository;

    public static final int SMS = 1;
    public static final int MMS = 2;
//    public static final int SMS_MAX_LENG = 20;
//    public static final int MMS_MAX_LENG = 30;
    public static final int SUB_MAX_LENG = 40;
    public static final int SMS_MAX_LENG = 140;
    public static final int MMS_MAX_LENG = 2000;

    Logger logger = LoggerFactory.getLogger(MessageService.class);

    public Message save(Message message) {
        int flag = 0;
        if(message != null)
            flag = message.getFlag();
//        String msg = StringReplace(message.getMsg());
        String msg = message.getMsg();

        if(flag == SMS){
            message.setMsg(getCuttingMessage(msg, SMS_MAX_LENG));
            message = saveSms(message);

        }else if(flag == MMS){
            message.setMsg(getCuttingMessage(msg, MMS_MAX_LENG));
            message = saveMms(message);

        }else{
            int msgLeng = msg.getBytes().length;
            message.setMsg(msg);

            if(msgLeng > 0 && msgLeng < SMS_MAX_LENG){
                message = saveSms(message);

            }else if(msgLeng < MMS_MAX_LENG){
                message = saveMms(message);

            }else if(msgLeng >= MMS_MAX_LENG){
                message.setMsg(getCuttingMessage(msg, MMS_MAX_LENG));
                logger.info("cutting msg : [{}]", message.getMsg());
                message = saveMms(message);

            }else{
            }
        }return message;
    }

    public List<?> getList(){
        return merge( smsRepository.findAllByOrderBySenddateDesc(), mmsRepository.findAllByOrderBySenddateDesc());
    }
    public List<Sms> getListSms(){
        return smsRepository.findAllByOrderBySenddateDesc();
    }
    public Sms getOneSms(Long id){
        return smsRepository.findOne(id);
    }
    public List<Mms> getListMms(){
        return mmsRepository.findAllByOrderBySenddateDesc();
    }
    public Mms getOneMms(Long id){
        return mmsRepository.findOne(id);
    }


    private Message saveMms(Message message) {
//        logger.info("log test save MMS [{}]", message.getMsg());
        message.setId(mmsRepository.findFirstByOrderByIdDesc().getId()+1);
        MmsSave mms = mmsSaveRepository.save(new MmsSave(message));
        int status = Integer.parseInt(mms.getStatus());
        switch(status) {
            case 2: case 3: status-=1; break;
        }
        message.setStatus(String.valueOf(status));
        message.setRealsenddate(mms.getSentdate());
        return message;
    }

    private Message saveSms(Message message) {
//        logger.info("log test save Sms [{}]", message.getMsg());
        message.setId(smsRepository.findFirstByOrderByIdDesc().getId()+1);
        SmsSave sms = smsSaveRepository.save(new SmsSave(message));
        message.setStatus(sms.getSendstat());
        message.setRealsenddate(sms.getRealsenddate());
        return message;
    }


    public static List<?> merge(List<?>...lists) {
        List<String> mergedLists = new ArrayList<>();
        for(List<?>list:lists){
            mergedLists.addAll((List<String>)list);
        }
        return mergedLists;
    }
    public static String StringReplace(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]"; //특수문자 제거 하기
        str = str.replaceAll(match, " ");
        String match2 = "\\s{2,}"; //연속 스페이스 제거
        str = str.replaceAll(match2, " ");
        return str;
    }
    public static String getCuttingMessage(String str, int maxbytes){
        byte[] strBytes = str.getBytes();
        int cnt = 0;
        String rtnStr;

        for(int i = 0; i<maxbytes; i++){
            if(strBytes[i] < 0)//한글, 한자는 음수
                cnt++;
        }
        if(cnt % 2 == 0)
            rtnStr = new String(str.getBytes(), 0, maxbytes);
        else
            rtnStr = new String(str.getBytes(), 0, maxbytes-1);

        return rtnStr;
    }
}
