package at.ac.tuwien.ifs.tulid.group16.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.tuwien.ifs.tulid.group16.domain.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepo;
	
	@Test
	public void testFindOne() {
		Course c = courseRepo.findOne("185.A49");
		assertNotNull("no result", c);
		assertEquals("Abstrakte Maschinen", c.getName());
		assertEquals(3.0, c.getECTS(), 0.01);
		assertEquals(2.0, c.getSemesterHours(), 0.01);
		assertEquals(Sets.newSet("2017S"), c.getSemesters());
	}
	
	@Test
	public void testFineOne_withMultipleSemesters() {
		
		Course c = courseRepo.findOne("185.A20");
		assertNotNull("no result", c);
		assertEquals("Projekt aus Software Engineering & Internet Computing", c.getName());
		assertEquals(12.0, c.getECTS(), 0.01);
		assertEquals(6.0, c.getSemesterHours(), 0.01);
		assertEquals(Sets.newSet("2016W", "2017S"), 
				c.getSemesters());
	}
	
	@Test
	public void testFindAll() {
		List<Course> res = courseRepo.findAll();
		assertEquals(72, res.size());
	}
}
