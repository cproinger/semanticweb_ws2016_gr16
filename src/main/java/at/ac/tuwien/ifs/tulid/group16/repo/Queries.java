package at.ac.tuwien.ifs.tulid.group16.repo;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;

import com.google.common.io.Resources;

public class Queries {
	
	public static class Builder {
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
			return pss.asQuery();
		}
	}
	public static interface ParamSetter {
		public void setParams(ParameterizedSparqlString pss);
	}

	public static Query COURSE_FIND = createQueryFromResourcePath("/queries/course-find.rq");
	
	public static Builder COURSE_FINDONE = Builder.fromResource("/queries/course-findOne.rq");

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
