package party.controller;

import org.springframework.stereotype.Service;
import party.data.Party;
import party.repository.PartyRepository;

import java.util.List;
import java.util.Map;

@Service
public class PartyService {
  private final PartyRepository partyRepository;

  public PartyService(PartyRepository partyRepository) {
    this.partyRepository = partyRepository;
  }

  public List<Party> findAll(){
    return partyRepository.findAll();
  }

  public Party findByPartyId(Long partyId) {
    return partyRepository.findByPartyId(partyId);
  }

  public Party createParty(Party party) {
    return partyRepository.save(party);
  }

  public void deleteParty(Long partyId) {
    partyRepository.delete(findByPartyId(partyId));
  }

  public Party updateParty(Party party, Long partyId) {
    return partyRepository.save(party);
  }

  public Party updateParty(Map<String, String> updates, Long partyId) {
    return null;
  }
}
