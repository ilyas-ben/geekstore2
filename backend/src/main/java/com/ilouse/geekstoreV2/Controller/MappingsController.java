package com.ilouse.geekstoreV2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@RestController
@RequestMapping("/mappings")
public class MappingsController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public void getAllMappings() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        System.out.println(requestMappingHandlerMapping.getHandlerMethods());
    }
}

