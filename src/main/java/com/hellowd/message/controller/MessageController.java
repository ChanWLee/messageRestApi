package com.hellowd.message.controller;

import com.hellowd.message.entity.Message;
import com.hellowd.message.entity.Mms;
import com.hellowd.message.entity.Sms;
import com.hellowd.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Helloworld
 * User : chanwoo.lee
 * Date : 2015-11-20
 * Time : 오전 11:33
 * 해당 클래스에 대한 기능 설명
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    Logger logger = LoggerFactory.getLogger(MessageService.class);

    /**
     * sms, mms list
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<?> getList() {
        return messageService.getList();
    }

    /**
     * sms list
     * @return
     */
    @RequestMapping(value = "sms", method = RequestMethod.GET)
    public List<Sms> getListSms() {
        return messageService.getListSms();
    }

    @RequestMapping(value = "sms/{id}", method = RequestMethod.GET)
    public Sms getSms(@PathVariable("id") Long id) {
        return messageService.getOneSms(id);
    }

    /**
     * mms list
     * @return
     */
    @RequestMapping(value = "mms", method = RequestMethod.GET)
    public List<Mms> getListMms() {
        return messageService.getListMms();
    }

    @RequestMapping(value = "mms/{id}", method = RequestMethod.GET)
    public Mms getMms(@PathVariable("id") Long id) {
        return messageService.getOneMms(id);
    }

    /**
     * sms, mms 입력
     * @param message
     * @return status:: 0:발송대기, 1:전송완료, 2:결과수신완료
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestBody Message message) {
        if (message.getPhone() != null) {
            message = messageService.save(message);
        }
        return message.getStatus();
    }
}
