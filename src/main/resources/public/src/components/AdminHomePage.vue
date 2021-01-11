<template>
  <div class="home">
    Admin home page lol
  </div>
</template>

<script>
import router from "../router";
import { mapGetters, mapActions } from "vuex";

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
  }
}
</script>

<style scoped>

</style>