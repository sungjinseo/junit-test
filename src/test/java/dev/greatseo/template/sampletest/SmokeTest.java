package dev.greatseo.template.sampletest;

import static org.assertj.core.api.Assertions.assertThat;

import dev.greatseo.template.apipage.controller.HomeController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private HomeController controller;

	// 스모크 테스트는 메인이 되는 컨텍스트가 기동되어 빈들이 정상주입되는지 확인하기 위함
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
