package at.ac.tuwien.ifs.tulid.group16.web;

import at.ac.tuwien.ifs.tulid.group16.domain.Person;
import at.ac.tuwien.ifs.tulid.group16.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/persons/")
public class PersonController {

    private final String URIBASE = "https://tiss.tuwien.ac.at/adressbuch/adressbuch/person/";

    private PersonRepository repo;

    @Autowired
    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    //Example: http://localhost:8080/persons/628153
    @RequestMapping(path="/{personOid:[0-9]+}", method = RequestMethod.GET, produces="application/hal+json")
    @ResponseBody
    public HttpEntity<Resource<Person>> getOne(@PathVariable("personOid") String personOid) {
        Person p = repo.findOne(personOid);

        if(p != null) {
            Resource<Person> res = new Resource<Person>(p);
            res.add(new Link(p.getURI()));
            return new HttpEntity<Resource<Person>>(res);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    //Example: http://localhost:8080/persons/
    @RequestMapping(path="/", method = RequestMethod.GET, produces="application/hal+json")
    @ResponseBody
    public List<Resource<Person>> findAll() {
        return repo.findAll().stream().map(p -> new Resource<>(p))
                .collect(Collectors.toList());
    }
}
