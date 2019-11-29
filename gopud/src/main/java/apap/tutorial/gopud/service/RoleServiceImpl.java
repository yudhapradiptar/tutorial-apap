package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RoleModel;
import apap.tutorial.gopud.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDB roleDb;

    @Override
    public List<RoleModel> getListRole(){
        return roleDb.findAll();
    }

}
