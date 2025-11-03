package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import org.junit.jupiter.api.Test;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.exceptions.SpecialityNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    @Test
    public void testFindSpecialtyById() {

        String NAME_EXPECTED = "radiology";
        Integer ID = 1;

        SpecialtyDTO specialty = null;

        try {
            specialty = this.specialtyService.findById(ID);
        } catch (SpecialityNotFoundException e) {
            fail(e.getMessage());
        }

        assertEquals(NAME_EXPECTED, specialty.getName());
    }

    @Test
    public void testFindSpecialtyByName() {

        String FIND_NAME = "surgery";
        int SIZE_EXPECTED = 1;

        List<SpecialtyDTO> specialties = this.specialtyService.findByName(FIND_NAME);

        assertEquals(SIZE_EXPECTED, specialties.size());
    }

    @Test
    public void testFindAllSpecialties() {

        int SIZE_EXPECTED = 3;

        List<SpecialtyDTO> specialties = this.specialtyService.findAll();

        assertTrue(specialties.size() >= SIZE_EXPECTED);
    }

    @Test
    public void testCreateSpecialty() {

        String SPECIALTY_NAME = "Dermatology";
        String SPECIALTY_OFFICE = "Building A";
        Integer H_OPEN = 8;
        Integer H_CLOSE = 16;

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setName(SPECIALTY_NAME);
        specialtyDTO.setOffice(SPECIALTY_OFFICE);
        specialtyDTO.setHOpen(H_OPEN);
        specialtyDTO.setHClose(H_CLOSE);

        SpecialtyDTO newSpecialtyDTO = this.specialtyService.create(specialtyDTO);

        log.info("SPECIALTY CREATED: " + newSpecialtyDTO.toString());

        assertNotNull(newSpecialtyDTO.getId());
        assertEquals(SPECIALTY_NAME, newSpecialtyDTO.getName());
        assertEquals(SPECIALTY_OFFICE, newSpecialtyDTO.getOffice());
    }

    @Test
    public void testUpdateSpecialty() {

        String SPECIALTY_NAME = "Cardiology";
        String SPECIALTY_OFFICE = "Building B";
        Integer H_OPEN = 9;
        Integer H_CLOSE = 17;

        String UP_SPECIALTY_NAME = "Veterinary Cardiology";
        String UP_SPECIALTY_OFFICE = "Building C";

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setName(SPECIALTY_NAME);
        specialtyDTO.setOffice(SPECIALTY_OFFICE);
        specialtyDTO.setHOpen(H_OPEN);
        specialtyDTO.setHClose(H_CLOSE);

        log.info(">" + specialtyDTO);
        SpecialtyDTO specialtyDTOCreated = this.specialtyService.create(specialtyDTO);
        log.info(">>" + specialtyDTOCreated);

        specialtyDTOCreated.setName(UP_SPECIALTY_NAME);
        specialtyDTOCreated.setOffice(UP_SPECIALTY_OFFICE);

        SpecialtyDTO upgradeSpecialtyDTO = this.specialtyService.update(specialtyDTOCreated);
        log.info(">>>>" + upgradeSpecialtyDTO);

        assertEquals(UP_SPECIALTY_NAME, upgradeSpecialtyDTO.getName());
        assertEquals(UP_SPECIALTY_OFFICE, upgradeSpecialtyDTO.getOffice());
    }

    @Test
    public void testDeleteSpecialty() {

        String SPECIALTY_NAME = "Ophthalmology";
        String SPECIALTY_OFFICE = "Building D";
        Integer H_OPEN = 8;
        Integer H_CLOSE = 18;

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setName(SPECIALTY_NAME);
        specialtyDTO.setOffice(SPECIALTY_OFFICE);
        specialtyDTO.setHOpen(H_OPEN);
        specialtyDTO.setHClose(H_CLOSE);

        SpecialtyDTO newSpecialtyDTO = this.specialtyService.create(specialtyDTO);
        log.info("" + specialtyDTO);

        try {
            this.specialtyService.delete(newSpecialtyDTO.getId());
        } catch (SpecialityNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.specialtyService.findById(newSpecialtyDTO.getId());
            assertTrue(false);
        } catch (SpecialityNotFoundException e) {
            assertTrue(true);
        }
    }
}