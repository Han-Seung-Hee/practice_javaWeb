package kr.co.practices.pro1_eatgo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface MenuItemRepo  extends CrudRepository<MenuItem, Long>{
    List<MenuItem> findAllByresId(Long resId);
}
