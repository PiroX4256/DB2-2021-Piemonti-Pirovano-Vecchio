<template>
  <div class="vue-tempalte">
    <b-table striped hover
             :items="questionnaires"
             :fields="fields"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             sort-icon-left
             responsive="sm">
      <template #cell(productImage)="data">
        <img :src="data.value" class="img-responsive img-product img-little" alt="Image">
      </template>
      <template #cell(id)="qid">
        <b-button @click="selectQuestionnaire(qid)">Inspect</b-button>
      </template>
    </b-table>
  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";
import router from "../router";

export default {
  name: "AdminInspectQuestionnaire",
  data() {
    return {
      selected: 0,
      questionnaires: [],
      sortBy: 'date',
      sortDesc: false,
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
        },
        {
          key: 'id',
          label: ''
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
  },
  methods: {
    selectQuestionnaire(id) {
      this.selected = id.value;
      router.push({ name: 'AdminInspectId', params: { id: this.selected.toString() } });
    },
  }
}
</script>

<style scoped>

</style>