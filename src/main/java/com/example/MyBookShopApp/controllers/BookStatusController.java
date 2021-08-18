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
    private String keptTemp = null;

    @PostMapping("/changeBookStatus")
    public ResponseEntity<ResultDTO> changeBookStatus(ChangeBookStatus changeBookStatus,
                                                      @CookieValue(name = "RATE", required = false) String notRegisteredUsers,
                                                      HttpServletResponse response, Model model) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(true);
        //keptTemp = kept;

        switch (changeBookStatus.getStatus()) {
            case "CART":
                //addCartBook(changeBookStatus, cart, keptTemp, response);
                break;
            case "KEPT":
                //addKeptBook(changeBookStatus, kept, response);
                break;
            case "UNLINK_CART":
                //removeCartBook(changeBookStatus, cart, response);
                break;
            case "UNLINK_KEPT":
                removeKeptBook(changeBookStatus.getBooksIds()[0], keptTemp, response);
                break;

            default:
                ;
        }
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    public void addCartBook(ChangeBookStatus changeBookStatus, String cart, String kept, HttpServletResponse response) {
        Cookie cookie = null;
        StringJoiner stringJoiner = new StringJoiner("/");

        for (String id : changeBookStatus.getBooksIds()) {
            stringJoiner.add(id);
            if (kept != null) {
                removeKeptBook(id, keptTemp, response);
            }
        }

        if (cart == null || cart.equals("")) {
            cookie = new Cookie("CART", stringJoiner.toString());
        } else if (!cart.contains("CART")) {
            cookie = new Cookie("CART", stringJoiner.add(cart).toString());
        }

        assert cookie != null;
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void addKeptBook(ChangeBookStatus changeBookStatus, String kept, HttpServletResponse response) {
        Cookie cookie = null;
        if (kept == null || kept.equals("")) {
            cookie = new Cookie("KEPT", changeBookStatus.getBooksIds()[0]);

        } else if (!kept.contains("KEPT")) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(kept).add(changeBookStatus.getBooksIds()[0]);
            cookie = new Cookie("KEPT", stringJoiner.toString());
        }
        assert cookie != null;
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void removeCartBook(ChangeBookStatus changeBookStatus, String cart, HttpServletResponse response) {
        String bookId = changeBookStatus.getBooksIds()[0];
        String newCart = Arrays.stream(cart.split("/")).distinct().filter(i -> !i.equals(bookId)).collect(Collectors.joining("/"));
        Cookie cookie = new Cookie("CART", newCart);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void removeKeptBook(String bookId, String kept, HttpServletResponse response) {
        String newCart = Arrays.stream(kept.split("/")).distinct().filter(i -> !i.equals(bookId)).collect(Collectors.joining("/"));
        Cookie cookie = new Cookie("KEPT", newCart);
        keptTemp = newCart;
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
