package at.ac.tuwien.ifs.tulid.group16.repo;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.AnonId;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
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
			Property p = resource.getModel().getProperty(SemanticApp.NS_BASE + "#courseName");
			return resource
					.getProperty(p).getObject().asLiteral().getString();
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
			Property p = model.getProperty(SemanticApp.NS_BASE + "#courseID");
			ResIterator resIt = model.listResourcesWithProperty(p, courseId);
			if(!resIt.hasNext()) {
				return null;
			}
			Resource r = resIt.next();
			if(resIt.hasNext()) {
				throw new RuntimeException("non unique result");
			}
			LOG.info("resource: " + r);
			return new Course(r);
			//TODO cp: muss noch herausfinden wie man aus dem model die root-node
			//rausbekommt um das in ein course-objekt zu stecken oder sowas.
			
			//das liefert mir mehr als eins
//			StmtIterator stmtIterator = model.listStatements();
//			if(!stmtIterator.hasNext()) {
//				return null;
//			}
//			Statement stmt = stmtIterator.next();
//			if(stmtIterator.hasNext()) {
//				throw new RuntimeException("non unique result");
//			}
		}
	}
}
