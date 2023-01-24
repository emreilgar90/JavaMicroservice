package com.emreilgar.controller;

import com.emreilgar.dto.request.BaseRequestDto;
import com.emreilgar.repository.Sale;
import com.emreilgar.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.emreilgar.constans.RestApis.GETALL;
import static com.emreilgar.constans.RestApis.SALES;

@RestController
@RequestMapping(SALES)
@RequiredArgsConstructor
public class SalesController {

    private final SaleService saleService;
    @PostMapping(GETALL)
    public ResponseEntity<List<Sale>> getAll(@RequestBody @Valid BaseRequestDto dto){
        return ResponseEntity.ok(saleService.findAll());
    }
}
