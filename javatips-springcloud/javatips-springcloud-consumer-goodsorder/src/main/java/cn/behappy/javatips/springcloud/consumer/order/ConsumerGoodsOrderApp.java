package cn.behappy.javatips.springcloud.consumer.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.behappy.javatips.springcloud.*")
@ComponentScan({"cn.behappy.javatips.springcloud.*"})
public class ConsumerGoodsOrderApp {

    public static  void main(String[] args) {
        SpringApplication.run(ConsumerGoodsOrderApp.class, args);
    }
}
