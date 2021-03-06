package instantcoffee.cinemaxx.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import instantcoffee.cinemaxx.entities.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column
  @NonNull
  private String email;

  @Column
  @NonNull
  private String username;

  @Column
  @NonNull
  private String password;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private Set<Booking> bookings = new HashSet<>();

  // TODO(v3s1e): Implement roles
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  /*
   * We (I) do not care about these :)
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
  /* ----------------------------------------------------------------------- */

  @Override
  public String toString() {
    return "User [id=" + id + ", password=" + password + ", username=" + username + ", email=" + email + "]";
  }

}
