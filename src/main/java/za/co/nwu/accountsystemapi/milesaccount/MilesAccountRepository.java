package za.co.nwu.accountsystemapi.milesaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MilesAccountRepository extends JpaRepository<MilesAccount, Integer> {

    @Query("SELECT a FROM MilesAccount a WHERE a.userId = :userId")
    Optional<MilesAccount> findMilesAccountByUserId(@Param("userId") int userId);

}
