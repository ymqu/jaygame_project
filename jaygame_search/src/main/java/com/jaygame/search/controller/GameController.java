package com.jaygame.search.controller;

import com.jaygame.search.pojo.Game;
import com.jaygame.search.service.GameService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public Result save(@RequestBody Game game){
       // System.out.printf(game.getDetail()+"" +game.getSpecs());
        gameService.save(game);
        return new Result(true, StatusCode.OK,"success");
    }

    @GetMapping(value="/delete/{gameId}")
    public Result delete(@PathVariable String gameId){
        gameService.delete(gameId);
        return new Result(true, StatusCode.OK, "delete sucess!");
    }

    @GetMapping(value="/{key}/{page}/{size}")
    public Result findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size){
        //System.out.println(key +" " + page);
        Page<Game> pageDate = gameService.findByKey(key,page, size);
        if(pageDate.getTotalPages()==0){
            return new Result(false, StatusCode.ERROR, "nothing found");
        }
        return new Result(true, StatusCode.OK,"success", new PageResult<Game>(pageDate.getTotalElements(), pageDate.getContent()));

    }
}
