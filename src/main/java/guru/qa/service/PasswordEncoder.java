package guru.qa.service;

public interface PasswordEncoder {

    String encode(String password);

    String decode(String password);

    class MockPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(String password) {
            return password;
        }

        @Override
        public String decode(String password) {
            return password;
        }
    }
}
