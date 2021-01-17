<template>
  <div class="vue-tempalte">

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

    }
  },
  created() {
    this.id = this.$route.params.id;
    axios.get(`${process.env.VUE_APP_API_ROOT}/admin/getUsersList`, {
      params: {
        questionnaireId: this.id
      }
    }).then(res => {
      this.usersSubmitted = res.data.submittedUsers;
      this.usersCancelled = res.data.cancelledUsers;
    });
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