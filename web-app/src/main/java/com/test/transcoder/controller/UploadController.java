package com.test.transcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.test.transcoder.beans.ClientBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ls on 10/22/17.
 */
@Controller
@RequestMapping("/")
public class UploadController {

    @RequestMapping(value="/upload", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(Model model) {
        ClientBean clientBean = new ClientBean();
        model.addAttribute("clientBean", clientBean);
        return "upload";
    }

}
