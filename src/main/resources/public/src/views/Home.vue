<template>
<div class="home">
  Home page lol
</div>
</template>

<script>
import router from "../router";
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "Home",
  computed: {
    ...mapGetters('veztore', [
      'getBearer',
    ])
  },
  async created() {
    if(!this.getBearer) {
      router.push("/login");
    }
    axios.get("http://localhost:8081/home", {headers: {
      'Access-Control-Allow-Origin': '*'
    }}).then(res => {
      console.log(`status: ${res}`);
    })
  }
}
</script>

<style scoped>

</style>