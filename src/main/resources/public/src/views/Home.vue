<template>
<div class="vue-tempalte">
  <div class="home-content" v-if="productName && productImage && date">
    <h4>{{ date }}</h4>
    <h3>Questionnaire of the day:</h3>
    <h3><u>{{ productName }}</u></h3>
    <div class="input-group input-group-lg">
      <img class = "img-responsive img-product" :src="`${productImage}`" :alt="`${productName}`">
    </div>

    <div class="input-group input-group-lg">
      <!-- Fill questionnaire button -->
      <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" :to="{ name: 'FillQuestionnaire',
      params: {
        questions: this.marketingQuestionList,
        productName: this.productName,
        productImage: this.productImage,
        date: this.date,
        statisticalQuestions: this.statisticalQuestionList
      }}">
        Fill questionnaire
      </router-link>

      <!-- List of questions with relative answers -->
      <div class="card">
        <ul class="list-group list-group-flush">
          <li class="list-group-item"
              v-for="(q, idx) in reviews" v-bind:key="idx"><h5>{{ q.marketingQuestion }}</h5>
            <ul class="list-group">
              <li class="list-group"
                v-for="(a, idx) in q.marketingAnswerList" v-bind:key="idx">
                <blockquote class="blockquote">
                  <p class="mb-0">{{ a.answer }}</p>
                  <footer class="blockquote-footer">User <cite title="Source Title">{{ a.user.username }}</cite></footer>
                </blockquote>
              </li>
            </ul>
          </li>
        </ul>
      </div>


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
      date: '',
      marketingQuestionList: [],
      statisticalQuestionList: [],
      reviews: [],
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
      await router.push("/login");
    } else if(this.getAdminBearer) {
      await router.push("/adminHomePage");
    } else
    //alert(`${this.getBearer}`);
    {
      axios.get(`${process.env.VUE_APP_API_ROOT}/home/getHomePage`, {
        headers: {
          'Authorization': `Bearer ${this.getBearer}`
        }
      }).then((res) => {
        this.productName = res.data.questionnaire.productName;
        this.productImage = res.data.questionnaire.productImage;
        this.date = res.data.questionnaire.date;
        this.marketingQuestionList = res.data.marketingQuestionList;
        this.statisticalQuestionList = res.data.statisticalQuestionList;
        console.log(`response: ${JSON.stringify(res)}`);
      }).then(
          axios.get(`${process.env.VUE_APP_API_ROOT}/home/getReviews`, {
            headers: {
              'Authorization': `Bearer ${this.getBearer}`
            }
          }).then(res => {
            this.reviews = res.data;
          }).catch(res => {
            if (res.status === 403) {
              console.log(`ERROR: ${res.status}`);
              console.log(`CLEARING STORAGE...`);
              this.clearBearer();
            } else {
              this.errorMsg = `SERVER ERROR`;
            }
          })
      ).catch((res) => {
        if (res.status === 403) {
          console.log(`ERROR: ${res.status}`);
          console.log(`CLEARING STORAGE...`);
          this.clearBearer();
        } else {
          this.errorMsg = `SERVER ERROR`;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>