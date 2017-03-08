package at.ac.tuwien.ifs.tulid.group16.repo;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import at.ac.tuwien.ifs.tulid.group16.tx.DatasetTransactional;

@Repository
public class CourseRepository {

	public static class Course {
		private Resource resource;

		public Course(Resource m) {
			this.resource = m;

		}

		public String getName() {
			return getPropertyObject(SemanticApp.NS_BASE + "#courseName")
					.asLiteral().getString();
		}

		public Double getECTS() {
			return getPropertyObject(SemanticApp.NS_BASE + "#ects")
					.asLiteral().getDouble();
		}

		private RDFNode getPropertyObject(String propURI) {
			Property p = resource.getModel().getProperty(propURI);
			RDFNode object = resource.getProperty(p).getObject();
			//TODO return some null-value(object) if it does not exist
			//so that calling methods don't get NPEs and don't need
			//to null-check
			return object;
		}

		public double getSemesterHours() {
			return getPropertyObject(SemanticApp.NS_BASE + "#semesterHours")
					.asLiteral().getDouble();
		}
	}

	private final static Logger LOG = LoggerFactory.getLogger(CourseRepository.class);

	private Dataset dataset;

	@Autowired
	public CourseRepository(Dataset dataset) {
		this.dataset = dataset;
	}

	@DatasetTransactional(readOnly = true)
	public Course findOne(String courseId) {
		// dataset.begin(rw -> {
		//
		// });
		final Query q = Queries.COURSE_FINDONE.newQuery(pss -> pss.setLiteral("paramId", courseId));
		LOG.info("query: " + q);
		try (QueryExecution qe = QueryExecutionFactory.create(q, dataset)) {
			Model model = qe.execConstruct();
			StringBuilderWriter w = new StringBuilderWriter();
			model.write(w, "TURTLE");
			LOG.info("model: \n" + w.toString());
			Property p = model.getProperty(SemanticApp.NS_BASE + "#courseID");
			ResIterator resIt = model.listResourcesWithProperty(p, courseId);
			if (!resIt.hasNext()) {
				return null;
			}
			Resource r = resIt.next();
			if (resIt.hasNext()) {
				throw new RuntimeException("non unique result");
			}
			LOG.info("resource: " + r);
			return new Course(r);
		}
	}
}
