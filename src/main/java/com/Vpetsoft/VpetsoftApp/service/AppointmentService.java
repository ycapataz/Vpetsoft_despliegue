package com.Vpetsoft.VpetsoftApp.service;

import com.Vpetsoft.VpetsoftApp.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    public List<Appointment> findAll();
    public Appointment findById(int id);
    public void create (Appointment appointment);
    public void update (Appointment appointment);
    public void delete (Appointment appointment);
}
