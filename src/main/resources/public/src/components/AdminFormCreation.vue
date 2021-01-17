<template>
<div class="vue-tempalte">
  <!-- Insert date, name and image url -->
  <div class="form-group row">
    <label for="example-date-input" class="col-2 col-form-label">Date</label>
    <div class="col-10">
      <input v-model="date" class="form-control" type="date" value="2021-01-01" id="example-date-input">
    </div>
  </div>
  <div class="input-group input-group-lg">
    <div class="input-group-prepend">
      <span class="input-group-text" id="productName">Product Name</span>
    </div>
    <input v-model="productName" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
  </div>
  <div class="input-group input-group-lg">
    <div class="input-group-prepend">
      <span class="input-group-text" id="productImageURL">Product Image URL</span>
    </div>
    <input v-model="productImage" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
  </div>
  <div class="input-group input-group-lg">
    <img v-if="productName && productImage" class="img-responsive img-product" :src="`${productImage}`" :alt="`${productName}`">
  </div>
  <!-- Questions -->
  <div v-for="question in questions" v-bind:key="question" class="input-group input-group-lg">
    <div class="input-group-prepend">
      <span class="input-group-text" id="`questionNum${questions.indexOf(question)}`">Question {{ questions.indexOf(question) + 1 }}</span>
    </div>
    <span type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm">{{ question }}</span>
  </div>

  <!-- Question generator -->
  <div class="input-group input-group-lg">
    <div class="input-group-prepend">
      <span class="input-group-text" id="inputGroup-sizing-lg">Question</span>
    </div>
    <input v-model="newQuestion" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
    <button class="btn btn-outline-secondary" type="button" @click="appendQuestion">New</button>
  </div>
  <div class="input-group input-group-lg">
    <button type="button" class="btn btn-success btn-lg btn-block" @click="submitForm">Submit</button>
  </div>

  <!-- Modal server response -->
  <b-modal ref="modal-s" title="Operation successful" >
    <p class="my-4">Success!</p>
    <template #modal-footer>
      <b-button variant="primary" @click="goToHomepage">Back</b-button>
    </template>
  </b-modal>
</div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";
import router from "../router";

export default {
  name: "AdminFormCreation",
  data() {
    return {
      today: new Date(),
      date: '',
      productName: '',
      productImage: '',
      questions: [],
      newQuestion: '',
    }
  },
  computed: {
    ...mapGetters('veztore', [
      'getAdminBearer',
    ])
  },
  methods: {
    appendQuestion() {
      if(!this.questions.includes(this.newQuestion) && this.newQuestion) {
        this.questions.push(this.newQuestion);
        this.newQuestion = '';
      }
    },
    submitForm() {
      axios.post(`${process.env.VUE_APP_API_ROOT}/admin/newQuestionnaire`, {
        productDTO: {
          productName: this.productName,
          productImage: this.productImage,
          date: this.date
        },
        marketingQuestions: this.questions,
      }, {
        headers: {
          'Authorization': `Bearer ${this.getAdminBearer}`
        }
      }
    ).then(res => {
        console.log(`${res.status}:: ${res.data}`);
        this.$refs['modal-s'].show();
      })
      .catch(res => {
        console.log(`ERROR: ${res.status}:: ${res.data}`);
      })
    },
    goToHomepage() {
      router.push('/adminHomePage');
    }
  }
}
</script>

<style scoped>
.input-group {
  padding-top: 5px;
}
.pad5 {
  padding-top: 5px;
}
</style>