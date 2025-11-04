package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecialityMapper {

    public Specialty toEntity(SpecialtyDTO dto) {
        if (dto == null) return null;
        return new Specialty(
                dto.getId(),
                dto.getName(),
                dto.getOffice(),
                dto.getHOpen(),
                dto.getHClose()
        );
    }

    public SpecialtyDTO toDTO(Specialty entity) {
        if (entity == null) return null;
        return new SpecialtyDTO(
                entity.getId(),
                entity.getName(),
                entity.getOffice(),
                entity.getHOpen(),
                entity.getHClose()
        );
    }

    public List<SpecialtyDTO> toDTOList(List<Specialty> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}