package com.jaygame.base.dao;

import com.jaygame.base.pojo.Game;
import com.jaygame.base.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameDao extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {

}
