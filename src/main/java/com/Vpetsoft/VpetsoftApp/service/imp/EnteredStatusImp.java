package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.EnteredStatus;
import com.Vpetsoft.VpetsoftApp.repository.EnteredStatusRepository;
import com.Vpetsoft.VpetsoftApp.service.EnteredStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnteredStatusImp implements EnteredStatusService {
    @Autowired
    private EnteredStatusRepository enteredStatusRepository;
    @Override
    public List<EnteredStatus> findAll() throws Exception {
        return this.enteredStatusRepository.findAll();
    }

    @Override
    public EnteredStatus findById(int id) {
        return this.enteredStatusRepository.findById(id);
    }

    @Override
    public void create(EnteredStatus enteredStatus) {
        this.enteredStatusRepository.save(enteredStatus);
    }

    @Override
    public void update(EnteredStatus enteredStatus) {
        this.enteredStatusRepository.save(enteredStatus);
    }

    @Override
    public void delete(EnteredStatus enteredStatus) {
        this.enteredStatusRepository.delete(enteredStatus);
    }
}
