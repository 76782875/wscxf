package com.cj.wscxf.client;

import com.cj.wscxf.service.UserService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;

public class Test {

    public static void main(String[] args) {
//        Test.wsclient1();
        Test.wsclient2();
//        Test.wsclient3();
    }

    /**
     * 1.代理类工厂的方式,通过接口协议获取数据类型，需要拿到对方的接口地址
     */
    public static void wsclient1() {
        try {
            // 接口地址
            String address = "http://127.0.0.1:8080/soap/user?wsdl";
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(UserService.class);
            // 创建一个代理接口实现
            UserService us = (UserService) jaxWsProxyFactoryBean.create();

            // 数据准备
            String userId = "111";
            // 调用代理接口的方法调用并返回结果
            String result = us.getUserName(userId);
            System.out.println("返回结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过接口协议获取数据类型,设置链接超时和响应时间
     */
    public static void wsclient2(){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/soap/user?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);

        UserService userService=(UserService) jaxWsProxyFactoryBean.create();

        Client proxy= ClientProxy.getClient(userService);
        HTTPConduit conduit=(HTTPConduit)proxy.getConduit();
        HTTPClientPolicy policy=new HTTPClientPolicy();
        policy.setConnectionTimeout(1000);
        policy.setReceiveTimeout(1000);
        conduit.setClient(policy);

        // 数据准备
        String userId = "111";
        // 调用代理接口的方法调用并返回结果
        String result = userService.getUserName(userId);
        System.out.println("返回结果:" + result);
    }




    /**
     * 方法三：动态调用方式
     */
    public static void wsclient3() {
        String wsldUrl = "http://127.0.0.1:8080/soap/user?wsdl";

        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsldUrl);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));

        // 命名空间，方法名
        QName name = new QName("http://service.wscxf.cj.com/", "getUserName");
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(name, "111");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }



}
