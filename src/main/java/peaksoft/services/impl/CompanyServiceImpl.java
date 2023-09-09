package peaksoft.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.AlreadyExistException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Company;
import peaksoft.repositories.CompanyRepository;
import peaksoft.services.CompanyService;

import java.time.ZonedDateTime;
import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public SimpleResponse saveCompany(CompanyRequest companyRequest) {
        if (companyRequest.getName()==null){
            Company company = new Company();
            company.setName(companyRequest.getName());
            company.setCountry(companyRequest.getCountry());
            company.setAddress(companyRequest.getAddress());
            company.setPhoneNumber(companyRequest.getPhoneNumber());
            company.setCreatedAt(ZonedDateTime.now());
            companyRepository.save(company);
            log.info("Company successfully saved !!!");
        }else {
            throw new AlreadyExistException("Company is already exist !!!");
        }
            return new SimpleResponse(
                    HttpStatus.OK,
                    "Company with ID %s is successfully saved !!!"
            );
    }
    @Override
    public List<CompanyResponse> getAllCompanies(){
        return companyRepository.getAllCompanies();
    }

    @Override
    public CompanyResponse getCompanyById(Long compId) {
        return companyRepository.getCompanyById(compId)
                .orElseThrow(()->{
                    String massage = "Company with Id: " + compId + " is not found!";
                    log.error(massage);
                    return   new NotFoundException(massage);
                });
    }

    @Override
    public SimpleResponse updateCompany(Long compId, CompanyRequest newCompanyRequest) {
        Company company = companyRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Company with ID " + compId + " is not found!!!"));
        company.setName(newCompanyRequest.getName());
        company.setCountry(newCompanyRequest.getCountry());
        company.setAddress(newCompanyRequest.getAddress());
        company.setPhoneNumber(newCompanyRequest.getPhoneNumber());
        company.setUpdatedAt(ZonedDateTime.now());
        log.info(" Company is successfully is updated!!!");
        companyRepository.save(company);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Company with ID "+company.getId()+" is successfully updated !!!")
        );
    }

    @Override
    public SimpleResponse deleteCompany(Long compId) {
        if (!companyRepository.existsById(compId)){
            throw new NotFoundException("Company with ID " + compId + " is not found!!!");
        }
        companyRepository.deleteById(compId);
        log.info(" Company is successfully deleted!!!");
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Company with ID "+compId+" is successfully deleted !!!")
        ) ;
    }

}
