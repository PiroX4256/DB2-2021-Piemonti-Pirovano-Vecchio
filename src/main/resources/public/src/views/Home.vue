<template>
<div class="home">
  Home page lol
  {{ errorMsg }}
</div>
</template>

<script>
import router from "../router";
import { mapGetters, mapActions } from "vuex";
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      errorMsg: ''
    }
  },
  computed: {
    ...mapGetters('veztore', [
      'getBearer',
      'getAdminBearer',
    ])
  },
  methods: {
    ...mapActions('veztore', [
      'setBearer',
      'clearBearer',
    ]),
  },
  async created() {
    if(!this.getBearer && !this.getAdminBearer) {
      router.push("/login");
    } else if(this.getAdminBearer) {
      router.push("/adminHomePage");
    }
    //alert(`${this.getBearer}`);
    axios.get("http://localhost:8081/home/getHomePage", {headers: {
        'Authorization': `Bearer ${this.getBearer}`
    }}).then((res) => {
      console.log(`status: ${res}`);
    }).catch((res) => {
      if(res.status === 403) {
        console.log(`ERROR: ${res}`);
        console.log(`CLEARING STORAGE...`);
        this.clearBearer();
      } else {
        this.errorMsg = `ERROR ${res.status}`;
      }
    })
  }
}
</script>

<style scoped>

</style>