package com.abcd.myapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 配置信息
 */
@Getter
@Setter
@Component
@ConfigurationProperties
public class Config {

	private Integer size;

}