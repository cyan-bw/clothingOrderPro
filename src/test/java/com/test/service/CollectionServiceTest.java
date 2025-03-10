package com.test.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ky.clothing.entity.Collectionss;
import com.ky.clothing.entity.User;
import com.ky.clothing.enums.SysParamEnum;
import com.ky.clothing.service.CartService;
import com.ky.clothing.service.CollectionService;
import com.ky.clothing.service.GoodsService;

public class CollectionServiceTest {

	private CollectionService collectionservice;
	//private CartService cartservice;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext-web.xml");
        collectionservice = applicationContext.getBean(CollectionService.class);
        //cartservice = applicationContext.getBean(CartService.class);
    }
	
	@Test
	public void test() {
		Collectionss clt=new Collectionss();
		clt.setUserId(5);
		clt.setCltGoodsSize("x");
		clt.setCltId(5);
		clt.setCltValid(true);
		clt.setGoodsId(5);
		collectionservice.addCollectionss(clt);
        //System.out.println(cltList);
	}

}
