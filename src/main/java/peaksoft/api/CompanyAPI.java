package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.CompanyService;

import java.util.List;



@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Tag(name = "Company API")
public class CompanyAPI {
    private final CompanyService companyService;
    @PermitAll
    @GetMapping
    public List<CompanyResponse> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveCompany( @RequestBody @Valid CompanyRequest companyRequest){
        return companyService.saveCompany(companyRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/get/{compID}")
    public CompanyResponse getCompanyById(@PathVariable Long compID){
        return companyService.getCompanyById(compID);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/put/{compId}")
    public SimpleResponse updateCompany(@PathVariable Long compId,
                                        @RequestBody @Valid CompanyRequest companyRequest){
        return companyService.updateCompany(compId,companyRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{compId}")
    public SimpleResponse deleteCompany(@PathVariable Long compId){
        return companyService.deleteCompany(compId);
    }


}
