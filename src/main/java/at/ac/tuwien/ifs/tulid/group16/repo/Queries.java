package at.ac.tuwien.ifs.tulid.group16.repo;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;

import com.google.common.io.Resources;

public class Queries {

	public static Query FIND_COURSES = createQuery("/queries/find-courses.rq");

	private static Query createQuery(String q) {
		try {
			return QueryFactory.create(Resources.toString(Queries.class.getResource(q), Charset.defaultCharset()));
		} catch (IOException e) {
			throw new RuntimeException("cannot create Query", e);
		}

	}
}
