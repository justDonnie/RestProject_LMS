package peaksoft.services;

import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyResponse;
import peaksoft.dto.SimpleResponse;

import java.util.List;

public interface CompanyService {

    SimpleResponse saveCompany(CompanyRequest companyRequest);

    List<CompanyResponse> getAllCompanies();

    CompanyResponse getCompanyById(Long compId);

    SimpleResponse updateCompany(Long compId, CompanyRequest newCompanyRequest);

    SimpleResponse deleteCompany(Long compId);


}
