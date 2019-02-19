package com.jaygame.base.controller;

import com.jaygame.base.pojo.Comments;
import com.jaygame.base.service.CommentService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value="/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK, "search success", commentService.findById(id));
    }

    @GetMapping(value="/comments/{gameId}")
    public Result findByGameId(@PathVariable String gameId, @RequestParam("pageindex") String pageIndex){
        System.out.println("gameId" + gameId +" pageindex" + pageIndex);
        Page<Comments> pageData = commentService.findByGameId(gameId, Integer.parseInt(pageIndex), 2);
        //System.out.printf(pageData.toString());
        return new Result(true, StatusCode.OK, "search Sucess", new PageResult<Comments>(pageData.getTotalElements(), pageData.getContent()));
        //return new Result(true, StatusCode.OK, "search Sucess", new PageResult<Comments>());

    }

    @PostMapping("/postcomment/{gameId}")
    public Result save(@PathVariable String gameId, @RequestBody Comments comments){
        comments.setGameId(gameId);
        commentService.save(comments);
        return new Result(true, StatusCode.OK, "save success!");
    }


}
