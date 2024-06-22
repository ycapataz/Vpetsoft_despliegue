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
                ClinicalRecord clinicalRecord = medicalFormula.getIdregistroclinico();
                if (clinicalRecord != null) {
                    ClinicalRecordDto clinicalRecordDto = new ClinicalRecordDto();
                    clinicalRecordDto.setId(clinicalRecord.getId());
                    clinicalRecordDto.setHeart_rate(clinicalRecord.getHeart_rate());
                    clinicalRecordDto.setObservations(clinicalRecord.getObservations());
                    clinicalRecordDto.setClinical_Record_Data(clinicalRecord.getClinical_Record_Data());
                    clinicalRecordDto.setTemperature(clinicalRecord.getTemperature());
                    medicalFormulaDto.setIdregistroclinico(clinicalRecordDto);

                    // Llave foranea empleado
                    Employee employee = clinicalRecord.getIdempleado();

                    if (employee != null) {
                        EmployeeDto employeeDto = new EmployeeDto();
                        employeeDto.setId(clinicalRecord.getIdempleado().getId());
                        employeeDto.setName(clinicalRecord.getIdempleado().getName());
                        employeeDto.setLastName(clinicalRecord.getIdempleado().getLastName());
                        employeeDto.setAge(clinicalRecord.getIdempleado().getAge());
                        employeeDto.setMail(clinicalRecord.getIdempleado().getMail());
                        employeeDto.setPassword(clinicalRecord.getIdempleado().getPassword());
                        employeeDto.setAddress(clinicalRecord.getIdempleado().getAddress());
                        employeeDto.setPhone(clinicalRecord.getIdempleado().getPhone());
                        employeeDto.setBirthdayDate(clinicalRecord.getIdempleado().getBirthdayDate());
                        employeeDto.setDni(clinicalRecord.getIdempleado().getDni());
                        employeeDto.setIdcargo(employeeDto.getIdcargo());
                        clinicalRecordDto.setIdempleado(employeeDto);

                        // Llaves foraneas de empleado
                        Post post = clinicalRecord.getIdempleado().getIdcargo();
                        if (post != null) {
                            PostDto postDto = new PostDto();
                            postDto.setId(post.getId());
                            postDto.setName(post.getName());

                            employeeDto.setIdcargo(postDto);
                        }
                        Eps eps = clinicalRecord.getIdempleado().getIdeps();
                        if (eps != null) {
                            EpsDto epsDto = new EpsDto();
                            epsDto.setId(eps.getId());
                            epsDto.setName(eps.getName());

                            employeeDto.setIdeps(epsDto);
                        }
                        Specialty specialty = clinicalRecord.getIdempleado().getIdespecialidad();
                        if (specialty != null) {
                            SpecialtyDto specialtyDto = new SpecialtyDto();
                            specialtyDto.setId(specialty.getId());
                            specialtyDto.setName(specialty.getName());

                            employeeDto.setIdespecialidad(specialtyDto);
                        }
                        // Llave foranea mascota
                        Pet pet = clinicalRecord.getIdmascota();
                        if (pet != null) {
                            PetDto petDto = new PetDto();
                            petDto.setId(clinicalRecord.getIdmascota().getId());
                            petDto.setName(clinicalRecord.getIdmascota().getName());
                            petDto.setColor(clinicalRecord.getIdmascota().getColor());
                            petDto.setDateBirth(clinicalRecord.getIdmascota().getDateBirth());
                            petDto.setNumber_microchip(clinicalRecord.getIdmascota().getNumber_microchip());
                            clinicalRecordDto.setIdmascota(petDto);
                            // Llaves foraneas de mascota
                            Customer customer = clinicalRecord.getIdmascota().getIdcliente();
                            if (customer != null) {
                                CustomerDto customerDto = new CustomerDto();
                                customerDto.setId(customer.getId());
                                customerDto.setName(customer.getName());
                                customerDto.setLastName(customer.getLastName());
                                customerDto.setDni(customer.getDni());
                                customerDto.setPhone(customer.getPhone());
                                customerDto.setMail(customer.getMail());
                                customerDto.setAddress(customer.getAddress());

                                petDto.setIdcliente(customerDto);

                                City city = pet.getIdcliente().getIdciudad();
                                if (city != null) {
                                    CityDto cityDto = new CityDto();
                                    cityDto.setId(city.getId());
                                    cityDto.setName(city.getName());

                                    customerDto.setIdciudad(cityDto);
                                }
                            }
                            PetGenus petGenus = clinicalRecord.getIdmascota().getIdgenmascota();
                            if (petGenus != null) {
                                PetGenusDto petGenusDto = new PetGenusDto();
                                petGenusDto.setId(petGenus.getId());
                                petGenusDto.setName(petGenus.getName());

                                petDto.setIdgenmascota(petGenusDto);
                            }
                            Species species = clinicalRecord.getIdmascota().getIdespecie();
                            if (species != null) {
                                SpeciesDto speciesDto = new SpeciesDto();
                                speciesDto.setId(species.getId());
                                speciesDto.setName(species.getName());

                                petDto.setIdespecie(speciesDto);
                            }
                            Breed breed = clinicalRecord.getIdmascota().getIdraza();
                            if (breed != null) {
                                BreedDto breedDto = new BreedDto();
                                breedDto.setId(breed.getId());
                                breedDto.setName(breed.getName());

                                petDto.setIdraza(breedDto);
                            }

                        }
                        // Llave foranea ingreso
                        Income income = clinicalRecord.getIdingreso();
                        if (income != null) {
                            IncomeDto incomeDto = new IncomeDto();
                            incomeDto.setId(clinicalRecord.getIdingreso().getId());
                            incomeDto.setDate(clinicalRecord.getIdingreso().getDate());
                            incomeDto.setHour(clinicalRecord.getIdingreso().getHour());
                            clinicalRecordDto.setIdingreso(incomeDto);

                            // Llaves foraneas de ingreso
                            Appointment appointment = income.getIdcita();
                            if (appointment != null) {
                                AppointmentDto appointmentDto = new AppointmentDto();
                                appointmentDto.setId(appointment.getId());
                                appointmentDto.setDate(appointment.getDate());
                                appointmentDto.setHour(appointment.getHour());

                                incomeDto.setIdcita(appointmentDto);

                            }
                            IncomeType incomeType = income.getIdtipoingreso();
                            if (incomeType != null) {
                                IncomeTypeDto incomeTypeDto = new IncomeTypeDto();
                                incomeTypeDto.setId(incomeType.getId());
                                incomeTypeDto.setName(incomeType.getName());

                                incomeDto.setIdtipoingreso(incomeTypeDto);

                            }
                            EnteredStatus enteredStatus = income.getIdestadoingreso();
                            if (incomeType != null) {
                                EnteredStatusDto enteredStatusDto = new EnteredStatusDto();
                                enteredStatusDto.setId(enteredStatus.getId());
                                enteredStatusDto.setName(enteredStatus.getName());

                                incomeDto.setIdestadoingreso(enteredStatusDto);
                            }
                        }
                        Disease disease = clinicalRecord.getIdenfermedad();
                        if (disease != null) {
                            DiseaseDto diseaseDto = new DiseaseDto();
                            diseaseDto.setId(disease.getId());
                            diseaseDto.setName(disease.getName());

                            clinicalRecordDto.setIdenfermedad(diseaseDto);
                        }
                        MedicalExam medicalExam = clinicalRecord.getIdexamenmedico();
                        if (medicalExam != null) {
                            MedicalExamDto medicalExamDto = new MedicalExamDto();
                            medicalExamDto.setId(medicalExam.getId());
                            medicalExamDto.setExam(medicalExam.getExam());

                            clinicalRecordDto.setIdexamenmedico(medicalExamDto);
                        }
                    }
                }
                // Llave foranea producto
                Product product = medicalFormula.getIdproducto();
                if (product != null) {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setExpiration(product.getExpiration());
                    productDto.setAmount(product.getAmount());
                    productDto.setBatch(product.getBatch());

                    medicalFormulaDto.setIdproducto(productDto);

                    // Llave foranea categoria
                    Category category = product.getIdcategoria();
                    if (category != null) {
                        CategoryDto categoryDto = new CategoryDto();
                        categoryDto.setId(category.getId());
                        categoryDto.setName(category.getName());

                        productDto.setIdcategoria(categoryDto);
                    }
                    // Llave foranea estado
                    State state = product.getIdestado();
                    if (state != null) {
                        StateDto stateDto = new StateDto();
                        stateDto.setId(state.getId());
                        stateDto.setName(state.getName());

                        productDto.setIdestado(stateDto);
                    }
                    // Llave foranea proveedor
                    Provider provider = product.getIdproveedor();
                    if (provider != null) {
                        ProviderDto providerDto = new ProviderDto();
                        providerDto.setId(provider.getId());
                        providerDto.setName(provider.getName());
                        providerDto.setRepresentative(provider.getRepresentative());
                        providerDto.setMail(provider.getMail());
                        providerDto.setPhone(provider.getPhone());
                        providerDto.setNit(provider.getNit());
                        productDto.setIdproveedor(providerDto);
                        // Llave foranea ciudad
                        City city = provider.getIdciudad();
                        if (city != null) {
                            CityDto cityDto = new CityDto();
                            cityDto.setId(city.getId());
                            cityDto.setName(city.getName());

                            providerDto.setIdciudad(cityDto);
                        }
                        // Llave foranea estado
                        State state1 = provider.getIdestado();
                        if (state1 != null) {
                            StateDto stateDto1 = new StateDto();
                            stateDto1.setId(state1.getId());
                            stateDto1.setName(state1.getName());

                            providerDto.setIdestado(stateDto1);
                        }
                    }
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
