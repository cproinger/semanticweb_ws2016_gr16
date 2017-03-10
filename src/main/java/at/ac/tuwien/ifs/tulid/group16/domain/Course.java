package at.ac.tuwien.ifs.tulid.group16.domain;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.springframework.hateoas.Identifiable;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;

public class Course {
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

	public String getId() {
		return getPropertyObject(SemanticApp.NS_BASE + "#courseID")
				.asLiteral().getString();
	}
}