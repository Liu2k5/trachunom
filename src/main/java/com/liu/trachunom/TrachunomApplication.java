package com.liu.trachunom;

import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme("trachunom")
public class TrachunomApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(TrachunomApplication.class, args);
	}

}
