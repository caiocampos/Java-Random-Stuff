/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.listarbd;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Caio
 */
@Controller
public class GreetingController {
    
    @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String greetingJson(@RequestParam(value="name", required=false, defaultValue="World") String name) throws JSONException {
        JSONObject greeting = new JSONObject();
        greeting.put("name", name);
        return greeting.toString();
    }
    
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String welcomeJson(@RequestParam(value="name", required=false, defaultValue="World") String name) throws JSONException {
        JSONObject greeting = new JSONObject();
        greeting.put("time", new Date());
        greeting.put("message", "Olá " + name + "!!!");
        return greeting.toString();
    }
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model, @RequestParam(value="name", required=false, defaultValue="Mundo") String name) {
        model.addAttribute("time", new Date());
        model.addAttribute("message", "Olá " + name + "!!!");
        return "welcome";
    }
    
    @RequestMapping("/foo")
    public String foo(Model model) {
        throw new RuntimeException("Foo");
    }
}