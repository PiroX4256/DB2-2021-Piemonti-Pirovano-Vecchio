import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../components/Login";
import Home from "../views/Home";
import Register from "../components/Register";
import AdminLogin from "../components/AdminLogin";
import AdminHomePage from "../components/AdminHomePage";

Vue.use(VueRouter);

const routes = [
    { path: '/login', name: 'Login', component: Login },
    { path: '/', name: 'Home', component: Home },
    { path: '/register', name: 'Register', component: Register },
    { path: '/admin', name: 'Admin', component: AdminLogin },
    { path: '/adminHomePage', name: 'AdminHomePage', component: AdminHomePage },
]

const router = new VueRouter({
    mode: "history",
    routes // short for `routes: routes`
});

export default router;
