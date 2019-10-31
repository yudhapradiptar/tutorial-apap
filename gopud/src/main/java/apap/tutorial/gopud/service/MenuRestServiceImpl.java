package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import apap.tutorial.gopud.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MenuRestServiceImpl implements MenuRestService{
    private final WebClient webClient;

    @Autowired
    private MenuDB menuDb;

    @Override
    public MenuModel createMenu(MenuModel menu){ return menuDb.save(menu);}

    @Override
    public List<MenuModel> retriveListMenu() {return menuDb.findAllByOrderByNamaAsc();}

    @Override
    public MenuModel getMenuByIdMenu(Long idMenu){
        MenuModel menu = menuDb.findByIdMenu(idMenu);
        return menu;
    }

    @Override
    public void deleteMenu(Long idMenu){
        MenuModel menu = getMenuByIdMenu(idMenu);
        menuDb.delete(menu);
    }

    @Override
    public MenuModel changeMenu(Long idMenu, MenuModel menuUpdate){
        MenuModel menu = getMenuByIdMenu(idMenu);
        menu.setNama(menuUpdate.getNama());
        menu.setHarga(menuUpdate.getHarga());
        menu.setDurasiMasak(menuUpdate.getDurasiMasak());
        menu.setDeskripsi(menuUpdate.getDeskripsi());
        return menuDb.save(menu);
    }

    public MenuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.menuUrl).build();
    }
}
