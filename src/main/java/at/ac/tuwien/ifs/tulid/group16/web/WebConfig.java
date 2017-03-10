package at.ac.tuwien.ifs.tulid.group16.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;

@Configuration
public class WebConfig {

	@Bean
	public CurieProvider curieProvider() {
		return new DefaultCurieProvider("ifs", 
				new UriTemplate("http://ifs.tuwien.ac.at/tulid/group16#{rel}"));
	}
}
