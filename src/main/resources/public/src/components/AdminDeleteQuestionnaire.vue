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
        <b-button variant="danger" @click="selectQuestionnaire(qid)">Delete</b-button>
      </template>
    </b-table>
    <b-modal ref="modal-confirm" title="Confirm action">
      <h4 class="my-4">Are you sure you want to delete questionnaire
        <u>{{ selName }}</u>?
      </h4>
      <template #modal-footer>
        <b-button variant="danger" @click="deleteSelectedQuestionnaire">Confirm deletion</b-button>
        <b-button @click="hideModal">Cancel</b-button>
      </template>
    </b-modal>
    <div>
      <b-modal ref="modal-s" title="Operation successful" >
        <p class="my-4">Success!</p>
        <template #modal-footer>
          <b-button variant="primary" @click="refreshPage">Back</b-button>
        </template>
      </b-modal>
      <b-modal ref="modal-e" title="Error">
        <p class="my-4">There was an error.</p>
        <template #modal-footer>
          <router-link tag="button" type="button" class="btn btn-primary btn-lg btn-block" to="/adminHomePage">Back</router-link>
        </template>
      </b-modal>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "AdminDeleteQuestionnaire",
  data() {
    return {
      selected: 0,
      selName: '',
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
      this.$refs['modal-confirm'].show();
      this.selected = id.value;
      this.selName = this.questionnaires.find(x => x.id === this.selected).productName;
    },
    hideModal() {
      this.$refs['modal-confirm'].hide();
      this.selected = 0;
    },
    refreshPage() {
      this.$router.go();
    },
    deleteSelectedQuestionnaire() {
      axios.get(`${process.env.VUE_APP_API_ROOT}/admin/deleteQuestionnaire`, {
        headers: {
          'Authorization': `Bearer ${this.getAdminBearer}`
        },
        params: {
          questionnaireId: this.selected
        }
      }).then(() => {
        this.$refs['modal-confirm'].hide();
        this.$refs['modal-s'].show();
      }).catch(() => {
        this.$refs['modal-e'].show();
      })
    }
  }
}
</script>

<style scoped>

</style>