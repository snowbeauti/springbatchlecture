package io.springbatch.springbatchlecture;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
/*
네가지 설정 클래스를 실행시키며 스프링 배치의 모든 초기화 및 실행 구성이 이루어짐
스프링부트 배치의 자동 설정 클래스가 실행됨으로 빈으로 등록된 모든 job을 검색해서 초기화 동시에 job을 수행하도록 구성
1. BatchAotoConfiguration 
 - 스프링 배치가 초기화 될 때 자동으로 실행되는 설정 클래스
 - job을 수행하는 JobLauncherApplicationRunner 빈을 생성
2. SimpleBatchConfiguration
 - JobBuilderFactory 와 StepBuilderFactory 생성
 - 스프링 배치의 주요 구성 요소 생성 - 프록시 객체로 생성됨
3. BatchConfigurerConfiguration
 - BasicBatchConfigurer
 ---SimplebatchConfiguration에서 생성한 프록시 객체의 실제 대상 객체를 생성하는 설정 클래스
 ---빈으로 의존성 주입 받아서 주요 객체들을 참조해서 사용할 수 있다.
 -JpaBatchConfigurer
 ---JPA 관련 객체를 생성하는 설정 클래스
 
 
 @EnableBatchProcessing
 >>>>>>>>>>>>>>>>>>>>>>
 SimpleBatchConfiguration
 >>>>>>>>>>>>>>>>>>>>>>>>>
 BatchConfigurerConfiguration
 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 BatchAotoConfiguration
 
 */

public class SpringBatchlectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchlectureApplication.class, args);
	}

}
