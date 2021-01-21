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
import {mapActions, mapGetters} from "vuex";
import router from "../router";

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
    axios.get(`${process.env.VUE_APP_API_ROOT}/auth/userPing`, {
      headers: {
        'Authorization': `Bearer ${this.getBearer}`
      }
    }).catch(() => {
      this.clearBearer();
      router.push('/');
    });
    axios.get(`${process.env.VUE_APP_API_ROOT}/home/getLeaderboard`, {
      headers: {
        'Authorization': `Bearer ${this.getBearer}`
      }
    }).then(res => {
      this.users = res.data;
    });
  },
  methods: {
    ...mapActions('veztore', [
      'clearBearer',
    ]),
  }
}
</script>

<style scoped>

</style>