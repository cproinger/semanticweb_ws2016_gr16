package at.ac.tuwien.ifs.tulid.group16.web;

import org.springframework.hateoas.ResourceSupport;

public class GenericResource<O> extends ResourceSupport {

	O content;

	public GenericResource(O content) {
		super();
		this.content = content;
	}
	
	public O getContent() {
		return content;
	}
}
