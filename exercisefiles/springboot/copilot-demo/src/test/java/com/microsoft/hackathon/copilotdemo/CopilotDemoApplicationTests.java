package com.microsoft.hackathon.copilotdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;;


@SpringBootTest()
@AutoConfigureMockMvc 
class CopilotDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
	void hello() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=world"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("hello world"));
	}

	// Test /hello endpoint with no key
	@Test
	void helloNoKey() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("key not passed"));
	}

	// test /diffdates
	@Test
	void diffdates() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/diffdates?date1=01-01-2021&date2=01-02-2021"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("diffdates 01-01-2021 01-02-2021"));
	}

	// test /validatephone
	@Test
	void validatephone() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+34666666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("validatephone +34666666666"));
	}

	// test /validatephone with wrong phone number
	// test /validatephone with wrong phone number
	@Test
	void validatephoneWrong() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+3566666666"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("invalid phone number"));
	}

	// test /validatephone with no phone number
	@Test
	void validatephoneNoPhone() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("phone not passed"));
	}
	
	// test /validatephone with parameter +34878
	@Test
	void validatephoneWrong2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+34878"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("invalid phone number"));
	}


	// test /validatedni
	@Test
	void validatedni() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni?dni=12345678A"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("validatedni 12345678A"));
	}

	// test /validatedni with wrong dni
	@Test
	void validatedniWrong() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni?dni=12345678"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("invalid dni"));
	}

	// test /validatedni with no dni
	@Test
	void validatedniNoDni() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("dni not passed"));
	}

	// validate /color with invalid color
	@Test
	void colorInvalid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/color?color=invalid"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("404 Not Found"));
	}

	// validate /color with null color
	@Test
	void colorNull() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/color"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("color not valid"));
	}

	// validate /color with white value
	@Test
	void colorWhite() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/color?color=white"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("#FFFFFF"));
	}

	// validate /color with red color
	@Test
	void colorRed() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/color?color=red"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("#FF0000"));
	}
	

}