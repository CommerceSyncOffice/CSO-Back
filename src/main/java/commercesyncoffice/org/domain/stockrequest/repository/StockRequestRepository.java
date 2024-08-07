package commercesyncoffice.org.domain.stockrequest.repository;

import commercesyncoffice.org.domain.stockrequest.model.StockRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRequestRepository extends JpaRepository<StockRequest, Long> {

}
