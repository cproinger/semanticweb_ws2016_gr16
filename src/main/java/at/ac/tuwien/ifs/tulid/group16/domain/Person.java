package at.ac.tuwien.ifs.tulid.group16.domain;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import org.apache.jena.rdf.model.Resource;

public class Person extends AbstractJenaResource {

    public Person(Resource r) {
        super(r);
    }

    public String getName() {
        return getPropertyObject(SemanticApp.FOAF_BASE + "name")
                .asLiteral().getString();
    }

    public String getFirstName() {
        return getPropertyObject(SemanticApp.FOAF_BASE + "firstName")
                .asLiteral().getString();
    }

    public String getLastName() {
        return getPropertyObject(SemanticApp.FOAF_BASE + "lastName")
                .asLiteral().getString();
    }

    public String getTitle() {
        return getPropertyObject(SemanticApp.FOAF_BASE + "title")
                .asLiteral().getString();
    }

    public String getPhoneNumber() {
        return getPropertyObject(SemanticApp.FOAF_BASE + "phone")
                .asLiteral().getString();
    }

    public String getEMail() {
        return getPropertyObject(SemanticApp.FOAF_BASE + "mbox")
                .asLiteral().getString();
    }
}
