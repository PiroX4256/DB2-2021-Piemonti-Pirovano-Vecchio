<template>
<div class="vue-tempalte">
  <!-- Insert name and image url -->
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

  <img v-if="productName && productImage" class = "img-responsive" :src="`${productImage}`" :alt="`${productName}`" width="100%">

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

  <button type="button" class="btn btn-success btn-lg btn-block pad5">Submit</button>

</div>
</template>

<script>
// TODO: submit form
export default {
  name: "AdminFormCreation",
  data() {
    return {
      productName: '',
      productImage: '',
      questions: [],
      newQuestion: '',
    }
  },
  methods: {
    appendQuestion() {
      if(!this.questions.includes(this.newQuestion)) {
        this.questions.push(this.newQuestion);
        this.newQuestion = '';
      }
    }
  }
}
</script>

<style scoped>
.input-group {
  padding-top: 5px;
}
.img-responsive {
  padding-top: 5px;
}
.pad5 {
  padding-top: 5px;
}
</style>