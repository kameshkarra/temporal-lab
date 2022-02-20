package party.data;


import party.PartyInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "party")
public final class Party implements PartyInterface {
  @Id
  @Column(name = "party_id")
  private Long partyId;

  @Column(name = "country", length = 3)
  private String country;

  @Column(name = "email", length = 32)
  private String email;

  @Column(name = "phone", length = 12)
  private String phone;

  @Column(name = "dob")
  private LocalDate dob;

  @Column(name = "citizenship", length = 3)
  private String citizenship;

  @Column(name = "national_id", length = 11)
  private String nationalId;

  public Party() {

  }

  public Party(Long partyId,
               String country,
               String email,
               String phone,
               LocalDate dob,
               String citizenship,
               String nationalId) {
    this.partyId = partyId;
    this.country = country;
    this.email = email;
    this.phone = phone;
    this.dob = dob;
    this.citizenship = citizenship;
    this.nationalId = nationalId;
  }

  public Long partyId() {
    return partyId;
  }

  public String country() {
    return country;
  }

  public String email() {
    return email;
  }

  public String phone() {
    return phone;
  }

  public LocalDate dob() {
    return dob;
  }

  public String citizenship() {
    return citizenship;
  }

  public String nationalId() {
    return nationalId;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Party) obj;
    return Objects.equals(this.partyId, that.partyId) &&
        Objects.equals(this.country, that.country) &&
        Objects.equals(this.email, that.email) &&
        Objects.equals(this.phone, that.phone) &&
        Objects.equals(this.dob, that.dob) &&
        Objects.equals(this.citizenship, that.citizenship) &&
        Objects.equals(this.nationalId, that.nationalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, country, email, phone, dob, citizenship, nationalId);
  }

  @Override
  public String toString() {
    return "Party[" +
        "id=" + partyId + ", " +
        "country=" + country + ", " +
        "email=" + email + ", " +
        "phone=" + phone + ", " +
        "dob=" + dob + ", " +
        "citizenship=" + citizenship + ", " +
        "nationalId=" + nationalId + ']';
  }

  @Override
  public Long getPartyId() {
    return partyId;
  }

  @Override
  public void setPartyId(Long partyId) {
    this.partyId = partyId;
  }

  @Override
  public String getCountry() {
    return country;
  }

  @Override
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getPhone() {
    return phone;
  }

  @Override
  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public LocalDate getDob() {
    return dob;
  }

  @Override
  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  @Override
  public String getCitizenship() {
    return citizenship;
  }

  @Override
  public void setCitizenship(String citizenship) {
    this.citizenship = citizenship;
  }

  @Override
  public String getNationalId() {
    return nationalId;
  }

  @Override
  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }
}

