package com.kin.web.myphoto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.kin.web.myphoto.pc.accountManager.mapper","com.kin.web.myphoto.pc.photoWall.mapper"})
public class MyphotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyphotoApplication.class, args);
    }

}
