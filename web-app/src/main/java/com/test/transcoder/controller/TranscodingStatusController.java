package com.test.transcoder.controller;

import com.test.transcoder.TranscodingStatus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.test.transcoder.beans.ClientBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ls on 10/22/17.
 *
 * Controller saves trans-coding status info
 */
@Controller
@RequestMapping("/")
public class TranscodingStatusController {

    @RequestMapping(value="/transcoding.status", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String transcodingStatus(Model model) {

        String jsonTranscodingStatus = TranscodingStatus.jsonTranscodingStatus;
        TranscodingStatus.jsonTranscodingStatus = null;

        if(jsonTranscodingStatus == null){
            return "{}";
        }else {
            return jsonTranscodingStatus;
        }
    }

}
