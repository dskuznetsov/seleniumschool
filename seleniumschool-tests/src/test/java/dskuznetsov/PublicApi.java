package dskuznetsov;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:PublicApi.properties"})
public interface PublicApi extends Config {
    @Key("xauthorization")
    String xauthorization();
}