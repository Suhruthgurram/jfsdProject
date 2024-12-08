package com.example.farmingapp.service;

import com.example.farmingapp.entity.Crop;
import com.example.farmingapp.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    // Fetch all crops
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    // Add a new crop
    public void addCrop(Crop crop) {
        cropRepository.save(crop);
    }

    // Fetch a crop by its ID
    public Crop getCropById(Long id) {
        return cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with ID: " + id));
    }

    // Update an existing crop
    public void updateCrop(Long id, Crop updatedCrop) {
        Crop existingCrop = getCropById(id); // Fetch the existing crop
        existingCrop.setCropName(updatedCrop.getCropName());
        existingCrop.setCropType(updatedCrop.getCropType());
        existingCrop.setPricePerKg(updatedCrop.getPricePerKg());
        cropRepository.save(existingCrop);
    }

    // Delete a crop by its ID
    public void deleteCrop(Long id) {
        if (!cropRepository.existsById(id)) {
            throw new RuntimeException("Crop not found with ID: " + id);
        }
        cropRepository.deleteById(id);
    }
}
