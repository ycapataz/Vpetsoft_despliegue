package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.State;
import com.Vpetsoft.VpetsoftApp.repository.StateRepository;
import com.Vpetsoft.VpetsoftApp.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StateImp implements StateService {
    @Autowired
    private StateRepository stateRepository;
    @Override
    public List<State> findAll() throws Exception {return this.stateRepository.findAll();}

    @Override
    public State findById(int id) {
        return this.stateRepository.findById(id);
    }

    @Override
    public State create(State state) {
        this.stateRepository.save(state);
        return state;
    }

    @Override
    public void update(State state) {
        this.stateRepository.save(state);
    }

    @Override
    public void delete(State state) {
        this.stateRepository.delete(state);
    }

}
