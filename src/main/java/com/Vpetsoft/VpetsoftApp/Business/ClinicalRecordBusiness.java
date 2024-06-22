package com.Vpetsoft.VpetsoftApp.Business;

import com.Vpetsoft.VpetsoftApp.dto.*;
import com.Vpetsoft.VpetsoftApp.entity.*;
import com.Vpetsoft.VpetsoftApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Registro Clinico
@Component
public class ClinicalRecordBusiness {
    @Autowired
    private ClinicalRecordService clinicalRecordService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private PetService petService;
    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private MedicalExamService medicalExamService;
    private List<ClinicalRecord> ClinicalRecordList;

    public List<ClinicalRecordDto> findAll() {
        List<ClinicalRecordDto> clinicalRecordDtoList =new ArrayList<>();
        try {
            this.ClinicalRecordList = this.clinicalRecordService.findAll();
            this.ClinicalRecordList.forEach( clinicalRecord-> {
                ClinicalRecordDto clinicalRecordDto = new ClinicalRecordDto();
                clinicalRecordDto.setId(clinicalRecord.getId());
                clinicalRecordDto.setHeart_rate(clinicalRecord.getHeart_rate());
                clinicalRecordDto.setObservations(clinicalRecord.getObservations());
                clinicalRecordDto.setClinical_Record_Data(clinicalRecord.getClinical_Record_Data());
                clinicalRecordDto.setTemperature(clinicalRecord.getTemperature());


                //Llave foranea empleado
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

                    //Llaves foraneas de empleado
                    Post post = clinicalRecord.getIdempleado().getIdcargo();
                    if (post != null){
                        PostDto postDto = new PostDto();
                        postDto.setId(post.getId());
                        postDto.setName(post.getName());

                        employeeDto.setIdcargo(postDto);
                    }
                    Eps eps = clinicalRecord.getIdempleado().getIdeps();
                    if (eps != null){
                        EpsDto epsDto = new EpsDto();
                        epsDto.setId(eps.getId());
                        epsDto.setName(eps.getName());

                        employeeDto.setIdeps(epsDto);
                    }
                    Specialty specialty = clinicalRecord.getIdempleado().getIdespecialidad();
                    if (specialty != null){
                        SpecialtyDto specialtyDto = new SpecialtyDto();
                        specialtyDto.setId(specialty.getId());
                        specialtyDto.setName(specialty.getName());

                        employeeDto.setIdespecialidad(specialtyDto);
                    }
                }

                //Llave foranea mascota
                Pet pet = clinicalRecord.getIdmascota();
                if (pet != null) {
                    PetDto petDto = new PetDto();
                    petDto.setId(clinicalRecord.getIdmascota().getId());
                    petDto.setName(clinicalRecord.getIdmascota().getName());
                    petDto.setColor(clinicalRecord.getIdmascota().getColor());
                    petDto.setDateBirth(clinicalRecord.getIdmascota().getDateBirth());
                    petDto.setNumber_microchip(clinicalRecord.getIdmascota().getNumber_microchip());
                    clinicalRecordDto.setIdmascota(petDto);
                    //Llaves foraneas de mascota
                    Customer customer = clinicalRecord.getIdmascota().getIdcliente();
                    if (customer != null){
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
                        if (city != null){
                            CityDto cityDto = new CityDto();
                            cityDto.setId(city.getId());
                            cityDto.setName(city.getName());

                            customerDto.setIdciudad(cityDto);
                        }
                    }
                    PetGenus petGenus = clinicalRecord.getIdmascota().getIdgenmascota();
                    if (petGenus != null){
                        PetGenusDto petGenusDto = new PetGenusDto();
                        petGenusDto.setId(petGenus.getId());
                        petGenusDto.setName(petGenus.getName());

                        petDto.setIdgenmascota(petGenusDto);
                    }
                    Species species = clinicalRecord.getIdmascota().getIdespecie();
                    if (species != null){
                        SpeciesDto speciesDto = new SpeciesDto();
                        speciesDto.setId(species.getId());
                        speciesDto.setName(species.getName());

                        petDto.setIdespecie(speciesDto);
                    }
                    Breed breed = clinicalRecord.getIdmascota().getIdraza();
                    if (breed != null){
                        BreedDto breedDto = new BreedDto();
                        breedDto.setId(breed.getId());
                        breedDto.setName(breed.getName());

                        petDto.setIdraza(breedDto);
                    }

                }
                //Llave foranea ingreso
                Income income = clinicalRecord.getIdingreso();
                if (income != null){
                    IncomeDto incomeDto = new IncomeDto();
                    incomeDto.setId(clinicalRecord.getIdingreso().getId());
                    incomeDto.setDate(clinicalRecord.getIdingreso().getDate());
                    incomeDto.setHour(clinicalRecord.getIdingreso().getHour());
                    clinicalRecordDto.setIdingreso(incomeDto);

                    //Llaves foraneas de ingreso
                    Appointment appointment = income.getIdcita();
                    if (appointment != null){
                        AppointmentDto appointmentDto = new AppointmentDto();
                        appointmentDto.setId(appointment.getId());
                        appointmentDto.setDate(appointment.getDate());
                        appointmentDto.setHour(appointment.getHour());

                        incomeDto.setIdcita(appointmentDto);

                    }
                    IncomeType incomeType = income.getIdtipoingreso();
                    if (incomeType != null){
                        IncomeTypeDto incomeTypeDto = new IncomeTypeDto();
                        incomeTypeDto.setId(incomeType.getId());
                        incomeTypeDto.setName(incomeType.getName());

                        incomeDto.setIdtipoingreso(incomeTypeDto);

                    }
                    EnteredStatus enteredStatus = income.getIdestadoingreso();
                    if (incomeType != null){
                        EnteredStatusDto enteredStatusDto = new EnteredStatusDto();
                        enteredStatusDto.setId(enteredStatus.getId());
                        enteredStatusDto.setName(enteredStatus.getName());

                        incomeDto.setIdestadoingreso(enteredStatusDto);
                    }
                }
                Disease disease = clinicalRecord.getIdenfermedad();
                if (disease != null){
                    DiseaseDto diseaseDto = new DiseaseDto();
                    diseaseDto.setId(disease.getId());
                    diseaseDto.setName(disease.getName());

                    clinicalRecordDto.setIdenfermedad(diseaseDto);
                }
                MedicalExam medicalExam = clinicalRecord.getIdexamenmedico();
                if (medicalExam != null){
                    MedicalExamDto medicalExamDto = new MedicalExamDto();
                    medicalExamDto.setId(medicalExam.getId());
                    medicalExamDto.setExam(medicalExam.getExam());

                    clinicalRecordDto.setIdexamenmedico(medicalExamDto);
                }

                clinicalRecordDtoList.add(clinicalRecordDto);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al recuperar el Registro Clinico", e);
        }
        return clinicalRecordDtoList;
    }

    public ClinicalRecordDto findClinicalRecordById(int id) {
        try {
            ClinicalRecord clinicalRecord = this.clinicalRecordService.findById(id);

            if (clinicalRecord != null) {
                ClinicalRecordDto clinicalRecordDto = new ClinicalRecordDto();
                clinicalRecordDto.setId(clinicalRecord.getId());
                clinicalRecordDto.setHeart_rate(clinicalRecord.getHeart_rate());
                clinicalRecordDto.setObservations(clinicalRecord.getObservations());
                clinicalRecordDto.setClinical_Record_Data(clinicalRecord.getClinical_Record_Data());
                clinicalRecordDto.setTemperature(clinicalRecord.getTemperature());

                return clinicalRecordDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el registro clínico por ID", e);
        }
    }

    public String createClinicalRecord(ClinicalRecordDto clinicalRecordDto) {
        try {
            ClinicalRecord clinicalRecord = new ClinicalRecord();

            // Llave foránea ingreso
            IncomeDto incomeDto = clinicalRecordDto.getIdingreso();
            Income income = incomeService.findById(incomeDto.getId());
            clinicalRecord.setIdingreso(income);

            // Llave foránea empleado
            EmployeeDto employeeDto = clinicalRecordDto.getIdempleado();
            Employee employee = employeeService.findById(employeeDto.getId());
            clinicalRecord.setIdempleado(employee);

            // Llave foránea examen médico
            MedicalExamDto medicalExamDto = clinicalRecordDto.getIdexamenmedico();
            MedicalExam medicalExam = medicalExamService.findById(medicalExamDto.getId());
            clinicalRecord.setIdexamenmedico(medicalExam);

            // Llave foránea mascota
            PetDto petDto = clinicalRecordDto.getIdmascota();
            Pet pet = petService.findById(petDto.getId());
            clinicalRecord.setIdmascota(pet);

            // Llave foránea enfermedad
            DiseaseDto diseaseDto = clinicalRecordDto.getIdenfermedad();
            Disease disease = diseaseService.findById(diseaseDto.getId());
            clinicalRecord.setIdenfermedad(disease);

            clinicalRecord.setHeart_rate(clinicalRecordDto.getHeart_rate());
            clinicalRecord.setObservations(clinicalRecordDto.getObservations());
            clinicalRecord.setClinical_Record_Data(clinicalRecordDto.getClinical_Record_Data());
            clinicalRecord.setTemperature(clinicalRecordDto.getTemperature());

            this.clinicalRecordService.create(clinicalRecord);

            return "Registro clínico creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el registro clínico", e);
        }
    }



    public String updateClinicalRecord(ClinicalRecordDto clinicalRecordDto) {
        try {
            // Verificar si el registro clínico a actualizar existe en la base de datos
            ClinicalRecord clinicalRecord = clinicalRecordService.findById(clinicalRecordDto.getId());

            // Si el registro clínico existe, actualizarlo con los datos del DTO
            if (clinicalRecord != null) {
                // Actualizar los campos del registro clínico
                clinicalRecord.setHeart_rate(clinicalRecordDto.getHeart_rate());
                clinicalRecord.setObservations(clinicalRecordDto.getObservations());
                clinicalRecord.setClinical_Record_Data(clinicalRecordDto.getClinical_Record_Data());
                clinicalRecord.setTemperature(clinicalRecordDto.getTemperature());

                // Verificar y actualizar las llaves foráneas

                // Llave foránea empleado
                if (clinicalRecordDto.getIdempleado() != null) {
                    EmployeeDto employeeDto = clinicalRecordDto.getIdempleado();
                    Employee employee = employeeService.findById(employeeDto.getId());
                    if (employee != null) {
                        clinicalRecord.setIdempleado(employee);
                    } else {
                        throw new RuntimeException("No se puede actualizar el registro clínico. El empleado no existe con ID: " + employeeDto.getId());
                    }
                }

                // Llave foránea ingreso
                if (clinicalRecordDto.getIdingreso() != null) {
                    IncomeDto incomeDto = clinicalRecordDto.getIdingreso();
                    Income income = incomeService.findById(incomeDto.getId());
                    if (income != null) {
                        clinicalRecord.setIdingreso(income);
                    } else {
                        throw new RuntimeException("No se puede actualizar el registro clínico. El ingreso no existe con ID: " + incomeDto.getId());
                    }
                }

                // Llave foránea examen médico
                if (clinicalRecordDto.getIdexamenmedico() != null) {
                    MedicalExamDto medicalExamDto = clinicalRecordDto.getIdexamenmedico();
                    MedicalExam medicalExam = medicalExamService.findById(medicalExamDto.getId());
                    if (medicalExam != null) {
                        clinicalRecord.setIdexamenmedico(medicalExam);
                    } else {
                        throw new RuntimeException("No se puede actualizar el registro clínico. El examen médico no existe con ID: " + medicalExamDto.getId());
                    }
                }

                // Llave foránea mascota
                if (clinicalRecordDto.getIdmascota() != null) {
                    PetDto petDto = clinicalRecordDto.getIdmascota();
                    Pet pet = petService.findById(petDto.getId());
                    if (pet != null) {
                        clinicalRecord.setIdmascota(pet);
                    } else {
                        throw new RuntimeException("No se puede actualizar el registro clínico. La mascota no existe con ID: " + petDto.getId());
                    }
                }

                // Llave foránea enfermedad
                if (clinicalRecordDto.getIdenfermedad() != null) {
                    DiseaseDto diseaseDto = clinicalRecordDto.getIdenfermedad();
                    Disease disease = diseaseService.findById(diseaseDto.getId());
                    if (disease != null) {
                        clinicalRecord.setIdenfermedad(disease);
                    } else {
                        throw new RuntimeException("No se puede actualizar el registro clínico. La enfermedad no existe con ID: " + diseaseDto.getId());
                    }
                }

                // Llamar al servicio para actualizar el registro clínico en la base de datos
                clinicalRecordService.update(clinicalRecord);

                // Devolver un mensaje indicando que el registro clínico se ha actualizado exitosamente
                return "Registro clínico actualizado exitosamente";
            } else {
                // Si el registro clínico no existe, lanzar una excepción
                throw new RuntimeException("No se puede actualizar el registro clínico. El registro no existe con ID: " + clinicalRecordDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción (no recomendado en producción)
            throw new RuntimeException("Error al actualizar el registro clínico", e); // Lanzar una excepción personalizada
        }
    }
}
