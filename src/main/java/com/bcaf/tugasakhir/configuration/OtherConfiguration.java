package com.bcaf.tugasakhir.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@PropertySource("classpath:other.properties")
public class OtherConfiguration {

    private static String flagLoging;
    private static String flagLogTable;

    private static String flagSmtpActive;

    private static String flagPwdTrap;

    public static String getFlagPwdTrap() {
        return flagPwdTrap;
    }

    @Value("${flag.pwd.trap}")
    private void setFlagPwdTrap(String flagPwdTrap) {
        OtherConfiguration.flagPwdTrap = flagPwdTrap;
    }

    public static String getFlagSmtpActive() {
        return flagSmtpActive;
    }

    @Value("${flag.smtp.active}")
    private void setFlagSmtpActive(String flagSmtpActive) {
        OtherConfiguration.flagSmtpActive = flagSmtpActive;
    }

    public static String getFlagLogTable() {
        return flagLogTable;
    }
    @Value("${flag.log.table}")
    private void setFlagLogTable(String flagLogTable) {
        OtherConfiguration.flagLogTable = flagLogTable;
    }

    public static String getFlagLoging() {
        return flagLoging;
    }
    @Value("${flag.logging}")
    private void setFlagLoging(String flagLoging) {
        OtherConfiguration.flagLoging = flagLoging;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}