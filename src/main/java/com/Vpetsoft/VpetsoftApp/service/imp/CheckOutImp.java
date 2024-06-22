package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.CheckOut;
import com.Vpetsoft.VpetsoftApp.repository.CheckOutRepository;
import com.Vpetsoft.VpetsoftApp.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckOutImp implements CheckOutService {

    @Autowired
    private CheckOutRepository checkOutRepository;
    @Override
    public List<CheckOut> findAll() throws Exception {
        return this.checkOutRepository.findAll();
    }

    @Override
    public CheckOut findById(int id) {
        return this.checkOutRepository.findById(id);
    }

    @Override
    public void create(CheckOut checkOut) {
        this.checkOutRepository.save(checkOut);
    }

    @Override
    public void update(CheckOut checkOut) {
        this.checkOutRepository.save(checkOut);
    }

    @Override
    public void delete(CheckOut checkOut) {
        this.checkOutRepository.delete(checkOut);
    }
}
