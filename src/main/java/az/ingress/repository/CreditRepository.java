package az.ingress.repository;

import az.ingress.constant.Status;
import az.ingress.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findAllByStatus(Status status);

    List<Credit> findByCheckDateAfter(LocalDateTime checkDateAfter);
}
