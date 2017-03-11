package at.ac.tuwien.ifs.tulid.group16.web;

import at.ac.tuwien.ifs.tulid.group16.domain.Course;
import at.ac.tuwien.ifs.tulid.group16.domain.Person;
import at.ac.tuwien.ifs.tulid.group16.repo.CourseRepository;
import at.ac.tuwien.ifs.tulid.group16.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(path="/persons/")
public class PersonController {

    private PersonRepository repo;

    @Autowired
    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    /*TODO
    @RequestMapping(path="/{personId:[0-9]+}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Resource<Person>> getOne(@PathVariable("personId") String personId) {
        Person p = repo.findOne(personId);

        if(p != null) {
            Resource<Person> res = new Resource<Person>(p);
            //res.add(createSelfLink(personId));
            //hm ... gefällt mir nicht ganz so gut.
            //res.add(linkTo(CourseController.class).slash(personId).slash("semesterCourses").withRel("semesterCourses"));
            return new HttpEntity<Resource<Person>>(res);
        } else {
            throw new ResourceNotFoundException();
        }
    }*/

    private Link createSelfLink(String courseId) {
        return linkTo(CourseController.class).slash(courseId).withSelfRel();
    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource<Person>> findAll() {

        List<Person> testList = repo.findAll();

        List<Resource<Person>> test = repo.findAll().stream().map(p -> new Resource<>(p))
                .collect(Collectors.toList());

        return test;
    }
}
