package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDB userDb;

    @Override
    public UserModel addUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserById(String id){
        return userDb.findById(id);
    }

    @Override
    public UserModel getUserByUsername(String username){
        return userDb.findByUsername(username);
    }

    @Override
    public boolean cekPasswordLama(String username, String passwordLama){
        UserModel user = userDb.findByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(passwordLama, user.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public boolean cekValidation(String password){
        String num = ".*[0-9].*";
        String hurufKapital = ".*[A-Z].*";
        String hurufKecil = ".*[a-z].*";
        Integer length = password.length();
        if(length>=8&&password.matches(num)&&(password.matches(hurufKapital)||password.matches(hurufKecil))){
            return true;
        }
        return false;
    }

    @Override
    public UserModel updatePassword(String username, String password){
        UserModel target = getUserByUsername(username);
        try{
            String temp = encrypt(password);
            target.setPassword(temp);
            return userDb.save(target);
        } catch (NullPointerException e){
            return null;
        }
    }

}
