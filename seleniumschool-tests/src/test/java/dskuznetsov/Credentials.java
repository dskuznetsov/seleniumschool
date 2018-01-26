package dskuznetsov;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:Credentials.properties"})
public interface Credentials extends Config {
    @Key("LOGIN")
    String LOGIN();

    @Key("PASSWORD")
    String PASSWORD();
}