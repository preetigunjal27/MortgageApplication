package com.businesslayer.mortgage.MortgageBusinessLayer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.businesslayer.mortgage.MortgageBusinessLayer.Controller.MortgageController;

@SpringBootTest
class MortgageBusinessLayerApplicationTests {

	@Autowired
	private MortgageController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
