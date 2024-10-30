package application.springbootworkshopmongodb.config;

import application.springbootworkshopmongodb.domain.Post;
import application.springbootworkshopmongodb.domain.User;
import application.springbootworkshopmongodb.dto.AuthorDTO;
import application.springbootworkshopmongodb.dto.CommentDTO;
import application.springbootworkshopmongodb.repository.PostRepository;
import application.springbootworkshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));


        Post post1 = new Post(null,new AuthorDTO(maria),"vou viajar para sao paulo!", "partiu viagem", sdf.parse("21/02/2012"));
        Post post2 = new Post(null, new AuthorDTO(maria), "Bom dia", "Acordei feliz", sdf.parse("23/02/2012"));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2012"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("20/03/2012"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("tenha um otimo dia!", sdf.parse("21/03/2012"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);




        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
