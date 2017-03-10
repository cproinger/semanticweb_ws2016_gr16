package at.ac.tuwien.ifs.tulid.group16.domain;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

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
	
	public Course getCourse() {
		return course;
	}
}
