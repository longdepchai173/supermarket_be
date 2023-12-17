package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CreateProviderRequest;
import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CanNotFoundProvider;
import com.project.supermarket_be.api.exception.customerException.CanNotUploadImage;
import com.project.supermarket_be.domain.model.Provider;
import com.project.supermarket_be.domain.repository.ProviderRepo;
import com.project.supermarket_be.domain.service.ProviderService;
import com.project.supermarket_be.domain.service.UploadImgService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepo repo;
    private final UploadImgService uploadImgService;
    @Override
    public Provider findById(Long id) {
        return repo.findById(id).orElseThrow(()->new CanNotFoundProvider(String.valueOf(id)));
    }

    @Override
    public ReturnResponse createProvider(CreateProviderRequest request) {

        String safetyInspectionUrl;
        try{
            safetyInspectionUrl = uploadImgService.uploadBase64Image(request.getSafetyInspection());
        }catch (Exception e){
            throw new CanNotUploadImage(request.getSafetyInspection());
        }

        Provider provider = Provider.builder()
                .name(request.getName())
                .contactPerson(request.getContactPerson())
                .emailContact(request.getEmailContact())
                .phoneContact(request.getPhoneContact())
                .note(request.getNote())
                .address(request.getAddress())
                .deletedFlag(false)
                .safetyInspection(safetyInspectionUrl)
                .build();
        Provider providerSaved = repo.save(provider);

        return ReturnResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .data(providerSaved)
                .build();
    }

    @Override
    public ReturnResponse update(CreateProviderRequest request, String id) {
        Long providerId = Long.valueOf(id);
        Provider provider = repo.findById(providerId).orElseThrow(()->new CanNotFoundProvider(id));
        provider.setContactPerson(request.getContactPerson() != null ? request.getContactPerson() : provider.getContactPerson());
        provider.setPhoneContact(request.getPhoneContact() != null ? request.getPhoneContact() : provider.getPhoneContact());
        provider.setEmailContact(request.getEmailContact() != null ? request.getEmailContact() : provider.getEmailContact());
        provider.setAddress(request.getAddress() != null ? request.getAddress() : provider.getAddress());
        provider.setSafetyInspection(request.getSafetyInspection() != null ? request.getSafetyInspection() : provider.getSafetyInspection());
        provider.setNote(request.getNote() != null ? request.getNote() : provider.getNote());
        provider.setDeletedFlag(false);

        provider.setName(request.getName() != null ? request.getName(): provider.getName());
        if(request.getSafetyInspection() != null){
            String safetyInspectionUrl;
            try{
                safetyInspectionUrl = uploadImgService.uploadBase64Image(request.getSafetyInspection());
            }catch (Exception e){
                throw new CanNotUploadImage(request.getSafetyInspection());
            }
            provider.setSafetyInspection(safetyInspectionUrl);
        }
        Provider updateProvider = repo.save(provider);

        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(updateProvider)
                .build();
    }
}
