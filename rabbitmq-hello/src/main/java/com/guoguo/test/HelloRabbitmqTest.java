package com.guoguo.test;

import com.guoguo.RabbitmqHelloApplication;
import com.guoguo.send.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mengying on 2018/1/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RabbitmqHelloApplication.class)
public class HelloRabbitmqTest {
    @Autowired
    private Sender sender;
    @Test
    public void hello() throws Exception{
        sender.send();
    }
    @Test
    public void hello2() throws Exception{
        sender.send2();
    }
}
