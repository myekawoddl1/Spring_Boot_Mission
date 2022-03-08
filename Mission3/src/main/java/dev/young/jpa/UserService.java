package dev.young.jpa;

import dev.young.jpa.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(@Autowired UserDao userDao){
        this.userDao = userDao;
    }
    public void createUser(UserDto userDto){
        this.userDao.createUser(userDto);
    }

    public UserDto readUser(String id){
        UserEntity userEntity = this.userDao.readUser(id);
        return new UserDto(
                userEntity.getId(),
                userEntity.getName()
        );
    }

    public List<UserDto> readUserAll(){
        Iterator<UserEntity> iterator = this.userDao.readUserAll();
        List<UserDto> userDtoList = new ArrayList<>();

        while (iterator.hasNext()){
            UserEntity userEntity = iterator.next();
            userDtoList.add(new UserDto(
                    userEntity.getId(),
                    userEntity.getName()
            ));
        }
        return userDtoList;
    }

    public void updateUser(String id, UserDto userDto){
        this.userDao.updateUser(id ,userDto);
    }

    public void deleteUser(String id){
        this.userDao.deleteUser(id);
    }
}
