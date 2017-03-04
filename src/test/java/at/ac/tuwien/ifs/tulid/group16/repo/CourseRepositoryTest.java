package at.ac.tuwien.ifs.tulid.group16.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.tuwien.ifs.tulid.group16.repo.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepo;
	
	@Test
	public void test() {
		//TODO
		courseRepo.findOne("185.A49");
	}
}
