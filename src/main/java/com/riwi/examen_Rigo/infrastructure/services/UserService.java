package com.riwi.examen_Rigo.infrastructure.services;

import java.util.ArrayList;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.examen_Rigo.api.dto.request.UserReq;
import com.riwi.examen_Rigo.api.dto.response.UserResp;
import com.riwi.examen_Rigo.domain.entities.User;
import com.riwi.examen_Rigo.domain.repositories.UserRepository;
import com.riwi.examen_Rigo.infrastructure.adtract_services.IUserService;
import com.riwi.examen_Rigo.util.enums.SortType;
import com.riwi.examen_Rigo.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    
    @Autowired
    private final UserRepository userRepository;
    
    @Override
    public UserResp create(UserReq request) {
       
       User user = this.requestToEntity(request, new User());

       return this.entityToResponse(this.userRepository.save(user));

    }

    @Override
    public UserResp get(Long id) {

        return this.entityToResponse(this.find(id));

    }
    @Override
    public UserResp update(UserReq request, Long id) {
        
        User user = this.find(id);

        User userUpdate = this.requestToEntity(request, user);
        userUpdate.setId(id);
        
        return this.entityToResponse(this.userRepository.save(userUpdate));
    }

    @Override
    public void delete(Long id) {
        
        User user = this.find(id);
        this.userRepository.delete(user);
    }

    private User find(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("user"));
    }

    @Override
    public Page<UserResp> getAll(int page, int size, SortType sortType) {
        
        if (page < 0)
            page = 0;
 
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        
        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }


    private UserResp entityToResponse(User user){

        UserResp response = new UserResp();
        BeanUtils.copyProperties(user, response);
        
        return response;
    
    }

    private User requestToEntity (UserReq request, User entity){

        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setActive(request.getActive());
        if (entity.getSurveys() == null){
            entity.setSurveys(new ArrayList<>());
        }

        return entity;
        
    }
    
}
