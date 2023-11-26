<script>
import Welcome from "@/components/Welcome.vue";
import Index from "@/components/Index.vue";
import PerIfo from "@/components/PerIfo.vue";
import zhuce from "@/components/Register.vue";


export default {
  name: 'App',
  components:{
    Index,
    Welcome,
    PerIfo,
    zhuce
  }
}
</script>

<template>
  <router-view/>

</template>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
