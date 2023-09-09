package peaksoft.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.GroupRequest;
import peaksoft.dto.GroupResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Course;
import peaksoft.models.Group;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.GroupRepository;
import peaksoft.services.GroupService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;


    @Override
    public SimpleResponse saveGroup(GroupRequest groupRequest,Long courseId){
        Course course = courseRepository.findCourseById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with ID"+courseId+" is not found!!!"));
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        group.setCreatedAt(ZonedDateTime.now());
        course.getGroups().add(group);
        groupRepository.save(group);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Group with ID %s is successfully saved!!!",group.getId())
        );
    }

    @Override
    public List<GroupResponse> getAllGroups(Long courseId){
        return groupRepository.getAllGroups(courseId);
    }

    @Override
    public GroupResponse getGroupById(Long grpId) {
        return groupRepository.getGroupById(grpId)
                .orElseThrow(()->new NotFoundException("Group with ID " + grpId + " is not found !!!"));
    }

    @Override
    public SimpleResponse updateGroup(Long grpId, Long courseId, GroupRequest newGroupRequest) {
        Course course = courseRepository.findCourseById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with ID" + courseId + " is not found!!!"));
        Group group = groupRepository.findById(grpId)
                .orElseThrow(() -> new NotFoundException(" Group with ID " + grpId + " is not found !!!"));
     //   course.getGroups().add(group); don't add this one!
        group.getCourses().add(course);

        group.setGroupName(newGroupRequest.getGroupName());
        group.setImageLink(newGroupRequest.getImageLink());
        group.setDescription(newGroupRequest.getDescription());
        group.setUpdatedAt(ZonedDateTime.now());
        groupRepository.save(group);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Group with ID %s is successfully updated !!!",group.getId())
        );
    }

    @Override
    public SimpleResponse deleteGroup(Long grpId) {
        Group group = groupRepository.findById(grpId).orElseThrow(() -> new NotFoundException("Not found group with ID: " + grpId));
        List<Course> courses = new ArrayList<>(group.getCourses());
        for(Course c:courses){
            group.getCourses().remove(c);
            c.getGroups().remove(group);
        }
        groupRepository.delete(group);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Group with ID "+group.getId()+" is successfully deleted!!!")
        );
    }

   // write a method to delete group from courses
}
