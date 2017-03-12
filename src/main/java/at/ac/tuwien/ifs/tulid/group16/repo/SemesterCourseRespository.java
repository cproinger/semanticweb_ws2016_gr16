package at.ac.tuwien.ifs.tulid.group16.repo;

import java.util.Collection;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import at.ac.tuwien.ifs.tulid.group16.domain.SemesterCourse;
import at.ac.tuwien.ifs.tulid.group16.tx.DatasetTransactional;

@Repository
public class SemesterCourseRespository extends AbstractJenaRepository<SemesterCourse> {

	@Autowired
	public SemesterCourseRespository(Dataset ds) {
		super(ds, SemanticApp.NS_BASE + "#course");
	}

	@Override
	protected SemesterCourse mapToObj(Resource r) {
		return new SemesterCourse(r);
	}

	@DatasetTransactional(readOnly = true)
	public SemesterCourse findOne(String courseId, String semester) {
		return executeConstructAndMapToObj(Queries.SEMESTER_COURSE_FINDONE.newQuery(pss -> {
			pss.setLiteral("paramId", courseId);
			pss.setLiteral("paramSemester", semester);
		}));
	}

	@DatasetTransactional(readOnly = true)
	public List<SemesterCourse> findByCourseId(String courseId) {
		return executeConstructAndMapToList(Queries.SEMESTER_COURSE_FINDBY_COURSEID.newQuery(pss -> {
			pss.setLiteral("paramId", courseId);
		}));
	}

	@DatasetTransactional(readOnly = true)
	public List<SemesterCourse> findAll() {
		return executeConstructAndMapToList(Queries.SEMESTER_COURSE_FINDALL.newQuery(pss -> {
			//empty
		}));
	}
}
