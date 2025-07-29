package com.example.assignment.repository;

import com.example.assignment.entity.Commenti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RepositoryComment extends CrudRepository<Commenti, Long> {

    @Query("SELECT c FROM Commenti c WHERE c.post_id = :postId ORDER BY c.creazione DESC LIMIT :limit OFFSET :offset")
    Collection<Commenti> findByPostId(@Param("postId") int postId, @Param("limit") int limit, @Param("offset") int offset);
}