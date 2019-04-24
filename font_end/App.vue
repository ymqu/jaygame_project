<template>
    <div class="app_container">
        <!-- <mt-header fixed title="Jay games"></mt-header> -->

	<div>
        <img src="https://source.unsplash.com/YP6lDrlxWYQ" class="img-fluid" alt="" style="height:100px; width:400px;">

        
		<router-link :to="{path:'/search', query:{searchKey: this.key}}">
                 <button type="submit" class="btn btn-primary" style=" float:right">Submit</button>
                <input type="text" name="key" placeholder="search...."  v-model="key" style="width: 30%; float:right; margin-right:20px">                   
        </router-link>
				 



        <ul class="nav nav-tabs" style="padding-top: 20px">
			<li>
				<router-link class="mui-tab-item" to="/home">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">home</span>
				</router-link>
			</li>
			<li>
				<router-link class="mui-tab-item" to="/game">
					<span class="mui-icon-extra mui-icon-extra-computer"><span class="mui-badge"></span></span>
					<span class="mui-tab-label">Games</span>
				</router-link>
			</li>
			<li>
				<router-link class="mui-tab-item" to="/news">
					<span class="mui-icon mui-icon-extra mui-icon-extra-new"></span>
					<span class="mui-tab-label">News</span>
				</router-link>
			</li>
			<li>
				<router-link class="mui-tab-item" to="/community">
					<span class="mui-icon  mui-icon-extra mui-icon-extra-people"></span>
					<span class="mui-tab-label">Community</span>
				</router-link>
			</li>
			<li>
				<router-link class="mui-tab-item" to="/about">
					<span class="mui-icon-extra mui-icon-extra-topic"></span>
					<span class="mui-tab-label">About</span>
				</router-link>
			</li>
			<li>
				<span v-if="flag1">111111 </span>
				<span v-if="flag2">111111 </span>
			</li>

			<li >
				<fieldset style="padding-right:0%"> 
					<p v-if="flag1">
					<label  class="label_class" style="color:lightblue;">username</label>
					<input  class="input_class" type="text" name="username" v-model="username" placeholder="username" id="">
					<label  class="label_class" style="color:lightblue;">password</label>
					<input  class="input_class" type="text" name="password" v-model="password" placeholder="password" id="">
					<button type="submit" class="btn btn-primary" @click="login()">login</button>
					<button type="submit" style="background-color:black; border:none;padding:0px;">
						<router-link to="/user/registration" class="btn btn-primary">register</router-link>
					</button>

					<!-- <button type="submit" class="btn btn-primary" @click="register()">register</button> -->
					</p>
					<p v-if="flag2">
						<label class="label_class"> welcome {{username}} from {{data.state}}, {{data.country}}
							<router-link to="/user/profile">personal page</router-link>
							<!-- <router-link to="/user/logout">logout</router-link> -->
							<router-link @click.native="logout()" to="/home">logout</router-link>
						</label>
					</p>
				</fieldset>
				  
			</li>
			

        </ul>
    </div>

        <router-view></router-view>
    </div>
</template>

<script>
	export default{
		data(){
			return{
					username: "",
					password: "",
					flag1: true,
					flag2: false,
					data:[],
					key:"",
					searchData:[]
			}
		},
		methods:{
			login(){
				//this.$http.post('http://localhost:9001/user/login', {
				this.$http.post('http://23.254.203.100:9001/user/login', {
						username: this.username,
						password: this.password
					}
				).then(result =>{
					if(result.body.code ===20000){
						console.log('login success');
						this.data = result.body.data;
						const token = this.data.token;
						window.localStorage.setItem('token', token);
						this.flag1 =false;
						this.flag2 = true;
					}else{
						console.log('login failed');
					}
				})
			},

			logout(){
				//console.log('logout success');
				window.localStorage.removeItem('token');
				this.flag2 = false;
				this.flag1 = true;
			},

			jump(){
				this.$router.push({path: '/search?searchKey=' + this.key});
				//this.$router.push({path: '/lookfor'});
			}
		}

	}
	
</script>


<style lang="scss" scoped>
.label_class{
	color:ligtblue;
	font-size:12px;
}
.app_container{
		padding-top: 20px;
		padding-left: 12%;
		padding-right: 12%;
		background-color: black;
	}
	.input_class{
		width:100px;
		height: 10px;
		color:lightblue;
		font-size:10px;
	}

</style>

