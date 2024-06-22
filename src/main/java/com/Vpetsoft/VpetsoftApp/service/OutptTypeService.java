package com.Vpetsoft.VpetsoftApp.service;
import com.Vpetsoft.VpetsoftApp.entity.OutptType;

import java.util.List;

public interface OutptTypeService {
    public List<OutptType> findAll() throws Exception;
    public OutptType findById(int id);
    public void create (OutptType outptType);
    public void update (OutptType outptType);
    public void delete (OutptType outptType);

}
