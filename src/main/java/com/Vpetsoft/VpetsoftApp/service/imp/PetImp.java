package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Pet;
import com.Vpetsoft.VpetsoftApp.repository.PetRepository;
import com.Vpetsoft.VpetsoftApp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetImp implements PetService {
    @Autowired
    private PetRepository petRepository;
    @Override
    public List<Pet> findAll(){
        return this.petRepository.findAll();
    }

    @Override
    public Pet findById(int id) {
        return this.petRepository.findById(id);
    }

    @Override
    public void create(Pet pet) {
        this.petRepository.save(pet);
    }

    @Override
    public void update(Pet pet) {
        this.petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        this.petRepository.delete(pet);
    }


}
