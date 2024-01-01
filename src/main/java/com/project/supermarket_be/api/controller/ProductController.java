package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.parameter.GetAllProductParam;
import com.project.supermarket_be.api.dto.request.AddProductToShelfRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ReturnResponse> getAllProduct(
            @RequestParam(name = "page-number", defaultValue = "0") int pageNumber,
            @RequestParam(name = "limit", defaultValue = "100") int limit,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "from", defaultValue = "") String from,
            @RequestParam(name = "to", defaultValue = "") String to) {
//        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String fromDate = null;
//        String toDate = null;
//        try {
//            Date date = inputFormat.parse(from);
//            fromDate = outputFormat.format(date);
//            date = inputFormat.parse(to);
//            toDate = outputFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace(); // Handle the exception appropriately
//        }

        GetAllProductParam param = GetAllProductParam.builder()
                .pageNumber(pageNumber)
                .search(search)
                .limit(limit)
                .from(from)
                .to(to)
                .build();
        ReturnResponse response = productService.getAllProductPaging(param);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ReturnResponse> getProductDetail(@PathVariable String productId) {
        ReturnResponse response = productService.getProductById(productId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

//    @PostMapping("/add-to-shelf")
//    public ResponseEntity<ReturnResponse> addProductToShelf(@RequestBody AddProductToShelfRequest request){
////        ReturnResponse response = productService.
//    }
}
