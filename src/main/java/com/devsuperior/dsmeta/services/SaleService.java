package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.projections.SaleSellerMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public List<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate finalDate = maxDate.equals("") ? LocalDate.ofInstant(Instant.now(),
                ZoneId.systemDefault()) : LocalDate.parse(maxDate);

        LocalDate initialDate = minDate.equals("") ? finalDate.minusYears(1L) : LocalDate.parse(minDate);

        Page<SaleMinProjection> list = repository.report(initialDate, finalDate, name, pageable);
        return list.map(x -> new SaleMinDTO(x)).toList();
    }

    public List<SaleSellerMinDTO> getSummary(String minDate, String maxDate) {
        LocalDate finalDate = maxDate.equals("") ? LocalDate.ofInstant(Instant.now(),
                ZoneId.systemDefault()) : LocalDate.parse(maxDate);

        LocalDate initialDate = minDate.equals("") ? finalDate.minusYears(1L) : LocalDate.parse(minDate);

        List<SaleSellerMinProjection> list = repository.summary(initialDate, finalDate);
        return list.stream().map(x -> new SaleSellerMinDTO(x)).toList();
    }
}
