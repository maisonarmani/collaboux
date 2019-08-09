package com.randomsturvs.excollabo;

import com.randomsturvs.excollabo.controller.SecuredController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@ComponentScan(basePackages = "com.randomsturvs.excollabo.*")
@RunWith(SpringRunner.class)
@WebMvcTest(SecuredController.class)
public class ExcollaboApplicationTests {

	@Autowired
	private MockMvc mvc;

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}