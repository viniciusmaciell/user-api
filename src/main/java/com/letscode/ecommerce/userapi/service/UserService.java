package com.letscode.ecommerce.userapi.service;

import com.letscode.ecommerce.userapi.domain.UserEntity;
import com.letscode.ecommerce.userapi.domain.UserRequest;
import com.letscode.ecommerce.userapi.domain.UserResponse;
import com.letscode.ecommerce.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponse save(UserRequest userRequest) {
//        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userRequest.getEmail());
        if (!userRepository.existsByEmail(userRequest.getEmail())) {

            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userRequest, userEntity);
            userEntity.setCreationDate(ZonedDateTime.now());
            userEntity.setUpdateDate(ZonedDateTime.now());

            userRepository.save(userEntity);

            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(userEntity, userResponse);

            return userResponse;

        }
        throw new RuntimeException("Not found"); //todo

    }

    public List<UserResponse> getAll() {
        var users = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        for (UserEntity userEntity : users) {

            UserResponse userResponse = new UserResponse();

            BeanUtils.copyProperties(userEntity, userResponse);
            userResponseList.add(userResponse);

        }


        return userResponseList;
    }


    public UserEntity getOne(Long id, UserRequest userRequest) {

        Optional<UserEntity> userEntityOptional = userRepository
                .findByIdAndEmailAndPassword(id,
                        userRequest.getEmail(),
                        userRequest.getPassword());
        return userEntityOptional.orElseThrow(RuntimeException::new);

    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {

        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        userEntityOptional.orElseThrow(RuntimeException::new); //todo impl exessao

        if (!userRepository.existsByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword())) {
            throw new RuntimeException(); // todo impl excessao
        }

        UserEntity user = new UserEntity();

        BeanUtils.copyProperties(userEntityOptional, user);
        BeanUtils.copyProperties(userRequest, user);
        userRepository.save(user);
        UserResponse userUpdate = new UserResponse();
        BeanUtils.copyProperties(user, userUpdate);
        return userUpdate;

    }

    public String deleteUser(Long id, UserRequest userRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity user = userEntityOptional.orElseThrow(RuntimeException::new); //todo impl exessao

        if (!userRepository.existsByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword())) {
            throw new RuntimeException(); // todo impl excessao
        }

        userRepository.delete(user);
        return "User successfully deleted";
    }

    public UserEntity getUser(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        return userEntityOptional.orElseThrow(RuntimeException::new);

    }
}
