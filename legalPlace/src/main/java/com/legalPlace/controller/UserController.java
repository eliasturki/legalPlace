/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legalPlace.controller;

import com.legalPlace.dao.UserRepository;
import com.legalPlace.model.Todo;
import com.legalPlace.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abbasturki.elias
 */
@RestController
@RequestMapping(path = "/users")
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(@RequestBody User t) {
        return userRepository.save(t);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return true;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User t) {
        t.setId_user(id);
        return userRepository.save(t);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<User> search(@RequestParam(name="mc",defaultValue = "") String mc,
                                @RequestParam(name="page",defaultValue = "0") int page,
                                @RequestParam(name="size",defaultValue = "5") int size) {
        return userRepository.search("%"+mc+"%", new PageRequest(page, size));
    }
    
}
