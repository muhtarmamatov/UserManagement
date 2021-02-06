package com.muhtar.service;

import com.muhtar.domain.User;
import com.muhtar.repository.UserRepository;
import com.muhtar.service.UserService;
import com.muhtar.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Boolean removeAll() {
        userRepository.deleteAll();
        return Boolean.TRUE;

    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> searchByTerm(String name, Pageable pageable) {
        return userRepository.searchByTerm(name,pageable);
    }

    @Override
    public Page<User> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> searchBy(String keyword, String criteria) {
        if ("firstName".equals(criteria)){
           return userRepository.findByFirstNameIgnoreCaseContaining(keyword);
        }else if("lastName".equals(criteria)){
          return   userRepository.findByLastNameIgnoreCaseContaining(keyword);
        }else if("email".equals(criteria)){
           return userRepository.findByEmailIgnoreCaseContaining(keyword);
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        userRepository.deleteById(id);
    }
}
