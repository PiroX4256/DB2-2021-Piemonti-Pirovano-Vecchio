<template>
<div class="vue-tempalte text-center">
  <h2>Thanks!</h2>
  <h5>Your response has been registered.</h5>
  <div>
  <b-icon-check2-circle role="img" font-scale="4" class="mr-auto" style="color: darkgreen"></b-icon-check2-circle>
  </div>
  <button class="btn btn-outline-success btn-lg btn-block" onclick="window.location.href='/'">Go back</button>
</div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import axios from "axios";
import router from "../router";

export default {
  name: "GreetingsPage",
  created() {
    axios.get(`${process.env.VUE_APP_API_ROOT}/auth/userPing`, {
      headers: {
        'Authorization': `Bearer ${this.getBearer}`
      }
    }).catch(() => {
      this.clearBearer();
      router.push('/');
    });
  },
  computed: {
    ...mapGetters('veztore', [
      'getBearer',
    ])
  },
  methods: {
    ...mapActions('veztore', [
      'clearBearer',
    ]),
  }
}
</script>

<style scoped>

</style>