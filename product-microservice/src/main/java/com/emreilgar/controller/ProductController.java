package com.emreilgar.controller;

import com.emreilgar.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.emreilgar.constansts.RestApis.*;  //RequestMapping in içinde ki PRODUCTa ulaşmak için
@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
}
