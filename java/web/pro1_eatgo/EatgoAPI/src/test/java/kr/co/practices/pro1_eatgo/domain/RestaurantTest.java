package kr.co.practices.pro1_eatgo.domain;

import org.junit.jupiter.api.Test;

// JUnit5 에서 asserthat, is 사용하려면..
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    @Test
    public void creation(){
        Restaurant res = new Restaurant(1004L,"BobZip", "Seoul");
        assertThat(res.getName(), is("BobZip"));
        assertThat(res.getAddr(), is("Seoul"));
        assertThat(res.getId(), is(1004L));
    }

    @Test
    public void inform(){
        Restaurant res = new Restaurant(1004L,"BobZip", "Seoul");

        assertThat(res.getInfo(), is("BobZip in Seoul"));
    }
}