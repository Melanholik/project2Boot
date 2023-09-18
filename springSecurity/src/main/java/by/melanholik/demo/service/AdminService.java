package by.melanholik.demo.service;

import by.melanholik.demo.repository.AdminRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void adminDoing() {
        adminRepository.doSomeThing();
    }
}
