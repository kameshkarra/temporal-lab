package party.controller;

import org.springframework.web.bind.annotation.*;
import party.data.Party;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PartyData")
public class PartyController {

  private final PartyService partyService;

  public PartyController(PartyService PartyService) {
    this.partyService = PartyService;
  }

  @GetMapping
  public List<Party> findAllParties() {
    return partyService.findAll();
  }

  @GetMapping("/{PartyId}")
  public Party findParty(@PathVariable Long PartyId) {
    return partyService.findByPartyId(PartyId);
  }

  @PostMapping
  public Party createParty(@RequestBody Party Party) {
    return partyService.createParty(Party);
  }

  @DeleteMapping("/{PartyId}")
  public void deleteParty(@PathVariable Long PartyId) {
    partyService.deleteParty(PartyId);
  }

  @PutMapping("/{PartyId}")
  public Party updateParty(@RequestBody Party Party, @PathVariable Long PartyId) {
    return partyService.updateParty(Party, PartyId);
  }

  @PatchMapping("/{PartyId}")
  public Party updateParty(
      @RequestBody Map<String, String> updates, @PathVariable Long PartyId) {
    return partyService.updateParty(updates, PartyId);
  }
}
