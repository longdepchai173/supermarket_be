package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.mapping_output.ProductIdCurrentQnt;
import com.project.supermarket_be.api.dto.request.AddProductToShelfRequest;
import com.project.supermarket_be.api.dto.request.CreateShelfRequest;
import com.project.supermarket_be.api.dto.request.ShelfRequest;
import com.project.supermarket_be.api.dto.response.AddProductTOShelfResponse;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.dto.response.ShelfResponse;
import com.project.supermarket_be.api.dto.response.TierCountInUse;
import com.project.supermarket_be.api.exception.customerException.CompartmentHasDiffProduct;
import com.project.supermarket_be.api.exception.customerException.ShelfCodeAlreadyExists;
import com.project.supermarket_be.api.exception.customerException.UserIDNotFoundException;
import com.project.supermarket_be.api.exception.customerException.UserNotFoundException;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.Shelf;
import com.project.supermarket_be.domain.repository.ShelfRepo;
import com.project.supermarket_be.domain.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Float.NaN;

@Service
@AllArgsConstructor
public class ShelfServiceImpl implements ShelfService {
    private final ShelfRepo repo;
    private final CategoryService categoryService;
    private final CompartmentService compartmentService;
    private final TierService tierService;
    private final ProductService productService;
    private List<AddProductTOShelfResponse> addProductTOShelfResponses;

    @Override
    public ReturnResponse getAll() {
        List<ShelfResponse> shelves = repo.getAll();
        List<ShelfResponse> toReturn = new ArrayList<>();
        for (ShelfResponse response : shelves) {
            Integer shelfId = response.getShelfId();
            List<Object[]> calculateInUse = repo.calculateInUse(shelfId);
            List<TierCountInUse> toCalculate = calculateInUse.stream()
                    .map(result -> TierCountInUse.builder()
                            .tierId((Integer) result[0])
                            .count((Long) result[1])
                            .build()
                    ).toList();
            int count = 0;
            for (TierCountInUse inUse : toCalculate) {
                if (inUse.getCount() != null && inUse.getCount() != 0) {
                    count++;
                }
            }
            float inUseValue;
            if (count == 0) {
                inUseValue = 0;
            } else
                inUseValue = ((float) count / (float) toCalculate.size()) * 100;

            response.setInUse(inUseValue);
        }

        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(shelves)
                .build();
    }

    @Override
    public ReturnResponse create(CreateShelfRequest request) {

        Category category = categoryService.findById(Long.valueOf(request.getCategoryId()));
        List<Object[]> checkExists = repo.findByShelfCode(request.getShelfCode());
        if (!checkExists.isEmpty()) {
            throw new ShelfCodeAlreadyExists(request.getShelfCode());
        }

        Shelf shelf = Shelf.builder()
                .shelfCode(request.getShelfCode())
                .category(category)
                .deletedFlag(false)
                .build();
        Shelf shelfSaved = repo.save(shelf);
        try {
            tierService.createTierByShelfId(shelf, request.getTiers());
        } catch (Exception e) {
            repo.delete(shelfSaved);
        }
        return ReturnResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .data("create shelf successfully")
                .build();
    }

    @Override
    public ReturnResponse update(ShelfRequest request) {
        Category category = categoryService.findById(Long.valueOf(request.getCategoryId()));
        Shelf shelf = repo.findById(Long.valueOf(request.getId())).orElseThrow(() -> new UserIDNotFoundException(request.getShelfCode()));
        shelf.setCategory(category);
        shelf.setShelfCode(request.getShelfCode());

        Shelf shelfSaved = repo.save(shelf);
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data("Update shelf successfully")
                .build();
    }

    @Override
    public ReturnResponse delete(Long id) {
        Integer currentQuantity = compartmentService.getCurrentQuantityByShelfId(id);
        if (currentQuantity == null || currentQuantity == 0) {
            Shelf shelf = repo.findById(id).orElseThrow(() -> new UserNotFoundException("Cái này là không tìm thấy shelf "));
            shelf.setDeletedFlag(true);
            repo.save(shelf);
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.OK)
                    .data("Delete shelf successfully")
                    .build();
        }
        return ReturnResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .data("Can not delete this shelf")
                .build();
    }

    @Override
    public ReturnResponse addProduct(AddProductToShelfRequest request) {
        Integer sumOfProductOnShelf = getSumOfProductOnShelf(request);
        Product product = productService.getProductById(Long.valueOf(request.getProductId()));
        Integer numberOfProductWantToAdd =
                product.getShelfArrangeQuantity() * request.getCompartmentIds().size() - sumOfProductOnShelf;
        Integer productOnInventory = product.getInputQuantity() - product.getSoldQuantity() - product.getShelfQuantity();
        if(productOnInventory >= numberOfProductWantToAdd){
            Integer updateShelfQnt = product.getShelfQuantity() + numberOfProductWantToAdd;
            product.setShelfQuantity(updateShelfQnt);
            productService.updateShelfQuantity(updateShelfQnt, product.getId());
            compartmentService.updateCurrentQuantities(request.getCompartmentIds(), product.getShelfArrangeQuantity());
        }else {
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data("Product in inventory not enough to add")
                    .build();
        }
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data("add product to shelf successfully")
                .build();
    }

    private Integer getSumOfProductOnShelf(AddProductToShelfRequest request) {
        Integer productId = request.getProductId();
        Integer tierId = request.getTierId();
        Integer sumOfProductOnShelf = 0;
        if (!request.getCompartmentIds().isEmpty())
            for (Integer compartmentId : request.getCompartmentIds()) {
                ProductIdCurrentQnt productQnt = compartmentService.getProductInCompartment(tierId, compartmentId);
                if (productQnt.getProductId() == null || productQnt.getProductId().equals(productId)) {
                    sumOfProductOnShelf += productQnt.getCurrentQuantity();
                } else throw new CompartmentHasDiffProduct(String.valueOf(compartmentId), String.valueOf(productId));

            }
        return sumOfProductOnShelf;
    }
}
