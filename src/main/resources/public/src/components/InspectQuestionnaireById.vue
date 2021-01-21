<template>
  <!--  o List of users who submitted the questionnaire.
        o List of users who cancelled the questionnaire.
        o Questionnaire answers of each user. -->
  <div class="vue-tempalte">
    <b-card-group deck>
      <b-card header="Users who submitted the questionnaire">
        <b-list-group>
          <b-list-group-item v-for="(u_s, idx_s) in usersSubmitted" v-bind:key="idx_s">
            {{ u_s }}
          </b-list-group-item>
        </b-list-group>
      </b-card>
    </b-card-group>

    <b-card-group deck>
      <b-card header="Users who cancelled the questionnaire">
        <b-list-group>
          <b-list-group-item v-for="(u_c, idx_c) in usersCancelled" v-bind:key="idx_c">
            {{ u_c }}
          </b-list-group-item>
        </b-list-group>
      </b-card>
    </b-card-group>

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
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "InspectQuestionnaireById",
  data() {
    return {
      id: '',
      usersSubmitted: [],
      usersCancelled: [],
      marketingQuestionList: [],
      reviews: [],
    }
  },
  created() {
    this.id = this.$route.params.id;
    axios.get(`${process.env.VUE_APP_API_ROOT}/admin/getUsersList`, {
      params: {
        questionnaireId: this.id
      },
      headers: {
        'Authorization': `Bearer ${this.getAdminBearer}`
      }
    }).then(res => {
      this.usersSubmitted = res.data.submittedUsers;
      this.usersCancelled = res.data.cancelledUsers;
    });
    axios.get(`${process.env.VUE_APP_API_ROOT}/admin/inspection`, {
      params: {
        questionnaireId: this.id
      },
      headers: {
        'Authorization': `Bearer ${this.getAdminBearer}`
      }
    }).then(res => {
      this.reviews = res.data;
    })
  },
  computed: {
    ...mapGetters('veztore', [
      'getAdminBearer',
    ])
  },
}
</script>

<style scoped>

</style>