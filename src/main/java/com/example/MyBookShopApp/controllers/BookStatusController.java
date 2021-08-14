package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.request.ChangeBookStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.StringJoiner;
import java.util.logging.Logger;

@Controller
public class BookStatusController {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @PostMapping("/changeBookStatus")
    public String changeBookStatus(ChangeBookStatus changeBookStatus,
                                   @CookieValue(name = "CART", required = false) String cart,
                                   @CookieValue(name = "KEPT", required = false) String kept,
                                   @CookieValue(name = "ARCHIVED", required = false) String archived,
                                   HttpServletResponse response, Model model) {
        switch (changeBookStatus.getStatus()) {
            case "CART":
                addCartBook(changeBookStatus, cart, response);
                model.addAttribute("isCartEmpty", false);
                break;

            default: ;
        }


        logger.info(changeBookStatus.toString());
        return null;
    }

    public void addCartBook(ChangeBookStatus changeBookStatus, String cart, HttpServletResponse response) {
        Cookie cookie = null;
        if (cart == null || cart.equals("")) {
            cookie = new Cookie("CART", changeBookStatus.getBooksIds());
            response.addCookie(cookie);
        } else if (!cart.contains("CART")) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(cart).add(changeBookStatus.getBooksIds());
            cookie = new Cookie("CART", stringJoiner.toString());
            response.addCookie(cookie);
        }
        assert cookie != null;
        cookie.setPath("/");
    }

}
