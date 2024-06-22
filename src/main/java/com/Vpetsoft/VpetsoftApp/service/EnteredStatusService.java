package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.EnteredStatus;

import java.util.List;

public interface EnteredStatusService {
    public List<EnteredStatus> findAll() throws Exception;
    public EnteredStatus findById(int id);
    public void create (EnteredStatus enteredStatus);
    public void update (EnteredStatus enteredStatus);
    public void delete (EnteredStatus enteredStatus);
}
