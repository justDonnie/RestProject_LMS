package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.GroupRequest;
import peaksoft.dto.GroupResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
@Tag(name = "Group API")
public class GroupAPI {

    private final GroupService groupService;

    @PermitAll
    @GetMapping("/{courseId}")
    public List<GroupResponse> getAllGroups(@PathVariable Long courseId){
        return groupService.getAllGroups(courseId);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/{courseId}")
    public SimpleResponse saveGroup(@RequestBody GroupRequest groupRequest,@PathVariable Long courseId){
        return groupService.saveGroup(groupRequest,courseId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/get/{grpId}")
    public GroupResponse findGroupById(@PathVariable Long grpId){
        return groupService.getGroupById(grpId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/update/{courseId}/{grpId}")
    public SimpleResponse updateGroupByCourseId(@PathVariable Long courseId,
                                                @PathVariable Long grpId,
                                                @RequestBody GroupRequest groupRequest){
        return groupService.updateGroup(courseId,grpId,groupRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/delete/{grpId}")
    public SimpleResponse deleteGroupByCourseId(@PathVariable Long grpId){
        return groupService.deleteGroup(grpId);
    }


}
