package at.ac.tuwien.ifs.tulid.group16.repo;

import java.util.Collection;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import at.ac.tuwien.ifs.tulid.group16.domain.Room;
import at.ac.tuwien.ifs.tulid.group16.tx.DatasetTransactional;

@Repository
public class RoomRepository extends AbstractJenaRepository<Room> {

	@Autowired
	public RoomRepository(Dataset dataset) {
		super(dataset, SemanticApp.NS_BASE + "#roomID");
	}
	
	@Override
	protected Room mapToObj(Resource r) {
		return new Room(r);
	}

	@DatasetTransactional(readOnly = true)
	public Collection<Room> findAll() {
		return executeConstructAndMapToList(Queries.GENERIC_FINDALL.newQuery(pss -> {
			pss.setIri("paramClass", SemanticApp.NS_BASE + "#Room");
		}));
	}
	@DatasetTransactional(readOnly = true)
	public Room findOne(String roomId) {
		return executeConstructAndMapToObj(Queries.GENERIC_FINDONE.newQuery(pss -> {
//			pss.setIri("paramClass", SemanticApp.NS_BASE + "#Room");
			pss.setLiteral("paramId", roomId);
		}));
	}

}
