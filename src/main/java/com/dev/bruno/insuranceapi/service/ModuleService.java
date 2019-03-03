package com.dev.bruno.insuranceapi.service;

import com.dev.bruno.insuranceapi.domain.Module;
import com.dev.bruno.insuranceapi.repository.ModuleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames={"modules"})
public class ModuleService {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @CacheEvict(allEntries = true)
    public Module insert(Module moduleToInsert) {
        Module newModule = new Module();
        updateEntity(newModule, moduleToInsert);
        return moduleRepository.save(newModule);
    }

    @CacheEvict(allEntries = true)
    public Module update(Long id, Module moduleToUpdate) {
        Module module = findOne(id);
        updateEntity(module, moduleToUpdate);
        return moduleRepository.save(module);
    }

    @CacheEvict(allEntries = true)
    public void delete(Long id) {
        Module module = findOne(id);
        moduleRepository.delete(module);
    }

    @Cacheable
    public Module findOne(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }

    @Cacheable
    public Iterable<Module> findAll() {
        return moduleRepository.findAll();
    }

    private void updateEntity(Module currentModule, Module module) {
        BeanUtils.copyProperties(module, currentModule, "prices", "id");
    }
}
