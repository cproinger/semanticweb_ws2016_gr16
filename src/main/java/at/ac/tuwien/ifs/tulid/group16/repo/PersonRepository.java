package at.ac.tuwien.ifs.tulid.group16.repo;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import at.ac.tuwien.ifs.tulid.group16.domain.Course;
import at.ac.tuwien.ifs.tulid.group16.domain.Person;
import at.ac.tuwien.ifs.tulid.group16.tx.DatasetTransactional;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository extends AbstractJenaRepository<Person> {

    private final static Logger LOG = LoggerFactory.getLogger(CourseRepository.class);

//	private Dataset dataset;

    @Autowired
    public PersonRepository(Dataset dataset) {
        super(dataset, SemanticApp.FOAF_BASE);
//		this.dataset = dataset;
    }

    @DatasetTransactional(readOnly = true)
    public List<Person> findAll() {
        return executeConstructAndMapToList(Queries.GENERIC_FINDALL.newQuery(pss -> {
            pss.setIri("paramClass", SemanticApp.FOAF_BASE + "Person");
        }));
    }

    /*TODO
    @DatasetTransactional(readOnly = true)
    public Person findOne(String personId) {
        return executeConstructAndMapToObj(Queries.COURSE_FINDONE.newQuery(pss -> {
            pss.setLiteral("paramId", personId);
        }));
    }*/

    @Override
    protected Person mapToObj(Resource r) {
        return new Person(r);
    }

}

