import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login";
import Home from "@/views/Home";

Vue.use(VueRouter);

const routes = [
    { path: '/login', component: Login },
    { path: '/', component: Home },
]

const router = new VueRouter({
    mode: "history",
    routes // short for `routes: routes`
});

export default router;
