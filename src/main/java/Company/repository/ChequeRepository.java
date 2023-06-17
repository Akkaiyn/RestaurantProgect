package Company.repository;

import Company.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {

}
