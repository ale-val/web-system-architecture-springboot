package com.example.assignment.repository;

import com.example.assignment.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RepositoryPost extends CrudRepository<Post, Long> {

    public Collection<Post> findAll();

    @Query("SELECT p FROM Post p ORDER BY p.creazione DESC LIMIT :limit OFFSET :offset")
    Collection<Post> findPostsWithLimit(@Param("limit") int limit, @Param("offset") int offset);

}
