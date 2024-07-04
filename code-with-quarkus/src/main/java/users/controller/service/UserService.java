package users.controller.service;

import io.vertx.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import users.controller.entity.UserEntity;
import users.controller.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

//Annotation que define o bean como disponivel para fazer injeção de dependência
@ApplicationScoped
public class UserService {

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return UserEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(UUID userId) {
        //(UserEntity) -> Casting
        return (UserEntity) UserEntity.findByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity createUser(UserEntity userEntity) {
        UserEntity.persist(userEntity);
        return userEntity;
    }

    public UserEntity updateUser(UUID userId, UserEntity userEntity) {
        var user = findById(userId);
        user.username = userEntity.username;
        UserEntity.persist(userEntity);
        return  user;
    }

    public void deleteById(UUID userId) {
       var user = findById(userId);
       UserEntity.deleteById(user.userId);
    }

}
