package com.emreilgar.service;

import com.emreilgar.repository.ISaleRepository;
import com.emreilgar.repository.Sale;
import com.emreilgar.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class SaleService extends ServiceManager<Sale,Long> {
    private final ISaleRepository repository;

    public SaleService(ISaleRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
