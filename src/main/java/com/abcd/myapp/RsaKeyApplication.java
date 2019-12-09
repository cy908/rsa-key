package com.abcd.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.var;

@SpringBootApplication
public class RsaKeyApplication {

	public static void main(String[] args) {
		var cac = SpringApplication.run(RsaKeyApplication.class, args);
		cac.getBean(RsaKeyGen.class).run();
	}

}
