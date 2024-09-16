/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.repository;

import crud.repository.model.UserDto;

/**
 *
 * @author losmelli
 */
public interface IUserDao {

    void registerUser(UserDto user);

    UserDto retrieveUser(String username);

}
