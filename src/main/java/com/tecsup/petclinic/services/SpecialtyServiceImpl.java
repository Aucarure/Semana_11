package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.mappers.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private SpecialtyMapper specialtyMapper;

    @Override
    public SpecialtyDTO create(SpecialtyDTO specialtyDTO) {
        Specialty specialty = specialtyMapper.toEntity(specialtyDTO);
        Specialty savedSpecialty = specialtyRepository.save(specialty);
        log.info("Specialty created: {}", savedSpecialty);
        return specialtyMapper.toDTO(savedSpecialty);
    }

    @Override
    public SpecialtyDTO update(SpecialtyDTO specialtyDTO) {
        Specialty specialty = specialtyMapper.toEntity(specialtyDTO);
        Specialty updatedSpecialty = specialtyRepository.save(specialty);
        log.info("Specialty updated: {}", updatedSpecialty);
        return specialtyMapper.toDTO(updatedSpecialty);
    }

    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found with id: " + id));
        specialtyRepository.delete(specialty);
        log.info("Specialty deleted with id: {}", id);
    }

    @Override
    public SpecialtyDTO findById(Integer id) throws SpecialtyNotFoundException {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found with id: " + id));
        return specialtyMapper.toDTO(specialty);
    }

    @Override
    public List<SpecialtyDTO> findByName(String name) {
        List<Specialty> specialties = specialtyRepository.findByName(name);
        return specialtyMapper.toDTOList(specialties);
    }

    @Override
    public List<SpecialtyDTO> findByOffice(String office) {
        List<Specialty> specialties = specialtyRepository.findByOffice(office);
        return specialtyMapper.toDTOList(specialties);
    }

    @Override
    public List<SpecialtyDTO> findAll() {
        List<Specialty> specialties = specialtyRepository.findAll();
        return specialtyMapper.toDTOList(specialties);
    }
}