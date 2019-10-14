package apap.tutorial.gopud.service;
import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDB;
import apap.tutorial.gopud.repository.RestoranDB;
import apap.tutorial.gopud.controller.RestoranControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class MenuServiceImplTest {
    @InjectMocks
    MenuService menuService = new MenuServiceImpl();

    @InjectMocks
    RestoranService restoranService = new RestoranServiceImpl();

    @Mock
    MenuDB menuDb;

    @Mock
    RestoranDB restoranDB;

    private RestoranModel generateDummyRestoranModel(int count) {
        RestoranModel dummyRestoranModel = new RestoranModel();
        dummyRestoranModel.setNama("dummy " + count);
        dummyRestoranModel.setAlamat("alamat " + count);
        dummyRestoranModel.setIdRestoran((long)count);
        dummyRestoranModel.setNomorTelepon(14000);
        dummyRestoranModel.setListMenu(new ArrayList<>());
        return dummyRestoranModel;
    }

    private MenuModel generateDummyMenuModel(int count) {
        MenuModel dummyMenuModel = new MenuModel();
        dummyMenuModel.setNama("dummyMenu " + count);
        dummyMenuModel.setHarga(BigInteger.valueOf(count*1000));
        dummyMenuModel.setDeskripsi("dummyMenu Enak");
        dummyMenuModel.setDurasiMasak(15);
        dummyMenuModel.setId((long)count);
        return dummyMenuModel;
    }

    @Test
    public void whenAddValidMenuItShouldCallMenuRepositorySave() {
        MenuModel newMenu = generateDummyMenuModel(1);
        newMenu.setRestoran(generateDummyRestoranModel(1));
        menuService.addMenu(newMenu);
        verify(menuDb, times(1)).save(newMenu);
    }

    @Test
    public void whenFindAllMenuByIdRestoranShouldReturnAllMenuCorrectly(){
        MenuModel newMenu = generateDummyMenuModel(1);
        newMenu.setRestoran(generateDummyRestoranModel(1));
        menuService.addMenu(newMenu);
        menuService.findAllMenuByIdRestoran(1L);
        verify(menuDb, times(1)).findByRestoranIdRestoran(1L);
    }

    @Test
    public void whenGetMenuListShouldReturnAllMenu(){
        MenuModel newMenu = generateDummyMenuModel(1);
        newMenu.setRestoran(generateDummyRestoranModel(1));
        menuService.addMenu(newMenu);
        menuService.getMenuList();
        verify(menuDb, times(1)).findAll();

    }

    @Test
    public void whenDeleteMenu(){
        MenuModel newMenu = generateDummyMenuModel(1);
        newMenu.setRestoran(generateDummyRestoranModel(1));
        menuService.addMenu(newMenu);
        menuService.deleteMenu(newMenu);
        verify(menuDb, times(1)).delete(newMenu);
    }

    @Test
    public void whenGetListMenuByHargaAsc(){
        MenuModel newMenu = generateDummyMenuModel(1);
        newMenu.setRestoran(generateDummyRestoranModel(1));
        menuService.addMenu(newMenu);
        menuService.getListMenuOrderByHargaAsc(1L);
        verify(menuDb, times(1)).findByRestoranIdRestoranOrderByHargaAsc(1L);

    }

    @Test
    public void whenChangeMenu(){
        MenuModel newMenu = generateDummyMenuModel(1);
        when(menuDb.findById(1L)).thenReturn(Optional.of(newMenu));
        when(menuService.changeMenu(newMenu)).thenReturn(newMenu);
        MenuModel updatedMenu = menuService.changeMenu(newMenu);
        assertEquals("dummyMenu 1", updatedMenu.getNama());
        assertEquals(BigInteger.valueOf(1000), updatedMenu.getHarga());
        assertEquals("dummyMenu Enak", updatedMenu.getDeskripsi());
        assertEquals(Integer.valueOf(15), updatedMenu.getDurasiMasak());
        assertEquals(Long.valueOf(1L), updatedMenu.getId());
    }

    @Test
    public void whenChangeMenuNull() {
        MenuModel updatedData = new MenuModel();
        updatedData.setId((long)1);
        when(menuDb.findById((long)1)).thenReturn(Optional.of(updatedData));
        when(menuDb.save(updatedData)).thenThrow(NullPointerException.class);

        MenuModel menuNull = menuService.changeMenu(updatedData);
        assertNull(menuNull);
    }


}
