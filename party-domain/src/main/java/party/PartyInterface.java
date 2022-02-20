package party;

import java.time.LocalDate;

public interface PartyInterface {
  Long getPartyId();

  void setPartyId(Long partyId);

  String getCountry();

  void setCountry(String country);

  String getEmail();

  void setEmail(String email);

  String getPhone();

  void setPhone(String phone);

  LocalDate getDob();

  void setDob(LocalDate dob);

  String getCitizenship();

  void setCitizenship(String citizenship);

  String getNationalId();

  void setNationalId(String nationalId);
}
