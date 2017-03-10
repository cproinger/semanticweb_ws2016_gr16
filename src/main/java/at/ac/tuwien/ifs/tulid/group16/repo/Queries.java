package at.ac.tuwien.ifs.tulid.group16.repo;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Resources;

public class Queries {
	
	private final static Logger LOG = LoggerFactory.getLogger(Queries.class);
	
	static class Builder {
		private static Builder fromResource(String resPath) {
			try {
				return new Builder(getClasspathResourceAsString(resPath));
			} catch (IOException e) {
				throw new RuntimeException("cannot load query-string", e);
			}
		}
		private String q;

		private Builder(String q) {
			this.q = q;
		}
		public Query newQuery(ParamSetter ps) {
			ParameterizedSparqlString pss = new ParameterizedSparqlString(q);
			ps.setParams(pss);
			Query asQuery = pss.asQuery();
			LOG.info("query: " + q);
			return asQuery;
		}
	}
	static interface ParamSetter {
		public void setParams(ParameterizedSparqlString pss);
	}
	
	static interface QueryConstructMapper<E> {
		public E mapResources(ResIterator ri);
	}

	public static Query COURSE_FIND = createQueryFromResourcePath("/queries/course-find.rq");
	
	static Builder GENERIC_FINDONE = Builder.fromResource("/queries/generic-findOne.rq");
	
	static Builder GENERIC_FINDALL = Builder.fromResource("/queries/generic-findAll.rq");

	private static Query createQueryFromResourcePath(String q) {
		try {
			return QueryFactory.create(getClasspathResourceAsString(q));
		} catch (IOException e) {
			throw new RuntimeException("cannot create Query", e);
		}

	}
	private static String getClasspathResourceAsString(String q) throws IOException {
		return Resources.toString(Queries.class.getResource(q), Charset.defaultCharset());
	}
}
