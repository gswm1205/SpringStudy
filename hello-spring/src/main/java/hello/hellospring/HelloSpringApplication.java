package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
	//tomcat 이라는 웹서버를 띄우며 자동 생성
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
