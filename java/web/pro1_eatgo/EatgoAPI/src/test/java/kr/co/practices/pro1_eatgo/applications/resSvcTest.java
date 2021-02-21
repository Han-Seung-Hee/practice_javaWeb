package kr.co.practices.pro1_eatgo.applications;

import kr.co.practices.pro1_eatgo.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class resSvcTest {

    private resSvc resSvc;
    @Mock
    private ResRepo resRepo;

    @Mock
    private MenuItemRepo menuItemRepo;

    @BeforeEach
    public void setUp(){
        //MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
        MockResRepo();
//        resRepo = new ResRepoImpl();
//        menuItemRepo = new MenuItemRepoImpl();
        MockItems();
        resSvc = new resSvc(resRepo,menuItemRepo);
    }

    private void MockResRepo() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L,"Bob's","Seoul"));
        BDDMockito.given(resRepo.findAll()).willReturn(restaurants);

        Restaurant res = new Restaurant(1004L,"Bob's","Seoul");
        BDDMockito.given(resRepo.findById(1004L)).willReturn(java.util.Optional.of(res));


    }

    private void MockItems() {
        List<MenuItem> menues = new ArrayList<>();
        menues.add(new MenuItem("Kimchi"));

        BDDMockito.given(menuItemRepo.findAllByresId(1004L)).willReturn(menues);
    }

    @Test
    public void getRes(){
        Restaurant res = resSvc.getRes(1004L);
        assertThat(res.getId(), is(1004L));
        MenuItem menuItem = res.getMenuItem().get(0);
        assertThat(menuItem.getName(), is("Kimchi"));

    }

    @Test
    public void res_mult(){
        List<Restaurant> res_mult = resSvc.getRes_mult();
        Restaurant res_one = res_mult.get(0);
        assertThat(res_one.getId(), is(1004L));

    }

    @Test
    public void addRes(){
        Restaurant res = new Restaurant("B-Dragon","Seoul");
        Restaurant res_saved = new Restaurant(1234L,"B-Dragon","Seoul");

        BDDMockito.given(resRepo.save(any())).willReturn(res_saved);
        Restaurant res_created = resSvc.addRes(res);
        assertThat(res_created.getId(), is(1234L));
    }

    @Test
    public void updateRes(){
        Restaurant res = new Restaurant(1004L,"Bob's","Seoul");

        BDDMockito.given(resRepo.findById(1004L)).willReturn(java.util.Optional.of(res));

        Restaurant updData = resSvc.updateRes(1004L,"Bob's","Busan");
        assertThat(res.getAddr(), is("Busan"));

    }
}