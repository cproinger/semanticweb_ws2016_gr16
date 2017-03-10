package at.ac.tuwien.ifs.tulid.group16.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.tuwien.ifs.tulid.group16.repo.Queries.QueryConstructMapper;

public abstract class AbstractJenaRepository<E> {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractJenaRepository.class);
	
	private final Dataset dataset;

	private String idPropertyURI;

	public AbstractJenaRepository(Dataset ds, String idPropertyURI) {
		this.dataset = ds;
		this.idPropertyURI = idPropertyURI;
	}
	
	protected <X> X executeConstruct(final Query q, QueryConstructMapper<X> qcm) {
		try (QueryExecution qe = QueryExecutionFactory.create(q, dataset)) {
			Model model = qe.execConstruct();
			StringBuilderWriter w = new StringBuilderWriter();
			model.write(w, "TURTLE");
			LOG.info("model: \n" + w.toString());
			Property p = model.getProperty(idPropertyURI);
			ResIterator ri = model.listSubjectsWithProperty(p);
			return qcm.mapResources(ri);
		}
	}

	protected E executeConstructAndMapToObj(final Query q) {
		return executeConstruct(q, (ri) -> {
			if (!ri.hasNext()) {
				return null;
			}
			Resource r = ri.next();
			if (ri.hasNext()) {
				throw new RuntimeException("non unique result");
			}
			LOG.info("resource: " + r);
			return mapToObj(r);
		});
	}
	


	protected List<E> executeConstructAndMapToList(Query q) {
		return executeConstruct(q, (ri) -> {
			ArrayList<E> result = new ArrayList<>();
			while(ri.hasNext()) {
				Resource r = ri.next();
				LOG.info("resource: " + r);
				result.add(mapToObj(r));
			}
			return result;
		});
	}

	protected abstract E mapToObj(Resource r);
}
