package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;
    private List<MenuModel> listMenu;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value="idRestoran") Long idRestoran, Model model){
        MenuModel menu = new MenuModel();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        List<MenuModel> menuList = new ArrayList<>();
        menuList.add(menu);

        restoran.setListMenu(menuList);

        model.addAttribute("resto", restoran);
        model.addAttribute("titleNavbar", "Add Menu");

        return "form-add-menu";
    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute RestoranModel restoran, MenuModel menu, Model model){
        RestoranModel restorann = restoranService.getRestoranByIdRestoran(restoran.getIdRestoran()).get();

        for(MenuModel menuTambahan: restoran.getListMenu()){
            menu.setRestoran(restorann);
            menuService.addMenu(menuTambahan);
        }
        menuService.addMenu(menu);

        model.addAttribute("nama", menu.getNama());
        model.addAttribute("titleNavbar", "Add Menu");

        return "add-menu";
    }

    @RequestMapping(value = "/menu/add/{restoranId}", params= {"addRow"}, method=RequestMethod.POST)
    private String addRow(@ModelAttribute RestoranModel restoran, Model model) {

        if (restoran.getListMenu() == null || restoran.getListMenu().size() == 0) {
            restoran.setListMenu(new ArrayList<>());
        }
        restoran.getListMenu().add(new MenuModel());
        model.addAttribute("resto", restoran);

        return "form-add-menu";
    }

    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value="restoran/change-menu/{id}", method = RequestMethod.GET)
    public String changeMenuFormPage(@ModelAttribute MenuModel menu,@PathVariable Long id, Model model) {
        List<MenuModel> listMenu = menuService.getMenuList();
        MenuModel menuLama = null;
        for(MenuModel cariMenu : listMenu){
            if(cariMenu.getId().equals(id)){
                menuLama = cariMenu;
            }
        }
        model.addAttribute("menu", menuLama);
        model.addAttribute("titleNavbar", "Change Data Menu");
        return "form-change-menu";
    }

    //API yang digunakan untuk submit form change restoran
    @RequestMapping(value="restoran/change-menu/{id}", method = RequestMethod.POST)
    public String changeMenuFormSubmit(@ModelAttribute MenuModel menu,@PathVariable Long id, Model model){
        MenuModel menuBaru = menuService.changeMenu(menu);
        model.addAttribute("menu", menuBaru);
        model.addAttribute("titleNavbar", "Change Data Menu");
        return "change-menu";
    }

    @RequestMapping(value="/menu/delete-menu", method = RequestMethod.POST)
    public String deleteRestoran(@ModelAttribute RestoranModel restoran, Model model){
        for(MenuModel menu: restoran.getListMenu()){
            menuService.deleteMenu(menu);
        }
        model.addAttribute("titleNavbar", "Delete Menu");
        return "delete-menu";
        /*List<MenuModel> listMenu = menuService.getMenuList();
        for(MenuModel cariMenu : listMenu){
            if(cariMenu.getId().equals(menu.getId())){
                menuService.deleteMenu(cariMenu);
                model.addAttribute("menu", cariMenu);
                return "delete-menu";
            }
        }
        return "error-restoran";*/
    }
}
