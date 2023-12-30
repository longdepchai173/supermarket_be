package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CreateShelfRequest;
import com.project.supermarket_be.api.dto.request.ShelfRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.dto.response.ShelfResponse;
import com.project.supermarket_be.api.exception.customerException.UserIDNotFoundException;
import com.project.supermarket_be.api.exception.customerException.UserNotFoundException;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.model.Shelf;
import com.project.supermarket_be.domain.repository.ShelfRepo;
import com.project.supermarket_be.domain.service.CategoryService;
import com.project.supermarket_be.domain.service.CompartmentService;
import com.project.supermarket_be.domain.service.ShelfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelfServiceImpl implements ShelfService {
    private final ShelfRepo repo;
    private final CategoryService categoryService;
    private final CompartmentService compartmentService;
    @Override
    public ReturnResponse getAll() {
        List<ShelfResponse> shelves = repo.getAll();
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(shelves)
                .build();
    }

    @Override
    public ReturnResponse create(CreateShelfRequest request) {

        Category category = categoryService.findById(Long.valueOf(request.getCategoryId()));

        Shelf shelf = Shelf.builder()
                .shelfCode(request.getShelfCode())
                .category(category)
                .deletedFlag(false)
                .build();
        Shelf shelfSaved = repo.save(shelf);
        ShelfResponse response = ShelfResponse.builder()
                .categoryId(request.getCategoryId())
                .shelfCode(request.getShelfCode())
                .id(Math.toIntExact(shelfSaved.getId()))
                .build();
        return ReturnResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .data(response)
                .build();
    }

    @Override
    public ReturnResponse update(ShelfRequest request) {
        Category category = categoryService.findById(Long.valueOf(request.getCategoryId()));
        Shelf shelf = repo.findById(Long.valueOf(request.getId())).orElseThrow(()->new UserIDNotFoundException(request.getShelfCode()));
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
        if(currentQuantity == null || currentQuantity == 0){
            Shelf shelf = repo.findById(id).orElseThrow(()-> new UserNotFoundException("Cái này là không tìm thấy shelf "));
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
}
