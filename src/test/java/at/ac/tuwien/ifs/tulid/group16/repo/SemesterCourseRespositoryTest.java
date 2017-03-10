package at.ac.tuwien.ifs.tulid.group16.repo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.tuwien.ifs.tulid.group16.domain.SemesterCourse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SemesterCourseRespositoryTest {

	@Autowired
	private SemesterCourseRespository repo;
	
	@Test
	public void testFindOne() {
		SemesterCourse sc = repo.findOne("185.A70", "2016W");
		assertNotNull("course not linked", sc.getCourse());
		assertEquals("Membrane Computing", sc.getCourse().getName());
	}
	
	@Test
	public void testFindByCourseId() {
		List<SemesterCourse> scs = repo.findByCourseId("185.A20");
		assertEquals("expected other size of result set", 2, scs.size());
		assertEquals( 
				Sets.newSet("2017S", "2016W"), 
				scs.stream().map(sc -> sc.getSemester())
				.collect(Collectors.toSet()));
	}
}
