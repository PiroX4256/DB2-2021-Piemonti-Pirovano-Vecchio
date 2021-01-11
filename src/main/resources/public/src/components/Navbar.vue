<template>
  <div>
    <ul class="nav navbar-nav flex-row float-right">
      <li v-if="!getBearer && !getAdminBearer" class="nav-item">
        <router-link class="nav-link pr-3" to="/login">Sign in</router-link>
      </li>
      <li v-if="!getBearer && !getAdminBearer" class="nav-item">
        <router-link class="btn btn-outline-primary" to="/register">Sign up</router-link>
      </li>
      <li v-if="getBearer || getAdminBearer" class="nav-item">
        <button class="btn btn-primary" @click="logout">Logout</button>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import router from "../router";

export default {
  name: "Navbar",
  computed: {
    ...mapGetters('veztore', [
        'getBearer',
        'getAdminBearer',
    ]),
  },
  methods: {
    ...mapActions('veztore', [
        'clearBearer',
        'clearAdminBearer',
    ]),
    logout() {
      if(this.getBearer) {
        this.clearBearer();
        router.push('/login');
      }
      if(this.getAdminBearer) {
        this.clearAdminBearer();
        router.push('/admin');
      }
    }
  }
}
</script>

<style scoped>

</style>