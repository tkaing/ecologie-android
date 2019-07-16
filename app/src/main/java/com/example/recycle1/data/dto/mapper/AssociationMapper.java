package com.example.recycle1.data.dto.mapper;


import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.model.Association;

import java.util.ArrayList;
import java.util.List;

public class AssociationMapper {

    public static List<Association> map (List<AssociationDTO> associationDTOList) {
        List<Association> associationList = new ArrayList<>();
        for(AssociationDTO associationDTO : associationDTOList) {
            associationList.add(map(associationDTO));
        }
        return associationList;
    }

    public static Association map(AssociationDTO associationDTO) {
        Association association = new Association();
        association.setName(associationDTO.getName());
        association.setEmail(associationDTO.getEmail());
        association.setPhone(associationDTO.getPhone());
        association.setIdentifier(associationDTO.getIdentifier());
        association.setBirthdate(associationDTO.getBirthdate());
        association.setLocation(associationDTO.getLocation());
        association.setCreatedAt(associationDTO.getCreatedAt());
        return association;
    }
}
