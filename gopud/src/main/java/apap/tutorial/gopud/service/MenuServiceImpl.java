package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDB;
import apap.tutorial.gopud.repository.RestoranDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDB menuDb;

    @Override
    public void addMenu(MenuModel menu){
        menuDb.save(menu);
    }

    @Override
    public List<MenuModel> findAllMenuByIdRestoran(Long idRestoran){
        return menuDb.findByRestoranIdRestoran(idRestoran);
    }

    @Override
    public List<MenuModel> getMenuList(){return menuDb.findAll();}

    @Override
    public MenuModel changeMenu(MenuModel menuModel){
        MenuModel targetMenu = menuDb.findById(menuModel.getId()).get();
        try{
            targetMenu.setNama(menuModel.getNama());
            targetMenu.setHarga(menuModel.getHarga());
            targetMenu.setDeskripsi(menuModel.getDeskripsi());
            targetMenu.setDurasiMasak(menuModel.getDurasiMasak());
            menuDb.save(targetMenu);
            return targetMenu;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public boolean deleteMenu(MenuModel menu){
        menuDb.delete(menu);
        return true;
    }

    @Override
    public List<MenuModel> getListMenuOrderByHargaAsc(Long idRestoran){
        return menuDb.findByRestoranIdRestoranOrderByHargaAsc(idRestoran);
    }
}
