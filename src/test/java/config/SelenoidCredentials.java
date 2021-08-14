package config;

import org.aeonbits.owner.Config;

public interface SelenoidCredentials extends Config {
    String login();
    String password();
}
