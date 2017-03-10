package at.ac.tuwien.ifs.tulid.group16.repo;

import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import at.ac.tuwien.ifs.tulid.group16.domain.Course;
import at.ac.tuwien.ifs.tulid.group16.tx.DatasetTransactional;

@Repository
public class CourseRepository extends AbstractJenaRepository<Course> {

	private final static Logger LOG = LoggerFactory.getLogger(CourseRepository.class);

//	private Dataset dataset;

	@Autowired
	public CourseRepository(Dataset dataset) {
		super(dataset, SemanticApp.NS_BASE + "#courseID");
//		this.dataset = dataset;
	}

	@DatasetTransactional(readOnly = true)
	public List<Course> findAll() {
		return executeConstructAndMapToList(Queries.GENERIC_FINDALL.newQuery(pss -> {
			pss.setIri("paramClass", SemanticApp.NS_BASE + "#Course");
		}));
	}

	@DatasetTransactional(readOnly = true)
	public Course findOne(String courseId) {
		return executeConstructAndMapToObj(Queries.GENERIC_FINDONE.newQuery(pss -> {
			pss.setIri("paramClass", SemanticApp.NS_BASE + "#Course");
			pss.setLiteral("paramId", courseId);
		}));
	}

	@Override
	protected Course mapToObj(Resource r) {
		return new Course(r);
	}

}
