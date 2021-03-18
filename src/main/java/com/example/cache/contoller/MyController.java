package com.example.cache.contoller;

import com.example.cache.Cache;
import com.example.cache.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    Cache objCache = new Cache();

    @GetMapping("/get-user-data")
    @ResponseBody
    public String renderData(@RequestParam int id) throws InterruptedException {

        if(objCache.has(id)==true) {
            return objCache.get(id);
        }
        else if(objCache.has(id) == false){
            User user = new User(id);
            String val = user.getDataSlow();
            objCache.set(id, val);

            return val;
        }

        return "/get-user-data";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteUserData(@RequestParam int id){
        objCache.delete(id);
        return "Your data has been deleted";
    }



}
