package com.cj.wscxf.config;

import com.cj.wscxf.service.UserService;
import org.apache.cxf.Bus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

//服务发布类
@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;

    @Autowired
    UserService userService;

    /**
     * 此方法作用是改变项目中服务名的前缀名
     *
     * 默认服务在Host:port/services/*路径下
     * 默认wsdl访问地址为http://127.0.0.1:8080/services/user?wsdl
     * 此方法生效后：wsdl访问地址为：http://127.0.0.1:8080/soap/user?wsdl
     * @return
     */
    /**
     *      * spring boot2.0.6之后的版本与xcf集成，不需要定义如下方法
     *      * 换成配置的方法，在application.yml中添加：cxf.path=/service实现
     */
//    @SuppressWarnings("all")
//    @Bean
//    public ServletRegistrationBean dispatcherServlet() {
//        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
//    }

    /** JAX-WS
     * 站点服务
     * **/
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/user");
        return endpoint;
    }
}