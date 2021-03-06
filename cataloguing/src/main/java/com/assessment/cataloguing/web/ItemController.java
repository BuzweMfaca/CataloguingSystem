package com.assessment.cataloguing.web;

import com.assessment.cataloguing.domain.Item;
import com.assessment.cataloguing.services.ItemService;
import com.assessment.cataloguing.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewItem(@Valid @RequestBody Item item, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Item item1 = itemService.saveOrUpdate(item);
        return new ResponseEntity<Item>(item1, HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable String itemId){

        Item item = itemService.findItemByIdentifier(itemId);

        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Item> getAllItems(){
        return itemService.findAllItems();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable String itemId){
        itemService.deleteItemByIdentifier(itemId);

        return new ResponseEntity<String>("Item with ID: '"+itemId+"' was deleted", HttpStatus.OK);
    }
}
