package kr.co.practices.pro1_eatgo.Interfaces;

import kr.co.practices.pro1_eatgo.applications.resSvc;
import kr.co.practices.pro1_eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin
@RestController

public class ResController {

//    @Autowired
//    private ResRepo resRepo ;
//
//    @Autowired
//    private MenuItemRepoImpl menuitemRepo;

    @Autowired
    private resSvc resSvc;

    @GetMapping("/res")
    public List<Restaurant> list(){
        return resSvc.getRes_mult();
    }

    @GetMapping("/res/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
   //     Restaurant res_2 = resRepo.findById(id);
          Restaurant res_2 = resSvc.getRes(id);

//        List<MenuItem> menuItems = menuitemRepo.findAllByresId(id);
//        res_2.setMenuItem(menuItems);
        return res_2;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant rsrc) throws URISyntaxException {
        final String name = rsrc.getName();
        String addr = rsrc.getAddr();
        Restaurant res = new Restaurant( name, addr);
        resSvc.addRes(res);
        URI location = new URI("/restaurants/"+res.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("res/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestBody Restaurant resc){
        String name = resc.getName();
        String addr = resc.getAddr();

        resSvc.updateRes(id, name, addr);
        return "{}";
    }

}
