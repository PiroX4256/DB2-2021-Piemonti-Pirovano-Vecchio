//import router from "@/router";

import router from "../../router";

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
            router.push('/');
        },
        clearBearer({ commit }) {
            commit('clearBearer');
        }
    },

    mutations: {
        setBearer(state, bearer) {
            state.bearer = bearer;
        },
        clearBearer(state) {
            state.bearer = null;
        }
    }
}
