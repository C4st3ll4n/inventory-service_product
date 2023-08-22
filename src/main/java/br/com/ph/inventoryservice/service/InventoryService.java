package br.com.ph.inventoryservice.service;

import br.com.ph.inventoryservice.dto.InventoryResponse;
import br.com.ph.inventoryservice.model.Inventory;
import br.com.ph.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository repository;

    @Transactional(readOnly = true)
    public boolean hasStock(String skuCode) {
        return repository.findBySkuCode(skuCode).isPresent();
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    public List<InventoryResponse> hasAllInStock(List<String> skuCode) {
        log.info("Started hasAllInStock");
        var inventories = repository.findBySkuCodeIn(skuCode);
        log.info("Finishing hasAllInStock with:{}",inventories);
        return inventories.map(inventoryList -> inventoryList.stream()
                        .map(this::mapInventoryResponse).toList())
                .orElse(Collections.emptyList());

    }

    private InventoryResponse mapInventoryResponse(Inventory inventory) {
        return new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0);
    }
}
