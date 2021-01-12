<template>
<div class="vue-tempalte">
  <div class="home-content" v-if="productName && productImage && date">
    <h4>{{ date }}</h4>
    <h3>Questionnaire of the day:</h3>
    <h3><u>{{ productName }}</u></h3>
    <div class="input-group input-group-lg">
      <img class = "img-responsive" :src="`${productImage}`" :alt="`${productName}`">
    </div>
  </div>
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
      errorMsg: '',
      productName: '',
      productImage: '',
      date: ''
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
    } else
    //alert(`${this.getBearer}`);
    {
      axios.get("http://localhost:8081/home/getHomePage", {
        headers: {
          'Authorization': `Bearer ${this.getBearer}`
        }
      }).then((res) => {
        this.productName = res.data.questionnaire.productName;
        this.productImage = res.data.questionnaire.productImage;
        this.date = res.data.questionnaire.date;
        console.log(`response: ${JSON.stringify(res)}`);
      }).catch((res) => {
        if (res.status === 403) {
          console.log(`ERROR: ${res.status}`);
          console.log(`CLEARING STORAGE...`);
          this.clearBearer();
        } else {
          this.errorMsg = `ERROR ${res.status}`;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>