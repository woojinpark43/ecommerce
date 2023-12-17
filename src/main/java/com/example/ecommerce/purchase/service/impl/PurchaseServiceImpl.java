package com.example.ecommerce.purchase.service.impl;

import com.example.ecommerce.purchase.dto.PurchaseDto;
import com.example.ecommerce.purchase.dto.Validate;
import com.example.ecommerce.purchase.entity.Purchase;
import com.example.ecommerce.purchase.repository.PurchaseRepository;
import com.example.ecommerce.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public boolean register(PurchaseDto purchaseDto) {

        if(purchaseDto.getValidate() == Validate.NOTVALIDATED) {
            return false;
        }

        Purchase newPurchase = Purchase.builder()
                .address(purchaseDto.getAddress())
                .updateAt(purchaseDto.getUpdateAt())
                .productId(purchaseDto.getProductId())
                .purchaseId(purchaseDto.getPurchaseId())
                .carNum(purchaseDto.getCarNum())
                .cartId(purchaseDto.getCartId())
                .createAt(purchaseDto.getCreateAt())
                .validate(purchaseDto.getValidate())
                .userId(purchaseDto.getUserId())
                .build();

        purchaseRepository.save(newPurchase);

        return true;
    }
}
