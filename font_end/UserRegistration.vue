<template>
        
    <div class="h2">
		<div class="mui-content">
            
			<form class="mui-input-group">
				<div class="mui-input-row" style="height: 60px">
					<label>username</label>
					<input v-model='username' type="text" class="mui-input-clear mui-input" placeholder="username">
				</div>
				<div class="mui-input-row" style="height: 60px">
					<label>password</label>
					<input v-model='password' type="password" class="mui-input-clear mui-input" placeholder="password">
				</div>
				<div class="mui-input-row" style="height: 60px">
					<label>confirm password</label>
					<input v-model='password_confirm' type="password" class="mui-input-clear mui-input" placeholder="password confirm">
				</div>
				<div class="mui-input-row" style="height: 60px">
					<label>email</label>
					<input v-model='email' type="email" class="mui-input-clear mui-input" placeholder="email">
				</div>
                <div class="mui-input-row" style="height: 60px">
					<label>state</label>
					<input v-model='state' type="state" class="mui-input-clear mui-input" placeholder="state">
				</div>
                <div class="mui-input-row" style="height: 60px">
					<label>country</label>
					<input v-model='country' type="country" class="mui-input-clear mui-input" placeholder="country">
				</div>
                <div class="mui-input-row" style="height: 60px">
					<label>language</label>
					<input v-model='language' type="language" class="mui-input-clear mui-input" placeholder="language">
				</div>
                <div class="mui-input-row" style="height: 60px">
					<label>gender</label>
					<input v-model='gender' type="gender" class="mui-input-clear mui-input" placeholder="gender">
				</div>
			</form>
            <br>
            <br>
            <br>
            <br>

			<div class="mui-content-padded">
				<button id='reg' class="mui-btn mui-btn-block mui-btn-primary" @click="register()">register</button>
			</div>
			<div class="mui-content-padded">
				<p></p>
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
			username:"",
			password:"",
			password_confirm:"",
			email:"",
			state:"",
			country:"",
			language:"",
			gender:"",
			popmsg:"",
        }
    },
    methods:{
        register(){
			if(this.username ==''){
				Toast("username can not be null");
				return false;
			}
			if(this.password ==''){
				Toast("password can not be null");
				return false;
			}
			if(this.password != this.password_confirm){
				Toast("password does not match!!");
				return false;
			}
			if(this.email ==''){
				Toast("email can not be null");
				return false;
			}
            this.$http.post('http://23.254.203.100:9001/user/reg',{
            //this.$http.post('http://localhost:9001/user/reg',{
				username: this.username,
				password: this.password,
				email: this.email,
				state: this.state,
				country: this.country,
				language: this.language,
				gender: this.gender,
			}
            ).then(result =>{
                if(result.body.code===20000){
					this.popmsg ="Registration success! Please go to login"
					Toast(this.popmsg, 500000);
					this.$router.push({path:"/home"});
				}else{
					Toast("Registration Fails",300000);
				}
            })
			
            
        },   
    },
}

</script>

<style lang="" scoped>
    .h2{
        color: rgb(241, 226, 226)};
    
</style>