package application.springbootworkshopmongodb.services;

import application.springbootworkshopmongodb.domain.User;
import application.springbootworkshopmongodb.dto.UserDTO;
import application.springbootworkshopmongodb.repository.UserRepository;
import application.springbootworkshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public User update(User obj){

            User entity = findById(obj.getId());
            updateData(entity,obj);
            return repository.save(entity);
    }




    private void updateData(User entity, User userObj){
        entity.setName(userObj.getName());
        entity.setEmail(userObj.getEmail());
    }



    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }

    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }


}
