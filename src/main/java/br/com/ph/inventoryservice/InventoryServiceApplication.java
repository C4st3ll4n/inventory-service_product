package br.com.ph.inventoryservice;

import br.com.ph.inventoryservice.model.Inventory;
import br.com.ph.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_14");
			inventory.setQuantity(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_13");
			inventory2.setQuantity(100);

			Inventory inventory3= new Inventory();
			inventory3.setSkuCode("iphone_12");
			inventory3.setQuantity(100);

			Inventory inventory4 = new Inventory();
			inventory4.setSkuCode("iphone_x");
			inventory4.setQuantity(100);

			repository.save(inventory);
			repository.save(inventory2);
			repository.save(inventory3);
			repository.save(inventory4);
		};
	}

}
