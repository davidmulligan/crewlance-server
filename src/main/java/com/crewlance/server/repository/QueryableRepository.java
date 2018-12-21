package com.crewlance.server.repository;

import com.crewlance.server.model.DomainObject;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface QueryableRepository<T extends DomainObject> extends CrudRepository<T, String>, QuerydslPredicateExecutor<T> {
}