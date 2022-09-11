package io.springbatch.springbatchlecture;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/*생성자를 생성하지 않고 바로 사용 Autowird 사용x
final사용해야함*/

@Configuration
/*Job을 정의하고 빈 설정*/

public class HelloJobConfiguration {
	
	/*
	JOB(일,일감)
	STEP(일의 항목, 단계)
	TASKLET(작업내용)
	*/
	
	private final JobBuilderFactory jobBuilderFactory;
	//Job을 생성하는 빌더 팩토리
	
	private final StepBuilderFactory stepBuilderFactory;
	//Step을 생성하는 빌더 팩토리

	
	public HelloJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	


	@Bean
	public Job helloJob() {
		return jobBuilderFactory.get("helloJob") /*helloJob 이름으로 Job 생성*/
				.start(step1()) /*helloStep 객체를 인자로 넣음*/
				.next(step2())
				.next(step3())
				.build();/*Job(인터페이스) 구현체의 객체가 생성됨*/
	}
	
	@Bean
	public Step helloStep1() {
		return stepBuilderFactory.get("helloStep1") //helloStep 이름으로 Step 객체 생성
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("=====================");
						System.out.println("Hello Spring Batch");
						System.out.println("=====================");
						return RepeatStatus.FINISHED;
						//한번만 실행되고 종료됨
					}
				}) 
				.build();
	}
	
	@Bean
	public Step helloStep2() {
		return stepBuilderFactory.get("helloStep2") /*helloStep 이름으로 Step 객체 생성*/
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
				}) 
				.build();
	}
	
	@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step1 has executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step3 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
	
}
