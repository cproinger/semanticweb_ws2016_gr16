package at.ac.tuwien.ifs.tulid.group16.repo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.tuwien.ifs.tulid.group16.domain.Course;
import at.ac.tuwien.ifs.tulid.group16.repo.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepo;
	
	@Test
	public void testFindOne() {
		//TODO
		Course c = courseRepo.findOne("185.A49");
		assertNotNull("no result", c);
		assertEquals("Abstrakte Maschinen", c.getName());
		assertEquals(3.0, c.getECTS(), 0.01);
		assertEquals(2.0, c.getSemesterHours(), 0.01);
	}
	
	@Test
	public void testFindAll() {
		List<Course> res = courseRepo.findAll();
		assertEquals(89, res.size());
	}
}