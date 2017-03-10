package at.ac.tuwien.ifs.tulid.group16.repo;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import at.ac.tuwien.ifs.tulid.group16.domain.Room;

@Repository
public class RoomRepository extends AbstractJenaRepository<Room> {

	@Autowired
	public RoomRepository(Dataset dataset) {
		//die roomID gibts noch nicht. 
		super(dataset, SemanticApp.NS_BASE + "#roomID");
	}
	
	@Override
	protected Room mapToObj(Resource r) {
		return new Room(r);
	}

}
