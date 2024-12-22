package com.toto.rest.webservices.restful_web_services.jpa;

import com.toto.rest.webservices.restful_web_services.user.Post;
import com.toto.rest.webservices.restful_web_services.user.User;
import com.toto.rest.webservices.restful_web_services.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaController(UserRepository repository, PostRepository postRepository){
        this.userRepository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }


//    http://localhost:8080/users
//    EntityModel
//    WebMvcLinkBuilder
    @GetMapping(path = "jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw   new UserNotFoundException("id:" + id);
        }

        EntityModel<User> entity = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entity.add(link.withRel("all-users"));
        return entity;
    }

    @DeleteMapping(path = "jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping(path = "jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping (path = "jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw   new UserNotFoundException("id:" + id);
        }
        return user.get().getPosts();
    }

    @PostMapping(path = "jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw   new UserNotFoundException("id:" + id);
        }
        post.setUser(user.get());
        Post savedpost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedpost.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedpost);
    }
}
