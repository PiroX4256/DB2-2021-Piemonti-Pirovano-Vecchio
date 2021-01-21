<template>
  <div>

    <ul class="nav navbar-nav flex-row float-right">
      <li class="nav-item active mr-5" v-if="getBearer">
        <a class="nav-link mr-auto">{{ getUsername }}, your score is: {{ score }}</a>
      </li>
      <li v-if="!getBearer && !getAdminBearer" class="nav-item">
        <router-link class="nav-link pr-3" to="/login">Sign in</router-link>
      </li>
      <li v-if="!getBearer && !getAdminBearer" class="nav-item">
        <router-link class="btn btn-outline-primary" to="/register">Sign up</router-link>
      </li>
      <li v-if="!getBearer && !getAdminBearer" class="nav-item" style="padding-left: 5px">
        <router-link class="btn btn-outline-danger"  to="/admin">Admin</router-link>
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
import axios from "axios";

export default {
  name: "Navbar",
  data() {
    return {
      score: 0,
    }
  },
  computed: {
    ...mapGetters('veztore', [
        'getBearer',
        'getAdminBearer',
        'getUsername'
    ]),
  },
  created() {
    this.updateScore();
  },
  updated() {
    this.updateScore();
  },
  methods: {
    ...mapActions('veztore', [
        'clearBearer',
        'clearAdminBearer',
        'clearUsername',
    ]),
    logout() {
      this.clearUsername();
      if(this.getBearer) {
        this.clearBearer();
        router.push('/login');
      }
      if(this.getAdminBearer) {
        this.clearAdminBearer();
        router.push('/admin');
      }
    },
    updateScore() {
      axios.get(`${process.env.VUE_APP_API_ROOT}/auth/getUser`, {
        params: {
          username: this.getUsername
        },
        headers: {
          'Authorization': `Bearer ${this.getBearer}`
        }
      }).then(res => {
        if(res.status === 200) this.score = res.data.score;
      });
    }
  }
}
</script>

<style scoped>

</style>