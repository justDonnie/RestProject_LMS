package peaksoft.dto;

import lombok.Builder;

@Builder
public record InstructorsFullInfo(
        Long id,
        String name,
        String phoneNumber,
        String groupName,
        int numberOfStudent
) {
}
