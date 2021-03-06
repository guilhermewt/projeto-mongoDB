
package com.guilhermesilva.projetomongoDB.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermesilva.projetomongoDB.domain.Post;
import com.guilhermesilva.projetomongoDB.resource.util.URL;
import com.guilhermesilva.projetomongoDB.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;
		
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Post> findByPost(@PathVariable String id) {
		Post obj = postService.findById(id);
	    return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/fullSearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> searchTitle(@RequestParam(value="text", defaultValue="")  String text,
												  @RequestParam(value="mindate", defaultValue="")  String minDate,
												  @RequestParam(value="maxdate", defaultValue="")  String maxDate){
		
		text = URL.decodeParam(text);
		Date min = URL.convert(minDate, new Date(0L));
		Date max = URL.convert(maxDate, new Date());
		List<Post> list = postService.searchFull(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/titleSearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> searchTitle(@RequestParam(value="text", defaultValue="")  String text){
		text = URL.decodeParam(text);
		List<Post> list = postService.searchTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
}
