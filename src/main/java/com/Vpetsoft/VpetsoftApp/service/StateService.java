package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.State;
import java.util.List;

public interface StateService {
    public List<State> findAll() throws Exception;
    public State findById(int id);
    public State create (State state);
    public void update (State state);
    public void delete (State state);
}
