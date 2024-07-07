package guru.qa.service;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Base64PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String password) {
        return Base64.getEncoder().encodeToString(
                password.getBytes(UTF_8)
        );
    }

    @Override
    public String decode(String password) {
        return new String(
                Base64.getDecoder().decode(
                        password.getBytes(UTF_8)
                )
        );
    }
}
