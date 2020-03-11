package com.example.demo.Service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
@Service
public class TransferRouterImpl  implements TransferRouter,BeanPostProcessor {
    private static Map<String, TransferChannel> channels = new HashMap<String,TransferChannel>();

    @Override
    public TransferChannel findChannelByName(String chnName) {
        return channels.get(chnName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)	throws BeansException {
        if(bean instanceof TransferChannel){
            TransferChannel channel = (TransferChannel)bean;
            channels.put(beanName, channel);
        }
        return bean;
    }

    @Override
    public Map<String, TransferChannel> getChannels() {
        return Collections.unmodifiableMap(channels);
    }
}
