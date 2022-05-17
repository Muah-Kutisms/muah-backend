package com.muah.muahbackend.domain.estimate.service;

import com.muah.muahbackend.domain.estimate.dto.SheetDto;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.repository.SheetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;


@Slf4j
@Service
@RequiredArgsConstructor
public class SheetService {

    private final SheetRepository sheetRepository;


    @Transactional(readOnly = true)
    public List<SheetDto> getSheets(@PathVariable Long id) {
        Collection<Sheet> sheets = sheetRepository.findAllByPetId(id);
        ArrayList<SheetDto> sheetList = sheets.stream().map(s-> new SheetDto(s)).collect(toCollection(ArrayList::new));
        return sheetList;
    }


}
