package com.nab.se.db.services;

import com.nab.se.db.components.TestComponent;
import com.nab.se.db.domains.Adviser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    @Autowired
    TestComponent testComponent;

    public Adviser getAdviser(int adviserId) {
        return testComponent.getAdviser(adviserId);
    }
}
