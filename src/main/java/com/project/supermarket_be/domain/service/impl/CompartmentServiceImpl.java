package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.mapping_output.ProductIdCurrentQnt;
import com.project.supermarket_be.api.dto.response.CompartmentResponse;
import com.project.supermarket_be.api.dto.response.ProductIdCategoryNameDto;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.UserIDNotFoundException;
import com.project.supermarket_be.api.exception.customerException.UserNotFoundException;
import com.project.supermarket_be.domain.model.Compartment;
import com.project.supermarket_be.domain.model.Tier;
import com.project.supermarket_be.domain.repository.CompartmentRepo;
import com.project.supermarket_be.domain.service.CategoryService;
import com.project.supermarket_be.domain.service.CompartmentService;
import com.project.supermarket_be.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompartmentServiceImpl implements CompartmentService {
    private final CompartmentRepo repo;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Override
    public ReturnResponse getAllCompartmentByTierId(String tierId) {
        List<Object[]> compartments = repo.getAllCompartmentByTierId(Long.valueOf(tierId));
        List<CompartmentResponse> responses = new ArrayList<>();
        for (Object[] row : compartments) {

            Integer compartmentId = (Integer) row[0];
            Integer productId = (Integer) row[1];
            String compartmentCode = (String) row[2];
            Integer currentQuantity = (Integer) row[3];
            String batchCode = (String) row[4];
            Integer shelfQnt = (Integer) row[5];
            Date expiredDate = (Date) row[6];
            Date manufactureDate = (Date) row[7];
            ProductIdCategoryNameDto proNameCateId = null;

            String categoryName= null;
            if (productId != null) {
                proNameCateId = productService.getNameAndCategoryId(Long.valueOf(productId));
                categoryName = categoryService.getName(Long.valueOf(proNameCateId.getCategoryId()));
            }

            CompartmentResponse temp = CompartmentResponse.builder()
                    .compartmentId(compartmentId)
                    .tierId(Integer.valueOf(tierId))
                    .productId(productId)
                    .compartmentCode(compartmentCode)
                    .currentQuantity(currentQuantity)
                    .productName(proNameCateId == null ? null : proNameCateId.getProductName())
                    .categoryName(categoryName)
                    .batchCode(batchCode)
                    .shelfQnt(shelfQnt)
                    .expiredDate(expiredDate)
                    .manufactureDate(manufactureDate)
                    .build();
            responses.add(temp);
        }
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(responses)
                .build();
    }


    public float calculateInUse(Long tierId) {
        float inUse;
        Integer count = 0;
        List<Object[]> compartments = repo.getCurrentQuantityOfCompartmentByTierId(tierId);
        for (Object[] row : compartments) {
            Integer compartmentId = (Integer) row[0];
            Integer current_quantity = (Integer) row[1];
            if (current_quantity == 0) {
                count++;
            }
        }

        return ((float) count / compartments.size()) * 100;
    }

    public Integer getCurrentQuantityByShelfId(Long shelfId){
        Integer quantity = repo.getCurrentQuantityByShelfId(shelfId);
        return quantity;
    }

    @Override
    public ReturnResponse clear(Long compartmentId) {

        Compartment compartment = repo.findById(compartmentId).orElseThrow(()->new UserNotFoundException("Khong tim thay compartment"));
        Long productId = compartment.getProduct().getId();
        Integer shelfQnt = productService.getShelfQuantity(productId);
        Integer subQnt = shelfQnt - compartment.getCurrentQuantity();
        boolean resultUpdate = productService.updateShelfQuantity(subQnt,productId);
        if(resultUpdate){
            compartment.setCurrentQuantity(0);
            repo.save(compartment);
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.OK)
                    .data("Clear compartment quantity successfully")
                    .build();
        }else{
            productService.updateShelfQuantity(shelfQnt, productId);
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data("Can not clear compartment")
                    .build();
        }
    }

    @Override
    public void createCompartmentList(Tier tier, Integer numberOfCompartment) {
        for(int i = 0; i < numberOfCompartment; i++){
            String compartmentCode = "CC".concat(i < 10 ? "0".concat(String.valueOf(i)): String.valueOf(i));
            Compartment compartment = Compartment.builder()
                    .compartmentCode(compartmentCode)
                    .product(null)
                    .currentQuantity(0)
                    .tier(tier)
                    .deletedFlag(false).build();
            repo.save(compartment);
        }
    }

    @Override
    public ProductIdCurrentQnt getProductInCompartment(Integer tierId, String compartmentCode) {
        ProductIdCurrentQnt toReturn = ProductIdCurrentQnt.builder()
                .productId(-1)
                .currentQuantity(0)
                .build();
        List<Object[]> check = new ArrayList<>();
        try{
            check = repo.checkCompartment(tierId,compartmentCode);
        }catch (Exception e){
            throw new UserIDNotFoundException(e.getMessage());
        }

        if(!check.isEmpty()){
            toReturn.setProductId((Integer)check.get(0)[0]);
            toReturn.setCurrentQuantity((Integer)check.get(0)[1]);
        }
        return toReturn;
    }
}
