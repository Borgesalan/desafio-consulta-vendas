package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<List<SaleMinDTO>> getReport(@RequestParam(name = "minDate", required = false, defaultValue = "") String minDate,
                                                      @RequestParam(name = "maxDate", required = false, defaultValue = "") String maxDate,
                                                      @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                                      Pageable pageable) {
        List<SaleMinDTO> dto = service.getReport(minDate, maxDate, name,pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SaleSellerMinDTO>> getSummary(@RequestParam(name = "minDate", required = false, defaultValue = "") String minDate,
                                                             @RequestParam(name = "maxDate", required = false, defaultValue = "") String maxDate) {
        List<SaleSellerMinDTO> dto = service.getSummary(minDate, maxDate);
        return ResponseEntity.ok(dto);
    }
}
