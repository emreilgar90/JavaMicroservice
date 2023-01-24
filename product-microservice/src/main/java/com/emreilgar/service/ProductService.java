package com.emreilgar.service;

import com.emreilgar.repository.IProductRepository;
import com.emreilgar.repository.entity.Product;
import com.emreilgar.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceManager<Product,Long> {
    private final IProductRepository repository;
    public ProductService(IProductRepository repository){
        super(repository);
        this.repository = repository;
    }
}
