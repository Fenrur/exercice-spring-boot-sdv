package com.exo1.exo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class Exo1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Exo1Application.class, args);
	}
	
	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
	}
}
