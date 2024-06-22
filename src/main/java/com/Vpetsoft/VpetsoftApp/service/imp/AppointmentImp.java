package com.Vpetsoft.VpetsoftApp.service.imp;

import com.Vpetsoft.VpetsoftApp.entity.Appointment;
import com.Vpetsoft.VpetsoftApp.repository.AppointmentRepository;
import com.Vpetsoft.VpetsoftApp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentImp implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public List<Appointment> findAll(){
        return this.appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(int id) {
        return this.appointmentRepository.findById(id);
    }

    @Override
    public void create(Appointment appointment) {
        this.appointmentRepository.save(appointment);
    }

    @Override
    public void update(Appointment appointment) {
        this.appointmentRepository.save(appointment);
    }

    @Override
    public void delete(Appointment appointment) {
        this.appointmentRepository.delete(appointment);
    }
}
