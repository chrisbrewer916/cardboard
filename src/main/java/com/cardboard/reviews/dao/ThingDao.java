package com.cardboard.reviews.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.SessionFactory;

import com.cardboard.reviews.model.Thing;

import io.dropwizard.hibernate.AbstractDAO;

public class ThingDao extends AbstractDAO<Thing> {

	public ThingDao(SessionFactory factory) {
		super(factory);
	}
	
	public Optional<Thing> findById(UUID id) {
        return Optional.ofNullable(get(id));
    }

    public Thing create(Thing thing) {
        return persist(thing);
    }

    @SuppressWarnings("unchecked")
	public List<Thing> findAll() {
        return list(namedQuery("com.cardboard.reviews.model.Thing.findAll"));
    }

}
