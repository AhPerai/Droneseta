package udesc.dsw.droneseta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udesc.dsw.droneseta.model.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin getAdminByEmail(String email);
}
