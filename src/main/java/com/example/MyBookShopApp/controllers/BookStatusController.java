package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.request.ChangeBookStatus;
import com.example.MyBookShopApp.dto.response.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class BookStatusController {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @PostMapping("/changeBookStatus")
    public ResponseEntity<ResultDTO> changeBookStatus(ChangeBookStatus changeBookStatus,
                                      @CookieValue(name = "CART", required = false) String cart,
                                      @CookieValue(name = "KEPT", required = false) String kept,
                                      @CookieValue(name = "ARCHIVED", required = false) String archived,
                                      HttpServletResponse response, Model model) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(true);

        switch (changeBookStatus.getStatus()) {
            case "CART":
                addCartBook(changeBookStatus, cart, response);
                break;
            case "KEPT":
                addKeptBook(changeBookStatus, kept, response);
                break;
            case "UNLINK_CART":
                removeCartBook(changeBookStatus.getBooksIds(), cart, response);
                break;

            default:
                ;
        }
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    public void addCartBook(ChangeBookStatus changeBookStatus, String cart, HttpServletResponse response) {
        Cookie cookie = null;
        if (cart == null || cart.equals("")) {
            cookie = new Cookie("CART", changeBookStatus.getBooksIds());

        } else if (!cart.contains("CART")) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(cart).add(changeBookStatus.getBooksIds());
            cookie = new Cookie("CART", stringJoiner.toString());
        }
        assert cookie != null;
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void addKeptBook(ChangeBookStatus changeBookStatus, String kept, HttpServletResponse response) {
        Cookie cookie = null;
        if (kept == null || kept.equals("")) {
            cookie = new Cookie("KEPT", changeBookStatus.getBooksIds());

        } else if (!kept.contains("KEPT")) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(kept).add(changeBookStatus.getBooksIds());
            cookie = new Cookie("KEPT", stringJoiner.toString());
        }
        assert cookie != null;
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void removeCartBook(String bookId, String cart, HttpServletResponse response) {
        String newCart = Arrays.stream(cart.split("/")).distinct().filter(i -> !i.equals(bookId)).collect(Collectors.joining("/"));
        Cookie cookie = new Cookie("CART", newCart);
        assert cookie != null;
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
