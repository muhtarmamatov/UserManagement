package com.muhtar.controller;

import com.muhtar.domain.User;
import com.muhtar.service.UserService;
import com.muhtar.utils.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @GetMapping(value = {"/","UserList"})
    public ModelAndView userList(@RequestParam("pageSize")Optional<Integer> pageSize,
                           @RequestParam("page") Optional<Integer> page){
        ModelAndView modelAndView = new ModelAndView("UserList");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.45
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get()- 1;
        Page<User> users = userService.listUsers(PageRequest.of(evalPage,evalPageSize));
        Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("users", users);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }



    @GetMapping("/page/{pageNo}")
    public ModelAndView page(@PathVariable(value = "pageNo") int pageNo, Model model){
        ModelAndView modelAndView = new ModelAndView("list");
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Page<User> page = userService.listUsers(pageable);
        List<User> listUsers = page.getContent();
        modelAndView.addObject("currentPage",pageNo);
        modelAndView.addObject("totalPages",page.getTotalPages());
        modelAndView.addObject("totalItems",page.getTotalElements());
        modelAndView.addObject("listUsers",listUsers);
        return modelAndView;
    }

}
