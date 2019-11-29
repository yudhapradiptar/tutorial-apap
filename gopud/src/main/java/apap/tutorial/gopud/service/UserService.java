package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    UserModel addUser(UserModel user);
    public String encrypt(String password);

    UserModel getUserById(String id);

    UserModel getUserByUsername(String username);

    boolean cekPasswordLama(String username, String passwordLama);

    boolean cekValidation(String password);

    UserModel updatePassword(String username, String password);
}
