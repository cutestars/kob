<template>
  <NavBar />
  <router-view />
</template>
<script>
import NavBar from '@/components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
//import $ from 'jquery'
export default{
  components:{
    NavBar
  },
  setup(){
  //   $.ajax({
  //     url:"http://localhost:3000/user/account/token/",
  //     type:"post",
  //     data:{
  //       username:'test',
  //       password:'test',
  //     },
  //     success(resp){
  //       console.log(resp);
  //     },
  //     error(resp){
  //       console.log(resp);
  //     }
  //   });
    // $.ajax({
    //   url:"http://localhost:3000/user/account/info/",
    //   type:"get",
    //   headers:{
    //     Authorization:"Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkMTkwZjA2ZjNmN2I0MDFkYmU3YTc0YzFhOTI3YzQzMyIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTY4MjMzODk3MywiZXhwIjoxNjgzNTQ4NTczfQ.uGHL_Im3CjHBZjIqHGAGa2GtmAkI_KrDEKBb_VJEWP4"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // });
  //   $.ajax({
  //     url:"http://localhost:3000/user/account/register/",
  //     type:"post",
  //     data:{
  //       username: "test2",
  //       password: "test2",
  //       confirmedPassword: "test2"
  //     },
  //     success(resp){
  //       console.log(resp);
  //     },
  //     error(resp){
  //       console.log(resp);
  //     }
  //   });
   }
}
</script>
<style>
body{
  background-image:url("@/assets/images/background.png");
  background-size: cover;
}
</style>
