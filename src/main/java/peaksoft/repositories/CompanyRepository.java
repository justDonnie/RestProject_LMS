package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CompanyResponse;
import peaksoft.models.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select new peaksoft.dto.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt) from Company c")
    List<CompanyResponse> getAllCompanies();

    Optional<CompanyResponse> getCompanyById(Long id);


}


