package az.ingress.repository;

import az.ingress.dto.OfferResponseDto;
import az.ingress.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByCreditId(Long creditId);
}
