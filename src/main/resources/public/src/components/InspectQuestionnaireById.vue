<template>
  <div class="vue-tempalte"></div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "InspectQuestionnaireById",
  data() {
    return {
      id: 0,
      usersSubmitted: [],
      usersCancelled: [],

    }
  },
  created() {
    this.id = this.$route.params.id;
  },
  computed: {
    ...mapGetters('veztore', [
      'getAdminBearer',
    ])
  }
}
</script>

<style scoped>

</style>