package application.springbootworkshopmongodb.resources;


import application.springbootworkshopmongodb.domain.Post;
import application.springbootworkshopmongodb.services.PostService;
import application.springbootworkshopmongodb.util.URL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "text", defaultValue = "") String minDate,
            @RequestParam(value = "text", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min= URL.convertDate(minDate, new Date(0L));
        Date max= URL.convertDate(maxDate, new Date());
        List<Post> posts = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(posts);
    }

}
