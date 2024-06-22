package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.CheckOut;

import java.util.List;

public interface CheckOutService {
    public List<CheckOut> findAll() throws Exception;
    public CheckOut findById(int id);
    public void create (CheckOut checkOut);
    public void update (CheckOut checkOut);
    public void delete (CheckOut checkOut);
}
