<template>
  <div class="vue-tempalte">
    <form @submit.prevent="submitForm">
      <h3>Sign Up</h3>

      <div class="form-group">
        <label>Username</label>
        <input v-model="username" autocomplete="section-reg username" type="text" class="form-control form-control-lg"/>
      </div>

      <div class="form-group">
        <label>Email address</label>
        <input v-model="email" autocomplete="section-reg email" type="email" class="form-control form-control-lg" />
      </div>

      <div class="form-group">
        <label>Password</label>
        <input v-model="password" autocomplete="section-reg current-password" type="password" class="form-control form-control-lg" />
      </div>

      <button type="submit" class="btn btn-dark btn-lg btn-block">Sign Up</button>

      <p class="forgot-password text-right">
        Already registered
        <router-link :to="{name: 'Login'}">sign in?</router-link>
      </p>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions } from "vuex";

export default {
  name: "Register",
  data() {
    return {
      username: '',
      password: '',
      email: ''
    }
  },
  methods: {
    ...mapActions('veztore', [
        'setBearer',
        'setUsername',
    ]),
    submitForm() {
      if(this.email && this.password && this.username) {
        axios.post(`${process.env.VUE_APP_API_ROOT}/auth/signup`, {
          username: this.username,
          email: this.email,
          password: this.password
        }).then((res) => {
          if(res.status === 200 && res.data) {
            this.setBearer(res.data);
            this.setUsername(this.username);
          }
        });
      }
    }
  }
}
</script>

<style scoped>

</style>