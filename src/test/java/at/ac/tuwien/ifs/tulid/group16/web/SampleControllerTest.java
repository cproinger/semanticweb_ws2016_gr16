package at.ac.tuwien.ifs.tulid.group16.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.tuwien.ifs.tulid.group16.web.SampleController.SampleResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SampleControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test() throws Exception {
//		restTemplate.getForEntity("/api/stuff", Object[].class);
		ResponseEntity<List<SampleResource>> resp = restTemplate.exchange("/api/stuff", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<SampleResource>>() {
		});
		List<SampleResource> body = resp.getBody();
		assertNotNull(body);
		assertEquals(1, body.size());
		assertEquals("Abstrakte Maschinen", body.get(0).getCourseName());
		assertEquals("185.A49", body.get(0).getCourseId());
	}
}
