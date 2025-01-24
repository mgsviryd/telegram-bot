package com.sviryd.telegram_bot.service;

import com.sviryd.telegram_bot.config.MessageSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageSourceResourceBundleService {
    @Autowired
    private MessageSourceConfig messageSourceConfig;

    public Set<String> getKeys(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(messageSourceConfig.getBasename(), locale);
        return new TreeSet<>(Collections.list(bundle.getKeys()));
    }
}