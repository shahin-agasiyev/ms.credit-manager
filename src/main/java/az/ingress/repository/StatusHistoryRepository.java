package az.ingress.repository;

import az.ingress.constant.Status;
import az.ingress.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {
}
