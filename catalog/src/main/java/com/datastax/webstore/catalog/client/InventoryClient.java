package com.datastax.webstore.catalog.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name="inventory", url = "${inventory.url}")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/inventory/{itemId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    String getInventoryStatus(@PathVariable("itemId") UUID itemId);

    /*

    @Component
    class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {
      @Override
      public InventoryClient create(Throwable cause) {
        return itemId -> "[{'quantity':-1}]";
      }
    }
    */

}