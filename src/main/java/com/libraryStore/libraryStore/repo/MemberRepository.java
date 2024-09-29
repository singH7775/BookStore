package com.libraryStore.libraryStore.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libraryStore.libraryStore.models.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {	
	Member findByUsername(String username);	
}
