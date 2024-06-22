package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.EnteredStatusDto;
import com.Vpetsoft.VpetsoftApp.dto.EpsDto;
import com.Vpetsoft.VpetsoftApp.entity.EnteredStatus;
import com.Vpetsoft.VpetsoftApp.entity.Eps;
import com.Vpetsoft.VpetsoftApp.service.EnteredStatusService;
import com.Vpetsoft.VpetsoftApp.service.EpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EpsBusiness {
    @Autowired
    private EpsService epsService;
    private List<Eps> EpsList;

    public List<EpsDto> findAll() {
        List<EpsDto> epsDtoList =new ArrayList<>();
        try {
            this.EpsList = this.epsService.findAll();
            this.EpsList.forEach( eps-> {
                EpsDto epsDto = new EpsDto();
                epsDto.setId(eps.getId());
                epsDto.setName(eps.getName());
                epsDtoList.add(epsDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar la eps", e);
        }
        return epsDtoList;
    }
    public EpsDto findEpsById(int id) {
        try {
            Eps eps = this.epsService.findById(id);

            if (eps != null) {
                EpsDto epsDto = new EpsDto();
                epsDto.setId(eps.getId());
                epsDto.setName(eps.getName());

                return epsDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la eps por ID", e);
        }
    }

    public String createEps(EpsDto epsDto) {
        try {
            Eps eps = new Eps();
            eps.setName(epsDto.getName());

            this.epsService.create(eps);

            return "Eps creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la eps", e);
        }
    }

    public String updateEps(EpsDto epsDto) {
        try {
            Eps eps = epsService.findById(epsDto.getId());

            if (eps != null) {
                eps.setName(epsDto.getName());

                epsService.update(eps);

                return "Eps actualizado exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar la eps. La eps no existe con ID: " + epsDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la eps", e);
        }
    }
}
