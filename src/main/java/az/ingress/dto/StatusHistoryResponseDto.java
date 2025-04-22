package az.ingress.dto;

import az.ingress.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistoryResponseDto {
    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private Long creditId;
}
