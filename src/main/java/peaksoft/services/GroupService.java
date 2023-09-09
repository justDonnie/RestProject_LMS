package peaksoft.services;

import peaksoft.dto.GroupRequest;
import peaksoft.dto.GroupResponse;
import peaksoft.dto.SimpleResponse;

import java.util.List;

public interface GroupService {

    SimpleResponse saveGroup(GroupRequest groupRequest,Long courseId);

    List<GroupResponse> getAllGroups(Long courseId);

    GroupResponse getGroupById(Long grpId);

    SimpleResponse updateGroup(Long grpId,Long courseId, GroupRequest newGroupRequest);

    SimpleResponse deleteGroup(Long grpId);
}
