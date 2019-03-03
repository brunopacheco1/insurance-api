package com.dev.bruno.insuranceapi.api;

import com.dev.bruno.insuranceapi.domain.Module;
import com.dev.bruno.insuranceapi.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ModuleRest {

    private final ModuleService moduleService;

    @Autowired
    public ModuleRest(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @RequestMapping(value = "/modules/{id}", method = RequestMethod.GET)
    public Module get(@PathVariable(value = "id") Long id) {
        return moduleService.findOne(id);
    }

    @RequestMapping(value = "/modules/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Long id, @Validated @RequestBody Module module) {
        moduleService.update(id, module);
    }

    @RequestMapping(value = "/modules", method = RequestMethod.POST)
    public Module insert(@Validated @RequestBody Module module) {
        return moduleService.insert(module);
    }

    @RequestMapping(value = "/modules/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        moduleService.delete(id);
    }

    @RequestMapping(value = "/modules", method = RequestMethod.GET)
    public Iterable<Module> list() {
        return moduleService.findAll();
    }

}
