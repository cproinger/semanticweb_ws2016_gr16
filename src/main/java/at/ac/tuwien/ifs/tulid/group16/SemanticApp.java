package at.ac.tuwien.ifs.tulid.group16;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY, proxyTargetClass = true)
public class SemanticApp {
	
	private static final String TURTLE = "TURTLE";
	private static final OntModelSpec DEFAULT_MODEL_SPEC = OntModelSpec.OWL_DL_MEM_RULE_INF;
	public static final String NS_BASE = "http://ifs.tuwien.ac.at/tulid/group16";
	public static final String FOAF_BASE = "http://xmlns.com/foaf/0.1/";

	public static void main(String[] args) {
		SpringApplication.run(SemanticApp.class);
	}
	

	private static void removeIndividuals(OntClass c) {
		List<? extends OntResource> l = 
				toStream(c.listInstances()).collect(Collectors.toList());
		l.forEach(r -> r.remove());
	}
	
	private static Stream<? extends OntResource> toStream(ExtendedIterator<? extends OntResource> sourceIterator) {
		Stream<? extends OntResource> orstream = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(sourceIterator, Spliterator.ORDERED), false);
		return orstream;
	}

	@Bean
	public OntModel ontModel() {
		OntModel ontModel = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC);
		ontModel.read(SemanticApp.class.getResourceAsStream("/university.owl"), NS_BASE, TURTLE);
		//remove test instances
		removeIndividuals(ontModel.getOntClass(SemanticApp.NS_BASE + "#Course"));
		removeIndividuals(ontModel.getOntClass(SemanticApp.NS_BASE + "#Professor"));
		removeIndividuals(ontModel.getOntClass(SemanticApp.NS_BASE + "#Room"));

		
		return ontModel;
	}

	@Bean
	public Dataset dataset(@Autowired OntModel ontModel) {
		Model unionModel = ModelFactory.createDefaultModel()
							.union(readRDF("courses"))
							.union(readRDF("courses-rooms"))
							.union(readRDF("persons"))
							;
		InfModel infModel = ModelFactory.createInfModel(
				ReasonerRegistry.getOWLReasoner(), 
				ontModel, 
				unionModel);
		return DatasetFactory.create(infModel);
	}


	private OntModel readRDF(String name) {
		OntModel m = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC);
		m.read(SemanticApp.class.getResourceAsStream("/rdf/" + name + ".ttl"), NS_BASE, TURTLE);
		return m;
	}
}
