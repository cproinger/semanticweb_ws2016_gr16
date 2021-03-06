package at.ac.tuwien.ifs.tulid.group16.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import at.ac.tuwien.ifs.tulid.group16.domain.Room;
import at.ac.tuwien.ifs.tulid.group16.domain.SemesterCourse;
import at.ac.tuwien.ifs.tulid.group16.repo.SemesterCourseRespository;

@RestController
@RequestMapping(path = "/semesterCourses/")
public class SemesterCourseController {

	private SemesterCourseRespository repo;

	@Autowired
	public SemesterCourseController(SemesterCourseRespository repo) {
		this.repo = repo;
	}

	@RequestMapping(path = "/{courseId:[a-zA-Z0-9\\.]+}/semester/{semester}", produces="application/hal+json")
	public HttpEntity<Resource<SemesterCourse>> getOne(@PathVariable("courseId") String courseId,
			@PathVariable("semester") String semester) {
		SemesterCourse sc = repo.findOne(courseId, semester);

		if (sc != null) {
			Resource<SemesterCourse> res = new Resource<SemesterCourse>(sc);
			res.add(createSelfLink(courseId, semester));
			// hm ... gefällt mir nicht ganz so gut.
			res.add(linkTo(CourseController.class).slash(courseId).withRel("course"));
			res.add(createRoomLinks(sc));
			return new HttpEntity<Resource<SemesterCourse>>(res);
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	private Set<Link> createRoomLinks(SemesterCourse sc) {
		Set<Link> result = new HashSet<>();
		if (sc == null || sc.getRooms() == null || sc.getRooms().isEmpty()) {
			return result;
		}
		for (String r : sc.getRooms()) {
				result.add(linkTo(RoomController.class).slash(r).withRel("rooms"));
		}
		return result;
	}

	private Link createSelfLink(String courseId, String semester) {
		return linkTo(SemesterCourseController.class).slash(courseId).slash("semester").slash(semester).withSelfRel();
	}

	@RequestMapping(path = "/{courseId:[a-zA-Z0-9\\.]+}", method = RequestMethod.GET, produces="application/hal+json")
	@ResponseBody
	public List<Resource<SemesterCourse>> findAll(
			@PathVariable("courseId") String courseId) {
		return repo.findByCourseId(courseId).stream()
				.map(c -> new Resource<>(c, createSelfLink(
						c.getCourse().getId(), 
						c.getSemester())))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/hal+json")
	@ResponseBody
	public List<Resource<SemesterCourse>> findAll() {
		return repo.findAll().stream()
				.map(c -> new Resource<>(c))
				.collect(Collectors.toList());
	}
}
