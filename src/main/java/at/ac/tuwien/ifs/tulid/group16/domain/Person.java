package at.ac.tuwien.ifs.tulid.group16.domain;

import at.ac.tuwien.ifs.tulid.group16.SemanticApp;
import at.ac.tuwien.ifs.tulid.group16.repo.CourseRepository;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person extends AbstractJenaResource {

    private final static Logger LOG = LoggerFactory.getLogger(CourseRepository.class);

    String uri = "";
    Resource myR;

    public Person(Resource r) {
        super(r);

        uri = r.getURI();
        myR = r;
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
        try {
            return getPropertyObject(SemanticApp.FOAF_BASE + "title")
                    .asLiteral().getString();
        } catch (Exception e){
            return "";
        }
    }
    public String getPhoneNumber() {
        try {
        return getPropertyObject(SemanticApp.FOAF_BASE + "phone")
                .asLiteral().getString();
    } catch (Exception e){
        return "";
    }
    }

    public String getEMail() {
        try {
        return getPropertyObject(SemanticApp.FOAF_BASE + "mbox")
                .asLiteral().getString();
    } catch (Exception e){
        return "";
        }
    }

    public String getOid() {
        try {
            return getPropertyObject(SemanticApp.NS_BASE + "#hasOid").asLiteral().getString();
        } catch (Exception e){
            return "";
        }
    }

    public String getURI() {
        return uri;
    }


}
