import Vue from "vue";
import Vuex from "vuex";
import VuexPersistence from "vuex-persist";
import veztore from "../store/modules/veztore"

Vue.use(Vuex);

const vuexLocal = new VuexPersistence({
    storage: window.localStorage
})

export default new Vuex.Store({
    modules: {
        veztore,
    },
    state: {},
    mutations: {},
    actions: {},
    plugins: [vuexLocal.plugin]
});
