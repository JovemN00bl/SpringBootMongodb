package application.springbootworkshopmongodb.services;


import application.springbootworkshopmongodb.domain.Post;
import application.springbootworkshopmongodb.repository.PostRepository;
import application.springbootworkshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found..."));
    }




}
