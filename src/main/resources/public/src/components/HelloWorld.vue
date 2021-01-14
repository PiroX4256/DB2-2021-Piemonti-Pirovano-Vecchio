<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>

    </p>
    <button @click="updateBearer">Bearer</button>
    {{ getBearer }}
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },

  computed: {
    ...mapGetters('veztore', [
        'getBearer',
    ])
  },

  methods: {
    ...mapActions('veztore', [
        'setBearer',
    ]),
    updateBearer() {
      console.log("Bearer: " + this.getBearer);
      if(this.getBearer == null) this.setBearer("dolphin");
      else this.setBearer("quokka");
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
