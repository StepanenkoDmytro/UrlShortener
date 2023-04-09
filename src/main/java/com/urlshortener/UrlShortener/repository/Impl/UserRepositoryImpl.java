package com.urlshortener.UrlShortener.repository.Impl;

import com.urlshortener.UrlShortener.entity.User;
import com.urlshortener.UrlShortener.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private List<User> dataBase;

    {
        dataBase = new ArrayList<>();
        User user = new User("user", "123");
        dataBase.add(user);
    }

    @Override
    public User findByUsername(String username) {
        for(User user : dataBase){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public void save(User user) {
//        if(dataBase == null){
//            dataBase = new ArrayList<>();
//        }
        dataBase.add(user);
    }
}
