<template>
<div class="vue-tempalte">
  <h4>{{ date }}</h4>
  <h3><u>{{ productName }}</u></h3>
  <div class="input-group input-group-lg">
    <img class = "img-responsive" :src="`${productImage}`" :alt="`${productName}`">
  </div>

  <form @submit.prevent="checkForm" v-if="formStep === 0">
    <div class="form-group" v-for="(q, idx) in questions" v-bind:key="idx">
      <label class="lb-question" :for="q.questionId">{{ q.questionContent }}</label>
      <textarea v-model="q.answer" type="text" class="form-control" :id="q.questionId" placeholder="Enter your answer"></textarea>
    </div>

    <button type="button" class="btn btn-danger mr-2" @click="cancelForm">Cancel</button>
    <button type="submit" class="btn btn-success">Next</button>
  </form>

  <form @submit.prevent="submitForm" v-if="formStep === 1">
    <b-form-group v-if="statisticalQuestions[0]">
      <label class="lb-question" for="age-spin-button">{{ statisticalQuestions[0] }}</label>
      <SpinButtonCustom id="age-spin-button" :value="age" @input="setAge($event)"></SpinButtonCustom>
    </b-form-group>

    <b-form-group v-if="statisticalQuestions[1]">
      <label class="lb-question" for="expertise">{{ statisticalQuestions[1] }}</label>
      <b-form-select id="expertise" v-model="ex_select" :options="ex_options"></b-form-select>
    </b-form-group>

    <b-form-group v-if="statisticalQuestions[2]">
      <label class="lb-question" for="gender">{{ statisticalQuestions[2] }}</label>
      <b-form-select id="gender" v-model="g_select" :options="g_options"></b-form-select>
    </b-form-group>

    <button type="submit" class="btn btn-primary mr-2" @click="formStep=0">Previous</button>
    <button type="button" class="btn btn-danger mr-2" @click="cancelForm">Cancel</button>
    <button type="submit" class="btn btn-success">Submit</button>
  </form>
  <div class="alert alert-danger mr-2" role="alert" v-if="errorMsg" style="padding-top: 5px"> {{ errorMsg }} </div>
  <div class="alert alert-success mr-2" role="alert" v-if="successMsg" style="padding-top: 5px"> {{ successMsg }} </div>
  <div>

    <b-modal ref="modal-s" title="Operation successful" >
      <p class="my-4">Success!</p>
      <template #modal-footer>
        <button class="btn btn-primary btn-lg btn-block" onclick="window.location.href='/'">Back</button>
      </template>
    </b-modal>
    <b-modal ref="modal-e" title="Error">
      <p class="my-4">You have already submitted this questionnaire!</p>
      <template #modal-footer>
        <button class="btn btn-primary btn-lg btn-block" onclick="window.location.href='/'">Back</button>
      </template>
    </b-modal>
    <b-modal ref="modal-b" title="Banned">
      <p class="my-4">You have been banned for writing an offensive word.</p>
      <template #modal-footer>
        <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" to="/login">Ok</router-link>
      </template>
    </b-modal>
  </div>
</div>
</template>

<script>
import axios from "axios";
import {mapActions, mapGetters} from "vuex";
import SpinButtonCustom from "./SpinButtonCustom";
import router from "../router";

export default {
  name: "QuestionnairePage",
  components: {SpinButtonCustom},
  data() {
    return {
      formStep: 0,
      questions: [],
      statisticalQuestions: [],
      productName: '',
      productImage: '',
      date: '',
      age: 0,
      marketingAnswers: [],
      statisticalAnswers: [],
      successMsg: '',
      errorMsg: '',
      submitted: false,
      ex_select: 0,
      ex_options: [
        { value: 1, text: 'Low' },
        { value: 2, text: 'Medium' },
        { value: 3, text: 'High' },
      ],
      g_select: 0,
      g_options: [
        { value: 1, text: 'Male' },
        { value: 2, text: 'Female' },
        { value: 3, text: 'Other' },
      ],
    }
  },
  computed: {
    ...mapGetters('veztore', [
      'getBearer',
    ])
  },
  created() {
    axios.get(`${process.env.VUE_APP_API_ROOT}/auth/userPing`, {
      headers: {
        'Authorization': `Bearer ${this.getBearer}`
      }
    }).catch(() => {
      this.clearBearer();
      router.push('/');
    });
    this.questions = this.$route.params.questions;
    this.productName = this.$route.params.productName;
    this.productImage = this.$route.params.productImage;
    this.date = this.$route.params.date;
    this.statisticalQuestions = this.$route.params.statisticalQuestions
  },
  methods: {
    ...mapActions('veztore', [
      'clearBearer',
      'clearUsername',
    ]),
    setAge(age) {
      if(age) this.age = age;
    },
    checkForm() {
      // fill marketingAnswers list
      this.errorMsg = '';
      this.marketingAnswers = [];
      for (let q of this.questions) {
        if (q.answer) {
          this.marketingAnswers.push({
            questionId: q.questionId,
            answerContent: q.answer
          });
        } else {
          this.errorMsg = 'You must fill all answers!';
          this.marketingAnswers = [];
          return;
        }
      }
      this.formStep = 1;
    },
    submitForm() {
      if(this.submitted) {
        this.errorMsg = 'You have already submitted!';
        return;
      }
      if(this.statisticalQuestions) {
        this.statisticalAnswers.push(this.age);
        this.statisticalAnswers.push(this.ex_select);
        this.statisticalAnswers.push(this.g_select);
      }
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
          /*
          this.successMsg = 'Submit successful!';
          this.submitted = true;
          this.$refs['modal-s'].show();*/
          window.location.href("/greetings");
        } else {
          this.errorMsg = 'Unexpected error! Retry later.';
          this.$refs['modal-e'].show();
        }
      }).catch(err => {
        if(err.response.status === 422) {
          this.errorMsg = 'You have been banned for writing an offensive word.';
          this.$refs['modal-b'].show();
          this.clearUsername();
          this.clearBearer();
        } else {
          this.errorMsg = `Server error! Retry later. Error code: ${err.response.status}`;
          this.$refs['modal-e'].show();
        }
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