<template>
    <div>
        <h1 class="h1">search result:{{this.pageIndex}} of {{this.numOfPage}} pages</h1>


    <ul class="mui-table-view mui-unfold">
				<li class="mui-table-view-cell mui-collapse mui-media mui-media-icon">
						<div class="mui-media-body">
							<dl v-for="item in this.data" :key="item.game_id">
                                <router-link :to="{path:'/game/gameDetail', query:{game_id: item.game_id}}">
                                    <dt style="background-color: white; font-size: large; font-family:cursive">
                                        {{item.title}}
  
                                    </dt>
                                </router-link>
                                <hr>
                                <dd style="background-color: white; margin-bottom:40px">
                                    {{item.summary}}
                                    {{item.detail}}
                                </dd>
                            </dl>
						</div>
				</li>
			</ul>

        <div class="PageContainer">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <ul class="pagination">
                        <li>
                            <button @click="prevPage">
                                Prev
                            </button>
                            <!-- <a href="#">Prev</a> -->
                        </li>
                        <!-- <li name="first" id="first">
                            <button  value={{this.pageIndex+1}}>
                                
                            </button>
                        </li>
                        <li name="second" id="second">
                            <button value={{this.pageIndex+2}}>
                                Prev
                            </button>
                        </li> -->
                        <li name="next" id="next">
                            <!-- <a href="#">Next</a> -->
                            <button @click="nextPage">
                                Next
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
</div>



</template>

<script>
  import {Toast} from "mint-ui";
    export default {
        data(){
            return{
                data:[],
                resultKey:"",
                pageIndex: 1,
                totalResult: 1,
                numOfEachPage: 1,
                numOfPage: 1,
            }
        },
        methods:{
            nextPage(){
                if(this.pageIndex < this.numOfPage){
                    this.pageIndex++;
                    this.search();
                }
            },
            prevPage(){
                if(this.pageIndex > 1){
                    this.pageIndex--;
                    this.search();
                } 
            },
            search(){
                this.resultKey = this.$route.query.searchKey;
                this.$http.get('http://localhost:9002/game/'+ this.resultKey +'/'
                            + this.pageIndex +'/1'
					).then(result =>{
					if(result.body.code ===20000){
                        this.totalResult = result.body.data.total;
                        this.data = result.body.data.row;
                        this.numOfPage = this.totalResult / this.numOfEachPage;
                        if(this.numOfPage <1 && this.numOfPage>0){this.numOfPage =1;}
					}else{
                        this.data ="";
                        Toast("nothing found");
                    }
				})
			},
        },
        created(){
            this.search();
        },
        // beforeUpdate(){
        //     this.search();
        // }
    }
</script>

<style lang="scss" scoped>
    .h1{
        font-family: monospace;
        color: thistle;
    }

    .pagination {
    padding-left: 40%;
    padding-bottom: 80%;  
}

</style>
