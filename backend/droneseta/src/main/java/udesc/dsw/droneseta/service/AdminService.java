package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udesc.dsw.droneseta.model.entity.Admin;
import udesc.dsw.droneseta.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Admin getAdminById(Long idAdmin){
        return adminRepository.findById(idAdmin).get();
    }

}
