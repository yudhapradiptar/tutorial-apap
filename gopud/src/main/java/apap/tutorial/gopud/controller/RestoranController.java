package apap.tutorial.gopud.controller;

import java.util.Comparator;
import java.util.List;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController{
    @Qualifier("restoranServiceImpl")
    @Autowired
    private RestoranService restoranService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public String home() {return "home"; }

    //URL mapping yang digunakan untuk mengakses halaman add restoran
    @RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
    public String addRestoranFormPage(Model model){
        RestoranModel newRestoran = new RestoranModel();
        model.addAttribute("restoran", newRestoran);
        return "form-add-restoran";
    }

    //URL mapping yang digunkan untuk submit form yang telah anda masukkan pada halaman add restoran
    @RequestMapping(value = "/restoran/add", method = RequestMethod.POST)
    public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model){
        restoranService.addRestoran(restoran);
        model.addAttribute("namaResto", restoran.getNama());
        return "add-restoran";
    }

    //URL mapping view
    @RequestMapping(path = "/restoran/view", method = RequestMethod.GET)
    public String view(
            //Request Parameter untuk dipass
            @RequestParam(value = "idRestoran") Long idRestoran, Model model
            ) {
        //Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        List<MenuModel> menuList = menuService.findAllMenuByIdRestoran(restoran.getIdRestoran());
        model.addAttribute("menuList", menuList);

        //Return view template
        return "view-restoran";
    }

    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value="restoran/change/{idRestoran}", method = RequestMethod.GET)
    public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model) {
        //Mengambuk existing data restoran
        RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        model.addAttribute("restoran", existingRestoran);
        return "form-change-restoran";
    }

    //API yang digunakan untuk submit form change restoran
    @RequestMapping(value="restoran/change/{idRestoran}", method = RequestMethod.POST)
    public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model){
        RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
        model.addAttribute("restoran", newRestoranData);

        return "change-restoran";
    }

    @RequestMapping("/restoran/viewall")
    public String viewall(Model model){

        // Mengambil semua objek RestoranModel yang ada
        List<RestoranModel> listRestoran = restoranService.getRestoranList();

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("restoList", listRestoran);
        listRestoran.sort(Comparator.comparing(RestoranModel::getNama));
        // Return view template
        return "viewall-restoran";
    }

    @RequestMapping(value="/restoran/delete/{idRestoran}", method = RequestMethod.GET)
    public String deleteRestoran(@PathVariable Long idRestoran,RestoranModel restoran, Model model){
        String strIdRestoran = String.valueOf(idRestoran);
        RestoranModel restoranDeleted = restoranService.getRestoranByIdRestoran(idRestoran).get();
        boolean isDeleted = restoranService.deleteRestoran(restoranDeleted);
        model.addAttribute("idRestoran", strIdRestoran);

        if(isDeleted==true){
            return "delete-restoran";
        } else{return "error-delete-restoran";}
    }



}

