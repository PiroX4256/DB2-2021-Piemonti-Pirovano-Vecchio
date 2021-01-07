package it.polimi.db2.db2project.services;

import org.springframework.stereotype.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Stateless
public class ProductService {
    @PersistenceContext
    private EntityManager em;

    public Product
}
