package com.jaygame.search.Dao;

import com.jaygame.search.pojo.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GameDao extends ElasticsearchRepository<Game, String> {

    public Page<Game> findGamesByTitleLikeOrSummaryLikeOrDetailLikeOrSpecsLike(
            String title, String summary, String specs, String detail, Pageable pageable
    );

}
