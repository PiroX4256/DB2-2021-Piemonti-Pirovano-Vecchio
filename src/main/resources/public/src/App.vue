<template>
  <div class="vue-tempalte">
    <!-- Navigation -->
    <nav class="navbar shadow bg-white rounded justify-content-between flex-nowrap flex-row fixed-top">
      <div class="container">
        <router-link to="/" class="navbar-brand float-left">
          Gamified quiz!
        </router-link>
        <Navbar></Navbar>
      </div>
    </nav>

    <!-- Main -->
    <div class="App">
      <div class="vertical-center">
        <div class="inner-block">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import Navbar from "./components/Navbar";

export default {
  name: 'App',
  components: {
    Navbar
  },
  computed: {
    ...mapGetters('veztore', [
        'getBearer',
        'getAdminBearer'
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
