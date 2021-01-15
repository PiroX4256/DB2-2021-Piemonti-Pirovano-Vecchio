<template>
<div class="vue-tempalte">
  <h4>{{ date }}</h4>
  <h3><u>{{ productName }}</u></h3>
  <div class="input-group input-group-lg">
    <img class = "img-responsive" :src="`${productImage}`" :alt="`${productName}`">
  </div>


  <form @submit.prevent="submitForm">
    <div class="form-group" v-for="(q, idx) in questions" v-bind:key="idx">
      <label class="lb-question" :for="q.questionId">{{ q.questionContent }}</label>
      <textarea v-model="q.answer" type="text" class="form-control" :id="q.questionId" placeholder="Enter your answer"></textarea>
    </div>

    <button type="button" class="btn btn-danger mr-2" @click="cancelForm">Cancel</button>
    <button type="submit" class="btn btn-success">Submit</button>

    <div class="alert alert-danger" role="alert" v-if="errorMsg" style="padding-top: 5px"> {{ errorMsg }} </div>
    <div class="alert alert-success" role="alert" v-if="successMsg" style="padding-top: 5px"> {{ successMsg }} </div>

  </form>
  <div>
    <b-modal ref="modal-s" title="Operation successful" >
      <p class="my-4">Success!</p>
      <template #modal-footer>
        <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" to="/">Back</router-link>
      </template>
    </b-modal>
    <b-modal ref="modal-e" title="Error">
      <p class="my-4">You have already submitted this questionnaire!</p>
      <template #modal-footer>
        <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" to="/">Back</router-link>
      </template>
    </b-modal>
  </div>
</div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "QuestionnairePage",
  data() {
    return {
      questions: [],
      statisticalQuestions: [],
      productName: '',
      productImage: '',
      date: '',
      marketingAnswers: [],
      statisticalAnswers: [],
      successMsg: '',
      errorMsg: '',
      submitted: false,
    }
  },
  computed: {
    ...mapGetters('veztore', [
      'getBearer',
    ])
  },
  created() {
    this.questions = this.$route.params.questions;
    this.productName = this.$route.params.productName;
    this.productImage = this.$route.params.productImage;
    this.date = this.$route.params.date;
    this.statisticalQuestions = this.$route.params.statisticalQuestions
  },
  methods: {
    submitForm() {
      if(this.submitted) {
        this.errorMsg = 'You have already submitted!';
        return;
      }
      for (let q of this.questions) {
        if (q.answer) {
          this.marketingAnswers.push({
            questionId: q.questionId,
            answerContent: q.answer
          });
        }
      }
      if(this.statisticalQuestions) {
        for (let s of this.statisticalQuestions) {
          if (s.answer) {
            this.statisticalAnswers.push(s.answer);
          }
        }
      }
      console.log(this.marketingAnswers);
      axios.post(`${process.env.VUE_APP_API_ROOT}/questionnaire/newAnswers`, {
            marketingAnswer: this.marketingAnswers,
            statisticalAnswer: this.statisticalAnswers,
            isCancelled: false
          },
          {
            headers: {
              'Authorization': `Bearer ${this.getBearer}`
            }
          }).then(res => {
        if(res.status === 200) {
          console.log(JSON.stringify(res.data));
          this.successMsg = 'Submit successful!';
          this.submitted = true;
          this.$refs['modal-s'].show();
        } else {
          this.errorMsg = 'Unexpected error! Retry later.';
          this.$refs['modal-e'].show();
        }
      }).catch(res => {
        this.errorMsg = `Server error! Retry later. Error code: ${res.status}`;
        this.$refs['modal-e'].show();
      });
    },
    cancelForm() {
      if(this.submitted) {
        this.errorMsg = 'You have already submitted!';
        this.$refs['modal-e'].show();
        return;
      }
      axios.post(`${process.env.VUE_APP_API_ROOT}/questionnaire/newAnswers`, {
        marketingAnswer: [],
        statisticalAnswer: [],
        isCancelled: true
      },
        {
          headers: {
            'Authorization': `Bearer ${this.getBearer}`
          }
        }).then(res => {
        if(res.status === 200) {
          console.log(JSON.stringify(res.data));
          this.successMsg = 'Cancel successful!';
          this.submitted = true;
          this.$refs['modal-s'].show();
        } else {
          this.errorMsg = 'Unexpected error! Retry later.';
          this.$refs['modal-e'].show();
        }
      }).catch(res => {
        this.errorMsg = `Server error! Retry later. Error code: ${res.status}`;
        this.$refs['modal-e'].show();
      });
    }
  }
}
</script>

<style scoped>

</style>