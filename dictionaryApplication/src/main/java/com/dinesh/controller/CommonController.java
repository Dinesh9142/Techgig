package com.dinesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dinesh.service.CommonService;
import com.dinesh.utils.Response;

@Controller
@RequestMapping("common")
public class CommonController {

	@Autowired
	CommonService lCommonService;
	
	@ResponseBody
	@RequestMapping(value = "/addDictionaryWords", method = RequestMethod.POST)
    public Response addDictoniary(@RequestParam String fileName){
        return lCommonService.addDictoniary(fileName);
    }
	
	@ResponseBody
	@RequestMapping(value = "/getDictionaryCount", method = RequestMethod.GET)
    public Response getDictionaryCount(){
        return lCommonService.getDictionaryCount();
    }
	
	@ResponseBody
	@RequestMapping(value = "/isWordExists", method = RequestMethod.GET)
    public Response isWordExists(@RequestParam String searchWord){
        return lCommonService.isWordExists(searchWord);
    }
}
