package com.scoprion.mall.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.scoprion.MallApplication;
import com.scoprion.mall.domain.good.GoodExt;
import com.scoprion.mall.domain.good.Goods;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-18 16:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallApplication.class)
@WebAppConfiguration
public class SellerGoodTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(get("/seller/good/findById/65")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 请求数据的格式
                .header("auth", "JSONID:94268"))//请求头
                .andExpect(status().isOk()) // 比较结果
                .andDo(print())//输出请求信息
                .andReturn()
                .getResponse()
                .getContentAsString();//结果以String返回

    }

    @Test
    public void add() throws Exception {
        List<MallImage> list = new ArrayList<>();
        list.add(new MallImage("78990.jpg"));
        GoodExt goodExt = new GoodExt();
        goodExt.setActivityId(18L);
        goodExt.setGoodName("测试用例测试数据");
        goodExt.setDescription("fgff从宽我未扣款");
        MockGood mockGood = new MockGood();
        mockGood.setGood(goodExt);
        mockGood.setImageList(list);
        String content = JSON.toJSONString(mockGood);
        mockMvc.perform(post("/seller/good/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 请求数据的格式
                .content(content)
                .header("auth", "JSONID:94268"))
                .andExpect(status().isOk()) // 比较结果
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
    }
}
