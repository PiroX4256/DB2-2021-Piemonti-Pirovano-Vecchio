<template>
  <div class="vue-tempalte">
    <form @submit.prevent="submitLoginForm">
      <h3>Sign In</h3>

      <div class="form-group">
        <label>Username</label>
        <input v-model="username" type="text" autocomplete="section-log username" class="form-control form-control-lg" />
      </div>

      <div class="form-group">
        <label>Password</label>
        <input v-model="password" type="password" autocomplete="section-log current-password" class="form-control form-control-lg" />
      </div>

      <button type="submit" class="btn btn-dark btn-lg btn-block">Sign In</button>
      <div class="alert alert-danger" role="alert" v-if="errmsg"> {{ errmsg }}</div>
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
      'getBearer',
    ])
  },
  methods: {
    ...mapActions('veztore', [
      'setBearer',
    ]),
    submitLoginForm() {
      if(this.username && this.password) {
        axios.post(`${process.env.VUE_APP_API_ROOT}/auth/signin`, {
          username: this.username,
          password: this.password
        }).then((res) => {
          if(res.status === 200) {
            this.setBearer(res.data);
          } else {
            this.errmsg = 'Wrong user/password combination.'
          }
        }).catch((res) => {
          console.log(`ERROR: ${res}`);
        });
      }
    }
  }
}
</script>

<style scoped>

</style>