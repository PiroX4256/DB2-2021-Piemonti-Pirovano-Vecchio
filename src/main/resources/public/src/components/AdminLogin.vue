<template>
  <div class="vue-tempalte">
    <form @submit.prevent="submitLoginForm">
      <h3 class="text-danger">Admin Sign In</h3>

      <div class="form-group">
        <label>Username</label>
        <input v-model="username" type="text" autocomplete="section-log username" class="form-control form-control-lg" />
      </div>

      <div class="form-group">
        <label>Password</label>
        <input v-model="password" type="password" autocomplete="section-log current-password" class="form-control form-control-lg" />
      </div>

      <button type="submit" class="btn btn-dark btn-lg btn-block">Sign In</button>
      <div class="alert alert-danger" role="alert" v-if="errmsg" style="padding-top: 5px"> {{ errmsg }} </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "Login",
  data() {
    return {
      username: '',
      password: '',
      errmsg: ''
    }
  },
  computed: {
    ...mapGetters('veztore', [
      'getAdminBearer',
    ])
  },
  methods: {
    ...mapActions('veztore', [
      'setAdminBearer',
    ]),
    submitLoginForm() {
      if(this.username && this.password) {
        axios.post(`${process.env.VUE_APP_API_ROOT}/auth/signin`, {
          username: this.username,
          password: this.password
        }).then((res) => {
          if(res.status === 200 && res.data) {
            this.setAdminBearer(res.data);
          } else {
            this.errmsg = 'Wrong user/password combination.'
          }
        }).catch((res) => {
          this.errmsg = `Error: ${res.status}`
        });
      }
    }
  }
}
</script>

<style scoped>

</style>