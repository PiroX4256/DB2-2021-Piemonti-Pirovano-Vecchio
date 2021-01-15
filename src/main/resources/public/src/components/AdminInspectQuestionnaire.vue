<template>
  <div class="vue-tempalte">
    <b-table striped hover :items="questionnaires">
      <template #cell(productImage)="data">
        <img :src="data.value" class="img-responsive img-product img-little" alt="Image">
      </template>
    </b-table>
  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "AdminInspectQuestionnaire",
  data() {
    return {
      questionnaires: [],
      fields: [
        {
          key: 'productName',
          label: 'Product Name',
          sortable: true
        },
        {
          key: 'date',
          sortable: true
        },
        {
          key: 'productImage',
          label: 'Product Image'
        }
      ]
    }
  },
  computed: {
    ...mapGetters('veztore', [
      'getAdminBearer',
    ])
  },
  async created() {
    await axios.get(`${process.env.VUE_APP_API_ROOT}/questionnaire/getAllQuestionnaires`, {
      headers: {
        'Authorization': `Bearer ${this.getAdminBearer}`
      }
    }).then(res => {
      this.questionnaires = res.data;
    })
  }
}
</script>

<style scoped>

</style>