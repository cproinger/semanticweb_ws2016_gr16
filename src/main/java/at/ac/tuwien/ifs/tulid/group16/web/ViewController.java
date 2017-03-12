package at.ac.tuwien.ifs.tulid.group16.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

//	@RequestMapping({
//		"/", "/Courses", "/People", "/SemesterCourses", "/Rooms",
//		"/Courses/**", "/People/**", "/SemesterCourses/**", "/Rooms/**"
//	})
	
	/**
	 * since only angular2 paths start with capital letters, 
	 * this mapping correctly forwards to index.html when
	 * they are requested directly. :-)
	 */
	@RequestMapping({
		"/", "/{path:[A-Z].*}"
	})
	public String index() {
		return "forward:/index.html";
	}
}
