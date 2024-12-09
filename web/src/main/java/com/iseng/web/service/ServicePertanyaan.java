package com.iseng.web.service;

import com.iseng.web.repository.RepositoriSoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePertanyaan {
    @Autowired
    private RepositoriSoal questionRepository;


}
