package apap.tutorial.gopud.controller;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.RestoranDB;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestoranController.class)
public class RestoranControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Qualifier("restoranServiceImpl")
    private RestoranService restoranService;
    @MockBean
    @Qualifier("menuServiceImpl")
    private MenuService menuService;
    @Mock
    RestoranDB restoranDb;

    @Test
    public void whenHomePageRouteAccessItShouldReturnStatusCode200() throws Exception {
        mockMvc.perform(get("/")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private RestoranModel generateDummyRestoranModel(int count) {
        RestoranModel dummyRestoranModel = new RestoranModel();
        dummyRestoranModel.setNama("dummy " + count);
        dummyRestoranModel.setAlamat("alamat " + count);
        dummyRestoranModel.setIdRestoran((long)count);
        dummyRestoranModel.setNomorTelepon(14000);
        dummyRestoranModel.setListMenu(new ArrayList<>());
        return dummyRestoranModel;
    }

    @Test
    public void whenViewAllRestoranAccessItShouldShowAllRestoranData() throws Exception {
        List<RestoranModel> allRestoranInTheDatabase = new ArrayList<>();
        for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
            allRestoranInTheDatabase.add(generateDummyRestoranModel(loopTimes));
        }
        when(restoranService.getRestoranList()).thenReturn(allRestoranInTheDatabase);
        mockMvc.perform(get("/restoran/viewall"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(Matchers.containsString("Daftar Seluruh Restoran")))
                .andExpect(content().string(Matchers.containsString("ID Restoran")))
                .andExpect(model().attribute("restoList", hasSize(3)))
                .andExpect(model().attribute("restoList", hasItem(
                        allOf(
                                hasProperty("idRestoran", is(1L)),
                                hasProperty("nama", is("dummy 1")),
                                hasProperty("alamat", is("alamat 1"))
                        )
                )))
                .andExpect(model().attribute("restoList", hasItem(
                        allOf(
                                hasProperty("idRestoran", is(2L)),
                                hasProperty("nama", is("dummy 2")),
                                hasProperty("alamat", is("alamat 2"))
                        )
                )))
                .andExpect(model().attribute("restoList", hasItem(
                        allOf(
                                hasProperty("idRestoran", is(3L)),
                                hasProperty("nama", is("dummy 3")),
                                hasProperty("alamat", is("alamat 3"))
                        )
                )));
        verify(restoranService, times(1)).getRestoranList();
    }

    @Test
    public void whenRestoranAddPostFormItShouldSuccessfullyReturnToRightView() throws
            Exception {
        String nama = "Dummy Restoran";
        String alamat = "Dummy Alamat";
        mockMvc.perform(post("/restoran/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nama", nama)
                .param("alamat", alamat)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-restoran"))
                .andExpect(model().attribute("namaResto", is(nama)));
    }

    @Test
    public void whenViewRestoranByIdRestoran() throws Exception{
        RestoranModel restoranDummy = generateDummyRestoranModel(1);
        Optional<RestoranModel> restoranViewed = Optional.of(restoranDummy);
        when(restoranService.getRestoranByIdRestoran(restoranViewed.get().getIdRestoran())).thenReturn(restoranViewed);
        mockMvc.perform(get("/restoran/view?idRestoran=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("resto", hasProperty("nama", is("dummy 1"))));
    }


    



}
