package com.jaygame.search.service;

import com.jaygame.search.Dao.GameDao;
import com.jaygame.search.pojo.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameDao gameDao;

    public void save(Game game){
        gameDao.save(game);
    }
    public void delete(String gameId){
        gameDao.deleteById(gameId);
    }

    public Page<Game> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
           return gameDao.findGamesByTitleLikeOrSummaryLikeOrDetailLikeOrSpecsLike(key, key, key, key,pageable);
    }
}
