package com.martin.joukl.cv02martinjoukl.Controller;

import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @Controller slouží pro označení kontroleru pro MVC - a jeho účelem je vrátit view (tedy generovanou HTML stránku)
// @RestController je jeho podmožina a slouží pouze pro REST odpovědi - tedy vrací data ve formě API odpovědi - JSON a nebo XML
// RestController anotace přidává anotaci @Controller a @ResponseBody
@RestController
public class HelloController {
    @GetMapping("")
    public String helloWorld() { return "Hello world from Spring Boot application."; }

    @GetMapping("sayBack")
    public String sayBack(@RequestParam String parameter) {
        return "Input parameter was: "+parameter;
    }

    @RequestMapping( method = {RequestMethod.GET}, path = "sayBackRequest")
    public String requestSayBack(@RequestParam String parameter) {
        return "From request - input was: "+parameter;
    }

    @RequestMapping( method = {RequestMethod.GET}, path = {"sayBackPath", "sayBackPath/{parameter}"})
    public String pathSayBack(@PathVariable String parameter) {
        return "From request - input was: "+parameter;
    }
}
