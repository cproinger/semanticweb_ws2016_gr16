package at.ac.tuwien.ifs.tulid.group16.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;

public class SemesterCourse extends AbstractJenaResource {

	private Course course;

	public SemesterCourse(Resource r) {
		super(r);
	
		Property courseProp = getProperty(SemanticApp.NS_BASE + "#course");
		Resource c = r.getProperty(courseProp).getObject().asResource();
		this.course = new Course(c);
	}

	public String getSemester() {
		return getPropertyObject(SemanticApp.NS_BASE + "#semester")
				.asLiteral().getString();
	}
	
	public Set<String> getRooms() {
		String p = SemanticApp.NS_BASE + "#locatedAt";
		StmtIterator psIt = listPropertyStatements(p);
		HashSet<String> s = new HashSet<>();
		while(psIt.hasNext()) {
			Statement rs = psIt.next();
			RDFNode o = rs.getObject();
			Statement semProp = o.asResource().getProperty(o.getModel().getProperty(SemanticApp.NS_BASE + "#roomID"));
			if(semProp == null)//fürs findAll
				return null;
			s.add(semProp.getObject().asLiteral().getString());
		}
		return s;
	}
	
	public Course getCourse() {
		return course;
	}

	public String getHeadOfCourse() {
		try {
			return getPropertyObject(SemanticApp.NS_BASE + "#headOfCourse")
				.asResource().getURI();
		} catch (Exception e){
			return "";
		}
	}
}
