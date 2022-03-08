package dev.young.jpa;

import dev.young.jpa.entity.UserEntity;
import dev.young.jpa.repository.UserRepository;
import dev.young.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private final UserRepository userRepository;

    public UserDao(
            @Autowired UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    public void createUser(UserDto dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.getName());
        this.userRepository.save(userEntity);
    }

    public UserEntity readUser(String id){
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if(userEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userEntity.get();
    }

    public Iterator<UserEntity> readUserAll(){
        return this.userRepository.findAll().iterator();
    }

    public void updateUser(String id, UserDto dto){
        Optional<UserEntity> targetEntity = this.userRepository.findById(id);
        if(targetEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserEntity userEntity = targetEntity.get();
        userEntity.setName(dto.getName() == null ? userEntity.getName() : dto.getName());
        this.userRepository.save(userEntity);
    }

    public void deleteUser(String id){
        //this.userRepository.deleteById((long) id);
        Optional<UserEntity> targetEntity = this.userRepository.findById(id);
        if (targetEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.userRepository.delete(targetEntity.get());
    }
}
