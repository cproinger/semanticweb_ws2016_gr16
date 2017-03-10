package at.ac.tuwien.ifs.tulid.group16.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.ifs.tulid.group16.domain.Course;
import at.ac.tuwien.ifs.tulid.group16.repo.CourseRepository;

@RestController
@RequestMapping(path="/courses/")
public class CourseController {

	private CourseRepository repo;

	@Autowired
	public CourseController(CourseRepository repo) {
		this.repo = repo;
	}
	
	@RequestMapping(path="/{courseId:[a-zA-z0-9\\.]+}", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<GenericResource<Course>> getOne(@PathVariable("courseId") String courseId) {
		Course c = repo.findOne(courseId);
		
		if(c != null) {
			GenericResource<Course> res = new GenericResource<Course>(c);
			res.add(linkTo(CourseController.class).slash(courseId).withSelfRel());
			return new HttpEntity<GenericResource<Course>>(res);
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	@ResponseBody
	public List<GenericResource<Course>> findAll() {
		return new ArrayList<GenericResource<Course>>();
	}
}
