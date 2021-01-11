//import router from "@/router";

import router from "../../router";

export default {
    namespaced: true,
    state: {
        bearer: null,
        adminBearer: null
    },

    getters: {
        getBearer(state) {
            return state.bearer;
        },
        getAdminBearer(state) {
            return state.adminBearer;
        }
    },

    actions: {
        setBearer({ commit }, bearer) {
            commit('setBearer', bearer);
            router.push('/');
        },
        clearBearer({ commit }) {
            commit('clearBearer');
        },
        setAdminBearer({ commit }, bearer) {
            commit('setAdminBearer', bearer);
            router.push('/adminHomePage');
        },
        clearAdminBearer({ commit }) {
            commit('clearAdminBearer');
        }
    },

    mutations: {
        setBearer(state, bearer) {
            state.bearer = bearer;
        },
        clearBearer(state) {
            state.bearer = null;
        },
        setAdminBearer(state, bearer) {
            state.adminBearer = bearer;
        },
        clearAdminBearer(state) {
            state.adminBearer = null;
        }
    }
}
