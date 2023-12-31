package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.dto.response.TierInfoResponse;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.model.Shelf;
import com.project.supermarket_be.domain.model.Tier;
import com.project.supermarket_be.domain.repository.TierRepo;
import com.project.supermarket_be.domain.service.CompartmentService;
import com.project.supermarket_be.domain.service.TierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TierServiceImpl implements TierService {
    private final TierRepo repo;
    private final CompartmentService compartmentService;

    @Override
    public ReturnResponse getAllTierByShelfId(String shelfId) {
        Long convShelfId = Long.valueOf(shelfId);
        List<Tier> tiers = repo.getAllTierByShelfId(convShelfId);
        List<TierInfoResponse> responses = new ArrayList<>();

        for (Tier tier : tiers) {
            float inUse = compartmentService.calculateInUse(tier.getId());
            responses.add(TierInfoResponse.builder()
                    .tierCode(tier.getTierCode())
                    .tierId(tier.getId())
                    .shelfId(convShelfId)
                    .inUse(inUse)
                    .build());
        }
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(responses)
                .build();
    }

    @Override
    public void createTierByShelfId(Shelf shelf, List<TierInCreateShelfRequest> tiers) {
        for(int i = 0; i < tiers.size(); i++){
            String tierCode = "TC".concat(i < 10 ? "0".concat(String.valueOf(i)) : String.valueOf(i));
            Tier tier = Tier.builder()
                    .tierCode(tierCode)
                    .shelf(shelf)
                    .deletedFlag(false)
                    .build();
            Tier tierSave = repo.save(tier);
            try{
                compartmentService.createCompartmentList(tierSave, tiers.get(i).getNumberOfCompartment());
            }catch (Exception e){
                repo.delete(tierSave);
            }
        }
    }
}
