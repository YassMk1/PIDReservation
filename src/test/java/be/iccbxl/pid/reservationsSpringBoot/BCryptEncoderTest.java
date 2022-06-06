package be.iccbxl.pid.reservationsSpringBoot;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

  @Test
  public void encrypt() {
    String password = "secret";
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = bCryptPasswordEncoder.encode(password);
    System.out.println(encodedPassword);
  }
}
