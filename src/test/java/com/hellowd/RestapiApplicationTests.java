package com.hellowd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellowd.message.controller.MessageController;
import com.hellowd.message.entity.Message;
import com.hellowd.message.service.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestapiApplication.class)
@WebIntegrationTest
@Transactional
public class RestapiApplicationTests {

//	Logger logger = Logger.getLogger(this.getClass());

    private MockMvc mockMvc;
    private String baseUrl;
    RestTemplate restTemplate;

    @Autowired
    private MessageController messageController;
    @Autowired
    private MessageService messageService;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        baseUrl = "http://localhost:8080/message";
        mockMvc = standaloneSetup(messageService).build();
    }

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void mms리스트() throws Exception {
        URI uri = URI.create(baseUrl + "/mms");
        String response = restTemplate.getForObject(uri, String.class);

        List<?> messages = messageController.getListMms();
        String json = this.jsonStringFromObject(messages);
        assertThat(response, is(equalTo(json)));
    }

    @Test
    public void sms리스트() throws Exception {
        URI uri = URI.create(baseUrl + "/sms");
        String response = restTemplate.getForObject(uri, String.class);

        List<?> messages = messageController.getListSms();
        String json = this.jsonStringFromObject(messages);
        assertThat(response, is(equalTo(json)));
    }

    @Test
    public void 문자입력Test() throws Exception {
        Message msg = new Message();
        msg.setPhone("01012341234");
        msg.setCallback("020001111");
        msg.setMsg("＃＄ⓐ＃ㅥ＋가 나 다라냐댜랴먀뱌샤야쟈챠캬탸퍄햐거너더러머버서어저처커터퍼허겨녀뎌려며벼셔여져쳐켜텨퍄햐거너더러머버서어저처커");

        URI uri = URI.create(baseUrl);
        String response = restTemplate.postForObject(uri, msg, String.class);
        assertThat(response, is(equalTo("0")));

    }

}
