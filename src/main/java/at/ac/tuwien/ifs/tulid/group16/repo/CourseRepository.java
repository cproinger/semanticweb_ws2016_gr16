package at.ac.tuwien.ifs.tulid.group16.repo;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.ifs.tulid.group16.tx.DatasetTransactional;

@Repository
public class CourseRepository {
	
	public static class Course {
		
	}
	
	private final static Logger LOG = LoggerFactory.getLogger(CourseRepository.class);

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
		final Query q = Queries.COURSE_FINDONE.newQuery(pss -> pss.setLiteral("paramId", courseId));
		LOG.info("query: " + q);
		try(QueryExecution qe = QueryExecutionFactory.create(q, dataset)) {
			Model model = qe.execConstruct();
			StringBuilderWriter w = new StringBuilderWriter();
			model.write(w, "TURTLE");
			LOG.info("model: \n" + w.toString());
			//TODO cp: muss noch herausfinden wie man aus dem model die root-node
			//rausbekommt um das in ein course-objekt zu stecken oder sowas. 
		}
				
		
		return null;
	}
}
