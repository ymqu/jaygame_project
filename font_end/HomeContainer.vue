<template>
   
   <div class="container-fluid">
        <div class="row">
            <div class="col-9">
              <mt-swipe :auto="4000">
                 <mt-swipe-item v-for="item in data" :key="item.game_id">
                    <img :src="item.game_icon"  alt="" width=100% height=100%>
                     </mt-swipe-item>
              </mt-swipe>     
            </div> 

<!--below is right side-->
            <div class="col-3">
            <p>This is part is for advertise</p>
        </div>
  </div>
<!--top games display-->
<div class="panel panel-primary" style="height:50px" display="inline-block">Toping Games</div>
<div class="panel-body">
  <ul class="mui-table-view mui-grid-view">
	<li class="mui-table-view-cell mui-media mui-col-xs-6" v-for="item in data" :key="item.game_id">
       
        <router-link :to="{path:'/game/gameDetail', query:{game_id: item.game_id}}">
            <img class="mui-media-object" :src="item.game_icon"  >
			<div class="mui-media-body">{{item.game_title}} </div>
        </router-link>    

	</li>
	
</ul>
 
  </div>
<div>
</div>
 <!-- </div>

        <router-view></router-view>
    </div> -->
<h3>this is Home container</h3>

</div>   
 

</template>

<script>

    import {Toast} from "mint-ui"

    export default{
        data(){
            return{
                data:[]
            }
        },
        methods:{
            allGame(){
                this.$http.get("http://23.254.203.100:9001/game/all").then(result =>{
                    if(result.body.code === 20000){
                        this.data = result.body.data;
                    }
                    if(result.body.code === 20006){
                        window.localStorage.removeItem('token');
                        
                        this.$router.push({path:"/home"});
                    }else{
                       // Toast("fail to load page......");
                    }
                })
            },

        },
        created(){
            this.allGame();

        }
    }
</script>

<style lang="scss" scoped> 
    .col-9{
        left:0%;
    }
    .mui-col-xs-6 {
        width: 15%;
    }
    .panel-body{
        padding: 0%;
    }
    .panel{
        margin-bottom: 10px;
    }
    .mint-swipe{
        height:300px
    }



   
    
</style>



