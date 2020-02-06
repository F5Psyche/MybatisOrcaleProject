package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.util.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {


    @Test
    public void chainTest() {
        String url = "http://localhost:8080/power/matter";
        System.out.println(url);
        JSONObject params = new JSONObject();
        params.put("organId", "admin");
        //params.put("organArea", "330000");
        Object data = HttpUtils.getHttpResponse("",url,params);
        System.out.println(data);
    }
}
