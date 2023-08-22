package br.com.ph.inventoryservice.dto;

public record InventoryResponse(
    String skuCode, boolean inStock
) {
}
