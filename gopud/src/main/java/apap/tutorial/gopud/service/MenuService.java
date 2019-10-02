package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface MenuService {
    void addMenu(MenuModel menu);
    List<MenuModel> findAllMenuByIdRestoran(Long idRestoran);

    MenuModel changeMenu(MenuModel menu);

    List<MenuModel> getMenuList();

    boolean deleteMenu(MenuModel menu);

    List<MenuModel> getListMenuOrderByHargaAsc(Long idRestoran);
}
