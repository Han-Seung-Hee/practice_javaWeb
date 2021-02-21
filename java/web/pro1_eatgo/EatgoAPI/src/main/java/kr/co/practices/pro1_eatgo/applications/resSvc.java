package kr.co.practices.pro1_eatgo.applications;

import kr.co.practices.pro1_eatgo.domain.*;
import org.h2.value.ValueResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class resSvc{
    @Autowired
    ResRepo resRepo;

    @Autowired
    MenuItemRepo menuItemRepo;

    public List<Restaurant> getRes_mult() {
        return resRepo.findAll();
    }

    public resSvc(ResRepo resRepo, MenuItemRepo menuItemRepo){
        this.resRepo =resRepo;
        this.menuItemRepo = menuItemRepo;
    }

    public Restaurant getRes(Long id){
        Restaurant res = resRepo.findById(id).orElse(null);
        List<MenuItem> menuItems = menuItemRepo.findAllByresId(id);
        res.setMenuItem(menuItems);
            return res;
    }


    public Restaurant addRes(Restaurant res) {
        return resRepo.save(res);
    }

    @Transactional
    public Restaurant updateRes(long id, String name, String addr) {
        // TODO : UPDATE RESTAURANT

        Restaurant res = resRepo.findById(id).orElse(null);

        res.updInfo(name,addr);

     //   Restaurant res = new Restaurant(id,name,addr);
        return res;
    }
}
