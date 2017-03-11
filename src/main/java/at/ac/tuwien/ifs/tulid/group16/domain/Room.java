package at.ac.tuwien.ifs.tulid.group16.domain;

import org.apache.jena.rdf.model.Resource;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;

public class Room extends AbstractJenaResource {


	public Room(Resource r) {
		super(r);
	}
	
	public String getName() {
		return getPropertyObject(SemanticApp.NS_BASE + "#roomName")
				.asLiteral().getString();
	}

	public String getId() {
		return getPropertyObject(SemanticApp.NS_BASE + "#roomID")
		.asLiteral().getString();
	}
	
	public Integer getCapacity() {
		return getPropertyObject(SemanticApp.NS_BASE + "#capacity")
		.asLiteral().getInt();
	}
	
	public String getBuilding() {
		return getPropertyObject(SemanticApp.NS_BASE + "#isInBuilding")
		.asLiteral().getString();
	}
	
	public String getAddress() {
		return getPropertyObject(SemanticApp.NS_BASE + "#hasAddress")
		.asLiteral().getString();
	}

}
