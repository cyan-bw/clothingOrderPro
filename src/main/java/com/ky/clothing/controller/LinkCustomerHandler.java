package com.ky.clothing.controller;

import com.ky.clothing.entity.Goods;
import com.ky.clothing.enums.SysParamEnum;
import com.ky.clothing.service.GoodsAssessService;
import com.ky.clothing.service.GoodsService;
import com.ky.clothing.util.IntegerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author Daye
 */
@Controller
@RequestMapping("/customerLink")
public class LinkCustomerHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkCustomerHandler.class);

    private GoodsService goodsService;

    private GoodsAssessService goodsAssessService;

    @RequestMapping(value = "/clothes/{pageNow}/{pageSize}", method = RequestMethod.GET)
    public String toClothes(@PathVariable("pageNow") Integer pageNow, @PathVariable("pageSize") Integer pageSize, HttpServletRequest request) {
    	if(IntegerUtil.isValid(pageNow)&&IntegerUtil.isValid(pageSize))
        {
            int count = goodsService.selectGoodsCount();
            int pages = (count % pageSize.intValue() == 0 ? count / pageSize.intValue() : count / pageSize.intValue() + 1);
            if(pageNow.intValue()>pages)
            {
                pageNow=new Integer(1);
            }
            List<Goods> goodlist1 = goodsService.selectGoodsLimit(pageNow, pageSize);
            HttpSession session = request.getSession();
            session.setAttribute(SysParamEnum.SESSION_GOODS_LIST_NAME.toString(),goodlist1);
            session.setAttribute(SysParamEnum.SESSION_GOODS_COUNT.toString(),count);
        }
        return "/pages/clothes";

    }

    /**
     * 跳转到注册页面
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String toSignUp() {
        return "pages/sign_up";
    }

    /**
     * 跳转到忘记密码修改密码页面
     */
    @RequestMapping(value = "/forgot/pwd/modify", method = RequestMethod.GET)
    public String toForgotPasswordModify() {
        return "/pages/forgot_password_modify";
    }

    /**
     * 跳转到忘记密码页面
     */
    @RequestMapping(value = "/forgot/pwd", method = RequestMethod.GET)
    public String toForgotPassword() {
        return "/pages/forgot_password";
    }

    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String toHome(HttpServletRequest request) {
        //查询前三的goods
        List<Goods> goodsList = goodsService.selectGoodsTopThree();
        //存入session
        request.getSession().setAttribute(SysParamEnum.SESSION_GOODS_LIST_NAME.toString(), goodsList);
        //查询前四的评论
        List<Map<String, Object>> assessList = goodsAssessService.selectBaseInfoTopFour();
        //存入session中
        request.getSession().setAttribute(SysParamEnum.SESSION_ASSESSES_NAME.toString(), assessList);
        return "/pages/home";
    }

    /**
     * 跳转到联系我们页面
     */
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String toContact() {
        return "/pages/contact_us";
    }

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Autowired
    public void setGoodsAssessService(GoodsAssessService goodsAssessService) {
        this.goodsAssessService = goodsAssessService;
    }
}
