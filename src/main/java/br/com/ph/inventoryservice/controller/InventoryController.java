package br.com.ph.inventoryservice.controller;

import br.com.ph.inventoryservice.dto.InventoryResponse;
import br.com.ph.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/{skuCode}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("skuCode") String skuCode){
        Boolean hasStock = service.hasStock(skuCode);
        return ResponseEntity.ok(hasStock);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isAllInStock(@RequestParam List<String> skuCode){
        log.info("Received call for {}",skuCode);
        return ResponseEntity.ok(service.hasAllInStock(skuCode));
    }
}
