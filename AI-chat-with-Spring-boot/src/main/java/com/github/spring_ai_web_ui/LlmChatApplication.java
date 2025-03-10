package com.github.spring_ai_web_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
class AutoOpenBrowserInitializer implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		// Open browser
		String url = "http://localhost:8080";
		String os = System.getProperties().getProperty("os.name");
		if (os != null && os.toLowerCase().startsWith("windows")) {
			Runtime.getRuntime().exec("cmd /c start " + url);
		} else if (os != null && os.toLowerCase().startsWith("linux")) {
			Runtime.getRuntime().exec("open " + url);
		} else { //other OS
			System.out.println(String.format("OS is:%s", os));
		}
	}
}

@SpringBootApplication(scanBasePackages = "com.github.spring_ai_web_ui")

//@SpringBootApplication
public class LlmChatApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(LlmChatApplication.class, args);
	}

}
