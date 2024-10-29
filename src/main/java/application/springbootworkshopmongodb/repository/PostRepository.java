package application.springbootworkshopmongodb.repository;

import application.springbootworkshopmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}