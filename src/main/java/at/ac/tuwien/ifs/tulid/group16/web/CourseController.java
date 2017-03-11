package at.ac.tuwien.ifs.tulid.group16.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.ifs.tulid.group16.domain.Course;
import at.ac.tuwien.ifs.tulid.group16.repo.CourseRepository;

@RestController
@RequestMapping(path="/courses")
public class CourseController {

	private CourseRepository repo;

	@Autowired
	public CourseController(CourseRepository repo) {
		this.repo = repo;
	}
	
	@RequestMapping(path="/{courseId:[a-zA-z0-9\\.]+}", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<Resource<Course>> getOne(@PathVariable("courseId") String courseId) {
		Course c = repo.findOne(courseId);
		
		if(c != null) {
			Resource<Course> res = new Resource<Course>(c);
			res.add(createSelfLink(courseId));
			//hm ... gefällt mir nicht ganz so gut. 
			res.add(linkTo(CourseController.class).slash(courseId).slash("semesterCourses").withRel("semesterCourses"));
			return new HttpEntity<Resource<Course>>(res);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	private Link createSelfLink(String courseId) {
		return linkTo(CourseController.class).slash(courseId).withSelfRel();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Resource<Course>> findAll() {
		return repo.findAll().stream().map(c -> new Resource<>(c, createSelfLink(c.getId())))
			.collect(Collectors.toList());
	}
}
