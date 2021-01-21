<template>
  <div class="vue-tempalte">
    <h2>Administrator page</h2>
    <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" to="/adminCreateQuestionnaire">Create new questionnaire</router-link>
    <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" to="/adminInspectQuestionnaire">Inspect questionnaire</router-link>
    <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" to="/adminDeleteQuestionnaire">Delete questionnaire</router-link>

  </div>
</template>

<script>
import router from "../router";
import { mapGetters, mapActions } from "vuex";
import axios from "axios";

export default {
  name: "Home",
  computed: {
    ...mapGetters('veztore', [
      'getAdminBearer',
    ])
  },
  methods: {
    ...mapActions('veztore', [
      'setAdminBearer',
      'clearAdminBearer',
    ]),
  },
  async created() {
    if(!this.getAdminBearer) {
      router.push("/admin");
    }
    axios.get(`${process.env.VUE_APP_API_ROOT}/auth/adminPing`, {
      headers: {
        'Authorization': `Bearer ${this.getAdminBearer}`
      }
    }).catch(() => {
      this.clearAdminBearer();
      router.push('/');
    });
  }
}
</script>

<style scoped>

</style>