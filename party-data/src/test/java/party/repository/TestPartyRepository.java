package party.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import party.data.Party;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TestPartyRepository {

  @Autowired
  private PartyRepository partyRepository;

  @Test
  @Transactional
  public void insert() {
    long partyId = 1;
    Party party = new Party(partyId,
        "USA",
        "a@a.com",
        "999-999-9999",
        LocalDate.of(1999, 9, 9),
        "USA",
        "999-88-7777"
    );
    Party savedParty = partyRepository.save(party);
    assertNotNull(savedParty);
    assertNotNull(partyRepository.findByPartyId(partyId));
  }
}
