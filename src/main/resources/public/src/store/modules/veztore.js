//import router from "@/router";

export default {
    namespaced: true,
    state: {
        bearer: null
    },

    getters: {
        getBearer(state) {
            return state.bearer;
        }
    },

    actions: {
        setBearer({ commit }, bearer) {
            commit('setBearer', bearer);
        }
    },

    mutations: {
        setBearer(state, bearer) {
            state.bearer = bearer;
        }
    }
}
