package kr.co.practices.pro1_eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface ResRepo extends CrudRepository<Restaurant,Long> {

    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant res);
}