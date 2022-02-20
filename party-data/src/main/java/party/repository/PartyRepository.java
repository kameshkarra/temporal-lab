package party.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import party.data.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

  public Party findByPartyId(Long partyId);
}
