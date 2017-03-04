package at.ac.tuwien.ifs.tulid.group16.repo;

import org.apache.jena.query.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.ifs.tulid.group16.tx.DatasetTransactional;

@Repository
public class CourseRepository {
	
	public static class Course {
		
	}

	private Dataset dataset;

	@Autowired
	public CourseRepository(Dataset dataset) {
		this.dataset = dataset;
	}
	
	@DatasetTransactional(readOnly = true)
	public Course findOne(String courseId) {
//		dataset.begin(rw -> {
//			
//		});
		return null;
	}
}
