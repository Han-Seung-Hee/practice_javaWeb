package kr.co.practices.pro1_eatgo.Interfaces;

import kr.co.practices.pro1_eatgo.applications.resSvc;
import kr.co.practices.pro1_eatgo.domain.*;
import org.junit.jupiter.api.Test;
// RunWith, Before를 Junit5 에서 사용하기 - RunWith 대신 ExtensionWith를 사용해야함.
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ResController.class)
class ResControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private resSvc resSvc;
//    @SpyBean(ResRepoImpl.class)
//    private ResRepo resRepo;
//
//    @SpyBean(MenuItemRepoImpl.class)
//    private MenuItemRepo menuitemRepo;



    @Test
    public void list() throws Exception {
        List<Restaurant> res = new ArrayList<>();
        res.add(new Restaurant(1004L,"Bob's", "Seoul"));

        given(resSvc.getRes_mult()).willReturn(res);

        mvc.perform(MockMvcRequestBuilders.get("/res"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                "\"id\":1004")
                        ))
                .andExpect(content().string(
                containsString(
                                "\"name\":\"Bob's")
                ));
    }

    @Test
    public void detail() throws Exception{
        Restaurant res = new Restaurant(1004L,"Bob's","Seoul");
        res.addMenuItem(new MenuItem("Kimchi"));

        Restaurant res2 = new Restaurant(2020L,"CybFood","Seoul");
        res2.addMenuItem(new MenuItem("Kimchi"));

        given(resSvc.getRes(1004L)).willReturn(res);
        mvc.perform(MockMvcRequestBuilders.get("/res/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                "\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString(
                                "\"name\":\"Bob's")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));

        given(resSvc.getRes(2020L)).willReturn(res2);
        mvc.perform(MockMvcRequestBuilders.get("/res/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                "\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString(
                                "\"name\":\"CybFood")
                ));
    }

    @Test
    public void create() throws Exception{
        Restaurant res = new Restaurant(1234L,"B-Dragon","Seoul");
        mvc.perform(MockMvcRequestBuilders.post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"B-Dragon\",\"addr\":\"Seoul\"}"))
                .andExpect(status().isCreated())
//               .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));
        verify(resSvc).addRes(any());
    }

    @Test
    public void update() throws Exception {
        //1004L,"name":Bob's","addr":"Busan"

        mvc.perform(MockMvcRequestBuilders.patch("/res/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Bob's\",\"addr\":\"Busan\"}"))
                .andExpect(status().isOk());
        verify(resSvc).updateRes(1004L, "Bob's","Busan");
    }
}