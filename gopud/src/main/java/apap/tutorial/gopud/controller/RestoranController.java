package apap.tutorial.gopud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController {
    @Autowired
    private RestoranService restoranService;
    // URL mapping add
    @RequestMapping("/restoran/add")
    public String add(
            // Request Parameter untuk dipass
            @RequestParam(value = "idRestoran", required = true) String idRestoran,
            @RequestParam(value = "nama", required = true) String nama,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
            Model model
    ) {
        // Membuat objek RestoranModel
        RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);

        // Memanggil service addRestoran
        restoranService.addRestoran(restoran);

        // Add variabel nama restoran ke "namaresto" untuk dirender
        model.addAttribute("namaResto", nama);

        // Return view template
        return "add-restoran"; }

    // URL mapping view
    @RequestMapping("/restoran/view")
    public String view(
            // Request Parameter untuk dipass
            @RequestParam(value = "idRestoran") String idRestoran, Model model){
        // Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        // Return view template
        if(restoran!=null){
            return "view-restoran";
        } else{
            return "error-restoran";
        } }

    // URL mapping viewall
    @RequestMapping("/restoran/viewall")
    public String viewall(Model model){

        // Mengambil semua objek RestoranModel yang ada
        List<RestoranModel> listRestoran = restoranService.getRestoranList();

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("restoList", listRestoran);

        // Return view template
        return "viewall-restoran";
    }

    @RequestMapping("/restoran/view/id-restoran/{idRestoran}")
    public String viewWithPathVariable(
            @PathVariable(value = "idRestoran") String idRestoran, Model model){
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        model.addAttribute("resto", restoran);

        if(restoran!=null){
            return "view-restoran";
        } else{
            return "error-restoran";
        }
    }

    @RequestMapping("/restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTeleponBaru}")
    public String updateNomorTelepon(
            @PathVariable(value = "idRestoran") String idRestoran,
            @PathVariable(value = "nomorTeleponBaru") Integer nomorTeleponBaru,
            Model model){
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        model.addAttribute("resto", restoran);

        if(restoran!=null){
            restoran.setNomorTelepon(nomorTeleponBaru);
            return "view-restoran";
        } else{
            return "error-restoran";
        }

    }

    @RequestMapping("/restoran/delete/id/{idRestoran}")
    public String deleteRestoran(
            @PathVariable(value = "idRestoran") String idRestoran,
            Model model) {

        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        if(restoran!=null){
            restoranService.deleteRestoran(restoran);
            return "delete-restoran";
        } else{
            return "error-restoran";
        }
    }




}
