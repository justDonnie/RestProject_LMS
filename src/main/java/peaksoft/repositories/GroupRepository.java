package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.GroupResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.models.Group;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select new peaksoft.dto.GroupResponse(g.id,g.groupName,g.imageLink,g.description,g.createdAt,g.updatedAt) from Course c join c.groups g where c.id=:courseId")
    List<GroupResponse> getAllGroups(Long courseId);

    @Query("select new peaksoft.dto.GroupResponse(g.id,g.groupName,g.imageLink,g.description,g.createdAt,g.updatedAt) from Group g where g.id=:groupId")
    Optional<GroupResponse> getGroupById(Long groupId);

}
