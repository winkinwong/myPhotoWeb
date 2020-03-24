package com.kin.web.myphoto.global;


import com.kin.web.myphoto.filter.SessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class GlobalFilterRegistrator {
    @Bean
    public FilterRegistrationBean sessionFilterRegistrationBean(){
        FilterRegistrationBean sessionFilterRegistrationBean = new FilterRegistrationBean();
        sessionFilterRegistrationBean.setName("sessionFilter");
        sessionFilterRegistrationBean.setOrder(2);
        sessionFilterRegistrationBean.setFilter(new SessionFilter());
        sessionFilterRegistrationBean.setUrlPatterns(Arrays.asList(new String[]{
                "/*"
        }));
        return sessionFilterRegistrationBean;
    }
}
