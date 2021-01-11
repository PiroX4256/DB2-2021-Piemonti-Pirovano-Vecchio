import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../components/Login";
import Home from "../views/Home";
import Register from "../components/Register";

Vue.use(VueRouter);

const routes = [
    { path: '/login', name: 'Login', component: Login },
    { path: '/', name: 'Home', component: Home },
    { path: '/register', name: 'Register', component: Register },
]

const router = new VueRouter({
    mode: "history",
    routes // short for `routes: routes`
});

export default router;
