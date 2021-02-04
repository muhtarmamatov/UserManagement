package com.muhtar.service;

import com.muhtar.domain.User;
import com.muhtar.utils.ResultInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    User findUserByEmail(String email);
    void  saveUser(User user);
    Boolean removeAll();
    void removeById(Long id);
    User findById(Long id);
    Page<User> searchByTerm(String name, Pageable pageable);
    Page<User> listUsers(Pageable pageable);
    List<User> searchBy(String keyword, String criteria);
    void deleteEmployeeById(Long id);

}
