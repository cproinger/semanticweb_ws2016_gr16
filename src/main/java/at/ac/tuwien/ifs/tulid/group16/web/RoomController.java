package at.ac.tuwien.ifs.tulid.group16.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

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

import at.ac.tuwien.ifs.tulid.group16.domain.Room;
import at.ac.tuwien.ifs.tulid.group16.repo.RoomRepository;

@RestController
@RequestMapping(path="/rooms/")
public class RoomController {

	private RoomRepository repo;

	@Autowired
	public RoomController(RoomRepository repo) {
		this.repo = repo;
	}
	
	@RequestMapping(path="/{roomId:[A-Z0-9]+}", method = RequestMethod.GET, produces="application/hal+json")
	@ResponseBody
	public HttpEntity<Resource<Room>> getOne(@PathVariable("roomId") String roomId) {
		Room r = repo.findOne(roomId);
		
		if(r != null) {
			Resource<Room> res = new Resource<Room>(r, createSelfLink(roomId));
			return new HttpEntity<Resource<Room>>(res);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	private Link createSelfLink(String roomId) {
		return linkTo(RoomController.class).slash(roomId).withSelfRel();
	}
	
	@RequestMapping(path="/", method = RequestMethod.GET, produces="application/hal+json")
	@ResponseBody
	public List<Resource<Room>> findAll() {
		return repo.findAll().stream().map(r -> new Resource<>(r, createSelfLink(r.getId()) )).collect(Collectors.toList());
	}
}
