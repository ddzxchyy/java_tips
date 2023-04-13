package cn.behappy.javatips.springcloud.provider.user.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AppUserBizApp {

    public static void main(String[] args) {
        SpringApplication.run(AppUserBizApp.class, args);
    }
}
