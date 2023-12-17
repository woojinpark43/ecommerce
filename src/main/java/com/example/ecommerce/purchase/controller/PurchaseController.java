package com.example.ecommerce.purchase.controller;

import com.example.ecommerce.purchase.dto.PurchaseDto;
import com.example.ecommerce.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/purchase/register")
    public ResponseEntity<?> register(@RequestBody @Valid PurchaseDto purchaseDto, Errors errors) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        purchaseDto.setCreateAt(currentDateTime);
        purchaseDto.setUpdateAt(currentDateTime);
        boolean registerSuccess = purchaseService.register(purchaseDto);

        if(!registerSuccess) {
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(purchaseDto);
    }
}
