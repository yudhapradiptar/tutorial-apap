package apap.tutorial.gopud.repository;

import apap.tutorial.gopud.model.MenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDB extends JpaRepository<MenuModel, Long>{
    List<MenuModel> findByRestoranIdRestoran(Long restoranId);
}
