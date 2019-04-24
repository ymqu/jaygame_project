
<template>
<div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-4 column">
                <img :src="info.game_icon"  alt="" width=100% height=100%>
			</div>
            <div class="col-md-8 column" style="color: white">
                <dl>
				<dt>Title</dt>
				<dd>{{info.game_title}}</dd>
                <dt>Summary</dt>
				<dd>{{info.game_summary}}</dd>
                <dt>specs</dt>
				<dd>{{info.game_specs}}</dd>
                <dt>price</dt>
				<dd>{{info.game_price}}</dd>
                <dt>download</dt>
				<dd>{{info.game_download_link}}</dd>	
			</dl>
            </div>
        </div>
        <hr>
         <div>
          <h1 class="h1" style="font-size:medium">Discription:</h1>
          <h1 class="h1" style="font-size:small">{{info.game_details}}</h1>
        </div>
        <hr>

	
			<!-- <div class="row mui-input-row">
				<textarea id='question' class="mui-input-clear question" placeholder="write your opinion..." v-model="msg"></textarea>
			</div> -->
            <textarea placeholder="your opinion" maxlength="120" v-model="msg"></textarea>

            <star-rating v-bind:increment="1"
                v-bind:max-rating="5"
                inactive-color="white"
                active-color="blue"
                v-bind:star-size="20"
                @rating-selected ="setRating">
            </star-rating>
        
             <button type="submit" class="btn btn-primary" style="float:right" @click="postComments">Submit</button>

            


        <div class="cmt-container">
          <h1 class="h1" style="font-size:medium">Comments:</h1>

          <div class="cmd-list" style="background-color: white; margin:10px 0">
            <div class="cmd-item" v-for="(item, i) in comments" :key="item._id">
                <div class="cmd-title" style="background-color: #ccc; line-height:30px">
                    {{i+1}}&nbsp;&nbsp; username:{{item.nickname}}&nbsp;&nbsp;{{item.publishtime | dateFormat}}
                </div>
                <div class="cmd-body" style="line-height:35px; text-indent:2em">
                   {{item.content}}
                </div>
            </div>
          </div>

        </div>

         <button type="submit" class="btn btn-primary" style="float:middle" @click="loadMore">Load More</button>
    </div>
      
</div>
</template>

<script>
  import {Toast} from "mint-ui";

export default {

    data(){
        return{
            info:[],
            comments:[],
            game:"",
            pageIndex:1,
            msg:'',
            totalPage:1,
            rating:0,
            username:"",
        }
        
    },

    methods:{

        setRating(rating){
            this.rating=rating;
        },

        getComments(){
            //  this.$http.get("http://localhost:9001/comment/comments/" + this.game +
              this.$http.get("http://23.254.203.100:9001/comment/comments/" + this.game +
         "?pageindex=" + this.pageIndex).then(
             result =>{
                 if(result.body.code ===20000){
                     this.totalPage = result.body.data.total;
                     this.comments = this.comments.concat(result.body.data.row);
                     
                 }else{
                     Toast("comments failed");
                 }
             }
         )
        },
        loadMore(){
            this.pageIndex++;
            if(this.pageIndex <= this.totalPage ){
                    this.getComments();
            }
            
        },

        postComments(){
            
            if(this.msg.trim().length ===0){
                Toast("comment can not be null");
            }
           // this.$http.post("http://localhost:9001/comment/postcomment/" + this.game,{
            this.$http.post("http://23.254.203.100:9001/comment/postcomment/" + this.game,{

                content: this.msg.trim(),
                publishtime: Date.now(),
                thumbup: this.rating,
                }).then(result =>{
                    if(result.body.code ===20000){
                        this.username = result.body.data;
                        var cmt ={nikename:this.username, publishtime:Date.now(), content:this.msg.trim()};
                        this.comments.unshift(cmt);
                        this.msg='';
                    }
                })
        }
    },

    created(){
         this.game = this.$route.query.game_id;
         //this.$http.get("http://localhost:9001/game/detail", {
             this.$http.get("http://23.254.203.100:9001/game/detail", {
                   params:{game_id: this.game}
                }). then(result =>{
                    if(result.body.code ===20000){
                        this.info = result.body.data;
                    }else{
                        Toast("Wrong............")
                    }
                })

        this.getComments();
       
    }
    
}
</script>

<style lang="scss" scoped>
    .h1{
        color: rgb(241, 226, 226)};
  
</style>