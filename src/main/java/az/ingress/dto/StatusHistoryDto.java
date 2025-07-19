package az.ingress.dto;

import az.ingress.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusHistoryDto {
    private Status status;
    private LocalDateTime createdAt;
    private Long creditId;
}
