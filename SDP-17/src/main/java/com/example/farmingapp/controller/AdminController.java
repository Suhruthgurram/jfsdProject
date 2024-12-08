package com.example.farmingapp.controller;

import com.example.farmingapp.entity.Admin;
import com.example.farmingapp.entity.Crop;
import com.example.farmingapp.service.AdminService;
import com.example.farmingapp.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CropService cropService;

    // Admin Login
    @GetMapping("/admin/login")
    public String showAdminLoginPage() {
        return "admin_login"; // Renders admin_login.html
    }

    @PostMapping("/admin/login")
    public String loginAdmin(String email, String password) {
        // Implement login logic here
        return "admin_dashboard"; // Redirect to dashboard
    }

    // Admin Signup
    @GetMapping("/admin/signup")
    public String showAdminSignupPage() {
        return "admin_signup"; // Renders admin_signup.html
    }

    @PostMapping("/admin/signup")
    public String registerAdmin(Admin admin) {
        adminService.registerAdmin(admin);
        return "admin_login"; // Redirect to login page after signup
    }

    // Dashboard showing list of crops
    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("crops", cropService.getAllCrops()); // Fetch all crops
        return "admin_dashboard"; // Renders admin_dashboard.html
    }

    // Add Crop
    @GetMapping("/crop/add")
    public String showAddCropPage(Model model) {
        model.addAttribute("crop", new Crop()); // Empty Crop object for form binding
        return "add_crop"; // Renders add_crop.html
    }

    @PostMapping("/crop/add")
    public String addCrop(Crop crop) {
        cropService.addCrop(crop); // Add new crop
        return "redirect:/dashboard"; // Redirect to dashboard
    }

    // Edit Crop
    @GetMapping("/crop/edit/{id}")
    public String showEditCropPage(@PathVariable Long id, Model model) {
        Crop crop = cropService.getCropById(id); // Fetch crop by ID
        model.addAttribute("crop", crop); // Add crop to model for form binding
        return "edit_crop"; // Renders edit_crop.html
    }

    @PostMapping("/crop/edit/{id}")
    public String updateCrop(@PathVariable Long id, Crop crop) {
        cropService.updateCrop(id, crop); // Update crop
        return "redirect:/dashboard"; // Redirect to dashboard
    }

    // Delete Crop
    @PostMapping("/crop/delete/{id}")
    public String deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id); // Delete crop by ID
        return "redirect:/dashboard"; // Redirect to dashboard
    }
}
