package at.ac.tuwien.ifs.tulid.group16.domain;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public abstract class AbstractJenaResource {

	private Resource resource;

	public AbstractJenaResource(Resource r) {
		this.resource = r;
	}
	

	protected final RDFNode getPropertyObject(String propURI) {
		Statement propStmt = getPropertyStatement(propURI);
		RDFNode object = propStmt.getObject();
		//TODO return some null-value(object) if it does not exist
		//so that calling methods don't get NPEs and don't need
		//to null-check
		return object;
	}

	protected final Statement getPropertyStatement(String propURI) {
		Property p = getProperty(propURI);
		return resource.getProperty(p);
	}

	protected final Property getProperty(String propURI) {
		return resource.getModel().getProperty(propURI);
	}
	
	protected final StmtIterator listPropertyStatements(String propURI) {
		Property p = getProperty(propURI);
		return resource.listProperties(p);
	}
	
	

}
