package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class SpecialtySearchServiceTest {
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
}
