package at.ac.tuwien.ifs.tulid.group16.tx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

/**
 * Die annotation zusammen mit dem DatasetTransactionManager nimmt einem
 * den boiler-plate-code fürs begin/commit/rollback ab.
 * @author cproinger
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "datasetTransactionManager")
public @interface DatasetTransactional {

	@AliasFor(annotation = Transactional.class)
	boolean readOnly() default false;
}
