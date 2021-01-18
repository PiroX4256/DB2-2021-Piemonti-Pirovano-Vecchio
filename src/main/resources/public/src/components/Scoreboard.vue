<template>
  <div class="vue-tempalte">
    <h4>Scoreboard</h4>

    <b-table striped hover
             :items="users"
             :fields="fields"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             sort-icon-left
             responsive="sm">
    </b-table>

  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "Scoreboard",
  data() {
    return {
      users: [],
      sortBy: 'score',
      sortDesc: true,
      fields: [
        {
          key: 'username',
          label: 'Username',
          sortable: true
        },
        {
          key: 'score',
          label: 'Score',
          sortable: true
        }
      ]
    }
  },
  computed: {
    ...mapGetters('veztore', [
      'getBearer',
    ])
  },
  created() {
    axios.get(`${process.env.VUE_APP_API_ROOT}/home/getLeaderboard`, {
      headers: {
        'Authorization': `Bearer ${this.getBearer}`
      }
    }).then(res => {
      this.users = res.data;
    });
  },
  methods: {}
}
</script>

<style scoped>

</style>