package br.com.atocf.consultapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.atocf.consultapi.model.FileLog;
import br.com.atocf.consultapi.repository.FileLogRepository;
import br.com.atocf.consultapi.service.FileLogService;

@RunWith(SpringRunner.class)
@WebMvcTest(FileLogController.class)
public class FileLogControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FileLogService service;

	@MockBean
	private FileLogRepository repository;

	private FileLog fileLog;

	@Before
	public void setup() {
		this.fileLog = new FileLog(Date.from(Instant.now()), "000.000.00.000", "GET / HTTP/1.1", 200,
				"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
	}

	@Test
	public void createAPI() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/filelog").content(asJsonString(fileLog))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void findAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/filelog").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void findAllByIp() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/filelog").param("ip", "000.000.00.000").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void findAllByDeAndAte() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/filelog").param("dt_de", "01/01/2018").param("dt_ate", "01/20/2018").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void findAllByIpAndDeAndAte() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/filelog").param("ip", "000.000.00.000").param("dt_de", "01/01/2018").param("dt_ate", "01/20/2018").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
}
