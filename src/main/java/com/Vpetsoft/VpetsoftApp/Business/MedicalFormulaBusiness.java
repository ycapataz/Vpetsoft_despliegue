package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.ClinicalRecordService;
import com.Vpetsoft.VpetsoftApp.service.MedicalFormulaService;
import com.Vpetsoft.VpetsoftApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalFormulaBusiness {
    @Autowired
    private MedicalFormulaService medicalFormulaService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ClinicalRecordService clinicalRecordService;
    private List<MedicalFormula> MedicalFormulaList;

    public List<MedicalFormulaDto> findAll() {
        List<MedicalFormulaDto> medicalFormulaDtoList = new ArrayList<>();
        try {
            this.MedicalFormulaList = this.medicalFormulaService.findAll();
            this.MedicalFormulaList.forEach(medicalFormula -> {
                MedicalFormulaDto medicalFormulaDto = new MedicalFormulaDto();
                medicalFormulaDto.setId(medicalFormula.getId());
                medicalFormulaDto.setDose(medicalFormula.getDose());
                medicalFormulaDto.setDuration(medicalFormula.getDuration());
                medicalFormulaDto.setAmount(medicalFormula.getAmount());
                medicalFormulaDto.setObservations(medicalFormula.getObservations());
                // Llave foranea registro clinico
                ClinicalRecord clinicalRecord =new ClinicalRecord();
                if ( clinicalRecord != null){
                    ClinicalRecordDto clinicalRecordDto = new ClinicalRecordDto();
                    clinicalRecordDto.setId(medicalFormula.getIdregistroclinico().getId());
                    clinicalRecordDto.setClinical_Record_Data(medicalFormula.getIdregistroclinico().getClinical_Record_Data());

                    medicalFormulaDto.setIdregistroclinico(clinicalRecordDto);

                    Pet pet = medicalFormula.getIdregistroclinico().getIdmascota();
                    if (pet != null){
                        PetDto petDto = new PetDto();
                        petDto.setId(pet.getId());
                        petDto.setName(pet.getName());

                        clinicalRecordDto.setIdmascota(petDto);

                        Customer customer = medicalFormula.getIdregistroclinico().getIdmascota().getIdcliente();
                        if (customer != null){
                            CustomerDto customerDto = new CustomerDto();
                            customerDto.setId(customer.getId());
                            customerDto.setName(customer.getName());
                            customerDto.setLastName(customer.getLastName());
                            customerDto.setDni(customer.getDni());

                            petDto.setIdcliente(customerDto);
                        }
                    }
                }
                //Llave foranea de Producto
                Product product = medicalFormula.getIdproducto();
                if (product != null){
                    ProductDto productDto = new ProductDto();
                    productDto.setId(medicalFormula.getIdproducto().getId());
                    productDto.setName(medicalFormula.getIdproducto().getName());

                    medicalFormulaDto.setIdproducto(productDto);
                }

                medicalFormulaDtoList.add(medicalFormulaDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar la formula medica", e);
        }
        return medicalFormulaDtoList;
    }

    public MedicalFormulaDto findMedicalFormulaById(int id) {
        try {
            MedicalFormula medicalFormula = this.medicalFormulaService.findById(id);

            if (medicalFormula != null) {
                MedicalFormulaDto medicalFormulaDto = new MedicalFormulaDto();
                medicalFormulaDto.setId(medicalFormula.getId());
                medicalFormulaDto.setDose(medicalFormula.getDose());
                medicalFormulaDto.setDuration(medicalFormula.getDuration());
                medicalFormulaDto.setAmount(medicalFormula.getAmount());
                medicalFormulaDto.setObservations(medicalFormula.getObservations());
                return medicalFormulaDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la formula medica por ID", e);
        }
    }

    public String createMedicalFormula(MedicalFormulaDto medicalFormulaDto) {
        try {
            MedicalFormula medicalFormula = new MedicalFormula();
            // Llave foranea producto
            ProductDto productDto = medicalFormulaDto.getIdproducto();
            Product product = productService.findById(productDto.getId());
            medicalFormula.setIdproducto(product);
            // Llave foranea registro clinico
            ClinicalRecordDto clinicalRecordDto = medicalFormulaDto.getIdregistroclinico();
            ClinicalRecord clinicalRecord = clinicalRecordService.findById(clinicalRecordDto.getId());
            medicalFormula.setIdregistroclinico(clinicalRecord);

            medicalFormula.setDose(medicalFormulaDto.getDose());
            medicalFormula.setDuration(medicalFormulaDto.getDuration());
            medicalFormula.setAmount(medicalFormulaDto.getAmount());
            medicalFormula.setObservations(medicalFormulaDto.getObservations());

            this.medicalFormulaService.create(medicalFormula);

            return "Formula medica creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la formula medica", e);
        }
    }

    public String updateMedicalFormula(MedicalFormulaDto medicalFormulaDto) {
        try {
            MedicalFormula medicalFormula = medicalFormulaService.findById(medicalFormulaDto.getId());

            if (medicalFormula != null) {
                // Actualizar los campos de la fórmula médica
                medicalFormula.setDose(medicalFormulaDto.getDose());
                medicalFormula.setDuration(medicalFormulaDto.getDuration());
                medicalFormula.setAmount(medicalFormulaDto.getAmount());
                medicalFormula.setObservations(medicalFormulaDto.getObservations());

                // Actualizar la llave foránea de producto si es necesario
                ProductDto productDto = medicalFormulaDto.getIdproducto();
                if (productDto != null) {
                    Product product = productService.findById(productDto.getId());
                    medicalFormula.setIdproducto(product);
                }

                // Actualizar la llave foránea de registro clínico si es necesario
                ClinicalRecordDto clinicalRecordDto = medicalFormulaDto.getIdregistroclinico();
                if (clinicalRecordDto != null) {
                    ClinicalRecord clinicalRecord = clinicalRecordService.findById(clinicalRecordDto.getId());
                    medicalFormula.setIdregistroclinico(clinicalRecord);
                }

                medicalFormulaService.update(medicalFormula);

                return "Fórmula médica actualizada exitosamente";
            } else {
                throw new RuntimeException("No se puede actualizar la fórmula médica. La fórmula médica no existe con ID: " + medicalFormulaDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la fórmula médica", e);
        }
    }
}
