package at.ac.tuwien.ifs.tulid.group16.tx;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

@Component
public class DatasetTransactionManager extends AbstractPlatformTransactionManager {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Dataset dataset;
	
	@Override
	protected void doBegin(Object arg0, TransactionDefinition def) throws TransactionException {
		dataset.begin(def.isReadOnly() ? ReadWrite.READ : ReadWrite.WRITE);
	}

	@Override
	protected void doCommit(DefaultTransactionStatus arg0) throws TransactionException {
		dataset.commit();
	}

	@Override
	protected Object doGetTransaction() throws TransactionException {
		return new Object();
	}

	@Override
	protected void doRollback(DefaultTransactionStatus arg0) throws TransactionException {
		dataset.abort();
	}

}
