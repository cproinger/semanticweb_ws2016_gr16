package at.ac.tuwien.ifs.tulid.group16.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;

public class Course extends AbstractJenaResource {

	public Course(Resource r) {
		super(r);
	}

	public String getName() {
		return getPropertyObject(SemanticApp.NS_BASE + "#courseName")
				.asLiteral().getString();
	}

	public Double getECTS() {
		return getPropertyObject(SemanticApp.NS_BASE + "#ects")
				.asLiteral().getDouble();
	}


	public double getSemesterHours() {
		return getPropertyObject(SemanticApp.NS_BASE + "#semesterHours")
				.asLiteral().getDouble();
	}

	public String getId() {
		return getPropertyObject(SemanticApp.NS_BASE + "#courseID")
				.asLiteral().getString();
	}
	
	public Set<String> getSemesters() {
		String p = SemanticApp.NS_BASE + "#semesterCourses";
		StmtIterator psIt = listPropertyStatements(p);
		HashSet<String> s = new HashSet<>();
		while(psIt.hasNext()) {
			Statement rs = psIt.next();
			RDFNode o = rs.getObject();
			Statement semProp = o.asResource().getProperty(o.getModel().getProperty(SemanticApp.NS_BASE + "#semester"));
			if(semProp == null)//fürs findAll
				return null;
			s.add(semProp.getObject().asLiteral().getString());
		}
		return s;
	}
	
	public String getType() {
		return getPropertyObject(SemanticApp.NS_BASE + "#hasCourseType")
				.asResource().getLocalName();
	}
}