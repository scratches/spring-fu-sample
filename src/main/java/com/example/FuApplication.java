package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;

import static org.springframework.boot.jafu.Jafu.application;

public class FuApplication {

	public static SpringApplication app = application(app -> {
		app.beans(beans -> {
			beans.bean(SampleService.class);
			beans.bean(SampleHandler.class);
		});
		app.server(server -> server.router(router -> {
			SampleHandler sampleHandler = app.ref(SampleHandler.class);
			router.GET("/", sampleHandler::hello);
			router.resources("/**", new ClassPathResource("static/"));
		}));
	});

	public static void main(String[] args) {
		app.run(args);
	}
}
