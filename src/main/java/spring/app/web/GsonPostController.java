package spring.app.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.app.domain.Post;
import spring.app.gson.PostGsonDeserializer;
import spring.app.gson.PostGsonSerializer;
import spring.app.service.contract.PostService;

@RestController
@RequestMapping("/api/gson/posts")
@Slf4j
public class GsonPostController {

    private final PostService postService;
    private final Gson gson =  new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .registerTypeAdapter(Post.class, new PostGsonSerializer())
            .registerTypeAdapter(Post.class, new PostGsonDeserializer())
            .create();

    @Autowired
    public GsonPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public String getPosts(){
        return gson.toJson(postService.getAllPosts());
    }

    @GetMapping(path = "/{id}", produces = "application/gson")
    public String getPosts(@PathVariable("id") Long id){
        return gson.toJson(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody String body){
        log.info("Body received: {}", body);
        Post post = gson.fromJson(body, Post.class);
        log.info("Post: {}", post);
        Post created = postService.addPost(post);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(created.getId().toString()).toUri()
        ).body(gson.toJson(created));
    }
}
