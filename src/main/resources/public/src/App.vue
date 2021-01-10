<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">

    <router-view/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'App',
  components: {
  },
  computed: {
    ...mapGetters('veztore', [
        'getBearer',
    ])
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
