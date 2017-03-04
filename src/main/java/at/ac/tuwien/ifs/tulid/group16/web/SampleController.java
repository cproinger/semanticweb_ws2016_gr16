package at.ac.tuwien.ifs.tulid.group16.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.ifs.tulid.group16.repo.Queries;

@RestController
public class SampleController {

	public static class SampleResource {
		private String courseId;
		private String courseName;

		public SampleResource() {
			// empty
		}

		public SampleResource(String courseId, String courseName) {
			this.courseId = courseId;
			this.courseName = courseName;
		}

		public String getCourseId() {
			return courseId;
		}

		public String getCourseName() {
			return courseName;
		}
	}

	private Dataset dataset;

	@Autowired
	public SampleController(Dataset dataset) {
		this.dataset = dataset;
	}

	@RequestMapping(path = "/api/stuff", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<SampleResource> stuff() {
		ArrayList<SampleResource> content = new ArrayList<>();
		// QueryExecutionFactory.create(Query.lt)
		
		dataset.begin(ReadWrite.READ);
		try (QueryExecution qexec = QueryExecutionFactory.create(Queries.FIND_COURSES, dataset)) {
			ResultSet rs = qexec.execSelect();

			for (; rs.hasNext();) {
				QuerySolution soln = rs.nextSolution();
				content.add(new SampleResource(soln.getLiteral("courseID").getString(),
						soln.getLiteral("courseName").getString()));
			}
		} catch (RuntimeException e) {
			dataset.abort();
			throw e;
		}
		dataset.commit();
		return content;
	}
}
